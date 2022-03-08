package site.xddongx.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.xddongx.board.dao.UserDao;
import site.xddongx.board.dto.PostDto;
import site.xddongx.board.dto.UserDto;
import site.xddongx.board.entity.PostEntity;
import site.xddongx.board.entity.UserEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(UserDto dto) {
        this.userDao.createUser(dto);
    }

    public UserDto readUser(Long id) {
        UserEntity userEntity = this.userDao.readUser(id);
        UserDto userDto = new UserDto(userEntity.passwordMasked());
        return userDto;
    }

    public List<UserDto> readUserAll() {
        Iterator<UserEntity> iterator = this.userDao.readUserAll();
        List<UserDto> userDtoList = new ArrayList<>();

        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();
            userDtoList.add(new UserDto(userEntity.passwordMasked()));
        }

        return userDtoList;
    }

    public void updateUser(Long id, UserDto dto) {
        this.userDao.updateUser(id, dto);
    }

    public void deleteUser(Long id) {
        this.userDao.deleteUser(id);
    }

}
