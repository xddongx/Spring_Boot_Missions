package site.xddongx.auth.config;

import site.xddongx.auth.filter.CustomLoginHandler;
import site.xddongx.auth.filter.CustomLogoutHandler;
import site.xddongx.auth.infra.CustomUserDetailsService;
import site.xddongx.auth.infra.NaverOAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String LIKELION_LOGIN_COOKIE = "likelion_login_cookie";

    private final UserDetailsService userDetailsService;
    private final NaverOAuth2Service oAuth2UserService;
    private final CustomLoginHandler customLoginHandler;
    private final CustomLogoutHandler customLogoutHandler;

    @Autowired
    public WebSecurityConfig(
            CustomUserDetailsService customUserDetailsService,
            NaverOAuth2Service oAuth2UserService,
            CustomLoginHandler customLoginHandler,
            CustomLogoutHandler customLogoutHandler
    ){
        this.userDetailsService = customUserDetailsService;
        this.oAuth2UserService = oAuth2UserService;
        this.customLoginHandler = customLoginHandler;
        this.customLogoutHandler = customLogoutHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/home/**",
                        "/user/signup/**",
                        "/",
                        "check-user",
                        "/css/**",
                        "/images/**",
                        "/js/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/home")
                .successHandler(customLoginHandler)
                .permitAll()
            .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessHandler(customLogoutHandler)
                .deleteCookies(LIKELION_LOGIN_COOKIE)
                .invalidateHttpSession(true)
                .permitAll()
            .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(this.oAuth2UserService)
                .and()
                .defaultSuccessUrl("/home")
            .and()
                .oauth2Client()
        ;
    }
}
