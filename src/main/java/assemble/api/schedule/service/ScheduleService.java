package assemble.api.schedule.service;

import assemble.api.club.business.finder.ClubFinder;
import assemble.api.club.business.finder.MemberClubFinder;
import assemble.api.club.business.policy.MemberClubPolicy;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.member.business.finder.MemberScheduleFinder;
import assemble.api.member.business.policy.MemberSchedulePolicy;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.mapping.MemberSchedule;
import assemble.api.schedule.business.factory.ScheduleFactory;
import assemble.api.schedule.business.finder.ScheduleFinder;
import assemble.api.schedule.converter.ScheduleConverter;
import assemble.api.schedule.domain.Schedule;
import assemble.api.schedule.dto.ScheduleRequestDTO;
import assemble.api.schedule.dto.ScheduleResponseDTO;
import assemble.api.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ClubFinder clubFinder;
    private final ScheduleFinder scheduleFinder;
    private final MemberScheduleFinder  memberScheduleFinder;
    private final MemberClubFinder memberClubFinder;
    private final MemberClubPolicy memberClubPolicy;
    private final MemberSchedulePolicy memberSchedulePolicy;
    private final ScheduleFactory scheduleFactory;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDTO.CreateScheduleResultDTO createSchedule(Member member, Long clubId, ScheduleRequestDTO.CreateScheduleDTO request) {
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);
        memberClubPolicy.validateLeaderOrManager(memberClub);

        Schedule schedule = scheduleFactory.create(club, request);
        scheduleRepository.save(schedule);

        return ScheduleConverter.toCreateScheduleResultDTO(schedule.getId());
    }

    public ScheduleResponseDTO.CreateScheduleResultDTO updateSchedule(Member member, Long clubId, Long scheduleId, ScheduleRequestDTO.UpdateScheduleDTO request) {
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);
        memberClubPolicy.validateLeaderOrManager(memberClub);

        Schedule schedule = scheduleFinder.findByScheduleId(scheduleId);
        schedule.updateInfo(request);

        return ScheduleConverter.toCreateScheduleResultDTO(schedule.getId());
    }

    public void deleteSchedule(Member member, Long clubId, Long scheduleId) {
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);
        memberClubPolicy.validateLeaderOrManager(memberClub);

        Schedule schedule = scheduleFinder.findByScheduleId(scheduleId);
        scheduleRepository.delete(schedule);
    }

    public ScheduleResponseDTO.AttendScheduleResultDTO attendClubSchedule(Member member, Long clubId, Long scheduleId) {
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);

        Schedule schedule = scheduleFinder.findByScheduleId(scheduleId);
        Optional<MemberSchedule> memberSchedule = memberScheduleFinder.findByMemberAndSchedule(member, schedule);

        boolean attend = memberSchedulePolicy.attendOrCancel(memberSchedule, member, schedule);
        return ScheduleConverter.toAttendScheduleResultDTO(attend);
    }
}
