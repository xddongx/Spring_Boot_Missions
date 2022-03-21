package site.xddongx.community.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.community.config.AuthenticationFacade;
import site.xddongx.community.config.PasswordEncoderConfig;
import site.xddongx.community.dto.UserDto;
import site.xddongx.community.entity.AreaEntity;
import site.xddongx.community.entity.UserEntity;
import site.xddongx.community.repository.AreaRepository;
import site.xddongx.community.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final AreaRepository areaRepository;
    private final AuthenticationFacade authFacade;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            AreaRepository areaRepository,
            AuthenticationFacade authFacade,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.authFacade = authFacade;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto userDto){

        String username = userDto.getUsername();
        String password = this.passwordEncoder.encode(userDto.getPassword());
        boolean isShopOwner = userDto.getIsShopOwner();

        Optional<AreaEntity> areaEntityOptional = this.areaRepository.findById(userDto.getAreaId());
        if (areaEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        AreaEntity residence = areaEntityOptional.get();


        UserEntity userEntity = new UserEntity(username, password, residence, isShopOwner);

        this.userRepository.save(userEntity);

        return new UserDto(userEntity);
    }

    public UserDto readUser(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new UserDto(userEntityOptional.get());
    }

    public List<UserDto> readUserAll(){
        List<UserDto> userDtoList = new ArrayList<>();
        this.userRepository.findAll().forEach(userEntity ->
                userDtoList.add(new UserDto(userEntity)));
        return userDtoList;
    }

    public void updateUser(Long id, UserDto dto){
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setPassword(
                dto.getPassword() == null ? userEntity.getPassword() : dto.getPassword()
        );
        userEntity.setShopOwner(
                dto.getIsShopOwner() == null ? userEntity.getShopOwner() : dto.getIsShopOwner()
        );

        Optional<AreaEntity> newArea = this.areaRepository.findById(
                dto.getId() == null ? userEntity.getResidence().getId() : dto.getAreaId());

        newArea.ifPresent(userEntity::setResidence);
        userRepository.save(userEntity);
    }

    public void deleteUser(Long id){
        if (!this.userRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        this.userRepository.deleteById(id);
    }
}
