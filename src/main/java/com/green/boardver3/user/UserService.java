package com.green.boardver3.user;

import com.green.boardver3.user.model.UserInsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper mapper;

    @Autowired
    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public int insUser(UserInsDto dto) {
        //비밀번호 암호화
        return mapper.insUser(dto);
    }
}
