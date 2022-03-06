package site.xddongx.board.repository;

import org.springframework.data.repository.CrudRepository;
import site.xddongx.board.entity.BoardEntity;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
}
