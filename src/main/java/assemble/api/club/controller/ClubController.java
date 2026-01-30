package assemble.api.club.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.auth.domain.MemberDetail;
import assemble.api.club.dto.ClubRequestDTO;
import assemble.api.club.dto.ClubResponseDTO;
import assemble.api.club.service.ClubService;
import assemble.api.member.domain.Member;
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
public class ClubController {

    private final ClubService clubService;

    @PostMapping("")
    @Operation(
            summary = "소모임 생성 API",
            description = "소모임을 생성하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.ClubResultDTO>> makeClub(@AuthenticationPrincipal MemberDetail memberDetail,
                                                      @RequestBody @Valid ClubRequestDTO.CreateClubDTO request){
        ClubResponseDTO.ClubResultDTO result = clubService.createClub(memberDetail.getMember(), request);
        return new ResponseEntity<>(CommonResponse.onSuccess(result),  HttpStatus.OK);
    }

    @PatchMapping("/{clubId}")
    @Operation(
            summary = "소모임 설정 수정 API",
            description = "소모임의 설정을 수정하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.ClubResultDTO>> modifyClub(@AuthenticationPrincipal MemberDetail memberDetail,
                                                        @PathVariable Long clubId,
                                                        @RequestBody @Valid ClubRequestDTO.UpdateClubDTO request){
        ClubResponseDTO.ClubResultDTO result = clubService.updateClub(memberDetail.getMember(), clubId, request);
        return new ResponseEntity<>(CommonResponse.onSuccess(result),  HttpStatus.OK);
    }

    @PostMapping("/{clubId}/likes")
    @Operation(
            summary = "소모임 좋아요/취소 API",
            description = "관심있는 소모임에 좋아요를 누르거나 취소하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.ClubLikesResultDTO>> createClubLikes(@AuthenticationPrincipal MemberDetail memberDetail,
                                                             @PathVariable Long clubId){
        ClubResponseDTO.ClubLikesResultDTO result = clubService.createClubLikes(memberDetail.getMember(), clubId);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @GetMapping("")
    @Operation(
            summary = "소모임 목록 조회 API",
            description = "소모임의 목록을 조회하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.ClubListResultDTO>> getClubList(@AuthenticationPrincipal MemberDetail memberDetail,
                                                         @RequestParam(required = false) String region,
                                                         @RequestParam(required = false) String category,
                                                         @RequestParam(required = true, defaultValue = "lastes") String sort){

        return new ResponseEntity<>(CommonResponse.onSuccess(null),  HttpStatus.OK);
    }
}
