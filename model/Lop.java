package model;

public class Lop {
    private String maLop;
    private String tenLop;
    private Khoa khoa;//has-A

    public Lop() {
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getKhoa() {
        return khoa.getMaKhoa();
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }
    
}
