package com.green.boardver3.board;

import com.google.gson.Gson;
import com.green.boardver3.board.model.*;
import com.green.boardver3.cmt.model.CmtRes;
import com.green.boardver3.cmt.model.CmtVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoardService service;

    @Test
    @DisplayName("Board 글등록")
    void postBoard() throws Exception {
        BoardInsDto dto = new BoardInsDto();
        dto.setTitle("제목입니다");
        dto.setCtnt("내용입니다");
        dto.setIuser(2);

        int result = 100;
        given(service.insBoard(dto)).willReturn(result);

        BoardInsDto param = new BoardInsDto();
        param.setTitle("제목입니다");
        param.setCtnt("내용입니다");
        param.setIuser(2);

        Gson gson = new Gson();
        String content = gson.toJson(param);

        mvc.perform(post("/board").content(content)
                                           .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("100"))
                .andDo(print());


        verify(service).insBoard(any());
    }

    @Test
    @DisplayName("Board 리스트")
    void getBoard() throws Exception {

        BoardSelDto param = new BoardSelDto();
        param.setPage(1);
        param.setRow(30);

        List<BoardVo> reqList = new ArrayList<>();
        reqList.add(new BoardVo(1, "제목1", "2022-10-10 12:12:12", "홍길동", null));
        reqList.add(new BoardVo(2, "제목2", "2022-10-12 13:13:13", "신사임당", "3/abc.jpg"));

        given(service.selBoard(param)).willReturn(reqList);

        mvc.perform(get("/board"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].iboard").exists())
                .andExpect(jsonPath("$[*].title").exists())
                .andExpect(jsonPath("$[*].createdAt").exists())
                .andExpect(jsonPath("$[*].writer").exists())
                .andExpect(jsonPath("$[*].writerMainPic").exists())

                .andExpect(jsonPath("$[*].iboard").isNotEmpty())
                .andExpect(jsonPath("$[*].title").isNotEmpty())
                .andExpect(jsonPath("$[*].createdAt").isNotEmpty())
                .andExpect(jsonPath("$[*].writer").isNotEmpty())
                .andDo(print());
    }


    @Test
    void getBoardMaxPage() throws Exception {
        int row = 30;
        int maxPage = 9;
        given(service.selBoardMaxPage(row)).willReturn(maxPage);

        mvc.perform(get("/board/maxpage?row=" + row))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(maxPage)))
                .andDo(print());

        verify(service).selBoardMaxPage(anyInt());
    }

    @Test
    void getBoardDetail() throws Exception {
        BoardSelDto dto = new BoardSelDto();
        dto.setIboard(1);

        List<CmtVo> cmtList = new ArrayList<>();
        cmtList.add(new CmtVo(1, 2, 3, "댓글내용1", "2023-06-08 16:00:00", "신사임당", "/3/djdkfk.jpg"));
        cmtList.add(new CmtVo(2, 2, 3, "댓글내용2", "2023-06-08 16:01:00", "신사임당", "/3/djdkfk.jpg"));

        BoardDetailCmtVo2 reqVo = BoardDetailCmtVo2.builder()
                .board(new BoardDetailVo(1, "제목", "내용", "2023-06-08 15:02:02", "홍길동", null))
                .cmt(CmtRes.builder().maxPage(8).row(30).isMore(1).list(cmtList).build())
                .build();

        given(service.selBoardDetail(dto)).willReturn(reqVo);

        mvc.perform(get("/board/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.board").exists())
                .andExpect(jsonPath("$.board.iboard").value(1))
                .andExpect(jsonPath("$.board.title").value("제목"))
                .andExpect(jsonPath("$.cmt").exists())
                .andExpect(jsonPath("$.cmt.list", hasSize(2)))
                .andDo(print());

        verify(service).selBoardDetail(any());
    }

    @Test
    void putBoard() throws Exception {
        BoardUpdDto dto = new BoardUpdDto();
        dto.setIboard(1);
        dto.setIuser(2);
        dto.setTitle("변경 제목");
        dto.setCtnt("변경 내용");

        int result = 1;

        given(service.updBoard(dto)).willReturn(result);

        BoardUpdDto param = new BoardUpdDto();
        param.setIboard(1);
        param.setIuser(2);
        param.setTitle("변경 제목");
        param.setCtnt("변경 내용");

        Gson gson = new Gson();
        String content = gson.toJson(param);

        mvc.perform(put("/board")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());

        verify(service).updBoard(any());
    }

    @Test
    void delBoard() throws Exception {
        BoardDelDto dto = new BoardDelDto();
        dto.setIboard(1);
        dto.setIuser(2);
        int result = 1;
        given(service.delBoard(dto)).willReturn(result);

        BoardDelDto param = new BoardDelDto();
        param.setIboard(1);
        param.setIuser(2);

        Gson gson = new Gson();
        String content = gson.toJson(param);
        mvc.perform(delete("/board")
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(result)))
                .andDo(print());

    }
}