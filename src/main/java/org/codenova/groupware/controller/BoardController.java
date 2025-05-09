package org.codenova.groupware.controller;


import lombok.RequiredArgsConstructor;
import org.codenova.groupware.entity.Board;
import org.codenova.groupware.repository.BoardRepository;
import org.codenova.groupware.repository.EmployeeRepository;
import org.codenova.groupware.request.WriteBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<Board> registerHandle(@RequestBody WriteBoard board) {

        Board post = Board.builder()
                .writer(employeeRepository.findById(board.getWriter()).get())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(0)
                .wroteAt(LocalDateTime.now()).build();

        boardRepository.save(post);
        messagingTemplate.convertAndSend("/public", "새글이 등록되었습니다.");

        return ResponseEntity.status(203).body(post);

    }

    @GetMapping
    public ResponseEntity<?> getBoards(@RequestParam(name="p") Optional<Integer> p) {
        // List<Board> list = boardRepository.findAll(Sort.by("id").descending());
        int pageNumber = p.orElse(1);
        pageNumber = Math.max(pageNumber, 1);
        Page<Board> boards = boardRepository.findAll(PageRequest.of(pageNumber-1, 5));  // 첫번째 인자가 페이지인덱스(0,, 두번째인자가 몇개씩 페이징 처리할껀지


        return ResponseEntity.status(200).body(boards);
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
