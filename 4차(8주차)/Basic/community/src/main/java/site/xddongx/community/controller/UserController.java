package site.xddongx.community.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.community.dto.UserDto;
import site.xddongx.community.entity.UserEntity;
import site.xddongx.community.repository.UserRepository;
import site.xddongx.community.service.UserService;

import java.util.Collection;
import java.util.Random;

@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("login")
    public String login() {
        return "login-form";
    }

    @GetMapping("signup")
    public String signup() {
        return "signup-form";
    }

    @PostMapping("signup")
    public String signupPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password_check") String passwordCheck,
            @RequestParam(value = "is_shop_owner", required = false) boolean isShopOwner,
            @RequestParam("area") String area
    ) throws IllegalAccessException {
        if (!password.equals(passwordCheck)) {
            throw new IllegalAccessException("비밀번호가 맞지 않습니다.");
        }

        Long areaId = Long.parseLong(area);

        UserDto userDto = new UserDto(username, password, isShopOwner, areaId);


        this.userService.createUser(userDto);

        return "redirect:/home";
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.readUser(id));
    }
//
//    @GetMapping
//    public ResponseEntity<Collection<UserDto>> readUserAll(){
//        return ResponseEntity.ok(this.userService.readUserAll());
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
//        this.userService.updateUser(id, userDto);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> DeleteUser(@PathVariable("id") Long id){
//        this.userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
}
