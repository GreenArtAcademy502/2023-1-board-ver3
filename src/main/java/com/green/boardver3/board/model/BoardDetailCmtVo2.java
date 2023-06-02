package com.green.boardver3.board.model;

import com.green.boardver3.cmt.model.CmtRes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDetailCmtVo2 {
    private BoardDetailVo board;
    private CmtRes cmt;
}
