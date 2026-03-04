package assemble.api.club.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.auth.domain.MemberDetail;
import assemble.api.club.domain.enums.DifficultyLevel;
import assemble.api.club.dto.ClubRequestDTO;
import assemble.api.club.dto.ClubResponseDTO;
import assemble.api.club.service.ClubService;
import assemble.api.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ClubResponseDTO.ClubResultDTO result = clubService.createClub(memberDetail.getMemberId(), request);
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
        ClubResponseDTO.ClubResultDTO result = clubService.updateClub(memberDetail.getMemberId(), clubId, request);
        return new ResponseEntity<>(CommonResponse.onSuccess(result),  HttpStatus.OK);
    }

    @PostMapping("/{clubId}/likes")
    @Operation(
            summary = "소모임 좋아요/취소 API",
            description = "관심있는 소모임에 좋아요를 누르거나 취소하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.ClubLikesResultDTO>> createClubLikes(@AuthenticationPrincipal MemberDetail memberDetail,
                                                             @PathVariable Long clubId){
        ClubResponseDTO.ClubLikesResultDTO result = clubService.createClubLikes(memberDetail.getMemberId(), clubId);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @GetMapping("/{clubId}")
    @Operation(
            summary = "소모임 상세 조회 API",
            description = "소모임을 상세히 조회하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.ClubDetailResultDTO>> getClubDetail(@AuthenticationPrincipal MemberDetail memberDetail,
                                                           @PathVariable Long clubId){
        ClubResponseDTO.ClubDetailResultDTO result = clubService.getClubDetailInfo(memberDetail.getMemberId(), clubId);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @GetMapping("")
    @Operation(
            summary = "소모임 목록 조회 API",
            description = "소모임의 목록을 조회하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.FindClubListResultDTO>> getClubList(@AuthenticationPrincipal MemberDetail memberDetail,
                                                         @RequestParam(required = false) String region,
                                                         @RequestParam(required = false) String category,
                                                         @RequestParam(required = false) List<DifficultyLevel> level,
                                                         @RequestParam(required = false) boolean recruiting,
                                                         @RequestParam(required = false) boolean online,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "latest") String sort){
        Pageable pageable = PageRequest.of(page, size);
        ClubResponseDTO.FindClubListResultDTO result = clubService.getClubListInfo(memberDetail.getMemberId(), region, category, level, recruiting, online, sort, pageable);
        return new ResponseEntity<>(CommonResponse.onSuccess(result),  HttpStatus.OK);
    }

    @GetMapping("/{clubId}/members/list")
    @Operation(
            summary = "소모임 내 가입한 회원 목록 조회 API",
            description = "소모임에 가입한 회원들의 목록을 조회하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.ClubMemberListResultDTO>> getClubMemberList(@AuthenticationPrincipal MemberDetail memberDetail,
                                                              @PathVariable Long clubId){
        ClubResponseDTO.ClubMemberListResultDTO result = clubService.getClubMemberListInfo(clubId);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @PostMapping("/{clubId}/join/request")
    @Operation(
            summary = "소모임 가입 신청 API",
            description = "원하는 소모임에 가입 신청을 하는 API"
    )
    public ResponseEntity<CommonResponse<?>> createJoinRequest(@AuthenticationPrincipal MemberDetail memberDetail,
                                                               @PathVariable Long clubId,
                                                               @RequestBody @Valid ClubRequestDTO.JoinRequestDTO request){
        clubService.createJoinClubRequest(memberDetail.getMemberId(), clubId, request);
        return new ResponseEntity<>(CommonResponse.onSuccess(null), HttpStatus.OK);
    }

    @PostMapping("/{clubId}/join")
    @Operation(
            summary = "소모임 가입 승인/거절 API",
            description = "소모임 가입 신청을 승인 또는 거절하는 API"
    )
    public ResponseEntity<CommonResponse<?>> approveOrRejectRequest(@AuthenticationPrincipal MemberDetail memberDetail,
                                                                    @PathVariable Long clubId,
                                                                    @RequestBody @Valid ClubRequestDTO.ApproveOrRejectDTO request){
        clubService.approveOrReject(memberDetail.getMemberId(), clubId, request);
        return new ResponseEntity<>(CommonResponse.onSuccess(null), HttpStatus.OK);
    }

    @PostMapping("/{clubId}/permissions")
    @Operation(
            summary = "소모임 회원 권한 관리(운영진 임명/해임)",
            description = "소모임의 회원들의 권한을 변경하는 API"
    )
    public ResponseEntity<CommonResponse<?>> changeMemberAuthority(@AuthenticationPrincipal MemberDetail memberDetail,
                                                                   @PathVariable Long clubId,
                                                                   @RequestBody @Valid ClubRequestDTO.ChangeMemberAuthorityDTO request){
        clubService.changeAuthority(memberDetail.getMemberId(), clubId, request);
        return new ResponseEntity<>(CommonResponse.onSuccess(null), HttpStatus.OK);
    }

    @GetMapping("/{clubId}/join")
    @Operation(
            summary = "소모임 가입 신청 목록 조회 API",
            description = "소모임에 가입 신청한 회원들의 목록을 조회하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.GetJoinRequestListDTO>> getJoinRequestList(@AuthenticationPrincipal MemberDetail memberDetail,
                                                                @PathVariable Long clubId,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        ClubResponseDTO.GetJoinRequestListDTO result = clubService.getJoinRequestListInfo(memberDetail.getMemberId(), clubId, pageable);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @GetMapping("/{clubId}/permissions")
    @Operation(
            summary = "소모임 권한 관리 목록 조회 API",
            description = "소모임의 회원들의 권한 목록을 조회하는 API"
    )
    public ResponseEntity<CommonResponse<ClubResponseDTO.GetClubMemberAuthorityListDTO>> getClubAuthorityList(@AuthenticationPrincipal MemberDetail memberDetail,
                                                                  @PathVariable Long clubId,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        ClubResponseDTO.GetClubMemberAuthorityListDTO result = clubService.getClubAuthorityListInfo(memberDetail.getMemberId(), clubId, pageable);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @DeleteMapping("/{clubId}/leave")
    @Operation(
            summary = "소모임 탈퇴 API",
            description = "가입한 소모임을 탈퇴하는 API"
    )
    public ResponseEntity<CommonResponse<?>> deleteMemberFromClub(@AuthenticationPrincipal MemberDetail memberDetail,
                                                                  @PathVariable Long clubId){
        clubService.deleteMemberClub(memberDetail.getMemberId(), clubId);
        return new ResponseEntity<>(CommonResponse.onSuccess(null), HttpStatus.OK);
    }
}
