package site.xddongx.community.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.community.entity.AreaEntity;
import site.xddongx.community.entity.UserEntity;
import site.xddongx.community.repository.AreaRepository;
import site.xddongx.community.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Service
public class CommunityUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CommunityUserDetailService.class);
    private final UserRepository userRepository;
    private final AreaRepository areaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CommunityUserDetailService(UserRepository userRepository, AreaRepository areaRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        logger.info("username: {}, last logged in at: {}", userEntity.getUsername(), userEntity.getLastLogin());

        return new AutoLockUserDetails(userEntity);
    }

    public void createUser(String username, String password, Boolean isShopOwner) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setShopOwner(isShopOwner);

        AreaEntity randomArea = this.randomArea();
        newUser.setResidence(randomArea);

        this.userRepository.save(newUser);
    }

    private AreaEntity randomArea() {
        List<AreaEntity> areaEntityList = new ArrayList<>();
        Iterable<AreaEntity> areaIterable = this.areaRepository.findAll();
        areaIterable.forEach(areaEntityList::add);
        Random random = new Random();

        return areaEntityList.get(random.nextInt(areaEntityList.size()));
    }
}
