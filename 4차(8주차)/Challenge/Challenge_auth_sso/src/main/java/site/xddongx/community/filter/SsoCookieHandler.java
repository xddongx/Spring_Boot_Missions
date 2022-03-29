package site.xddongx.community.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

@Component
public class SsoCookieHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final int COOKIE_EXPIRY = 60 * 60 * 24 * 30;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("onAuthenticationSuccess, create new cookie");

        Cookie loginCookie = new Cookie("likelion_login_cookie", "test_value");
        logger.debug("set cookie max age");
        loginCookie.setMaxAge(COOKIE_EXPIRY);
        loginCookie.setPath("/");
        logger.info("set cookie to httpServletResponse");
        response.addCookie(loginCookie);

        logger.debug("call super");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
