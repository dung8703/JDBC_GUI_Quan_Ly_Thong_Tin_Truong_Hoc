/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao_DieuKhienDaTa.SinhVienDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Lop;
import model.SinhVien;

/**
 *
 * @author PC ACER
 */
public class SinhVien_Service {
    private static Scanner sc = new Scanner(System.in);
    
    public static void addSinhVien(){
        SinhVien sv = new SinhVien();
        SinhVienDao svDao = new SinhVienDao();
        SinhVien_Service sv_Service = new SinhVien_Service();
        sv_Service.inp(sv);
        try{
            svDao.add(sv);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void deleteSinhVien(){
        SinhVienDao svDao = new SinhVienDao();    
        System.out.println("Nhap vao ma sinh vien de delete sinh vien : ");
        String maSV = sc.nextLine();
        try{
            svDao.delete(maSV);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void updateSinhVien(){
        SinhVien sv = new SinhVien();
        SinhVienDao svDao = new SinhVienDao();
        SinhVien_Service sv_Service = new SinhVien_Service();
        sv_Service.inp(sv);
        try{
            svDao.update(sv);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void findAllSinhVien() throws SQLException{
        List<SinhVien> list = new ArrayList();
        SinhVienDao sinhVienDao = new SinhVienDao();
        SinhVien_Service lop_Service = new SinhVien_Service();
        list = sinhVienDao.findAll();
        for(SinhVien sv : list){
            lop_Service.info(sv);
        }
    }
    public static void findSinhVien() throws SQLException{
        System.out.println("Nhap vao ma sinh vien de find sinh vien : ");
        String maSV = sc.nextLine();
        List<SinhVien> list = new ArrayList();
        SinhVienDao sinhVienDao = new SinhVienDao();
        SinhVien_Service lop_Service = new SinhVien_Service();
        list = sinhVienDao.find(maSV);
        for(SinhVien sv : list){
            lop_Service.info(sv);
        }
    }
    public void inp(SinhVien sv){
        System.out.println("Nhap maSV: ");
        sv.setMaSV(sc.nextLine());
        System.out.println("Nhap hoTen: ");
        sv.setHoTen(sc.nextLine());
        System.out.println("Nhap gioiTinh: ");
        sv.setGioiTinh(sc.nextLine());
        System.out.println("Nhap ngaySinh: ");
        sv.setNgaySinh(sc.nextLine());

        //nhap maLop
        Lop lop = new Lop();
        System.out.println("Nhap ma_lop: ");
        lop.setMaLop(sc.nextLine());
        sv.setLop(lop);

        System.out.println("Nhap hocBong: ");
        sv.setHocBong(sc.nextLine());
    }
    public void info(SinhVien sv) {
        System.out.println("MaSV: "+sv.getMaSV() + 
                        " /HoTen: " +sv.getHoTen()+ 
                        " /GioiTinh: " +sv.getGioiTinh()+
                        " /NgaySinh: " +sv.getNgaySinh()+
                        " /MaLop: " +sv.getLop());
    }
}
