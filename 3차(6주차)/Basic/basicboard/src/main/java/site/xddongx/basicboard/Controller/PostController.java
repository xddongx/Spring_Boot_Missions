package site.xddongx.basicboard.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.xddongx.basicboard.model.BoardDto;
import site.xddongx.basicboard.model.PostDto;
import site.xddongx.basicboard.Service.PostService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("board/{boardId}/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(@Autowired PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@PathVariable("boardId") Long boardId, @RequestBody PostDto postDto, @PathParam("userName") String userName) {
        PostDto result = this.postService.create(boardId, postDto, userName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> readPost(@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId){
        PostDto postDto = this.postService.read(boardId, postId);
        if (postDto == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(postDto);
    }

    @GetMapping()
    public ResponseEntity<List<PostDto>> readPostAll(@PathVariable("boardId") Long boardId) {
        Collection<PostDto> postDtoCollection = this.postService.readAll(boardId);
        if (postDtoCollection == null) return ResponseEntity.notFound().build();

        List<PostDto> postDtoList = new ArrayList<>(postDtoCollection);

        return ResponseEntity.ok(postDtoList);
    }

    @PutMapping("{postId}")
    public ResponseEntity<?> updatePost(@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId, @RequestBody PostDto postDto) {
        if (!this.postService.update(boardId, postId, postDto)) return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("boardId") Long boardId, @PathVariable("postId") Long postId) {
        if (!this.postService.delete(boardId, postId)) return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
