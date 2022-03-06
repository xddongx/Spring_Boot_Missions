package site.xddongx.board.dto;

import site.xddongx.board.entity.PostEntity;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class BoardDto {
    private Long id;

    @NotBlank
    private String name;

    private List<PostEntity> postEntityList;

    public BoardDto() {
    }

    public BoardDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BoardDto(Long id, String name, List<PostEntity> postEntityList) {
        this.id = id;
        this.name = name;
        this.postEntityList = postEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostEntity> getPostEntityList() {
        return postEntityList;
    }

    public void setPostEntityList(List<PostEntity> postEntityList) {
        this.postEntityList = postEntityList;
    }

    public void addPostEntity(Long id, PostEntity postEntity) {
        this.postEntityList.add(postEntity);
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postEntityList=" + postEntityList +
                '}';
    }
}
