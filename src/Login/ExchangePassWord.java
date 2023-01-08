/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

/**
 *
 * @author PC ACER
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
/**
 *
 * @author CONG NGHE NT
 */
public class ExchangePassWord {
    private static String account;
    private static String password;
    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        ExchangePassWord.account = account;
    }
    public static void setPass(String password) {
        ExchangePassWord.password = password;
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Đổi mật khẩu");
        JLabel lbl1 = new JLabel("Đổi mật khẩu");
        JLabel lbl2 = new JLabel("Thuận tiện nhanh chóng và dễ dàng");
        JLabel lbl3 = new JLabel("Nhập mật khẩu cũ :");
        JLabel lbl4 = new JLabel("Nhập mật khẩu mới :");
        JLabel lbl5 = new JLabel("Nhập lại mật khẩu mới :");
        JLabel lbl7 = new JLabel("");
        
        lbl1.setBounds(20,20,700,60);
        lbl1.setForeground(Color.BLUE);
        lbl1.setFont(new Font("Calibri",Font.BOLD,46));
        lbl2.setBounds(20, 60, 700, 60);
        lbl2.setFont(new Font("serif",Font.PLAIN,28));
        lbl3.setBounds(80, 110, 700, 60);
        lbl3.setFont(new Font("serif",Font.PLAIN,28));
        lbl4.setBounds(80, 220, 700, 60);
        lbl4.setFont(new Font("serif",Font.PLAIN,28));
        lbl5.setBounds(80, 330, 700, 60);
        lbl5.setFont(new Font("serif",Font.PLAIN,28));
        lbl7.setBounds(80, 500, 700, 60);
        lbl7.setFont(new Font("serif",Font.PLAIN,30));
        
        JTextField jtf1 = new JTextField("");
        JTextField jtf2 = new JTextField("");
        JTextField jtf3 = new JTextField("");
        
        jtf1.setBounds(80, 160, 350, 50);
        jtf1.setFont(new Font("serif",Font.PLAIN,28));
        jtf2.setBounds(80, 270, 350, 50);
        jtf2.setFont(new Font("serif",Font.PLAIN,28));
        jtf3.setBounds(80, 380, 350, 50);
        jtf3.setFont(new Font("serif",Font.PLAIN,28));
        
        JButton btn = new JButton ("Exchange");
        btn.setBackground(Color.CYAN);
        btn.setBounds(120, 450, 250, 50);
        btn.setFont(new Font("Comic Sans MS", Font.BOLD,34));
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    final String username1 = "root";
                    final String password1 = "";
                    final String url = "jdbc:mysql://localhost:3306/quanlysinhvien";
                    Connection con = DriverManager.getConnection(url, username1, password1);                    
                    try{
                        String sql1 = "UPDATE account SET pass = ? WHERE acc = ?";
                        PreparedStatement stm1 = con.prepareStatement(sql1);  
                        if(!jtf1.getText().equals(password)){
                            lbl7.setText("Please try again !");
                            return;
                        }else{
                            stm1.setString(1, jtf2.getText());
                            stm1.setString(2, account);
                            stm1.execute();
                            lbl7.setText("Successful Exchange !");
                        }
                        
                    }
                    catch(NumberFormatException | SQLException e){
                        e.printStackTrace();
                        lbl7.setText("Please try again !");
                    }                        
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        });
        frame.setSize(590,660);
        frame.setLocationRelativeTo(frame);
        frame.setLayout(null);
        frame.add(lbl1);frame.add(lbl2);frame.add(lbl3);frame.add(lbl4);frame.add(lbl5);frame.add(lbl7);
        frame.add(jtf1);frame.add(jtf2);frame.add(jtf3);
        frame.add(btn);
        frame.setVisible(true);
    }
}
