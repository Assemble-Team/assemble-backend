package assemble.api.schedule.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.auth.domain.MemberDetail;
import assemble.api.schedule.dto.ScheduleRequestDTO;
import assemble.api.schedule.dto.ScheduleResponseDTO;
import assemble.api.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clubs")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/{clubId}/schedules")
    @Operation(
            summary = "소모임 일정 생성 API",
            description = "소모임 내 일정을 생성하는 API"
    )
    public ResponseEntity<CommonResponse<ScheduleResponseDTO.CreateScheduleResultDTO>> makeSchedule(@AuthenticationPrincipal MemberDetail memberDetail,
                                                                            @PathVariable Long clubId,
                                                                            @RequestBody @Valid ScheduleRequestDTO.CreateScheduleDTO request){
        ScheduleResponseDTO.CreateScheduleResultDTO result = scheduleService.createSchedule(memberDetail.getMember(), clubId, request);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @PatchMapping("/{clubId}/schedules/{scheduleId}")
    @Operation(
            summary = "소모임 일정 수정 API",
            description = "소모임 내 일정을 수정하는 API"
    )
    public ResponseEntity<CommonResponse<ScheduleResponseDTO.CreateScheduleResultDTO>> modifySchedule(@AuthenticationPrincipal MemberDetail memberDetail,
                                                            @PathVariable Long clubId,
                                                            @PathVariable Long scheduleId,
                                                            @RequestBody ScheduleRequestDTO.UpdateScheduleDTO request){
        ScheduleResponseDTO.CreateScheduleResultDTO result = scheduleService.updateSchedule(memberDetail.getMember(), clubId, scheduleId, request);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @DeleteMapping("/{clubId}/schedules/{scheduleId}")
    @Operation(
            summary = "소모임 일정 삭제 API",
            description = "소모임 내 일정을 삭제하는 API"
    )
    public ResponseEntity<CommonResponse<?>> deleteSchedule(@AuthenticationPrincipal MemberDetail memberDetail,
                                                            @PathVariable Long clubId,
                                                            @PathVariable Long scheduleId){
        scheduleService.deleteSchedule(memberDetail.getMember(), clubId, scheduleId);
        return new ResponseEntity<>(CommonResponse.onSuccess(null), HttpStatus.OK);
    }

    @PostMapping("/{clubId}/schedules/{scheduleId}")
    @Operation(
            summary = "활동 참가/취소하기 API",
            description = "소모임 내 활동에 참가하는 API"
    )
    public ResponseEntity<CommonResponse<ScheduleResponseDTO.AttendScheduleResultDTO>> attendSchedule(@AuthenticationPrincipal MemberDetail memberDetail,
                                                            @PathVariable Long clubId,
                                                            @PathVariable Long scheduleId){
        ScheduleResponseDTO.AttendScheduleResultDTO result = scheduleService.attendClubSchedule(memberDetail.getMember(), clubId, scheduleId);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @GetMapping("/{clubId}/schedule")
    @Operation(
            summary = "활동 일정 목록 조회",
            description = "소모임 내 활동 일정 목록을 조회하는 API"
    )
    public ResponseEntity<CommonResponse<ScheduleResponseDTO.GetScheduleListResultDTO>> getScheduleList(@AuthenticationPrincipal MemberDetail memberDetail,
                                                             @PathVariable Long clubId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "startAt"));
        ScheduleResponseDTO.GetScheduleListResultDTO result = scheduleService.getScheduleListInfo(memberDetail.getMember(), clubId, pageable);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }
}
