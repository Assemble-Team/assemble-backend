package assemble.api.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateScheduleResultDTO{
        Long scheduleId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AttendScheduleResultDTO{
        boolean attend;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetScheduleListResultDTO{
        List<GetScheduleResultDTO> list;
        int totalPages;
        int page;
        int size;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetScheduleResultDTO{
        LocalDateTime startAt;
        String title;
        String location;
        boolean attend;
    }
}
