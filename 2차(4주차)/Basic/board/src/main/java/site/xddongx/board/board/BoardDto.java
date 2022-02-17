package site.xddongx.board.board;

import lombok.Getter;
import lombok.Setter;
import site.xddongx.board.post.PostDto;

import java.util.List;

@Getter
@Setter
public class BoardDto {
    private String title;
    private List<PostDto> postList;

    public BoardDto() {
    }

    public BoardDto(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "title='" + title + '\'' +
                ", postList=" + postList +
                '}';
    }
}
