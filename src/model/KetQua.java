/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author PC ACER
 */
public class KetQua {
    private SinhVien sinhVien;//has-A
    private MonHoc monHoc;//has-A
    private float diemThi;

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

    public float getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(float diemThi) {
        this.diemThi = diemThi;
    }
}
