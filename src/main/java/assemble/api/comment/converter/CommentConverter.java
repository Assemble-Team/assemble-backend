package assemble.api.comment.converter;

import assemble.api.board.domain.Board;
import assemble.api.comment.domain.Comment;
import assemble.api.comment.dto.CommentResponseDTO;
import assemble.api.member.domain.Member;

public class CommentConverter {

    public static CommentResponseDTO.CommentResultDTO toCommentResultDTO(Comment comment, Member member, Board board){
        return CommentResponseDTO.CommentResultDTO.builder()
                .memberId(member.getId())
                .boardId(board.getId())
                .comment(comment.getContent())
                .build();
    }
}
