package site.xddongx.basicboard.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.xddongx.basicboard.model.BoardDto;
import site.xddongx.basicboard.Service.BoardService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/board")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto) {
        // ResponseEntity: 응답을 잘 조작해서 보낼 때 사용?
        BoardDto result = this.boardService.create(boardDto);

        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoardDto> readBoard(@PathVariable("id") Long id) {
        BoardDto boardDto = this.boardService.read(id);
        if (boardDto == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(boardDto);
    }

    @GetMapping()
    public ResponseEntity<List<BoardDto>> readBoardAll() {
        Collection<BoardDto> boardDtoCollection = this.boardService.readAll();
        if (boardDtoCollection.isEmpty()) return ResponseEntity.notFound().build();

        List<BoardDto> boardDtoList = new ArrayList<>(boardDtoCollection);

        return ResponseEntity.ok(boardDtoList);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBoard(@PathVariable("id") Long id, @RequestBody BoardDto dto) {
        if (!boardService.update(id, dto)) return ResponseEntity.notFound().build();             // 왜 build 해주는가?
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
        if (!boardService.delete(id)) return ResponseEntity.notFound().build();             // 왜 build 해주는가?
        return ResponseEntity.accepted().build();
    }
}
