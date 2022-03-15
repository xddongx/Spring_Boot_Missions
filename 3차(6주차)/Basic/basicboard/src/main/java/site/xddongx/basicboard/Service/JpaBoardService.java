package site.xddongx.basicboard.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.basicboard.jpa.entity.BoardEntity;
import site.xddongx.basicboard.model.BoardDto;
import site.xddongx.basicboard.repository.BoardRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JpaBoardService implements BoardService {
    private final BoardRepository boardRepository;

    public JpaBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public BoardDto create(BoardDto boardDto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(boardDto.getName());

        boardEntity = this.boardRepository.save(boardEntity);

        return new BoardDto(boardEntity);
    }

    @Override
    public BoardDto read(Long id) {
        Optional<BoardEntity> boardDtoOptional = this.boardRepository.findById(id);
        if (boardDtoOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        BoardEntity boardEntity = boardDtoOptional.get();

        return new BoardDto(boardEntity);
    }

    @Override
    public Collection<BoardDto> readAll() {
        List<BoardDto> boardDtoList = new ArrayList<>();
        this.boardRepository.findAll().forEach(boardEntity -> boardDtoList.add(new BoardDto(boardEntity)));

        return boardDtoList;
    }

    @Override
    public boolean update(Long id, BoardDto dto) {
        Optional<BoardEntity> boardEntityOptional = this.boardRepository.findById(id);
        if (boardEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        BoardEntity boardEntity = boardEntityOptional.get();

        boardEntity.setName((dto.getName().isEmpty() || dto.getName() == null) ? boardEntity.getName() : dto.getName());
        this.boardRepository.save(boardEntity);

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<BoardEntity> boardEntityOptional = this.boardRepository.findById(id);
        if (boardEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        BoardEntity boardEntity = boardEntityOptional.get();

        this.boardRepository.delete(boardEntity);

        return true;
    }
}
