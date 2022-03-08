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

    /**
     * POST http://localhost:8080/users<br>
     * user 생성<br>
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto){
        this.userService.createUser(userDto);
    }

    /**
     * GET http://localhost:8080/users/{id}<br>
     * user 조회<br>
     * 1개 조회
     */
    @GetMapping("/{id}")
    public UserDto readUser(@PathVariable("id") Long id) {
        return this.userService.readUser(id);
    }

    /**
     * GET http://localhost:8080/users<br>
     * user 전체 조회<br>
     */
    @GetMapping
    public List<UserDto> readUserAll() {
        return this.userService.readUserAll();
    }

    /**
     * PUT http://localhost:8080/users/{id}<br>
     * user 업데이트<br>
     */
    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        this.userService.updateUser(id, userDto);
    }

    /**
     * DELETE http://localhost:8080/users<br>
     * user 삭제<br>
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
    }
}
