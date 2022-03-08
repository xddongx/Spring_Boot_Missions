package site.xddongx.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.board.dao.PostDao;
import site.xddongx.board.dto.PostDto;
import site.xddongx.board.entity.BoardEntity;
import site.xddongx.board.entity.PostEntity;
import site.xddongx.board.repository.BoardRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostDao postDao;
    private final BoardRepository boardRepository;

    @Autowired
    public PostService(PostDao postDao, BoardRepository boardRepository) {
        this.postDao = postDao;
        this.boardRepository = boardRepository;
    }

    public void createPost(Long boardId, PostDto dto, String UserId) {

        this.postDao.createPost(boardId, dto, UserId);
    }

    public PostDto readPost(Long postId) {
        PostEntity postEntity = this.postDao.readPost(postId);
        PostDto postDto = new PostDto(postEntity.getId(), postEntity.getTitle(), postEntity.getContent(), postEntity.getBoardEntity());
        return postDto;
    }

    public List<PostDto> readPostAll() {
        Iterator<PostEntity> iterator = this.postDao.readPostAll();
        List<PostDto> postDtoList = new ArrayList<>();

        while (iterator.hasNext()) {
            PostEntity postEntity = iterator.next();
            postDtoList.add(new PostDto(postEntity.getId(), postEntity.getTitle(), postEntity.getContent(), postEntity.getBoardEntity()));
        }

        return postDtoList;
    }

    public void updatePost(Long postId, PostDto dto) {
        this.postDao.updatePost(postId, dto);
    }

    public void deletePost(Long id) {
        this.postDao.deletePost(id);
    }
}
