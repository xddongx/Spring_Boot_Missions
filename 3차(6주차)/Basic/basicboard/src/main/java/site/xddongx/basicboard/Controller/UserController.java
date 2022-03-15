package site.xddongx.basicboard.Controller;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.xddongx.basicboard.Service.UserService;
import site.xddongx.basicboard.model.PostDto;
import site.xddongx.basicboard.model.UserDto;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto result = this.userService.create(userDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable("id") Long id) {
        UserDto userDto = this.userService.read(id);
        if (userDto == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> readUserAll() {
        List<UserDto> userDtoList = (List<UserDto>) this.userService.readAll();
        if (userDtoList == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userDtoList);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(null);
    }

}
