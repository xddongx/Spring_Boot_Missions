package site.xddongx.basicboard.Service;

import site.xddongx.basicboard.model.UserDto;

import java.util.Collection;

public interface UserService {

    UserDto create(UserDto dot);
    UserDto read(Long id);
    Collection<UserDto> readAll();
    boolean update(Long id, UserDto dto);
    boolean delete(Long id);
}
