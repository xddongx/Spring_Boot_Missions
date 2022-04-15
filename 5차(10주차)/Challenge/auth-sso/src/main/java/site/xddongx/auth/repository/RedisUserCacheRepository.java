package site.xddongx.auth.repository;

import org.springframework.data.repository.CrudRepository;
import site.xddongx.auth.model.UserHash;

public interface RedisUserCacheRepository extends CrudRepository<UserHash, String> {
}
