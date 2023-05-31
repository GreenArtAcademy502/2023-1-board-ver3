package com.green.boardver3.cmt.model;

import lombok.Data;

@Data
public class CmtEntity {
    private int iboardCmt;
    private int iboard;
    private int iuser;
    private String ctnt;
    private String createdAt;
    private String updatedAt;
}
