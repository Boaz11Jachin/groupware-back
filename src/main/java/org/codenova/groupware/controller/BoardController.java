package org.codenova.groupware.controller;


import lombok.RequiredArgsConstructor;
import org.codenova.groupware.entity.Board;
import org.codenova.groupware.repository.BoardRepository;
import org.codenova.groupware.repository.EmployeeRepository;
import org.codenova.groupware.request.WriteBoard;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<Board> registerHandle(@RequestBody WriteBoard board) {

        Board post = Board.builder()
                .writer(employeeRepository.findById(board.getWriter()).get())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(0)
                .wroteAt(LocalDateTime.now()).build();

        boardRepository.save(post);

        return ResponseEntity.status(203).body(post);

    }

    @GetMapping
    public ResponseEntity<List<Board>> getBoardHandle() {
        List<Board> list = boardRepository.findAll(Sort.by("id").descending());
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getPostDetailHandle(@PathVariable Long id) {

        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(board.get());
    }

}
