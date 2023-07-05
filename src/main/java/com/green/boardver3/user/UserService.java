package com.green.boardver3.user;

import com.green.boardver3.user.model.*;
import com.green.boardver3.utils.CommonUtils;
import com.green.boardver3.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service

public class UserService {

    private final UserMapper mapper;
    private final CommonUtils commonUtils;
    private final String FILE_DIR;

    @Autowired
    public UserService(UserMapper mapper, CommonUtils commonUtils, @Value("${file.dir}") String fileDir) {
        this.mapper = mapper;
        this.commonUtils = commonUtils;
        this.FILE_DIR = fileDir;
    }

    public int insUser(UserInsDto dto) {

        //성별 항상 대문자 변경
        char gender = Character.toUpperCase(dto.getGender());
        if(!(gender == 'M' || gender == 'F')) {
            return -1;
        }
        dto.setGender(gender);

        //비밀번호 암호화
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
        dto.setUpw(hashedPw);
        try {
            return mapper.insUser(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int login(UserLoginDto dto) {
        UserLoginVo vo = mapper.selUserByUid(dto);
        if(vo == null) { return 2; }
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
        if(vo.getUpw().equals(hashedPw)) {
            return 1;
        }
        return 3;
    }

    public int updUserPw(UserPatchPwDto dto) {
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
        dto.setUpw(hashedPw);
        return mapper.updUserPw(dto);
    }

    public int updUserPic(MultipartFile pic, UserPatchPicDto dto) {
        // user/pk/uuid.jpg
        // user/1/abcd.jpg
        String centerPath = String.format("user/%d", dto.getIuser());
        String dicPath = String.format("%s/%s", FILE_DIR, centerPath);

        File dic = new File(dicPath);
        if(!dic.exists()) {
            dic.mkdirs();
        }

        String originFileName = pic.getOriginalFilename();
        String savedFileName = FileUtils.makeRandomFileNm(originFileName);
        String savedFilePath = String.format("%s/%s", centerPath, savedFileName);
        String targetPath = String.format("%s/%s", FILE_DIR, savedFilePath);
        File target = new File(targetPath);
        try {
            pic.transferTo(target);
        }catch (Exception e) {
            return 0;
        }
        dto.setMainPic(savedFilePath);
        try {
            int result = mapper.updUserPic(dto);
            if(result == 0) {
                throw new Exception("프로필 사진을 등록할 수 없습니다.");
            }
        } catch (Exception e) {
            //파일 삭제
            target.delete();
            return 0;
        }
        return 1;
    }
}
