package site.xddongx.auth.infra;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserCacheService {
    void saveUserCache(String cookieId, UserDetails userDetails);
    UserHash getUserCache(String cookieId);
    void removeUserCache(String cookieId);
}
