package assemble.api.club.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.auth.domain.MemberDetail;
import assemble.api.club.dto.ClubRequestDTO;
import assemble.api.club.dto.ClubResponseDTO;
import assemble.api.club.service.ClubService;
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
        return new ResponseEntity<>(CommonResponse.onSuccess(result),   HttpStatus.OK);
    }
}
