package site.xddongx.board.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.xddongx.board.FileDto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createPost(
            @RequestParam("boardId") int id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("writer") String writer,
            @RequestParam("password") String password,
            @RequestParam("file") MultipartFile[] files
    ) throws IllegalStateException, IOException {
        List<FileDto> fileList = createFileList(files);
        PostDto dto = new PostDto(id, title, content, writer, password, fileList);
        this.postService.createPost(dto);
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

    public List<FileDto> createFileList(MultipartFile[] files) throws IllegalStateException, IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";
        List<FileDto> fileList = new ArrayList<>();

        for (MultipartFile file: files) {
            if (!file.isEmpty()){
                // UUID를 이용해 unique한 파일 이름을 만드러준다.
                FileDto dto = new FileDto(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
                fileList.add(dto);

                File newFilewName = new File(projectPath, dto.getUuid() + "_" + dto.getFileName());
                // 전달된 내용을 실제 물리적인 파일로 저장해준다.
                file.transferTo(newFilewName);
            }
        }
        return fileList;
    }
}
