package assemble.api.board.service;

import assemble.api.board.business.factory.BoardFactory;
import assemble.api.board.business.finder.BoardFinder;
import assemble.api.board.converter.BoardConverter;
import assemble.api.board.domain.Board;
import assemble.api.board.dto.BoardRequestDTO;
import assemble.api.board.dto.BoardResponseDTO;
import assemble.api.board.repository.BoardRepository;
import assemble.api.club.business.finder.ClubFinder;
import assemble.api.club.business.finder.MemberClubFinder;
import assemble.api.club.business.policy.MemberClubPolicy;
import assemble.api.club.domain.Club;
import assemble.api.club.domain.mapping.MemberClub;
import assemble.api.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final ClubFinder clubFinder;
    private final BoardFinder boardFinder;
    private final BoardFactory boardFactory;
    private final MemberClubPolicy memberClubPolicy;
    private final MemberClubFinder memberClubFinder;
    private final BoardRepository boardRepository;

    public BoardResponseDTO.CreateBoardResultDTO createBoard(Member member, BoardRequestDTO.CreateBoardDTO request, Long clubId) {
        Club club = clubFinder.findByClubId(clubId);
        MemberClub memberClub = memberClubFinder.findByMemberAndClub(member, club);
        memberClubPolicy.validateLeaderOrManager(memberClub);

        Board board = boardFactory.create(request, member, club);
        boardRepository.save(board);

        return BoardConverter.toCreateBoardResultDTO(board.getId());
    }

    public BoardResponseDTO.BoardListResultDTO getBoardListInfo(Long clubId, Pageable page) {
        Club club = clubFinder.findByClubId(clubId);
        Page<Board> boardPage = boardFinder.findByClub(club, page);
        return BoardConverter.toBoardListResultDTO(boardPage);
    }
}
