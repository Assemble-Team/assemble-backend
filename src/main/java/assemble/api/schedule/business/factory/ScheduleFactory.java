package assemble.api.schedule.business.factory;

import assemble.api.club.domain.Club;
import assemble.api.schedule.domain.Schedule;
import assemble.api.schedule.dto.ScheduleRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduleFactory {

    public Schedule create(Club club, ScheduleRequestDTO.CreateScheduleDTO request) {
        return Schedule.builder()
                .title(request.getTitle())
                .location(request.getLocation())
                .club(club)
                .startAt(request.getStartAt())
                .build();
    }
}
