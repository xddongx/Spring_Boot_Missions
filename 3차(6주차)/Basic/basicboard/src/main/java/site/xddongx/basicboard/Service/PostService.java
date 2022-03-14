package site.xddongx.basicboard.Service;

import site.xddongx.basicboard.model.PostDto;

import java.util.Collection;

public interface PostService {
    PostDto create(Long boardId, PostDto dto);
    PostDto read(Long boardId, Long postId);
    Collection<PostDto> readAll(Long boardId);
    boolean update(Long boardId, Long postId, PostDto dto);
    boolean delete(Long boardId, Long postId, String password);
}
