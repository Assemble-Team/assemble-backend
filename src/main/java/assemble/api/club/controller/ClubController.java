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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CommonResponse<ClubResponseDTO.CreateClubResultDTO>> makeClub(@AuthenticationPrincipal MemberDetail memberDetail,
                                                      @RequestBody @Valid ClubRequestDTO.CreateClubDTO request){
        ClubResponseDTO.CreateClubResultDTO result = clubService.createClub(memberDetail.getMember(), request);
        return new ResponseEntity<>(CommonResponse.onSuccess(result),  HttpStatus.OK);
    }
}
