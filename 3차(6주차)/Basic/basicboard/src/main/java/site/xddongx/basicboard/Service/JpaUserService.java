package site.xddongx.basicboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.basicboard.jpa.entity.UserEntity;
import site.xddongx.basicboard.model.UserDto;
import site.xddongx.basicboard.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public JpaUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(dto.getPassword());
        userEntity = this.userRepository.save(userEntity);
        return new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
    }

    @Override
    public UserDto read(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserEntity userEntity = userEntityOptional.get();
        return new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
    }

    @Override
    public Collection<UserDto> readAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        this.userRepository.findAll().forEach(userEntity -> userDtoList.add(new UserDto(
                userEntity.getId(), userEntity.getUsername(), userEntity.getPassword()
        )));
        return null;
    }

    @Override
    public boolean update(Long id, UserDto dto) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
