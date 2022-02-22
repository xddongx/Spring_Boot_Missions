package site.xddongx.board.post;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import site.xddongx.board.FileDto;

import java.util.List;

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
    private List<FileDto> fileList;

    public PostDto() {
    }

    public PostDto(int boardId, String title, String content, String writer, String password, List<FileDto> fileList) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.fileList = fileList;
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
                ", fileList=" + fileList +
                '}';
    }
}
