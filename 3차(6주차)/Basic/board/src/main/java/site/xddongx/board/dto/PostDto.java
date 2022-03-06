package site.xddongx.board.dto;

import site.xddongx.board.entity.BoardEntity;

import javax.validation.constraints.NotBlank;

public class PostDto {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private BoardEntity boardEntity;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostDto(Long id, String title, String content, BoardEntity boardEntity) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardEntity = boardEntity;
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

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boardEntity=" + boardEntity +
                '}';
    }
}
