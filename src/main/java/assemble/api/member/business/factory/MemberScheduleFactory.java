package assemble.api.member.business.factory;

import assemble.api.member.domain.Member;
import assemble.api.member.domain.enums.ScheduleStatus;
import assemble.api.member.domain.mapping.MemberSchedule;
import assemble.api.schedule.domain.Schedule;
import org.springframework.stereotype.Component;

@Component
public class MemberScheduleFactory {

    public MemberSchedule create(Member member, Schedule schedule) {
        return MemberSchedule.builder()
                .schedule(schedule)
                .member(member)
                .status(ScheduleStatus.ATTENDING)
                .build();
    }
}
