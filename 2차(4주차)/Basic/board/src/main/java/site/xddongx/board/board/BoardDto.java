package site.xddongx.board.board;

import lombok.Getter;
import lombok.Setter;
import site.xddongx.board.post.PostDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardDto {
    static int count;
    private int id;
    private String title;
    private List<PostDto> postList = new ArrayList<>();

    public BoardDto() {
    }

    public BoardDto(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postList=" + postList +
                '}';
    }
}
