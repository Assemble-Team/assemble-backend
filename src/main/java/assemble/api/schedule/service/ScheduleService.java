package assemble.api.schedule.service;

import assemble.api.club.business.finder.ClubFinder;
import assemble.api.club.business.finder.MemberClubFinder;
import assemble.api.club.business.policy.MemberClubPolicy;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.member.domain.Member;
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

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ClubFinder clubFinder;
    private final ScheduleFinder scheduleFinder;
    private final MemberClubFinder memberClubFinder;
    private final MemberClubPolicy memberClubPolicy;
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
}
