package site.xddongx.community.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import site.xddongx.community.config.NaverOAuth2Service;
import site.xddongx.community.handler.CustomSuccessHandler;

@Configuration
@EnableWebSecurity      // 스프링 시큐리티의 설정을 조작 할 수 있다.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final NaverOAuth2Service naverOAuth2Service;
    private final CustomSuccessHandler customSuccessHandler;

    @Autowired
    public WebSecurityConfig(CommunityUserDetailService communityUserDetailService,
                             UserDetailsService userDetailsService,
                             NaverOAuth2Service naverOAuth2Service,
                             CustomSuccessHandler customSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.naverOAuth2Service = naverOAuth2Service;
        this.customSuccessHandler = customSuccessHandler;
    }

    @Override public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 사용자 관리, 비밀 번호 유저네임이 일치하는지 관리
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password(passwordEncoder().encode("user1pass"))
//                .roles("USER")
//                .and()
//                .withUser("admin1")
//                .password(passwordEncoder().encode("admin1pass"))
//                .roles("ADMIN");
        auth.userDetailsService(this.userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home/**", "/user/signup", "/", "/css/**", "/images/**", "/js/**", "/h2-console/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/home")
                .successHandler(customSuccessHandler)
                .permitAll()
            .and()
                .oauth2Login()
                    .userInfoEndpoint()
                    .userService(this.naverOAuth2Service)
                .and()
                    .defaultSuccessUrl("/home")
            .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/home")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll();
    }
}
