package site.xddongx.auth.repository;

import org.springframework.data.repository.CrudRepository;
import site.xddongx.auth.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
