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
public class SinhVien {
    private String maSV;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private Lop lop;
    private String hocBong;

    public String getLop() {
        return lop.getMaLop();
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public SinhVien() {
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getHocBong() {
        return hocBong;
    }

    public void setHocBong(String hocBong) {
        this.hocBong = hocBong;
    }
}
