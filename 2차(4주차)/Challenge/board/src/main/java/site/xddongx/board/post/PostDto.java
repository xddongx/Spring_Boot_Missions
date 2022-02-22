package site.xddongx.board.post;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    private String filename;
    private String filepath;
//    private List<MultipartFile> multipartFileList;

    public PostDto() {
    }

    public PostDto(int boardId, String title, String content, String writer, String password, String filename, String filepath) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.filename = filename;
        this.filepath = filepath;
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
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
