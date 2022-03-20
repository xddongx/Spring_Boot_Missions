package site.xddongx.community.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.xddongx.community.entity.UserEntity;
import site.xddongx.community.repository.UserRepository;

import java.util.ArrayList;

@Service
public class CommunityUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CommunityUserDetailService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        final UserEntity testUserEntity = new UserEntity();
        testUserEntity.setUsername("test1");
        testUserEntity.setPassword(passwordEncoder.encode("test1"));
        testUserEntity.setShopOwner(true);
        this.userRepository.save(testUserEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity userEntity = this.userRepository.findByUsername(username);
        return new User(username, userEntity.getPassword(), new ArrayList<>());
    }


}
