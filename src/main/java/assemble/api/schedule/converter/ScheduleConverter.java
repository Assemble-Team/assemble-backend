package assemble.api.schedule.converter;

import assemble.api.member.domain.enums.ScheduleStatus;
import assemble.api.member.domain.mapping.MemberSchedule;
import assemble.api.schedule.domain.Schedule;
import assemble.api.schedule.dto.ScheduleResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static ScheduleResponseDTO.GetScheduleListResultDTO toGetScheduleListResultDTO(Page<Schedule> scheduleList, Map<Long, MemberSchedule> memberScheduleMap) {
        List<ScheduleResponseDTO.GetScheduleResultDTO> list = scheduleList.stream()
                .map(schedule -> {
                    MemberSchedule ms = memberScheduleMap.get(schedule.getId());
                    return toGetScheduleResultDTO(schedule, ms);
                })
                .toList();
        return ScheduleResponseDTO.GetScheduleListResultDTO.builder()
                .list(list)
                .size(scheduleList.getSize())
                .page(scheduleList.getNumber())
                .totalPages(scheduleList.getTotalPages())
                .build();
    }

    public static ScheduleResponseDTO.GetScheduleResultDTO toGetScheduleResultDTO(Schedule schedule, MemberSchedule memberSchedule) {
        boolean attend = memberSchedule != null && memberSchedule.getStatus() == ScheduleStatus.ATTENDING;
        return ScheduleResponseDTO.GetScheduleResultDTO.builder()
                .title(schedule.getTitle())
                .location(schedule.getLocation())
                .startAt(schedule.getStartAt())
                .attend(attend)
                .build();
    }

}
