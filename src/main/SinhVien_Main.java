/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao_DieuKhienDaTa.SinhVienDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.SinhVien;
import service.SinhVien_Service;

/**
 *
 * @author PC ACER
 */
public class SinhVien_Main {
    public static void main(String[] args) {
        boolean check = true;
        while(check) {
            System.out.println("__MENU__");
            System.out.println("0. Exit");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Update");
            System.out.println("4. FindAll");
            System.out.println("5. Find");
            System.out.println("Please select!");
            try {
                int c = new Scanner(System.in).nextInt();
                switch (c) {
                        case 0: {
                                check = false;
                                break;
                        }
                        case 1: {
                                SinhVien_Service.addSinhVien();
                                break;
                        }
                        case 2: {
                                SinhVien_Service.deleteSinhVien();
                                break;
                        }
                        case 3: {
                                SinhVien_Service.updateSinhVien();
                                break;
                        }
                        case 4: {
                                SinhVien_Service.findAllSinhVien();
                                break;
                        }
                        case 5: {
                                SinhVien_Service.findSinhVien();
                                break;
                        }
                        default:
                                System.out.println("Please select correct number !!!");
                    }
            } 
            catch (Exception e) {
                    System.out.println("Please select number !!!");
            }
        }
    }
}
