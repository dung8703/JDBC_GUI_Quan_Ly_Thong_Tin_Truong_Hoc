/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import GiaoDienQuanLy.GUIThongTinSinhVien;
import GiaoDienQuanLy.MENU;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

/**
 *
 * @author CONG NGHE NT
 */
public class Login {
//    GUI
    public static void main(String[] args) {

        JFrame frame = new JFrame("PHẦM MỀM QUẢN LÝ THÔNG TIN CỦA TRƯỜNG ĐH CÔNG NGHIỆP");
        JButton btn1 = new JButton("Log In");
        JButton btn2 = new JButton("Forgot password");
        JButton btn3 = new JButton("Register");
        btn1.setBounds(1300, 500, 140, 60);
        btn1.setFont(new Font("Serif", Font.BOLD,25));
        btn1.setBackground(Color.CYAN);
        
        btn2.setBounds(1450, 500, 250, 60);
        btn2.setFont(new Font("Serif", Font.BOLD,25));
        btn2.setBackground(Color.CYAN);
        btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                ForgotPassWord.main(args);
            }
        });
        btn3.setBounds(1300, 600, 400, 60);
        btn3.setFont(new Font("Serif", Font.BOLD,25));
        btn3.setBackground(Color.gray);
        btn3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Register.main(args);
            }
        });
        
        JLabel lbl1 = new JLabel("Login ứng dụng quản lý");
        JLabel lbl2 = new JLabel("Thời điểm tốt nhất để trồng cây là hai mươi năm trước");
        JLabel lbl3 = new JLabel("Thời điểm thứ hai là ngay bây giờ");
        JLabel lbl4 = new JLabel("UserName: ");
        JLabel lbl5 = new JLabel("PassWord: ");
        JLabel lbl6 = new JLabel("Quản lý");
        JLabel lbl7 = new JLabel("");
        
        lbl1.setBounds(200, 300, 850, 100);
        lbl1.setForeground(Color.blue);
        lbl1.setFont(new Font("Serif", Font.PLAIN, 80));
        lbl2.setBounds(220, 470, 950, 60);
        lbl2.setFont(new Font("Calibri", Font.BOLD,40));
        lbl3.setBounds(220, 520, 800, 60);
        lbl3.setFont(new Font("Calibi", Font.BOLD,40));
        lbl4.setBounds(1150, 300, 400, 60);
        lbl4.setFont(new Font("Serif", Font.BOLD,25));
        lbl5.setBounds(1150, 400, 400, 60);
        lbl5.setFont(new Font("Serif", Font.BOLD,25));
        lbl6.setBounds(1150, 180, 750, 100);
        lbl6.setForeground(Color.blue);
        lbl6.setFont(new Font("Serif", Font.PLAIN, 80));
        lbl7.setFont(new Font("Serif", Font.BOLD,25));
        lbl7.setBackground(Color.gray);
        
        JTextField jtf1 = new JTextField("");
        JPasswordField password = new JPasswordField("");
        
        jtf1.setBounds(1300, 300, 400, 60);
        jtf1.setFont(new Font("Serif", Font.BOLD,25));
        password.setBounds(1300, 400, 400, 60);
        password.setFont(new Font("Serif", Font.BOLD,25));
        btn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    final String username1 = "root";
                    final String password1 = "";
                    final String url = "jdbc:mysql://localhost:3306/quanlysinhvien";

                    Connection con = DriverManager.getConnection(url, username1, password1);
                    String user = jtf1.getText();
                    String pass = String.valueOf(password.getPassword());
                    Statement stm = con.createStatement();     
                    String sql = "SELECT * FROM account";                   
                    ResultSet rs = stm.executeQuery(sql);
                    if(user.equals("admin") && pass.equals("admin")){
                        MENU.main(args);
                    }
                    else{
                        int dem = 0;
                        while(rs.next()){
                            if(rs.getString(1).equals(user)&&rs.getString(2).equals(pass)){
                                GUIThongTinSinhVien.setMaSV(rs.getString(3));
                                GUIThongTinSinhVien.main(args);
                                dem++;
                                return;
                            }
                        }    
                        if(dem != 0) {
                            lbl7.setBounds(1350, 650, 400, 60);
                            lbl7.setText("Your account or password incorrect !");
                        }   
                    }
                    con.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        frame.setSize(2000, 2000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(frame);
        frame.add(lbl1);frame.add(lbl2);frame.add(lbl3);frame.add(lbl4);frame.add(lbl5);frame.add(lbl6);frame.add(lbl7);
        frame.add(jtf1);frame.add(password);
        frame.add(btn1);frame.add(btn2);frame.add(btn3);
        frame.setVisible(true);
    }
}