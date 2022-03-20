package site.xddongx.community.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.xddongx.community.config.AuthenticationFacade;

@Controller
@RequestMapping("home")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final AuthenticationFacade authFacade;

    @Autowired
    public HomeController(AuthenticationFacade authFacade) {
        this.authFacade = authFacade;
    }

    @GetMapping
    public String home() {
        try {
//            logger.info("connected user: {}", principal.getName());
//            logger.info("connected user: {}", authentication.getName());
            logger.info("connected user: {}", authFacade.getUserName());
        } catch (NullPointerException e) {
            logger.info("no user logged in");
        }

        return "index";
    }
}
