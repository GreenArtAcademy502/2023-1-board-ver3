package com.green.boardver3.cmt;

import com.green.boardver3.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmtService {

    private final CmtMapper mapper;

    @Autowired
    public CmtService(CmtMapper mapper) {
        this.mapper = mapper;
    }

    int insBoardCmt(CmtEntity entity) {
        try {
            int result = mapper.insBoardCmt(entity);
            if (result == 1) {
                return entity.getIboardCmt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public CmtRes selBoardCmt(CmtSelDto dto) {
        int startIdx = (dto.getPage() - 1) * dto.getRow();
        dto.setStartIdx(startIdx);
        List<CmtVo> list = mapper.selBoardCmt(dto);

        int rowCnt = mapper.selBoardCmtRowCountByIBoard(dto.getIboard());
        int maxPage = (int)Math.ceil((double)rowCnt / dto.getRow());
        int isMore = maxPage > dto.getPage() ? 1 : 0;

        return CmtRes.builder()
                    .list(list)
                    .isMore(isMore)
                    .maxPage(maxPage)
                    .row(dto.getRow())
                    .build();
    }

    public int updBoardCmt(CmtEntity entity) {
        return mapper.updBoardCmt(entity);
    }

    public int delBoardCmt(CmtDelDto dto) {
        return mapper.delBoardCmt(dto);
    }
}
