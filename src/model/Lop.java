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
