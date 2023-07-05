package com.green.boardver3.cmt;

import com.green.boardver3.cmt.model.CmtEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest //스프링의 모든 빈을 로드
class CmtServiceTestWithBean {

    @Autowired
    private CmtService service;

    @Autowired
    private CmtMapper mapper;


    @Test
    @DisplayName("댓글 등록")
    void insBoardCmt() {
        //given
        CmtEntity entity = new CmtEntity();
        entity.setIboard(1_002);
        entity.setIuser(3);
        entity.setCtnt("junit5 테스트 중2");

        CmtEntity reqEntity = new CmtEntity();
        reqEntity.setIboard(1_002);
        reqEntity.setIuser(3);
        reqEntity.setCtnt("junit5 테스트 중2");

        given(mapper.insBoardCmt(entity)).willReturn(1008);

        //when
        int result = service.insBoardCmt(reqEntity);


    }

    @Test
    void selBoardCmt() {
    }
}