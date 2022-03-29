package site.xddongx.community.entity;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "community_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToOne(
            targetEntity = AreaEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "area_id")
    private AreaEntity residence;

    @Column(updatable = false)
    private Instant signUpDate;
    private Instant lastLogin;

    @Column
    private Boolean isShopOwner;

    public UserEntity() {
        this.signUpDate = Instant.now();
        this.lastLogin = Instant.now();
    }

    public UserEntity(String username, String password, AreaEntity residence, Boolean isShopOwner) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.residence = residence;
        this.isShopOwner = isShopOwner;
    }

    public UserEntity(Long id, String username, String password, Boolean isShopOwner) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isShopOwner = isShopOwner != null ? isShopOwner : false;
    }

    public UserEntity(Long id, String username, String password, AreaEntity residence, Instant signUpDate, Instant lastLogin, Boolean isShopOwner) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.residence = residence;
        this.signUpDate = signUpDate;
        this.lastLogin = lastLogin;
        this.isShopOwner = isShopOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AreaEntity getResidence() {
        return residence;
    }

    public void setResidence(AreaEntity residence) {
        this.residence = residence;
    }

    public Boolean getShopOwner() {
        return isShopOwner;
    }

    public void setShopOwner(Boolean shopOwner) {
        isShopOwner = shopOwner;
    }

    public Instant getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Instant signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }
}
