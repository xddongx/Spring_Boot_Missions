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
     *
     */
    public void createPost(Long boardId, PostDto dto, String userId) {
        UserEntity targetUserEntity = this.userRepository.findByUserId(userId);

        if (targetUserEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<BoardEntity> targetBoardEntity = this.boardRepository.findById(boardId);

        // user와 board가 없거나 null이면 에러
        if (targetBoardEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        BoardEntity boardEntity = targetBoardEntity.get();

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());
        this.postRepository.save(postEntity);
        logger.info("Post Entity in create method1: " + postEntity);
        boardEntity.addPost(postEntity);
        logger.info("Post Entity in create method2: " + postEntity);
        targetUserEntity.addUserInPost(postEntity);

        this.boardRepository.save(boardEntity);
        this.userRepository.save(targetUserEntity);

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

    public void updatePost(Long id, PostDto dto, String userId) {
        Optional<PostEntity> targetPostEntity = this.postRepository.findById(id);

        if (targetPostEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        PostEntity postEntity = targetPostEntity.get();

        // post user의 id와 같지 않다면 not found
        if (!postEntity.getUserEntity().getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

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
