package site.xddongx.board.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostRestController {
    private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);
    private final PostServiceInterface postService;

    public PostRestController(@Autowired PostServiceInterface postServiceInterface) {
        this.postService = postServiceInterface;
    }

    /**
     * (POST) http://localhost:8080/posts
     */
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createPost(
            @RequestParam("boardId") int id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("writer") String writer,
            @RequestParam("password") String password,
            @RequestParam("file") MultipartFile multipartFile
    ) {
        logger.info("boardId: " + id);
        logger.info("title: " + title);
        logger.info("content: " + content);
        logger.info("writer: " + writer);
        logger.info("password: " + password);
        logger.info("file original name: " + multipartFile.getOriginalFilename());
        PostDto dto = new PostDto(id, title, content, writer, password, null, null);
        this.postService.createPost(dto, multipartFile);
    }

    /**
     * (GET) http://localhost:8080/posts
     */
    @GetMapping()
    public List<PostDto> readPostAll() {
        logger.info("read Post All");
        return this.postService.readPostAll();
    }

    /**
     * (GET) http://localhost:8080/posts/{id}
     */
    @GetMapping("/{id}")
    public PostDto readPostOne(@PathVariable("id") int id) {
        logger.info("read Post One");
        return this.postService.readPostOne(id);
    }

    /**
     * (PUT) http://localhost:8080/posts/id
     */
    @PutMapping("/{id}")
    public void updatePost(@PathVariable("id") int id, @RequestBody PostDto dto) {
        logger.info("updatePost");
        this.postService.updatePost(id, dto);
    }

    /**
     * (PUT) http://localhost:8080/posts/id
     */
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") int id) {
        logger.info("delete Post");
        this.postService.deletePost(id);
    }
}
