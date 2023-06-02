package com.green.boardver3.user;

import com.green.boardver3.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserInsDto dto);
    UserLoginVo selUserByUid(UserLoginDto dto);
    int updUserPw(UserPatchPwDto dto);
    int updUserPic(UserPatchPicDto dto);
}
