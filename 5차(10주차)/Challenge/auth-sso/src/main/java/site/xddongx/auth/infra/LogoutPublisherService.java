package site.xddongx.auth.infra;

public interface LogoutPublisherService {
    void publishLogoutEvent(String cookieValue);
}
