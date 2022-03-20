package site.xddongx.community.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Component
public class CookieFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CookieFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUUID = UUID.randomUUID().toString().split("_")[0];
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        logger.info("get query string: " + httpServletRequest.getQueryString());

        chain.doFilter(httpServletRequest, response);

//        if (SecurityContextHolder.getContext() == null){
//            SecurityContextHolder.getContext().setAuthentication(new Authentication() {
//                @Override
//                public Collection<? extends GrantedAuthority> getAuthorities() {
//                    return Collections.emptyList();
//                }
//
//                @Override
//                public Object getCredentials() {
//                    return null;
//                }
//
//                @Override
//                public Object getDetails() {
//                    return null;
//                }
//
//                @Override
//                public Object getPrincipal() {
//                    return (Principal) () -> "dummy";
//                }
//
//                @Override
//                public boolean isAuthenticated() {
//                    return true;
//                }
//
//                @Override
//                public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//
//                }
//
//                @Override
//                public String getName() {
//                    return "dummy";
//                }
//            });
//            chain.doFilter(request, response);
//        }
    }
}
