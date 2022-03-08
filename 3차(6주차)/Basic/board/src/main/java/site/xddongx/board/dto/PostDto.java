package site.xddongx.board.dto;

import site.xddongx.board.entity.BoardEntity;
import site.xddongx.board.entity.PostEntity;
import site.xddongx.board.entity.UserEntity;

import javax.validation.constraints.NotBlank;

public class PostDto {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private BoardEntity boardEntity;

    private UserEntity userEntity;

    public PostDto() {
    }

    public PostDto(PostEntity postEntity) {
        this.id = postEntity.getId();
        this.title = postEntity.getTitle();
        this.content = postEntity.getContent();
        this.boardEntity = postEntity.getBoardEntity();
        this.userEntity = postEntity.getUserEntity();
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
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boardEntity=" + boardEntity +
                ", userEntity=" + userEntity +
                '}';
    }
}
