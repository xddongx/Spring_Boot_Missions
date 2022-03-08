package site.xddongx.board.dao;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.board.dto.UserDto;
import site.xddongx.board.entity.PostEntity;
import site.xddongx.board.entity.UserEntity;
import site.xddongx.board.repository.UserRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    private final UserRepository userRepository;

    @Autowired
    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(dto.getUserId());
        userEntity.setUserName(dto.getUserName());
        userEntity.setPassword(dto.getPassword());
        this.userRepository.save(userEntity);
    }

    public UserEntity readUser(Long id) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return userEntity.get().passwordMasked();
    }

    public Iterator<UserEntity> readUserAll() {
        return this.userRepository.findAll().iterator();
    }

    public void updateUser(Long id, UserDto dto) {
        Optional<UserEntity> targetEntity = this.userRepository.findById(id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = targetEntity.get();

        userEntity.setUserName(dto.getUserName() == null ? userEntity.getUserName() : dto.getUserName());
        userEntity.setPassword(dto.getPassword() == null ? userEntity.getPassword() : dto.getPassword());

        this.userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}
