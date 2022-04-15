package site.xddongx.community.auth.service;

public interface LogoutCacheService {
    boolean checkUserStatus(String cookieValue);
}
