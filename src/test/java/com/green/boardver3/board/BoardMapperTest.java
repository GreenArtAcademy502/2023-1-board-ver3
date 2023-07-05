package com.green.boardver3.board;

import com.green.boardver3.board.model.BoardEntity;
import com.green.boardver3.board.model.BoardSelDto;
import com.green.boardver3.board.model.BoardUpdDto;
import com.green.boardver3.board.model.BoardVo;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;

    @Test
    void insBoard() {
        //given
        BoardEntity entity = new BoardEntity();
        entity.setTitle("제목1");
        entity.setCtnt("내용1");
        entity.setIuser(2);

        int result = mapper.insBoard(entity);
        System.out.println("iboard: " + entity.getIboard());
        assertEquals(result, 1);
    }

    @Test
    void selBoard() {
        int page = 1;
        int row = 3;
        int startIdx = (page - 1) * row;
        BoardSelDto dto = new BoardSelDto();
        dto.setStartIdx(startIdx);
        dto.setRow(row);
        List<BoardVo> list = mapper.selBoard(dto);

        assertEquals(3, list.size());
    }

    @Test
    void selBoardRowCount() {
    }

    @Test
    void selBoardDetail() {
    }

    @Test
    void updBoard() {
        BoardUpdDto dto = new BoardUpdDto();
        dto.setIboard(927);
        dto.setIuser(3);
        dto.setTitle("테스트");
        dto.setCtnt("jjjjjjj");

        int result = mapper.updBoard(dto);

        assertEquals(1, result);
    }

    @Test
    void delBoard() {
    }
}