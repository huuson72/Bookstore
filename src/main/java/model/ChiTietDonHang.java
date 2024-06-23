package model;
@lombok.Data
@lombok.AllArgsConstructor
public class ChiTietDonHang {
    private String maChiTietDonHang;
    private DonHang donHang;
    private SanPham sanPham;
    private double soLuong;
    private double giaGoc;
    private double giamGia;
    private	double giaBan;
    private double thueVAT;
    private double tongTien;

    public ChiTietDonHang() {

    }
}
