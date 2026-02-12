package assemble.api.schedule.business.finder;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.ScheduleErrorStatus;
import assemble.api.club.domain.Club;
import assemble.api.schedule.domain.Schedule;
import assemble.api.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleFinder {

    private final ScheduleRepository scheduleRepository;

    public Schedule findByScheduleId(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new GeneralException(ScheduleErrorStatus.NOT_EXIST_SCHEDULE));
    }

    public Page<Schedule> findByClubAndStartAtAfter(Club club, LocalDateTime now, Pageable pageable) {
        return scheduleRepository.findByClubAndStartAtAfter(club, now, pageable);
    }
}
