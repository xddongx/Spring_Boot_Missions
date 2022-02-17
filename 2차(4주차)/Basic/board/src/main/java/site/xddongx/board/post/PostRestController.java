package site.xddongx.board.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostRestController {
    private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);
    private final PostServiceInterface postService;

    public PostRestController(@Autowired PostServiceInterface postServiceInterface) {
        this.postService = postServiceInterface;
    }

    @PostMapping()
    public void createPost(@RequestBody PostDto dto) {
        logger.info(dto.toString());
        this.postService.createPost(dto);
    }

    @GetMapping()
    public List<PostDto> readPostAll() {
        logger.info("read Post All");
        return this.postService.readPostAll();
    }

    @GetMapping("/{id}")
    public PostDto readPostOne(@PathVariable("id") int id, PostDto dto) {
        logger.info("read Post One");
        return this.postService.readPostOne(id);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable("id") int id, PostDto dto) {
        logger.info("update Post: " + dto.toString());
        this.postService.updatePost(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") int id) {
        logger.info("delete Post");
        this.postService.deletePost(id);
    }
}
