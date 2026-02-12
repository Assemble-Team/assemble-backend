package assemble.api.member.repository;

import assemble.api.member.domain.Member;
import assemble.api.member.domain.mapping.MemberSchedule;
import assemble.api.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberScheduleRepository extends JpaRepository<MemberSchedule, Long> {
    Optional<MemberSchedule> findByMemberAndSchedule(Member member, Schedule schedule);
}
