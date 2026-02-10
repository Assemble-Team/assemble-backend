package assemble.api.board.repository;

import assemble.api.board.domain.Board;
import assemble.api.club.domain.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findBoardsByClub(Club club, Pageable page);
}
