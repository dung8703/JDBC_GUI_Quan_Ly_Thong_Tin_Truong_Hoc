package GiaoDienQuanLy;

import dao_DaTa.AbstractDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MENU{
    AbstractDao a = new AbstractDao() {};
    // Khai báo các thuộc tính
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtAddress, txtPhone, txtMaLop;
    JButton btnAdd, btnUpdate, btnDelete, btnFind, btnFind1;

    // Khởi tạo constructor
    public MENU() throws SQLException {   
        jFrame = new JFrame();
        jFrame.setSize(550, 400);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setTitle("MENU quản lý");

        JLabel lblMSSV = new JLabel("Quản lý sinh viên: ");
        lblMSSV.setBounds(50, 40, 250, 35);
        lblMSSV.setFont(new Font("Serif", Font.BOLD,25));
        jFrame.add(lblMSSV);

        JLabel lblName = new JLabel("Quản lý lớp: ");
        lblName.setBounds(50, 100, 250, 35);
        lblName.setFont(new Font("Serif", Font.BOLD,25));
        jFrame.add(lblName);

        JLabel lblAddress = new JLabel("Quản lý khoa : ");
        lblAddress.setBounds(50, 160, 250, 35);
        lblAddress.setFont(new Font("Serif", Font.BOLD,25));
        jFrame.add(lblAddress);

        JLabel lblPhone = new JLabel("Quản lý môn học: ");
        lblPhone.setBounds(50, 220, 250, 35);
        lblPhone.setFont(new Font("Serif", Font.BOLD,25));
        jFrame.add(lblPhone);
        
        JLabel lblMaLop = new JLabel("Quản lý điểm thi: ");
        lblMaLop.setBounds(50, 280, 250, 35);
        lblMaLop.setFont(new Font("Serif", Font.BOLD,25));
        jFrame.add(lblMaLop);

        btnAdd = new JButton("SINH VIÊN");
        btnAdd.setBounds(280, 40, 180, 40);
        btnAdd.setBackground(Color.GRAY);
        btnAdd.setForeground(Color.white);
        jFrame.add(btnAdd);

        btnUpdate = new JButton("LỚP");
        btnUpdate.setBounds(280, 100, 180, 40);
        btnUpdate.setBackground(Color.GRAY);
        btnUpdate.setForeground(Color.white);
        jFrame.add(btnUpdate);

        btnDelete = new JButton("KHOA");
        btnDelete.setBounds(280, 160, 180, 40);
        btnDelete.setBackground(Color.GRAY);
        btnDelete.setForeground(Color.white);
        jFrame.add(btnDelete);
        
        btnFind = new JButton("MÔN HỌC");
        btnFind.setBounds(280, 220, 180, 40);
        btnFind.setBackground(Color.GRAY);
        btnFind.setForeground(Color.white);
        jFrame.add(btnFind);
        
        btnFind1 = new JButton("KẾT QUẢ");
        btnFind1.setBounds(280, 280, 180, 40);
        btnFind1.setBackground(Color.GRAY);
        btnFind1.setForeground(Color.white);
        jFrame.add(btnFind1);
        
        btnAdd.addActionListener(new ActionListener() {
            private String[] args;
            @Override
            public void actionPerformed(ActionEvent e) {
                GUISinhVien.main(args);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            private String[] args;
            @Override
            public void actionPerformed(ActionEvent e) {
                GUILopHoc.main(args);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            private String[] args;
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIKhoa.main(args);
            }
        });
        btnFind.addActionListener(new ActionListener() {
            private String[] args;
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIMonHoc.main(args);
            }
        });
        btnFind1.addActionListener(new ActionListener() {
            private String[] args;
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIKetQua.main(args);
            }
        });
        jFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        try {
            new MENU();
        } catch (SQLException ex) {
            Logger.getLogger(MENU.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
