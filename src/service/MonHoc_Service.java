/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao_DieuKhienDaTa.MonHocDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.MonHoc;

/**
 *
 * @author PC ACER
 */
public class MonHoc_Service {
    private static Scanner sc = new Scanner(System.in);
    
    public static void addMonHoc(){
        MonHoc mh = new MonHoc();
        MonHocDao mhDao = new MonHocDao();
        MonHoc_Service mh_Service = new MonHoc_Service();
        mh_Service.inp(mh);
        try{
            mhDao.add(mh);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void deleteMonHoc(){
        System.out.println("Please input MaMonHoc to delete MonHoc: ");
        MonHocDao mhDao = new MonHocDao();
        String maMH = sc.nextLine();
        try{
            mhDao.delete(maMH);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateMonHoc(){
        MonHoc mh = new MonHoc();
        MonHocDao mhDao = new MonHocDao();
        MonHoc_Service mh_Service = new MonHoc_Service();
        mh_Service.inp(mh);
        try{
            mhDao.update(mh);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void findAllMonHoc(){
        List<MonHoc> list = new ArrayList();
        MonHocDao lopDao = new MonHocDao();
        MonHoc_Service lop_Service = new MonHoc_Service();
        list = lopDao.findAll();
        for(MonHoc mh : list){
            lop_Service.info(mh);
        }
    }
    public static void findMonHoc(){
        System.out.println("Please input MaMonHoc to find MonHoc: ");
        String maMH = sc.nextLine();
        List<MonHoc> list = new ArrayList();
        MonHocDao lopDao = new MonHocDao();
        MonHoc_Service lop_Service = new MonHoc_Service();
        list = lopDao.find(maMH);
        for(MonHoc mh : list){
            lop_Service.info(mh);
        }
    }
    public void inp(MonHoc monHoc){
        System.out.println("Nhap ma mon hoc : ");
        monHoc.setMaMH(sc.nextLine());
        System.out.println("Nhap ten mon hoc : ");
        monHoc.setTenMH(sc.nextLine());
        System.out.println("Nhap so tin hoc : ");
        monHoc.setSoTiet(sc.nextInt());
    }
    
    public void info(MonHoc monHoc) {
        System.out.println("MaMH: "+monHoc.getMaMH() + 
                        " /TenMH: " +monHoc.getTenMH()+ 
                        " /SoTiet: " +monHoc.getSoTiet());
	}
}
