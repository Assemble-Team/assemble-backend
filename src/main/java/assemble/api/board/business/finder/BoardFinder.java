package assemble.api.board.business.finder;

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
}
