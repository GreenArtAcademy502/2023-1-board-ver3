package com.green.boardver3.board;

import com.green.boardver3.board.model.BoardInsDto;
import com.green.boardver3.board.model.BoardSelDto;
import com.green.boardver3.board.model.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardInsDto dto);
    List<BoardVo> selBoard(BoardSelDto dto);
    int selBoardRowCount(int row);
}
