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

    public List<CmtVo> selBoardCmt(CmtSelDto dto) {
        int startIdx = (dto.getPage() - 1) * dto.getRow();
        dto.setStartIdx(startIdx);
        return mapper.selBoardCmt(dto);
    }

    public int updBoardCmt(CmtEntity entity) {
        return mapper.updBoardCmt(entity);
    }

    public int delBoardCmt(CmtDelDto dto) {
        return mapper.delBoardCmt(dto);
    }
}
