package site.xddongx.board.board;

import java.util.List;

public interface BoardRepositoryInterface {

    boolean save(BoardDto dto);
    List<BoardDto> findAll();
    BoardDto findById(int id);
    boolean update(int id, BoardDto dto);
    boolean delete(int id);
}
