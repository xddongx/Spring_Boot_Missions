package site.xddongx.board.post;

import java.util.List;

public interface PostServiceInterface {
    void createPost(PostDto dto);
    List<PostDto> readPostAll();
    PostDto readPostOne(int id);
    void updatePost(int id, PostDto dto);
    void deletePost(int id);
}
