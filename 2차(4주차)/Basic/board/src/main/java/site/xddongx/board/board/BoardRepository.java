package site.xddongx.board.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static site.xddongx.board.util.Util.selectBoard;

@Repository
public class BoardRepository implements BoardRepositoryInterface {
    private static final Logger logger = LoggerFactory.getLogger(BoardRepository.class);
    private final List<BoardDto> boardList;

    public BoardRepository() {
        this.boardList = new ArrayList<>();
    }

    /**
     * id로 게시판 목록에서 게시판 하나를 찾는 메소드
     * */
    public static BoardDto selectBoard(int id, List<BoardDto> boardList) {
        BoardDto targetBoard = null;

        for (BoardDto board:boardList){
            if (board.getId() == id){
                targetBoard = board;
                return targetBoard;
            }
        }

        return new BoardDto();
    }

    /**
     * 새로운 게시판을 등록하면서 게시판을 식별할 수 있는 id 자동 증가
     */
    @Override
    public boolean save(BoardDto dto) {
        dto.setId(BoardDto.count++);
        logger.info("Repository >>> \n" + dto.toString());
        return this.boardList.add(dto);
    }

    /**
     * 게시판 목록
     * */
    @Override
    public List<BoardDto> findAll() {
        return this.boardList;
    }

    /**
     * 게시판 id로 게시판을 찾는다.
     * */
    @Override
    public BoardDto findById(int id) {
        BoardDto targetBoard = selectBoard(id, this.boardList);

        return targetBoard;
    }

    /**
     * 게시판 업데이트를 위해 게시판을 찾고, 업데이트할 게시판 제목이 null이 아니라면 업데이트
     * */
    @Override
    public boolean updateBoard(int id, BoardDto dto) {
        // boardRepository 게시판 찾기로 게시판 찾기
        BoardDto targetBoard = this.findById(id);

        if (dto.getTitle() != null) {
            targetBoard.setTitle(dto.getTitle());
        }

        return true;
    }

    /**
     * 게시판 id로 게시판을 찾아 삭제
     * */
    @Override
    public boolean deleteBoard(int id) {
        // id 게시판 찾기로 게시판 찾기
        BoardDto targetBoard = this.findById(id);

        this.boardList.remove(targetBoard);
        return true;
    }
}
