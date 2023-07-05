package com.green.boardver3.board;

import com.green.boardver3.board.model.BoardEntity;
import com.green.boardver3.board.model.BoardInsDto;
import com.green.boardver3.board.model.BoardSelDto;
import com.green.boardver3.board.model.BoardVo;
import com.green.boardver3.cmt.CmtMapper;
import com.green.boardver3.cmt.CmtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({BoardService.class})
class BoardServiceTest {

    @MockBean
    private BoardMapper mapper;

    @MockBean
    private CmtMapper cmtMapper;

    @MockBean
    private CmtService cmtService;

    @Autowired
    private BoardService service;

    @Test
    @DisplayName("Board-Service : 글 등록")
    void insBoard() {
        BoardEntity dto = new BoardEntity();
        dto.setTitle("test-제목");
        dto.setCtnt("test-내용");
        dto.setIuser(3);
        int expectedResult = 1;
        when(mapper.insBoard(dto)).thenReturn(expectedResult);

        BoardInsDto param = new BoardInsDto();
        param.setTitle("test-제목");
        param.setCtnt("test-내용");
        param.setIuser(3);
        int result = service.insBoard(param);

        verify(mapper).insBoard(dto);
    }

    @Test
    @DisplayName("Board-Service : 글 리스트")
    void selBoard() {
        BoardSelDto dto = new BoardSelDto();
        dto.setPage(1);
        dto.setRow(30);

        List<BoardVo> resList = new ArrayList<>();
        resList.add(new BoardVo(1, "제목1", "2023-06-01 12:05:22", "작성자1", null));
        resList.add(new BoardVo(2, "제목2", "2023-06-02 12:05:22", "작성자2", "abc.jpg"));
        resList.add(new BoardVo(3, "제목3", "2023-06-03 12:05:22", "작성자3", null));

        when(mapper.selBoard(any(BoardSelDto.class))).thenReturn(resList);

        List<BoardVo> testList = service.selBoard(dto);

        assertEquals(resList.size(), testList.size());
        assertEquals(resList.get(0).getIboard(), testList.get(0).getIboard());

        verify(mapper).selBoard(any());
    }

    @Test
    @DisplayName("Board-Service : 마지막 페이지 값")
    void selBoardMaxPage() {
        final int MAX_PAGE = 4;
        final int ROW_CNT = 100;
        final int ROW_PER_PAGE = 30;
        when(mapper.selBoardRowCount()).thenReturn(ROW_CNT);

        int result = service.selBoardMaxPage(ROW_PER_PAGE);

        assertEquals(MAX_PAGE, result);

        verify(mapper).selBoardRowCount();
    }
}