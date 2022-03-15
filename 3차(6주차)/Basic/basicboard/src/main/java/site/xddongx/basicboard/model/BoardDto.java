package site.xddongx.basicboard.model;

import site.xddongx.basicboard.jpa.entity.BoardEntity;

public class BoardDto {
    private Long id;
    private String name;

    public BoardDto() {
    }

    public BoardDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BoardDto(BoardEntity boardEntity) {
        this.id = boardEntity.getId();
        this.name = boardEntity.getName();
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

    @Override
    public String toString() {
        return "BoardDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
