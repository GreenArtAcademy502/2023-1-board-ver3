package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.cmt.CmtMapper;
import com.green.boardver3.cmt.CmtService;
import com.green.boardver3.cmt.model.CmtDelDto;
import com.green.boardver3.cmt.model.CmtRes;
import com.green.boardver3.cmt.model.CmtSelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper mapper;
    private final CmtService cmtService;

    @Autowired
    public BoardService(BoardMapper mapper, CmtService cmtService) {
        this.mapper = mapper;
        this.cmtService = cmtService;
    }

    public int insBoard(BoardInsDto dto) {
        BoardEntity entity = new BoardEntity();
        entity.setTitle(dto.getTitle());
        entity.setCtnt(dto.getCtnt());
        entity.setIuser(dto.getIuser());
        int result = mapper.insBoard(entity);
        if(result == 1) {
            return entity.getIboard();
        }
        return result;
    }

    public List<BoardVo> selBoard(BoardSelDto dto) {
        int startIdx = (dto.getPage() - 1) * dto.getRow();
        dto.setStartIdx(startIdx);
        return mapper.selBoard(dto);
    }

    public int selBoardMaxPage(int row) {
        int count = mapper.selBoardRowCount();
        return (int)Math.ceil((double)count / row);
    }

    public BoardDetailCmtVo selBoardDetail(BoardSelDto dto) {
        BoardDetailVo vo = mapper.selBoardDetail(dto);

        CmtSelDto cmtDto = new CmtSelDto();
        cmtDto.setIboard(dto.getIboard());
        cmtDto.setPage(1);
        cmtDto.setRow(5);
        CmtRes cmt = cmtService.selBoardCmt(cmtDto);

        return BoardDetailCmtVo.builder()
                .iboard(vo.getIboard())
                .title(vo.getTitle())
                .ctnt(vo.getCtnt())
                .createdAt(vo.getCreatedAt())
                .writer(vo.getWriter())
                .writerMainPic(vo.getWriterMainPic())
                .cmt(cmt)
                .build();
    }

    public int updBoard(BoardUpdDto dto) {
        return mapper.updBoard(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delBoard(BoardDelDto dto) throws Exception {

        CmtDelDto cmtDto = new CmtDelDto();
        cmtDto.setIboard(dto.getIboard());
        cmtService.delBoardCmt(cmtDto);
        // 그 글에 달려있는 댓글을 전부 삭제해야 함.
        int result = 0;
        result = mapper.delBoard(dto);
        if (result == 0) {
            throw new Exception("삭제 권한 없음");
        }
        return result;
    }
}
