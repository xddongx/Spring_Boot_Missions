package site.xddongx.board.repository;

import org.springframework.data.repository.CrudRepository;
import site.xddongx.board.entity.PostEntity;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    PostEntity findFirstByOrderByIdDesc();
}
