package assemble.api.member.business.policy;

import assemble.api.member.business.factory.MemberScheduleFactory;
import assemble.api.member.domain.Member;
import assemble.api.member.domain.enums.ScheduleStatus;
import assemble.api.member.domain.mapping.MemberSchedule;
import assemble.api.member.repository.MemberScheduleRepository;
import assemble.api.schedule.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class MemberSchedulePolicy {

    private final MemberScheduleRepository memberScheduleRepository;
    private final MemberScheduleFactory memberScheduleFactory;

    public boolean attendOrCancel(Optional<MemberSchedule> memberSchedule, Member member, Schedule schedule) {
        if(memberSchedule.isEmpty()){
            MemberSchedule newMemberSchedule = memberScheduleFactory.create(member, schedule);
            memberScheduleRepository.save(newMemberSchedule);
            return true;
        }
        memberSchedule.get().changeStatus(ScheduleStatus.ABSENT);
        return false;
    }
}
