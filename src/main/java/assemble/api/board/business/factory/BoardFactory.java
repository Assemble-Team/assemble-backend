package assemble.api.board.business.factory;

import assemble.api.board.domain.Board;
import assemble.api.board.dto.BoardRequestDTO;
import assemble.api.club.domain.Club;
import assemble.api.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class BoardFactory {
    public Board create(BoardRequestDTO.CreateBoardDTO request, Member member, Club club) {
        return Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .club(club)
                .member(member)
                .build();
    }
}
