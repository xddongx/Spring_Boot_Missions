package site.xddongx.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import site.xddongx.board.dto.PostDto;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

//    @ManyToOne(targetEntity = BoardEntity.class, fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonIgnore
//    @JsonManagedReference               // 순환참조 방지
    private BoardEntity boardEntity;

//    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;

    public PostEntity() {
    }

    public PostEntity(PostDto postDto) {
        this.id = postDto.getId();
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.boardEntity = postDto.getBoardEntity();
        this.userEntity = postDto.getUserEntity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BoardEntity getBoardEntity() {
        return boardEntity;
    }

    public void setBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boardEntity=" + boardEntity +
                ", userEntity=" + userEntity +
                '}';
    }
}
