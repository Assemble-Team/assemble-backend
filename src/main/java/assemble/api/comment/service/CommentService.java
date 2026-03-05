package assemble.api.comment.service;

import assemble.api.board.business.finder.BoardFinder;
import assemble.api.board.domain.Board;
import assemble.api.board.dto.BoardRequestDTO;
import assemble.api.club.business.finder.ClubFinder;
import assemble.api.club.business.finder.MemberClubFinder;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.comment.business.factory.CommentFactory;
import assemble.api.comment.converter.CommentConverter;
import assemble.api.comment.domain.Comment;
import assemble.api.comment.dto.CommentRequestDTO;
import assemble.api.comment.dto.CommentResponseDTO;
import assemble.api.comment.repository.CommentRepository;
import assemble.api.member.business.finder.MemberFinder;
import assemble.api.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberFinder memberFinder;
    private final ClubFinder clubFinder;
    private final MemberClubFinder memberClubFinder;
    private final BoardFinder boardFinder;
    private final CommentRepository commentRepository;

    public CommentResponseDTO.CommentResultDTO writeComment(Long memberId, Long clubId, Long boardId, CommentRequestDTO.CreateBoardCommentDTO request){
        Member member = memberFinder.findById(memberId);
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);

        Board board = boardFinder.findByClub(club);
        Comment comment = CommentFactory.create(request, board, member);
        commentRepository.save(comment);
        return CommentConverter.toCommentResultDTO(comment, member, board);
    }

}
