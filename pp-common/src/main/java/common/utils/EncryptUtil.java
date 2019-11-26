package common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class EncryptUtil {
    /**
     * MD5加密
     *
     * @param str 加密字符串
     * @return 加密后的字符串
     */
    public static String getMD5String(String str) {
        try {
            StringBuilder sb = new StringBuilder();

            MessageDigest digest = MessageDigest.getInstance("MD5");

            byte[] array = digest.digest(str.getBytes(StandardCharsets.UTF_8));

            for (byte b : array) {
                sb.append(Integer.toHexString(b & 0xFF).toUpperCase());
            }

            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}