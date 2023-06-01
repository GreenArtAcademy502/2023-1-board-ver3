package com.green.boardver3.cmt;

import com.green.boardver3.cmt.model.CmtDelDto;
import com.green.boardver3.cmt.model.CmtEntity;
import com.green.boardver3.cmt.model.CmtSelDto;
import com.green.boardver3.cmt.model.CmtVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmtMapper {
    int insBoardCmt(CmtEntity entity);
    List<CmtVo> selBoardCmt(CmtSelDto dto);
    int selBoardCmtRowCountByIBoard(int iboard);
    int updBoardCmt(CmtEntity entity);
    int delBoardCmt(CmtDelDto dto);
}
