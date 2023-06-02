package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@Tag(name="게시판", description = "")
public class BoardController {

    private final BoardService service;

    @Autowired
    public BoardController(BoardService service) {
        this.service = service;
    }

    @PostMapping
    public int postBoard(@RequestBody BoardInsDto dto) {
        return service.insBoard(dto);
    }

    @GetMapping
    public List<BoardVo> getBoard(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "30") int row) {
        BoardSelDto dto = new BoardSelDto();
        dto.setPage(page);
        dto.setRow(row);
        return service.selBoard(dto);
    }

    @GetMapping("/maxpage")
    public int getBoardMaxPage(@RequestParam int row) {
        return service.selBoardMaxPage(row);
    }

    @GetMapping("/{iboard}")
    public BoardDetailCmtVo getBoardDetail(@PathVariable int iboard) {
        BoardSelDto dto = new BoardSelDto();
        dto.setIboard(iboard);
        return service.selBoardDetail(dto);
    }

    @PutMapping
    public int putBoard(@RequestBody BoardUpdDto dto) {
        return service.updBoard(dto);
    }

    @DeleteMapping
    public int delBoard(@RequestBody BoardDelDto dto) throws Exception {
        return service.delBoard(dto);
    }
}
