/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDienQuanLy;

import dao_DieuKhienDaTa.AbstractDao;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mapper.SinhVienMapper;
import model.SinhVien;

public class MENU{
    AbstractDao a = new AbstractDao() {};
    // Khai báo các thuộc tính
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtAddress, txtPhone, txtMaLop;
    JButton btnAdd, btnUpdate, btnDelete, btnFind, btnFind1;

//    void Query(DefaultTableModel defaultTableModel){
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
//            // Khởi tạo Statement
//            Statement statement = connection.createStatement();
//            // Lấy dữ liệu
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM sinhvien");
//            // Lặp dữ liệu
//            while (resultSet.next()) {
//                // Lấy dữ liệu từng dòng              
//                SinhVienMapper svm = new SinhVienMapper();
//                SinhVien sv = svm.mapRow(resultSet);
//                // Thêm dữ liệu vào model
//                defaultTableModel.addRow(new Object[] { sv.getMaSV(), sv.getHoTen(), sv.getGioiTinh(), sv.getNgaySinh(), sv.getLop() });
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
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
