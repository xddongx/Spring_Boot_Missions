package site.xddongx.board.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService implements BoardServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
    private final BoardRepositoryInterface boardRepository;

    public BoardService(@Autowired BoardRepositoryInterface boardRepositoryInterface) {
        this.boardRepository = boardRepositoryInterface;
    }

    @Override
    public void createBoard(BoardDto dto) {
        this.boardRepository.save(dto);
    }

    @Override
    public List<BoardDto> readBoardAll() {
        return this.boardRepository.findAll();
    }

    @Override
    public BoardDto readBoard(int id) {
        return this.boardRepository.findById(id);
    }

    @Override
    public void updateBoard(int id, BoardDto dto) {
        this.boardRepository.updateBoard(id, dto);
    }

    @Override
    public void deleteBoard(int id) {
        this.boardRepository.deleteBoard(id);
    }
}
