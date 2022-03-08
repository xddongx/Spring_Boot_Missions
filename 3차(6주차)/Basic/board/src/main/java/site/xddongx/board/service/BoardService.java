package site.xddongx.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.xddongx.board.dao.BoardDao;
import site.xddongx.board.dto.BoardDto;
import site.xddongx.board.dto.PostDto;
import site.xddongx.board.entity.BoardEntity;
import site.xddongx.board.entity.PostEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BoardService {
    private final BoardDao boardDao;

    @Autowired
    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void createBoard(BoardDto dto) {
        this.boardDao.createBoard(dto);
    }

    public BoardDto readBoard(Long id) {
        BoardEntity boardEntity = this.boardDao.readBoard(id);
        BoardDto boardDto = new BoardDto(boardEntity);

        return boardDto;
    }

    public List<BoardDto> readBoardAll() {
        Iterator<BoardEntity> iterator = this.boardDao.readBoardAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        while (iterator.hasNext()) {
            BoardEntity boardEntity = iterator.next();
            boardDtoList.add(new BoardDto(boardEntity));
        }
        return boardDtoList;
    }

    public void updateBoard(Long id, BoardDto dto) {
        this.boardDao.updateBoard(id, dto);
    }

    public void deleteBoard(Long id) {
        this.boardDao.deleteBoard(id);
    }
}
