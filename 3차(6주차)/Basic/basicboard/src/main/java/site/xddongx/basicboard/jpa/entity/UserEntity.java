package site.xddongx.basicboard.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "community_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(targetEntity = PostEntity.class, fetch = FetchType.LAZY, mappedBy = "writer")
    private List<PostEntity> writenPosts;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, List<PostEntity> writenPosts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.writenPosts = writenPosts;
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

    public List<PostEntity> getWritenPosts() {
        return writenPosts;
    }

    public void setWritenPosts(List<PostEntity> writenPosts) {
        this.writenPosts = writenPosts;
    }
}
