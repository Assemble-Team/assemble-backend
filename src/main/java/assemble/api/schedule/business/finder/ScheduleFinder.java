package assemble.api.schedule.business.finder;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.ScheduleErrorStatus;
import assemble.api.schedule.domain.Schedule;
import assemble.api.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleFinder {

    private final ScheduleRepository scheduleRepository;

    public Schedule findByScheduleId(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new GeneralException(ScheduleErrorStatus.NOT_EXIST_SCHEDULE));
    }
}
