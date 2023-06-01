package com.green.boardver3.cmt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CmtVo {
    private int iboardCmt;
    private int iboard;
    private int iuser;
    private String ctnt;
    private String createdAt;
    private String writer;
    private String writerMainPic;
}
