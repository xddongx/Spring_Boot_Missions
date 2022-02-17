package site.xddongx.board.post;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository implements PostRepositoryInterface {
    private final List<PostDto> postList;

    public PostRepository() {
        this.postList = new ArrayList<>();
    }


    @Override
    public void save(PostDto dto) {
        this.postList.add(dto);
    }

    @Override
    public List<PostDto> findPostAll() {
        return this.postList;
    }

    @Override
    public PostDto findById(int id) {
        return this.postList.get(id);
    }

    @Override
    public boolean updatePost(int id, PostDto dto) {
        PostDto targetPost = this.postList.get(id);

        if (targetPost.getPassword().equals(dto.getPassword())){
            if (dto.getTitle() != null) {
                targetPost.setTitle(targetPost.getTitle());
            }

            if (dto.getContent() != null) {
                targetPost.setContent(targetPost.getContent());
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean deletePost(int id) {
        this.postList.remove(id);
        return true;
    }
}
