package app.jjickda.global.utils;

import app.jjickda.global.config.exception.CustomException;
import app.jjickda.global.config.exception.ErrorCode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@UtilityClass
public class SHA256Util {

    public static String encrypt(String str) {
        String sha;

        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte[] byteData = sh.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            sha = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            // 암호 알고리즘이 요구되었음에도 불구하고, 현재의 환경에서는 사용 가능하지 않은 경우에 예외를 발생시킵니다.

            log.error("SHA256 암호화에 사용된 값 : {}", str);
            throw new CustomException("암호화 문제발생(SHA256)", ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return sha;
    }
}