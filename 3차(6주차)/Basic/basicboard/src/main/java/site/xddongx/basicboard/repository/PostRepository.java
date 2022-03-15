package site.xddongx.basicboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.xddongx.basicboard.jpa.entity.PostEntity;
import site.xddongx.basicboard.jpa.entity.UserEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {

}
