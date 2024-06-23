package model;
@lombok.Data
@lombok.AllArgsConstructor


public class SanPham {
	private String maSanPham;
	private String tenSanPham;
	private TacGia tacGia;
	private int namXuatBan;
	private double giaNhap;
	private double giaGoc;
	private double giaBan;
	private int soLuong;
	private TheLoai theLoai;;
	private String ngonNgu;
	private String moTa;


}
