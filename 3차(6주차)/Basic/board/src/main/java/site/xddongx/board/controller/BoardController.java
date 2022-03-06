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

    @PostMapping
    public void createBoard(@RequestBody BoardDto dto) {
        this.boardService.createBoard(dto);
    }

    @GetMapping("/{id}")
    public BoardDto readBoard(@PathVariable("id") Long id) {
        return this.boardService.readBoard(id);
    }

    @GetMapping
    public List<BoardDto> readBoardAll() {
        return this.boardService.readBoardAll();
    }

    @PutMapping("/{id}")
    public void updateBoard(@PathVariable("id") Long id, @RequestBody BoardDto dto) {
        this.boardService.updateBoard(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        this.boardService.deleteBoard(id);
    }
}
