package site.xddongx.board.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.board.aspect.LogReturn;
import site.xddongx.board.dto.BoardDto;
import site.xddongx.board.dto.PostDto;
import site.xddongx.board.entity.BoardEntity;
import site.xddongx.board.entity.PostEntity;
import site.xddongx.board.repository.BoardRepository;
import site.xddongx.board.repository.PostRepository;
import site.xddongx.board.service.BoardService;

import java.util.Iterator;
import java.util.Optional;

@Repository
public class PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final BoardDao boardDao;

    @Autowired
    public PostDao(PostRepository postRepository, BoardService boardService, BoardDao boardDao, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.boardService = boardService;
        this.boardDao = boardDao;
        this.boardRepository = boardRepository;
    }

    public void createPost(Long boardId, PostDto dto, String UserId) {
        if (!UserId.isEmpty()){
            Optional<BoardEntity> targetEntity = this.boardRepository.findById(boardId);

            if (targetEntity.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            BoardEntity boardEntity = targetEntity.get();

            PostEntity postEntity = new PostEntity();
            postEntity.setTitle(dto.getTitle());
            postEntity.setContent(dto.getContent());

            this.postRepository.save(postEntity);

            boardEntity.addPost(postEntity);

            this.boardRepository.save(boardEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


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
        Optional<PostEntity> targetEntity = this.postRepository.findById(id);

        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        PostEntity postEntity = targetEntity.get();
        postEntity.setTitle(dto.getTitle() == null ? postEntity.getTitle() : dto.getTitle());
        postEntity.setContent(dto.getContent() == null ? postEntity.getContent() : dto.getContent());
        postEntity.setBoardEntity(dto.getBoardEntity() == null ? postEntity.getBoardEntity() : dto.getBoardEntity());

        this.postRepository.save(postEntity);
    }

    public void deletePost(Long id) {
        this.postRepository.deleteById(id);
    }
}
