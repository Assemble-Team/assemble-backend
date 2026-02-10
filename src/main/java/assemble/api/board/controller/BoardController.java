package assemble.api.board.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.auth.domain.MemberDetail;
import assemble.api.board.dto.BoardRequestDTO;
import assemble.api.board.dto.BoardResponseDTO;
import assemble.api.board.service.BoardService;
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
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/{clubId}/boards")
    @Operation(
            summary = "모임 게시판 작성 API",
            description = "모임 게시판에 게시글을 작성하는 API"
    )
    public ResponseEntity<CommonResponse<BoardResponseDTO.CreateBoardResultDTO>> makeBoard(@AuthenticationPrincipal MemberDetail memberDetail,
                                                                                           @RequestBody @Valid BoardRequestDTO.CreateBoardDTO request,
                                                                                           @PathVariable Long clubId){
        BoardResponseDTO.CreateBoardResultDTO result = boardService.createBoard(memberDetail.getMember(), request, clubId);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

    @GetMapping("/{clubId}/boards")
    @Operation(
            summary = "모임 게시판 목록 조회 API",
            description = "모임 게시판에 작성된 게시글의 목록을 조회하는 API"
    )
    public ResponseEntity<CommonResponse<BoardResponseDTO.BoardListResultDTO>> getBoardList(@PathVariable Long clubId,
                                                                                            @RequestParam(defaultValue = "0") int page,
                                                                                            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        BoardResponseDTO.BoardListResultDTO result = boardService.getBoardListInfo(clubId, pageable);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }
}
