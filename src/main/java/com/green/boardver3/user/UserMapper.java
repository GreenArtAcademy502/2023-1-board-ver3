package com.green.boardver3.user;

import com.green.boardver3.user.model.UserInsDto;
import com.green.boardver3.user.model.UserLoginDto;
import com.green.boardver3.user.model.UserLoginVo;
import com.green.boardver3.user.model.UserPatchPwDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserInsDto dto);
    UserLoginVo selUserByUid(UserLoginDto dto);
    int updUserPw(UserPatchPwDto dto);
}
