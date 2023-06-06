package util;

import java.util.regex.Pattern;

public class CheckUtil {
    /**
     * 使用正则表达式验证手机号码
     * @param phoneNumber 手机号码
     * @return
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        String pattern = "^(\\+\\d{1,3})?\\s?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
        return Pattern.matches(pattern, phoneNumber);
    }

    /**
     * 使用正则表达式验证身份证号码
     * @param idNumber 身份证号码
     * @return
     */
    public static boolean validateIDNumber(String idNumber) {
        String pattern = "^\\d{17}(\\d|x|X)$";
        return Pattern.matches(pattern, idNumber);
    }
}
