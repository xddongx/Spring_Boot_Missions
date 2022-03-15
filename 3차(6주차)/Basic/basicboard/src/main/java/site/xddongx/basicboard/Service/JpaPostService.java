package site.xddongx.basicboard.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.basicboard.jpa.entity.BoardEntity;
import site.xddongx.basicboard.jpa.entity.PostEntity;
import site.xddongx.basicboard.jpa.entity.UserEntity;
import site.xddongx.basicboard.model.PostDto;
import site.xddongx.basicboard.repository.BoardRepository;
import site.xddongx.basicboard.repository.PostRepository;
import site.xddongx.basicboard.repository.UserRepository;

import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JpaPostService implements PostService {
    private static Logger logger = LoggerFactory.getLogger(JpaPostService.class);
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public JpaPostService(PostRepository postRepository, BoardRepository boardRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDto create(Long boardId, PostDto postDto, String userName) {
        if (!this.boardRepository.existsById(boardId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        logger.info("Board Entity exists pass");
        if (!this.userRepository.existsByUsername(userName)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        logger.info("User Entity exists pass");

        BoardEntity boardEntity = this.boardRepository.findById(boardId).get();
        UserEntity userEntity = this.userRepository.findByUsername(userName);

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContent(postDto.getContent());
        postEntity.setWriter(userEntity);
        postEntity.setBoard(boardEntity);

        postEntity = this.postRepository.save(postEntity);

        return new PostDto(postEntity);
    }

    @Override
    public PostDto read(Long boardId, Long postId) {
        if (!this.boardRepository.existsById(boardId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = this.postRepository.findById(postId).get();
        if (!postEntity.getBoard().getId().equals(boardId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return new PostDto(postEntity);
    }

    @Override
    public Collection<PostDto> readAll(Long boardId) {
        Optional<BoardEntity> boardEntityOptional = this.boardRepository.findById(boardId);
        if (boardEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        BoardEntity boardEntity = boardEntityOptional.get();
        List<PostDto> postDtoList = new ArrayList<>();

        boardEntity.getPostEntityList().forEach(postEntity -> postDtoList.add(new PostDto(postEntity)));

        return postDtoList;
    }

    @Override
    public boolean update(Long boardId, Long postId, PostDto postDto) {
        if (!this.postRepository.existsById(postId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = this.postRepository.findById(postId).get();
        if (!postEntity.getBoard().getId().equals(boardId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        logger.info("Post Entity: " + postEntity);
        if (!postEntity.getWriter().getId().equals(postDto.getUserId())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        postEntity.setTitle((postDto.getTitle().isEmpty() || postDto.getTitle() == null) ? postEntity.getTitle() : postDto.getTitle());
        postEntity.setContent((postDto.getContent().isEmpty() || postDto.getContent() == null) ? postEntity.getContent() : postDto.getContent());

        this.postRepository.save(postEntity);
        return true;
    }

    @Override
    public boolean delete(Long boardId, Long postId) {
        if (!this.postRepository.existsById(postId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = this.postRepository.findById(postId).get();
        if (!postEntity.getBoard().getId().equals(boardId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        this.postRepository.deleteById(postId);

        return true;
    }
}
