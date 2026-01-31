package assemble.api.notice.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.auth.domain.MemberDetail;
import assemble.api.notice.dto.NoticeRequestDTO;
import assemble.api.notice.dto.NoticeResponseDTO;
import assemble.api.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/{clubId}/notice")
    @Operation(
            summary = "공지사항 생성 API",
            description = "새로운 공지사항을 생성하는 API"
    )
    public ResponseEntity<CommonResponse<NoticeResponseDTO.ClubNoticeResultDTO>> makeClubNotice(@AuthenticationPrincipal MemberDetail memberDetail,
                                                                                                @PathVariable Long clubId,
                                                                                                @RequestBody @Valid NoticeRequestDTO.ClubNoticeDTO request){
        NoticeResponseDTO.ClubNoticeResultDTO result = noticeService.createClubNotice(memberDetail.getMember(), clubId, request);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }
}
