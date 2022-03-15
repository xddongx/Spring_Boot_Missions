package site.xddongx.basicboard.model;

import org.w3c.dom.html.HTMLDivElement;
import site.xddongx.basicboard.jpa.entity.PostEntity;

import java.util.List;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Long boardId;
    private Long userId;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content, Long boardId, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.userId = userId;
    }

    public PostDto(PostEntity postEntity) {
        this.id = postEntity.getId();
        this.title = postEntity.getTitle();
        this.content = postEntity.getContent();
        this.boardId = postEntity.getBoard().getId();
        this.userId = postEntity.getWriter().getId();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boardId=" + boardId +
                ", userId=" + userId +
                '}';
    }
}
