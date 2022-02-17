package site.xddongx.board.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private String password;

    public PostDto() {
    }

    public PostDto(int id, String title, String content, String writer, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
