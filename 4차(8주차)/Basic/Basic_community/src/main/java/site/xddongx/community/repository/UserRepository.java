package site.xddongx.community.repository;

import org.springframework.data.repository.CrudRepository;
import site.xddongx.community.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
