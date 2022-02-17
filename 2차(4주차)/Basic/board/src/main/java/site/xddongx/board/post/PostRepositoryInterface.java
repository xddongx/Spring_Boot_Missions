package site.xddongx.board.post;

import java.util.List;

public interface PostRepositoryInterface {
    void save(PostDto dto);
    List<PostDto> findPostAll();
    PostDto findById(int id);
    boolean updatePost(int id, PostDto dto);
    boolean deletePost(int id);
}
