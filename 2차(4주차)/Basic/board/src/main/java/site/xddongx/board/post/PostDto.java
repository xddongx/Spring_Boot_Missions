package site.xddongx.board.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    static int count;
    private int id;
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private String password;

    public PostDto() {
    }

    public PostDto(int boardId, String title, String content, String writer, String password) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", boardId=" + boardId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
