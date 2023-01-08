/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao_DieuKhienDaTa.LopDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Khoa;
import model.Lop;

/**
 *
 * @author PC ACER
 */
public class Lop_Service {
    private static Scanner sc = new Scanner(System.in);
    
    public static void addLop(){
        Lop lop = new Lop();
        LopDao lopDao = new LopDao();
        Lop_Service lop_Service = new Lop_Service();
        lop_Service.inp(lop);
        try{
            lopDao.add(lop);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void deleteLop(){
        System.out.println("Please input MaLop to delete Lop: ");
        String maLop = sc.nextLine();
        LopDao lopDao = new LopDao();
        try{
            lopDao.delete(maLop);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void updateLop(){
        Lop lop = new Lop();
        LopDao lopDao = new LopDao();
        Lop_Service lop_Service = new Lop_Service();
        lop_Service.inp(lop);
        try{
            lopDao.update(lop);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void findAllLop(){
        List<Lop> list = new ArrayList();
        LopDao lopDao = new LopDao();
        Lop_Service lop_Service = new Lop_Service();
        list = lopDao.findAll();
        for(Lop lop : list){
            lop_Service.info(lop);
        }
    }
    
    public static void findLop(){
        System.out.println("Please input MaLop to find Lop: ");
        String maLop = sc.nextLine();
        List<Lop> list = new ArrayList();
        LopDao lopDao = new LopDao();
        Lop_Service lop_Service = new Lop_Service();
        list = lopDao.find(maLop);
        for(Lop lop : list){
            lop_Service.info(lop);
        }
    }
    public void inp(Lop lop){
        System.out.println("Nhap ma lop : ");
        lop.setMaLop(sc.nextLine());
        System.out.println("Nhap ten lop : ");
        lop.setTenLop(sc.nextLine());
        
        //nhap Khoa
        Khoa khoa = new Khoa();
        System.out.println("Nhap ma_khoa: ");
        khoa.setMaKhoa(sc.nextLine());
        System.out.println("Nhap ten_khoa: ");
        khoa.setTenKhoa(sc.nextLine());
        lop.setKhoa(khoa);
    }
    
    public void info(Lop lop) {
        System.out.println("Ma_Lop: "+lop.getMaLop() + 
                        " /Ten_Lop: " +lop.getTenLop()+ 
                        " /Ma_khoa: " +lop.getKhoa());
	}
}
