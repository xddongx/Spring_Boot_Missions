package site.xddongx.community.repository;

import org.springframework.data.repository.CrudRepository;
import site.xddongx.community.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
