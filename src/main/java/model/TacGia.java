package model;

import java.sql.Date;
@lombok.Data
@lombok.AllArgsConstructor

public class TacGia {
	private String 	maTacGia;
	private String 	hoVaTen;
	private Date	ngaySinh;
	private String	tieuSu;

}
