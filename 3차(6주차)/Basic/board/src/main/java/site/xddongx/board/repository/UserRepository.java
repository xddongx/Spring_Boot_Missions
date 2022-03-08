package site.xddongx.board.repository;

import org.springframework.data.repository.CrudRepository;
import site.xddongx.board.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
}
