package model;

import java.sql.Date;
@lombok.Data
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.ToString


public class TacGia {
	private String 	maTacGia;
	private String 	hoVaTen;
	private Date	ngaySinh;
	private String	tieuSu;

	public TacGia() {

	}

	public TacGia(String maTacGia) {
	}

}
