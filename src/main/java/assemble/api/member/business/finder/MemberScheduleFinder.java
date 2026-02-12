package assemble.api.member.business.finder;

import assemble.api.member.domain.Member;
import assemble.api.member.domain.mapping.MemberSchedule;
import assemble.api.member.repository.MemberScheduleRepository;
import assemble.api.schedule.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberScheduleFinder {

    private final MemberScheduleRepository memberScheduleRepository;

    public Optional<MemberSchedule> findByMemberAndSchedule(Member member, Schedule schedule) {
        return memberScheduleRepository.findByMemberAndSchedule(member,schedule);
    }

    public List<MemberSchedule> findByMemberAndSchedules(Member member, Page<Schedule> scheduleList) {
        return memberScheduleRepository.findByMemberAndScheduleIn(member, scheduleList.stream().toList());
    }
}
