package assemble.api.schedule.converter;

import assemble.api.schedule.dto.ScheduleResponseDTO;

public class ScheduleConverter {

    public static ScheduleResponseDTO.CreateScheduleResultDTO toCreateScheduleResultDTO(Long scheduleId){
        return ScheduleResponseDTO.CreateScheduleResultDTO.builder()
                .scheduleId(scheduleId)
                .build();
    }

    public static ScheduleResponseDTO.AttendScheduleResultDTO toAttendScheduleResultDTO(boolean attend) {
        return ScheduleResponseDTO.AttendScheduleResultDTO.builder()
                .attend(attend)
                .build();
    }
}
