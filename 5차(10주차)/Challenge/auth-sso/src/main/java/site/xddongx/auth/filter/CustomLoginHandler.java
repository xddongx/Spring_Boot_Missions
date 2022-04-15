package site.xddongx.auth.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import site.xddongx.auth.infra.UserCacheService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static site.xddongx.auth.config.WebSecurityConfig.LIKELION_LOGIN_COOKIE;

@Component
public class CustomLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final int COOKIE_EXPIRY = 60 * 60 * 24 * 30;
    private final UserCacheService userCacheService;

    public CustomLoginHandler(UserCacheService userCacheService) {
        this.userCacheService = userCacheService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info("onAuthenticationSuccess, create new cookie");
        String cookieId = UUID.randomUUID().toString();

        Cookie loginCookie = new Cookie(LIKELION_LOGIN_COOKIE, cookieId);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userCacheService.saveUserCache(cookieId, userDetails);

        logger.debug("set cookie max age");
        loginCookie.setMaxAge(COOKIE_EXPIRY);
        loginCookie.setPath("/");
        logger.info("set cookie to httpServletResponse");
        response.addCookie(loginCookie);


        super.onAuthenticationSuccess(request, response, authentication);
    }
}
