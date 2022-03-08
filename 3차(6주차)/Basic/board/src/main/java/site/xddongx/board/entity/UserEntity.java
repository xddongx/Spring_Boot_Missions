package site.xddongx.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import site.xddongx.board.dto.UserDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "userid")
    private String userId;
    @Column(name = "username")
    private String userName;
    private String password;

    @OneToMany(mappedBy = "userEntity")
    @JsonIgnore
    private List<PostEntity> postEntityList = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(UserDto userDto) {
        this.id = userDto.getId();
        this.userId = userDto.getUserId();
        this.userName = userDto.getUserName();
        this.password = userDto.getPassword();
        this.postEntityList = userDto.getPostEntityList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostEntity> getPostEntityList() {
        return postEntityList;
    }

    public void setPostEntityList(List<PostEntity> postEntityList) {
        this.postEntityList = postEntityList;
    }

    public void addUserInPost(PostEntity postEntity) {
        postEntity.setUserEntity(this);
        getPostEntityList().add(postEntity);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", postEntityList=" + postEntityList +
                '}';
    }
}
