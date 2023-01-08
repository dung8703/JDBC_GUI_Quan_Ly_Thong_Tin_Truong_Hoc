/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao_DieuKhienDaTa.LopDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Lop;
import service.Lop_Service;

/**
 *
 * @author PC ACER
 */
public class Lop_Main {
    public static Scanner sc = new Scanner(System.in);
    
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
                                    Lop_Service.addLop();
                                    break;
                            }
                            case 2: {
                                    Lop_Service.deleteLop();
                                    break;
                            }
                            case 3: {
                                    Lop_Service.updateLop();
                                    break;
                            }
                            case 4: {
                                    Lop_Service.findAllLop();
                                    break;
                            }
                            case 5: {
                                    Lop_Service.findLop();
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
