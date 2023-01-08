/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao_DieuKhienDaTa.KetQuaDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.KetQua;
import model.MonHoc;
import model.SinhVien;

/**
 *
 * @author PC ACER
 */
public class KetQua_Service {
    private static Scanner sc = new Scanner(System.in);
    
    public static void addKetQua(){
        KetQua kq = new KetQua();
        KetQuaDao kqDao = new KetQuaDao();
        KetQua_Service kq_Service = new KetQua_Service();
        kq_Service.inp(kq);
        try{
            kqDao.add(kq);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void deleteKetQua(){
        System.out.println("Please input MaSV va MaMH to delete Ket qua: ");
        KetQuaDao kqDao = new KetQuaDao();
        try{
            kqDao.delete(sc.nextLine(),sc.nextLine());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void updateKetQua(){
        KetQua kq = new KetQua();
        KetQuaDao kqDao = new KetQuaDao();
        KetQua_Service kq_Service = new KetQua_Service();
        kq_Service.inp(kq);
        try{
            kqDao.update(kq);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void findAllKetQua(){
        List<KetQua> list = new ArrayList();
        KetQuaDao kqDao = new KetQuaDao();
        KetQua_Service kq_Service = new KetQua_Service();
        list = kqDao.findAll();
        for( KetQua kq : list){
            kq_Service.info(kq);
        }
    }
    public static void findKetQua(){
        System.out.println("Nhap vao ma sinh vien");
        String msv = sc.nextLine();
        System.out.println("Nhap vao ma mon hoc");
        String mmh = sc.nextLine();
        List<KetQua> list = new ArrayList();
        KetQuaDao ketQuaDao = new KetQuaDao();
        KetQua_Service ketQua_Service = new KetQua_Service();
        list = ketQuaDao.find(msv,mmh);
        for( KetQua ketQua : list){
            ketQua_Service.info(ketQua);
        }
    }
    public void inp(KetQua kq){
        SinhVien sv = new SinhVien();
        System.out.println("Nhap ma sinh vien: ");
        sv.setMaSV(new Scanner(System.in).nextLine());
        kq.setSinhVien(sv);

        MonHoc mh = new MonHoc();
        System.out.println("Nhap ma mon hoc: ");
        mh.setMaMH(new Scanner(System.in).nextLine());
        kq.setMonHoc(mh);

        System.out.println("Nhap diem Thi: ");
        kq.setDiemThi(new Scanner(System.in).nextFloat());
    }
    
    public void info(KetQua kq) {
		System.out.println("MaSV: " +kq.getSinhVien()+ 
				" /MaMH: " +kq.getMonHoc()+ 
				" /DiemThi: " +kq.getDiemThi());
    }
}
