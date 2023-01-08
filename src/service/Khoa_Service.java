/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao_DieuKhienDaTa.KhoaDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Khoa;

/**
 *
 * @author PC ACER
 */
public class Khoa_Service {
    private static Scanner sc = new Scanner(System.in);

    
    public static void addKhoa(){
        Khoa khoa = new Khoa();
        KhoaDao khoaDao = new KhoaDao();
        Khoa_Service khoa_Service = new Khoa_Service();
        khoa_Service.inp(khoa);
        try{
            khoaDao.add(khoa);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void deleteKhoa(){
        System.out.println("Please input MaKhoa to delete Khoa: ");
        KhoaDao khoaDao = new KhoaDao();
        try{
            khoaDao.delete(sc.nextLine());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateKhoa(){
        Khoa khoa = new Khoa();
        KhoaDao khoaDao = new KhoaDao();
        Khoa_Service khoa_Service = new Khoa_Service();
        khoa_Service.inp(khoa);
        try{
            khoaDao.update(khoa);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void findAllKhoa(){
        List<Khoa> list = new ArrayList();
        KhoaDao khoaDao = new KhoaDao();
        Khoa_Service khoa_Service = new Khoa_Service();
        list = khoaDao.findAll();
        for(Khoa khoa : list){
            khoa_Service.info(khoa);
        }
    }
    public static void findKhoa(){
        System.out.println("Please input MaKhoa to find Khoa: ");
        List<Khoa> list = new ArrayList();
        KhoaDao khoaDao = new KhoaDao();
        Khoa_Service khoa_Service = new Khoa_Service();
        list = khoaDao.find(sc.nextLine());
        for( Khoa khoa : list){
            khoa_Service.info(khoa);
        }
        
    }
    public void inp(Khoa khoa){
        System.out.println("Nhap ma khoa : ");
        khoa.setMaKhoa(sc.nextLine());
        System.out.println("Nhap ten khoa : ");
        khoa.setTenKhoa(sc.nextLine());
    }
    public void info(Khoa khoa){
        System.out.println("MaKhoa: " + khoa.getMaKhoa() + 
                        " /TenKhoa: " + khoa.getTenKhoa());
    }
    
}
