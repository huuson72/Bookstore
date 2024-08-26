package util;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VNPayUtils {

    public static String hashAllFields(Map<String, String> fields, String secretKey) {
        // Tạo chuỗi dữ liệu
        String hashData = fields.entrySet().stream()
                .filter(e -> e.getValue() != null && !e.getValue().isEmpty())
                .map(Map.Entry::getValue)
                .collect(Collectors.joining("&")) + secretKey;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(hashData.getBytes(StandardCharsets.UTF_8));

            // Khai báo biến hexString
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String hmacSHA512(String key, String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKey);
            byte[] hashBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Sử dụng StringBuilder để tạo chuỗi kết quả
            StringBuilder hashString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hashString.append('0'); // Thêm số 0 nếu độ dài chuỗi hex chỉ có 1
                }
                hashString.append(hex);
            }
            return hashString.toString();
        } catch (Exception e) {
            return "";
        }
    }


    public static String buildQueryUrl(Map<String, String> params, String baseUrl) {
        String query = params.entrySet().stream()
                .map(p -> p.getKey() + "=" + p.getValue())
                .collect(Collectors.joining("&"));
        return baseUrl + "?" + query;
    }

    public static boolean validateSignature(Map<String, String> vnp_Params, String vnp_SecureHash, String vnp_HashSecret) {
        // Loại bỏ tham số vnp_SecureHash ra khỏi danh sách vnp_Params
        vnp_Params.remove("vnp_SecureHash");

        // Sắp xếp các tham số theo thứ tự bảng chữ cái
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        // Tạo chuỗi dữ liệu để tính toán chữ ký
        StringBuilder hashData = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                // Append fieldName=fieldValue& vào hashData
                hashData.append(fieldName).append('=').append(fieldValue).append('&');
            }
        }

        // Loại bỏ dấu & cuối cùng
        if (hashData.length() > 0) {
            hashData.setLength(hashData.length() - 1);
        }

        // Tạo chữ ký từ hashData và vnp_HashSecret
        String secureHash = hmacSHA512(vnp_HashSecret, hashData.toString());

        // So sánh chữ ký tạo ra với chữ ký trả về từ VNPay
        return secureHash.equals(vnp_SecureHash);
    }

}
