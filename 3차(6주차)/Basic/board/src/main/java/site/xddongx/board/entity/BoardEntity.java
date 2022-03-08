package site.xddongx.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import site.xddongx.board.dto.BoardDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "boardEntity")            // mappedBy: 관계의 주인을 나타낸다. 관계의 주인은 PostEntity이다. 관계 주인에게 설정을 해줘야한다.
    @JsonIgnore
//    @JsonBackReference
    private List<PostEntity> postEntityList = new ArrayList<>();

    public BoardEntity() {
    }

    public BoardEntity(BoardDto boardDto) {
        this.id = boardDto.getId();
        this.name = boardDto.getName();
        this.postEntityList = boardDto.getPostEntityList();
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

    public void addBoardInPost(PostEntity postEntity) {
        postEntity.setBoardEntity(this);            // onwer
        getPostEntityList().add(postEntity);        // 왜 하는가?
    }

    @Override
    public String toString() {
        return "BoardEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postEntityList=" + postEntityList +
                '}';
    }
}

