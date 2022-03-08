package site.xddongx.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.board.dto.BoardDto;
import site.xddongx.board.entity.BoardEntity;
import site.xddongx.board.repository.BoardRepository;

import java.util.Iterator;
import java.util.Optional;

@Repository
public class BoardDao {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardDao(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void createBoard(BoardDto dto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(dto.getName());
        this.boardRepository.save(boardEntity);
    }

    public BoardEntity readBoard(Long id) {
        Optional<BoardEntity> boardEntity = this.boardRepository.findById(id);
        if (boardEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return boardEntity.get();
    }

    public Iterator<BoardEntity> readBoardAll() {
        return this.boardRepository.findAll().iterator();
    }

    public void updateBoard(Long id, BoardDto dto) {
        Optional<BoardEntity> targetEntity = this.boardRepository.findById(id);

        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        BoardEntity boardEntity = targetEntity.get();
        boardEntity.setName(dto.getName() == null ? boardEntity.getName() : dto.getName());

        this.boardRepository.save(boardEntity);
    }

    public void deleteBoard(Long id) {
        this.boardRepository.deleteById(id);
    }

}
