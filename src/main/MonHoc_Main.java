/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao_DieuKhienDaTa.MonHocDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.MonHoc;
import service.MonHoc_Service;

/**
 *
 * @author PC ACER
 */
public class MonHoc_Main {
    
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
                                MonHoc_Service.addMonHoc();
                                break;
                        }
                        case 2: {
                                MonHoc_Service.deleteMonHoc();
                                break;
                        }
                        case 3: {
                                MonHoc_Service.updateMonHoc();
                                break;
                        }
                        case 4: {
                                MonHoc_Service.findAllMonHoc();
                                break;
                        }
                        case 5: {
                                MonHoc_Service.findMonHoc();
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
