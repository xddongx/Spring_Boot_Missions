package site.xddongx.board.dao;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.board.dto.PostDto;
import site.xddongx.board.entity.BoardEntity;
import site.xddongx.board.entity.PostEntity;
import site.xddongx.board.entity.UserEntity;
import site.xddongx.board.repository.BoardRepository;
import site.xddongx.board.repository.PostRepository;
import site.xddongx.board.repository.UserRepository;
import site.xddongx.board.service.BoardService;

import java.util.Iterator;
import java.util.Optional;

@Repository
public class PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostDao(PostRepository postRepository, BoardRepository boardRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    /**
     * 입력받은 post를 저장하고 Board와 User를 연결해준다.
     */
    public void createPost(Long boardId, PostDto dto, String userId) {
        // userId로 User 정보를 받아온다.
        UserEntity targetUserEntity = this.userRepository.findByUserId(userId);

        // User가 있는지 확인
        if (targetUserEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // boardId로 Board 정보를 받아온다.
        Optional<BoardEntity> targetBoardEntity = this.boardRepository.findById(boardId);

        // Board가 있는지 확인
        if (targetBoardEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        BoardEntity boardEntity = targetBoardEntity.get();

        // 입력받은 Poste데이터 저장
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());
        this.postRepository.save(postEntity);

        // Post <-> Board, User 양방향 연결
        targetUserEntity.addUserInPost(postEntity);
        boardEntity.addBoardInPost(postEntity);
        this.userRepository.save(targetUserEntity);
        this.boardRepository.save(boardEntity);


    }

    public PostEntity readPost(Long id) {
        Optional<PostEntity> postEntity = this.postRepository.findById(id);
        if (postEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return postEntity.get();
    }

    public Iterator<PostEntity> readPostAll() {
        return this.postRepository.findAll().iterator();
    }

    public void updatePost(Long id, PostDto dto) {
        Optional<PostEntity> targetPostEntity = this.postRepository.findById(id);

        if (targetPostEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        PostEntity postEntity = targetPostEntity.get();

        postEntity.setTitle(dto.getTitle() == null ? postEntity.getTitle() : dto.getTitle());
        postEntity.setContent(dto.getContent() == null ? postEntity.getContent() : dto.getContent());
        postEntity.setBoardEntity(dto.getBoardEntity() == null ? postEntity.getBoardEntity() : dto.getBoardEntity());
        postEntity.setUserEntity(dto.getUserEntity() == null ? postEntity.getUserEntity() : dto.getUserEntity());

        this.postRepository.save(postEntity);
    }

    public void deletePost(Long id) {
        this.postRepository.deleteById(id);
    }
}
