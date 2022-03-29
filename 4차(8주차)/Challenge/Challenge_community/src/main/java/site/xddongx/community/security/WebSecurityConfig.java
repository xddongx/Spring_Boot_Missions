package site.xddongx.community.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import site.xddongx.community.filter.LikelionSsoConsts;
import site.xddongx.community.handler.CustomSuccessHandler;

@Configuration
@EnableWebSecurity      // 스프링 시큐리티의 설정을 조작 할 수 있다.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home/**", "/user/signup", "/", "/css/**", "/images/**", "/js/**", "/h2-console/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/home")
                .deleteCookies(LikelionSsoConsts.LIKELION_LOGIN_COOKIE)
                .invalidateHttpSession(true)
                .permitAll();
    }
}
