package site.xddongx.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.xddongx.board.dto.UserDto;

import site.xddongx.board.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto){
        this.userService.createUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto readUser(@PathVariable("id") Long id) {
        return this.userService.readUser(id);
    }

    @GetMapping
    public List<UserDto> readUserAll() {
        return this.userService.readUserAll();
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        this.userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
    }
}
