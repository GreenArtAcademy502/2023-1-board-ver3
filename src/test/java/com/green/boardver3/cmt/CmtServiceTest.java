package com.green.boardver3.cmt;

import com.green.boardver3.cmt.model.CmtEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CmtServiceTest {

    private CmtService service;

    @Mock
    private CmtMapper mapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new CmtService(mapper);
    }

    @Test
    @DisplayName("댓글 등록")
    void insBoardCmt() {
        CmtEntity entity = new CmtEntity();
        entity.setIboard(1_002);
        //entity.setIuser(3);
        entity.setCtnt("junit5 테스트 중");

        int result = service.insBoardCmt(entity);
        verify(mapper).insBoardCmt(entity);
    }

    @Test
    void selBoardCmt() {
    }
}