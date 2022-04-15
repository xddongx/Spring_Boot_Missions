package site.xddongx.auth.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.auth.repository.RedisUserCacheRepository;
import site.xddongx.auth.model.UserHash;

import java.time.Instant;

@Service
public class RedisCacheService implements UserCacheService {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheService.class);
    private final RedisUserCacheRepository userCacheRepository;

    @Autowired
    public RedisCacheService(RedisUserCacheRepository userCacheRepository) {
        this.userCacheRepository = userCacheRepository;
    }

    @Override
    public void saveUserCache(String cookieId, UserDetails userDetails) {
        UserHash userHash = new UserHash();
        userHash.setCookieId(cookieId);
        userHash.setUsername(userDetails.getUsername());
        userHash.setLonginTimestamp(Instant.now());
        userCacheRepository.save(userHash);
    }

    @Override
    public UserHash getUserCache(String cookieId) {
        return userCacheRepository.findById(cookieId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public void removeUserCache(String cookieId) {
        userCacheRepository.deleteById(cookieId);
    }
}
