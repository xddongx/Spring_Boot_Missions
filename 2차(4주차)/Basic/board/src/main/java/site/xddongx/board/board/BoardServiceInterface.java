package site.xddongx.board.board;

import java.util.List;

public interface BoardServiceInterface {

    void createBoard(BoardDto dto);
    List<BoardDto> readBoardAll();
    BoardDto readBoard(int id);
    void updateBoard(int id, BoardDto dto);
    void deleteBoard(int id);
}
