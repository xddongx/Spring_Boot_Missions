package site.xddongx.board.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardRepository implements BoardRepositoryInterface {
    private static final Logger logger = LoggerFactory.getLogger(BoardRepository.class);
    private final List<BoardDto> boardList;

    public BoardRepository() {
        this.boardList = new ArrayList<>();
    }

    @Override
    public boolean save(BoardDto dto) {
        return this.boardList.add(dto);
    }

    @Override
    public List<BoardDto> findAll() {
        return this.boardList;
    }

    @Override
    public BoardDto findById(int id) {
        return this.boardList.get(id);
    }

    @Override
    public boolean update(int id, BoardDto dto) {
        BoardDto targetBoard = this.boardList.get(id);

        if (dto.getTitle() != null) {
            targetBoard.setTitle(dto.getTitle());
        }

        return true;
    }

    @Override
    public boolean delete(int id) {
        this.boardList.remove(id);
        return true;
    }
}
