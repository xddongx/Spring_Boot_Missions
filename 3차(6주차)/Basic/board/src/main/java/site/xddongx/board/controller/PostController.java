package site.xddongx.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.xddongx.board.aspect.LogArguments;
import site.xddongx.board.aspect.LogReturn;
import site.xddongx.board.dto.PostDto;
import site.xddongx.board.entity.BoardEntity;
import site.xddongx.board.service.PostService;

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

    @LogArguments
    @PostMapping
    public void createPost(@PathVariable("boardId") Long boardId, @RequestBody PostDto dto, @RequestParam("userId") String UserId) {
        this.postService.createPost(boardId, dto, UserId);
    }

    @GetMapping("/{id}")
    public PostDto readPost(@PathVariable("id") Long postId) {
        return this.postService.readPost(postId);
    }

    @GetMapping
    public List<PostDto> readPostAll() {
        return this.postService.readPostAll();
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable("id") Long postId, @RequestBody PostDto dto) {
        this.postService.updatePost(postId, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("boardId") Long boardId, @PathVariable("id") Long postId) {
        this.postService.deletePost(postId);
    }
}
