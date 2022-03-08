package site.xddongx.board.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {

    private Long id;
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public UserDto() {
    }

    public UserDto(Long id, String userId, String userName) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
    }

    public UserDto(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserDto(Long id, String userId, String userName, String password) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
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
