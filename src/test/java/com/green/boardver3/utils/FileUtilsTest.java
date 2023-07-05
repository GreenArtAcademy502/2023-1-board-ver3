package com.green.boardver3.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    @DisplayName("FileUtils : 확장자 리턴")
    void getExt() {
        final String ext = "jpg";
        final String fileNm = "abcd.efg.hijk." + ext;
        String result = FileUtils.getExt(fileNm);
        assertEquals(ext, result);

        final String ext2 = "png";
        final String fileNm2 = "abcdefghji." + ext2;
        String result2 = FileUtils.getExt(fileNm2);
        assertEquals(ext2, result2);
    }

    @Test
    @DisplayName("FileUtils : 파일명 리턴")
    void getFileNm() {
        final String fileNm = "abcd.efg.hijk";
        final String fileNmExt = fileNm + ".jpg";
        String result = FileUtils.getFileNm(fileNmExt);
        assertEquals(fileNm, result);
    }
}