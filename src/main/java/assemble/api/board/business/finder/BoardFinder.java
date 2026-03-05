package assemble.api.board.business.finder;

import assemble.api.apiPayload.handler.GeneralException;
import assemble.api.apiPayload.status.BoardErrorStatus;
import assemble.api.board.domain.Board;
import assemble.api.board.repository.BoardRepository;
import assemble.api.club.domain.Club;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardFinder {

    private final BoardRepository boardRepository;

    public Page<Board> findByClub(Club club, Pageable page) {
        return boardRepository.findBoardsByClub(club, page);
    }

    public Board findByClub(Club club) {
        return boardRepository.findBoardByClub(club)
                .orElseThrow(() -> new GeneralException(BoardErrorStatus.NOT_EXIST_BOARD));
    }
}
