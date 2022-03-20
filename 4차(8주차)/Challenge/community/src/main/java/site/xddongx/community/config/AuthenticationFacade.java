package site.xddongx.community.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import site.xddongx.community.dto.UserDto;

@Component
public class AuthenticationFacade {

    public String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
