package model;
@lombok.Data
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.ToString


public class TheLoai {
    private String maTheLoai;
    private String tenTheLoai;


    public TheLoai() {

    }

    public TheLoai(String maTheLoai) {
    }
}
