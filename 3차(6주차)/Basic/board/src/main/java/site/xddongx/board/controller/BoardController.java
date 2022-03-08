package site.xddongx.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.xddongx.board.dto.BoardDto;
import site.xddongx.board.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * POST http://localhost:8080/boards
     * board 생성
     */
    @PostMapping
    public void createBoard(@RequestBody BoardDto dto) {
        this.boardService.createBoard(dto);
    }

    /**
     * GET http://localhost:8080/boards/{id}
     * board 1개 조회
     */
    @GetMapping("/{id}")
    public BoardDto readBoard(@PathVariable("id") Long id) {
        return this.boardService.readBoard(id);
    }

    /**
     * GET http://localhost:8080/boards
     * board 전체 조회
     */
    @GetMapping
    public List<BoardDto> readBoardAll() {
        return this.boardService.readBoardAll();
    }

    /**
     * PUT http://localhost:8080/boards/{id}
     * board update
     */
    @PutMapping("/{id}")
    public void updateBoard(@PathVariable("id") Long id, @RequestBody BoardDto dto) {
        this.boardService.updateBoard(id, dto);
    }

    /**
     * DELETE http://localhost:8080/boards
     * board delete
     */
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        this.boardService.deleteBoard(id);
    }
}
