package model;

import java.util.Date;

@lombok.Data
@lombok.AllArgsConstructor
public class KhachHang {
    private String maKhacHang;
    private String tenDangNhap;
    private String matKhau;
    private String hoVaTen;
    private String gioiTinh;
    private String diaChi; // xa, huyen, tinh
    private String diaChiNhanHang;
    private String diaChiMuaHang;
    private Date ngaySinh;
    private String soDienThoai;
    private String email;
    private boolean dangKyNhanBangTin;


    public KhachHang() {

    }
}
