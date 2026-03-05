package assemble.api.comment.controller;

import assemble.api.apiPayload.CommonResponse;
import assemble.api.auth.domain.MemberDetail;
import assemble.api.board.dto.BoardRequestDTO;
import assemble.api.comment.dto.CommentRequestDTO;
import assemble.api.comment.dto.CommentResponseDTO;
import assemble.api.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clubs")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{clubId}/boards/{boardId}/comment")
    @Operation(
            summary = "모임 게시판 댓글 등록 API",
            description = "모임 게시판에 댓글 등록하는 API"
    )
    public ResponseEntity<CommonResponse<CommentResponseDTO.CommentResultDTO>> writeBoardComment(@AuthenticationPrincipal MemberDetail memberDetail,
                                                                                @PathVariable Long clubId,
                                                                                @PathVariable Long boardId,
                                                                                @RequestBody CommentRequestDTO.CreateBoardCommentDTO request){
        CommentResponseDTO.CommentResultDTO result = commentService.writeComment(memberDetail.getMemberId(), clubId, boardId, request);
        return new ResponseEntity<>(CommonResponse.onSuccess(result), HttpStatus.OK);
    }

}
