/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDienQuanLy;

import dao_DaTa.AbstractDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import service.KetQua_Service;

public class GUIKetQua{
    AbstractDao a = new AbstractDao() {};
    // Khai báo các thuộc tính
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtDiem;
    JButton btnAdd, btnUpdate, btnDelete, btnFind;

    // Khởi tạo constructor
    public GUIKetQua() throws SQLException {   
        jFrame = new JFrame();
        jFrame.setSize(750, 500);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setTitle("Quản lý điểm thi");

        JLabel lblMSSV = new JLabel("Mã sinh viên: ");
        lblMSSV.setBounds(20, 30, 150, 40);
        jFrame.add(lblMSSV);

        txtMSSV = new JTextField();
        txtMSSV.setBounds(100, 30, 180, 40);
        jFrame.add(txtMSSV);

        JLabel lblName = new JLabel("Mã môn học: ");
        lblName.setBounds(20, 100, 150, 40);
        jFrame.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(100, 100, 180, 40);
        jFrame.add(txtName);
        
        JLabel lblDiem = new JLabel("Điểm thi: ");
        lblDiem.setBounds(20, 170, 150, 40);
        jFrame.add(lblDiem);

        txtDiem = new JTextField();
        txtDiem.setBounds(100, 170, 180, 40);
        jFrame.add(txtDiem);

        btnAdd = new JButton("Thêm");
        btnAdd.setBounds(420, 30, 120, 30);
        jFrame.add(btnAdd);

        btnUpdate = new JButton("Sửa");
        btnUpdate.setBounds(420, 80, 120, 30);
        jFrame.add(btnUpdate);

        btnDelete = new JButton("Xóa");
        btnDelete.setBounds(420, 130, 120, 30);
        jFrame.add(btnDelete);
        
        btnFind = new JButton("Tìm kiếm");
        btnFind.setBounds(420, 180, 120, 30);
        jFrame.add(btnFind);

        // Khởi tạo JTable
        jTable = new JTable();
        // Tạo model cho JTable
        DefaultTableModel defaultTableModel = new DefaultTableModel(
            new Object[][] {},
            new Object[] { "Mã sinh viên", "Mã môn học", "Điểm"}
        );
        // Set model
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(20, 250, 650, 180);
        jFrame.add(jScrollPane);
        KetQua_Service.findAllKetQua(defaultTableModel);
        
        
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KetQua_Service.addKetQua(txtMSSV.getText(), txtName.getText(), Float.valueOf(txtDiem.getText()) , defaultTableModel);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {          
                KetQua_Service.updateKetQua(txtMSSV.getText(), txtName.getText(), Float.valueOf(txtDiem.getText()), defaultTableModel);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KetQua_Service.deleteKetQua(txtMSSV.getText(), txtName.getText(), defaultTableModel);
            }
        });
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KetQua_Service.findKetQua(txtMSSV.getText(), txtName.getText(), defaultTableModel);
            }
        });
        jFrame.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        try {
            GUIKetQua guiKetQua = new GUIKetQua();
        } catch (SQLException ex) {
            Logger.getLogger(GUIKetQua.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
