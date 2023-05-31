package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5EncryptionUtil {
    public static String encryptMD5(String input) {
        try {
            // 创建一个MD5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 将字符串转为字节数组并进行MD5计算
            byte[] messageDigest = md.digest(input.getBytes());
            // 将字节数组转为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密算法不存在");
        }
    }
}

