package assemble.api.schedule.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

public class ScheduleRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateScheduleDTO{
        @NotNull
        String title;
        @NotNull
        String location;
        @NotNull
        LocalDateTime startAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateScheduleDTO{
        String title;
        String location;
        LocalDateTime startAt;
    }
}
