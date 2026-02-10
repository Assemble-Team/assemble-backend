package assemble.api.board.converter;

import assemble.api.board.domain.Board;
import assemble.api.board.dto.BoardResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BoardConverter {

    public static BoardResponseDTO.CreateBoardResultDTO toCreateBoardResultDTO(Long boardId){
        return BoardResponseDTO.CreateBoardResultDTO.builder()
                .boardId(boardId)
                .build();
    }

    public static BoardResponseDTO.BoardListResultDTO toBoardListResultDTO(Page<Board> boardPage) {
        List<BoardResponseDTO.BoardResultDTO> list = boardPage.stream()
                .map(BoardConverter::toBoardResultDTO)
                .toList();
        return BoardResponseDTO.BoardListResultDTO.builder()
                .boardList(list)
                .size(boardPage.getSize())
                .page(boardPage.getNumber())
                .totalPages(boardPage.getTotalPages())
                .build();
    }

    public static BoardResponseDTO.BoardResultDTO toBoardResultDTO(Board board){
        LocalDateTime time = board.getCreatedAt();
        return BoardResponseDTO.BoardResultDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(LocalDate.of(time.getYear(), time.getMonth(), time.getDayOfMonth()))
                .build();
    }
}
