/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao_DieuKhienDaTa.KetQuaDao;
import dao_DieuKhienDaTa.KhoaDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.KetQua;
import service.KetQua_Service;

/**
 *
 * @author PC ACER
 */
public class KetQua_Main {
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
                                KetQua_Service.addKetQua();
                                break;
                        }
                        case 2: {
                                KetQua_Service.deleteKetQua();
                                break;
                        }
                        case 3: {
                                KetQua_Service.updateKetQua();
                                break;
                        }
                        case 4: {
                                KetQua_Service.findAllKetQua();
                                break;
                        }
                        case 5: {
                                KetQua_Service.findKetQua();
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
