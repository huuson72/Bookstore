package model;

import java.sql.Date;
@lombok.Data
@lombok.AllArgsConstructor
public class DonHang {
	private String maDonHang;
	private KhachHang khachHang;
	private String diaChiMuaHang;
	private String diaChiNhanHang;
	private String trangThai;
	private String hinhThucThanhToan;
	private String trangThaiThanhToan;
	private double soTienDaThanhToan;
	private double soTienConThieu;
	private Date ngayDatHang;
	private Date ngayGiaoHang;


	public DonHang(String maDH, KhachHang khachHang, String diaChiNguoiMua, String diaChiNhanHang, String trangThai, String hinhThucThanhToan, double soTienDaThanhToan, double soTienConThieu, Date ngayDatHang, Date ngayGiaoHang) {
	}
}
