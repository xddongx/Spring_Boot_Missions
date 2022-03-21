package site.xddongx.community.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication
    ) throws ServletException, IOException {
        logger.info("===========================================================================");
        logger.info("===========================================================================");
        logger.info("request URI: {}");logger.info(request.getRequestURI());
        logger.info("request Method: {}"); logger.info(request.getMethod());
        logger.info("request PathInfo: {}"); logger.info(request.getPathInfo());
        logger.info("request Query String: {}"); logger.info(request.getQueryString());
        logger.info("request Context Path: {}"); logger.info(request.getContextPath());
        logger.info("request User Principal: {}"); logger.info(request.getUserPrincipal());


        logger.info("Security Context Holder: {}"); logger.info(request.getUserPrincipal());
        logger.info("===========================================================================");
        logger.info("===========================================================================");

        response.addCookie(new Cookie("likelion_login_cookie", "test_value"));

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
