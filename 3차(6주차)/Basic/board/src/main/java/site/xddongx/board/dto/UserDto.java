package site.xddongx.board.dto;

import site.xddongx.board.entity.PostEntity;
import site.xddongx.board.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserDto {

    private Long id;
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    private List<PostEntity> postEntityList;

    public UserDto() {
    }

    public UserDto(Long id, String userId, String userName, String password, List<PostEntity> postEntityList) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.postEntityList = postEntityList;
    }

    public UserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.userId = userEntity.getUserId();
        this.userName = userEntity.getUserName();
        this.password = userEntity.getPassword();
        this.postEntityList = userEntity.getPostEntityList();
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

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
