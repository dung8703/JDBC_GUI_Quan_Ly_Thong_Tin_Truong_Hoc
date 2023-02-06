package model;

public class KetQua {
    private SinhVien sinhVien;//has-A
    private MonHoc monHoc;//has-A
    private Float diemThi;

    public String getSinhVien() {
        return sinhVien.getMaSV();
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public String getMonHoc() {
        return monHoc.getMaMH();
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public Float getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(Float diemThi) {
        this.diemThi = diemThi;
    }
}
