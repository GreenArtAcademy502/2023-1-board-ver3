package com.green.boardver3.cmt;

import com.green.boardver3.cmt.model.CmtEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CmtMapper {
    int insBoardCmt(CmtEntity entity);
}
