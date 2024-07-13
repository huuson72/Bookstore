package util;



import java.util.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class VnPayUtils {
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return formatter.format(new Date());
    }

    public static String getRandomNumber(int length) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public static String hashAllFields(Map<String, String> fields, String secretKey) {
        // Sắp xếp các trường theo thứ tự bảng chữ cái
        SortedMap<String, String> sortedFields = new TreeMap<>(fields);
        StringBuilder hashData = new StringBuilder();
        Set<String> keys = sortedFields.keySet();
        for (String key : keys) {
            hashData.append(key).append('=').append(sortedFields.get(key)).append('&');
        }
        hashData.deleteCharAt(hashData.length() - 1); // Bỏ ký tự '&' cuối cùng
        return hmacSHA512(secretKey, hashData.toString());
    }

    public static String buildPaymentUrl(String vnp_Url, Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            query.append(key).append('=').append(params.get(key)).append('&');
        }
        query.deleteCharAt(query.length() - 1); // Bỏ ký tự '&' cuối cùng
        return vnp_Url + "?" + query.toString();
    }

    public static boolean validateChecksum(Map<String, String> fields, String secretKey, String checksum) {
        String hashData = hashAllFields(fields, secretKey);
        return hashData.equals(checksum);
    }

    private static String hmacSHA512(String key, String data) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA512");
            javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA512");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(data.getBytes("UTF-8"));
            StringBuilder hashString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hashString.append('0');
                hashString.append(hex);
            }
            return hashString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo HMAC SHA-512", e);
        }
    }
}
