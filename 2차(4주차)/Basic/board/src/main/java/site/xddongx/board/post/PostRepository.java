package site.xddongx.board.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import site.xddongx.board.board.BoardDto;
import site.xddongx.board.board.BoardServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository implements PostRepositoryInterface {
    private static final Logger logger = LoggerFactory.getLogger(PostRepository.class);
    private final List<PostDto> postList;
    private List<BoardDto> boardList;
    private BoardServiceInterface boardService;

    /**
     * boardService는 등록된 게시판들을 목록을 불옴
     * */
    public PostRepository(@Autowired BoardServiceInterface boardServiceInterface) {
        this.boardService = boardServiceInterface;
        this.postList = new ArrayList<>();
        this.boardList = boardService.readBoardAll();
    }

    /**
     * id로 게시글 목록에서 게시글 하나를 찾는 메소드
     * */
    public static PostDto selectPost(int id, List<PostDto> postList) {
        PostDto targetPost = null;

        for (PostDto post:postList){
            if (post.getId() == id) {
                targetPost = post;
                return targetPost;
            }
        }
        return new PostDto();
    }

    /**
     * 새로운 게시글을 등록하면서 게시글을 식별할 수 있는 id 자동 증가<br>
     * 등록되는 게시글에서 게시판 id를 받아 게시판 목록에서 게시판에 게시글을 추가한다.
     * */
    @Override
    public void save(PostDto dto) {
        dto.setId(PostDto.count++);
        int boardId = dto.getBoardId();
        BoardDto targetBoard = boardService.readBoard(boardId);     // id로 게시판 찾기

        targetBoard.getPostList().add(dto);             // 게시판에 게시글 추가

        logger.info("Repository >>> \n" + dto.toString());
        this.postList.add(dto);
    }

    /**
     * 게시글 목록
     * */
    @Override
    public List<PostDto> findPostAll() {
        return this.postList;
    }

    /**
     * id로 게시글을 찾는다.
     * */
    @Override
    public PostDto findById(int id) {
        PostDto targetPost = selectPost(id, this.postList);
        return targetPost;
    }

    /**
     * id로 게시글을 찾아 게시글 비밀번호가 같은지 비교하여 같다면 업데이트
     * */
    @Override
    public boolean updatePost(int id, PostDto dto) {
        PostDto targetPost = this.findById(id);         // id로 게시글 찾기

        // 패스워드가 같은지 비교
        if (targetPost.getPassword().equals(dto.getPassword())){

            if (dto.getTitle() != null) {
                targetPost.setTitle(dto.getTitle());
            }

            if (dto.getContent() != null) {
                targetPost.setContent(dto.getContent());
            }

            return true;
        }
        return false;
    }

    /**
     * id로 게시글 삭제
     * */
    @Override
    public boolean deletePost(int id) {
        PostDto targetPost = this.findById(id);
        this.postList.remove(targetPost);
        return true;
    }
}
