package com.green.boardver3.cmt;

import com.green.boardver3.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class CmtController {

    private final CmtService service;

    @Autowired
    public CmtController(CmtService service) {
        this.service = service;
    }

    @PostMapping("/{iboard}/cmt")
    public int postBoardCmt(@PathVariable int iboard
                          , @RequestBody CmtInsDto dto){
        CmtEntity entity = new CmtEntity();
        entity.setIboard(iboard);
        entity.setIuser(dto.getIuser());
        entity.setCtnt(dto.getCtnt());
        return service.insBoardCmt(entity);
    }

    @GetMapping("/{iboard}/cmt")
    public List<CmtVo> getBoardCmt(@PathVariable int iboard
                                 , @RequestParam int page
                                 , @RequestParam(defaultValue = "5") int row) {
        CmtSelDto dto = new CmtSelDto();
        dto.setIboard(iboard);
        dto.setPage(page);
        dto.setRow(row);
        return service.selBoardCmt(dto);
    }

    @DeleteMapping("/cmt/{iboardCmt}")
    public int delBoardCmt(@PathVariable int iboardCmt
                         , @RequestParam int iuser) {
        CmtDelDto dto = new CmtDelDto();
        dto.setIboardCmt(iboardCmt);
        dto.setIuser(iuser);
        return service.delBoardCmt(dto);
    }
}
