package com.green.boardver3.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "회원가입")
public class UserInsDto {
    @Schema(description = "[30] 회원 아이디")
    private String uid;

    @Schema(description = "[100] 회원 비밀번호")
    private String upw;
    private String nm;
    private char gender;
    private String addr;
}
