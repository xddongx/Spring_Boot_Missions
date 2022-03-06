package site.xddongx.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public void createPost(@PathVariable("boardId") Long boardId, @RequestBody PostDto dto) {
        this.postService.createPost(boardId, dto);
    }

    @GetMapping("/{id}")
    public PostDto readPost(@PathVariable("boardId") Long boardId, @PathVariable("id") Long postId) {
        return this.postService.readPost(boardId, postId);
    }

    @GetMapping
    public List<PostDto> readPostAll(@PathVariable("boardId") Long boardId) {
        return this.postService.readPostAll();
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable("boardId") Long boardId, @PathVariable("id") Long postId, @RequestBody PostDto dto) {
        this.postService.updatePost(postId, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("boardId") Long boardId, @PathVariable("id") Long postId) {
        this.postService.deletePost(postId);
    }
}
