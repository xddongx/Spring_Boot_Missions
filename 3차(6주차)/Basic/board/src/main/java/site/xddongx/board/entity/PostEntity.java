package site.xddongx.board.entity;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(targetEntity = BoardEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public PostEntity() {
    }

    public PostEntity(Long id, String title, String content, BoardEntity boardEntity) {
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
        if (this.boardEntity != null) {
            this.boardEntity.getPostEntityList().remove(this);
        }
        this.boardEntity = boardEntity;
        boardEntity.addPost(this);
    }


    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boardEntity=" + boardEntity +
                '}';
    }
}
