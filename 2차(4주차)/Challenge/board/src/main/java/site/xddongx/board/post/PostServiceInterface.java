package site.xddongx.board.post;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostServiceInterface {
    void createPost(PostDto dto, MultipartFile file);
    List<PostDto> readPostAll();
    PostDto readPostOne(int id);
    void updatePost(int id, PostDto dto);
    void deletePost(int id);
}
