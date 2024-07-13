package util;

import java.security.MessageDigest;
import java.util.Base64;

public class maHoaMatKhau {
    public static String toSHA1(String str) {
        String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn"; // Thêm một salt để làm phức tạp mật khẩu
        String result = null;

        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.getEncoder().encodeToString(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(toSHA1("123456"));
    }
}
