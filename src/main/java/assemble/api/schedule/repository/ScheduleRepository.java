package assemble.api.schedule.repository;

import assemble.api.club.domain.Club;
import assemble.api.schedule.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findByClubAndStartAtAfter(Club club, LocalDateTime localDate, Pageable pageable);
}
