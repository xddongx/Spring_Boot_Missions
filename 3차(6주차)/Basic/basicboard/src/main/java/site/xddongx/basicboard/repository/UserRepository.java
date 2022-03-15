package site.xddongx.basicboard.repository;

import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.xddongx.basicboard.jpa.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    boolean existsByUsername(String userName);
    UserEntity findByUsername(String userName);
}
