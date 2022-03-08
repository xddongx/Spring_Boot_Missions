package site.xddongx.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.xddongx.board.aspect.LogArguments;
import site.xddongx.board.aspect.LogReturn;
import site.xddongx.board.dto.PostDto;
import site.xddongx.board.entity.BoardEntity;
import site.xddongx.board.service.PostService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards/{boardId}/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * POST http://localhost:8080/boards/{boardId}/posts?userId={userId}<br>
     * post 생성<br>
     * boardId: 어떤 board의 post를 생성할 것인가<br>
     * userId: 작성자가 누구인가
     */
    @PostMapping
    public void createPost(@PathVariable("boardId") Long boardId, @RequestBody PostDto dto, @RequestParam("userId") String userId) {
        this.postService.createPost(boardId, dto, userId);
    }

    /**
     * GET http://localhost:8080/boards/{boardId}/posts/{postId}<br>
     * post 1개 조회<br>
     * boardId: 어떤 board의 post인가(RequestMapping상 넣음)<br>
     * postId: 어떤 post인가
     */
    @GetMapping("/{id}")
    public PostDto readPost(@PathVariable("id") Long postId) {
        return this.postService.readPost(postId);
    }

    /**
     * POST http://localhost:8080/boards/{boardId}/posts<br>
     * post 전체 조회<br>
     * boardId: 어떤 board의 post인가(RequestMapping상 넣음)
     */
    @GetMapping
    public List<PostDto> readPostAll() {
        return this.postService.readPostAll();
    }

    /**
     * POST http://localhost:8080/boards/{boardId}/posts?userId={userId}<br>
     * post 업데이트<br>
     * boardId: 어떤 board의 post인가<br>
     * userId: 작성자가 누구인가(작성자를 확인하고 틀리면 업데이트 안됨)
     */
    @PutMapping("/{id}")
    public void updatePost(@PathVariable("id") Long postId, @RequestBody PostDto dto, @RequestParam("userId") String userId) {
        this.postService.updatePost(postId, dto, userId);
    }

    /**
     * DELETE http://localhost:8080/boards/{boardId}/posts?userId={userId}<br>
     * post 삭제<br>
     * boardId: 어떤 board의 post인가<br>
     * userId: 요청 user가 누구인가(작성자 id와 비교 후 삭제 가능)
     */
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("boardId") Long boardId, @PathVariable("id") Long postId, @RequestParam("userId") String userId) {
        this.postService.deletePost(postId, userId);
    }
}
