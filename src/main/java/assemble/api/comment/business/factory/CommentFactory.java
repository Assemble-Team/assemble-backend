package assemble.api.comment.business.factory;

import assemble.api.board.domain.Board;
import assemble.api.comment.domain.Comment;
import assemble.api.comment.dto.CommentRequestDTO;
import assemble.api.member.domain.Member;
import lombok.RequiredArgsConstructor;

public class CommentFactory {

    public static Comment create(CommentRequestDTO.CreateBoardCommentDTO request, Board board, Member member) {
        return Comment.builder()
                .board(board)
                .member(member)
                .content(request.getComment())
                .build();
    }

}
