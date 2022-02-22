package site.xddongx.board.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PostService implements PostServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepositoryInterface postRepository;

    public PostService(@Autowired PostRepositoryInterface postRepositoryInterface) {
        this.postRepository = postRepositoryInterface;
    }

    @Override
    public void createPost(PostDto dto, MultipartFile file) {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dto.setFilename(fileName);
        dto.setFilepath("/image/" + fileName);

        this.postRepository.save(dto);
    }

    @Override
    public List<PostDto> readPostAll() {
        return this.postRepository.findPostAll();
    }

    @Override
    public PostDto readPostOne(int id) {
        return this.postRepository.findById(id);
    }

    @Override
    public void updatePost(int id, PostDto dto) {
        this.postRepository.updatePost(id, dto);
    }

    @Override
    public void deletePost(int id) {
        this.postRepository.deletePost(id);
    }
}
