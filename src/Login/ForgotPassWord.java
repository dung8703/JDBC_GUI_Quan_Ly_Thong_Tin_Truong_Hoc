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
public class ForgotPassWord {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Password retrieval");
        JLabel lbl1 = new JLabel("Password retrieval");
        JLabel lbl2 = new JLabel("Convenient, quickly and easily");
        JLabel lbl3 = new JLabel("Enter account :");
        JLabel lbl4 = new JLabel("Enter code :");
        JLabel lbl6 = new JLabel("");
        JLabel lbl7 = new JLabel("");
        JLabel lbl8 = new JLabel("");
        JLabel lbl9 = new JLabel("");
        JLabel lbl10 = new JLabel("");
        
        lbl1.setBounds(20,20,700,60);
        lbl1.setForeground(Color.BLUE);
        lbl1.setFont(new Font("Calibri",Font.BOLD,46));
        lbl2.setBounds(20, 60, 700, 60);
        lbl2.setFont(new Font("serif",Font.PLAIN,28));
        lbl3.setBounds(20, 160, 700, 60);
        lbl3.setFont(new Font("serif",Font.PLAIN,28));
        lbl4.setBounds(20, 260, 700, 60);
        lbl4.setFont(new Font("serif",Font.PLAIN,28));
        lbl6.setBounds(20, 410, 700, 60);
        lbl6.setFont(new Font("serif",Font.PLAIN,28));
        lbl7.setBounds(400, 465, 700, 60);
        lbl7.setFont(new Font("serif",Font.PLAIN,22));
        
        JTextField jtf1 = new JTextField("");
        JTextField jtf2 = new JTextField("");
        JTextField jtf4 = new JTextField("");
        
        jtf1.setBounds(20, 210, 350, 50);
        jtf1.setFont(new Font("serif",Font.PLAIN,28));
        jtf2.setBounds(20, 310, 350, 50);
        jtf2.setFont(new Font("serif",Font.PLAIN,28));
        
        JButton btn = new JButton ("Get password");
        JButton btn1 = new JButton ("YES");
        JButton btn2 = new JButton ("NO");
        btn.setBackground(Color.CYAN);
        btn.setBounds(380, 200, 230, 150);
        btn.setFont(new Font("Comic Sans MS", Font.BOLD,30));
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    final String username1 = "root";
                    final String password1 = "";
                    final String url = "jdbc:mysql://localhost:3306/quanlysinhvien";
                    Connection con = DriverManager.getConnection(url, username1, password1);                                

                    String sql = "SELECT * FROM account WHERE acc = ? AND code = ?";
                    PreparedStatement stm1 = con.prepareStatement(sql);           
                    stm1.setString(1, jtf1.getText());
                    stm1.setString(2, jtf2.getText());
                    ResultSet rs = stm1.executeQuery();
                    while(rs.next()){
                        jtf4.setText(rs.getString(2));
                                               
                    }
                    if(jtf4.getText().length() == 0){
                        lbl9.setBounds(100, 490, 700, 60);
                        lbl9.setFont(new Font("serif",Font.PLAIN,22));
                        lbl9.setText("Your information is incorrect, please enter again!");
                    } 
                    else{
                        lbl6.setText("This is password :");
                        lbl7.setText("Successful !"); 
                        ExchangePassWord.setPass(jtf4.getText());
                        jtf4.setBounds(20, 470, 350, 50);
                        jtf4.setFont(new Font("serif",Font.PLAIN,28));
                        lbl8.setText("Do you want to exchange password ?");
                        lbl8.setBounds(140, 540, 700, 60);
                        lbl8.setFont(new Font("serif",Font.PLAIN,28));
                        btn1.setBackground(Color.CYAN);
                        btn1.setBounds(160, 610, 140, 50);
                        btn1.setFont(new Font("Comic Sans MS", Font.BOLD,34));
                        btn1.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent ae){
                                ExchangePassWord.main(args);
                                ExchangePassWord.setAccount(jtf1.getText());
                            }
                        });
                        btn2.setBackground(Color.CYAN);
                        btn2.setBounds(340, 610, 140, 50);
                        btn2.setFont(new Font("Comic Sans MS", Font.BOLD,34));
                        btn2.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent ae){
                                lbl10.setText("Oke, Thank you !");
                                lbl10.setBounds(240, 660, 700, 60);
                                lbl10.setFont(new Font("serif",Font.PLAIN,28));
                            }
                        });
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        });
        frame.setSize(650,800);
        frame.setLocationRelativeTo(frame);
        frame.setLayout(null);
        frame.add(lbl1);frame.add(lbl2);frame.add(lbl3);frame.add(lbl4);frame.add(lbl6);frame.add(lbl7);frame.add(lbl10);frame.add(lbl8);frame.add(lbl9);
        frame.add(jtf1);frame.add(jtf2);frame.add(jtf4);
        frame.add(btn);frame.add(btn1);frame.add(btn2);
        frame.setVisible(true);
    }
}
