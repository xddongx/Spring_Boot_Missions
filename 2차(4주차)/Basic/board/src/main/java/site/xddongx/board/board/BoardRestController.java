package site.xddongx.board.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardRestController {
    private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
    private final BoardServiceInterface boardService;

    public BoardRestController(@Autowired BoardServiceInterface boardServiceInterface) {
        this.boardService = boardServiceInterface;
    }


    /**
     * (POST) http://localhost:8080/boards
     */
    @PostMapping()
    public void createBoard(@RequestBody BoardDto dto) {
        logger.info("create Board");
        this.boardService.createBoard(dto);
    }

    /**
     * (GET) http://localhost:8080/boards
     */
    @GetMapping()
    public List<BoardDto> readBoardAll() {
        logger.info("read Board All");
        return this.boardService.readBoardAll();
    }

    /**
     * (GET) http://localhost:8080/boards/{id}
     */
    @GetMapping("/{id}")
    public BoardDto readBoardOne(@PathVariable("id") int id) {
        logger.info("read Board One");
        return this.boardService.readBoard(id);
    }

    /**
     * (PUT) http://localhost:8080/boards/{id}
     */
    @PutMapping("/{id}")
    public void updateBoard(@PathVariable("id") int id, @RequestBody BoardDto dto) {
        logger.info("update Board");
        this.boardService.updateBoard(id, dto);
    }

    /**
     * (DELETE) http://localhost:8080/boards/{id}
     */
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable("id") int id) {
        logger.info("delete Board");
        this.boardService.deleteBoard(id);
    }

}
