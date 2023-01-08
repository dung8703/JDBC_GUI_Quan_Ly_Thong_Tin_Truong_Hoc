/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDienQuanLy;

import dao_DieuKhienDaTa.AbstractDao;
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
import mapper.KetQuaMapper;
import mapper.KhoaMapper;
import mapper.LopMapper;
import mapper.SinhVienMapper;
import model.SinhVien;

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
        a.Query(defaultTableModel,"SELECT * FROM ketqua",new KetQuaMapper());
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                String mssv = txtMSSV.getText();
                String name = txtName.getText();
                String diem = txtDiem.getText();
                // Thêm dữ liệu vào Database
                try {
                    // Set giá trị cho các tham số
                    String sql = "INSERT INTO ketqua VALUES (?,?,?)";
                    PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
                    ps.setString(1, mssv);
                    ps.setString(2, name);
                    ps.setString(3, diem);
                    ps.executeUpdate();
                    // Thực thi câu lệnh
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel,"SELECT * FROM ketqua",new KetQuaMapper());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                String mssv = txtMSSV.getText();
                String name = txtName.getText();
                String diem = txtDiem.getText();
                // Cập nhật dữ liệu vào Database
                try {
                    // Khởi tạo Connection
                    String sql = "UPDATE ketQua SET diemThi = ? WHERE maSV = ? AND maMH = ?";
                    PreparedStatement preparedStatement = AbstractDao.getCon().prepareStatement(sql);// Set giá trị cho các tham số
                    preparedStatement.setString(1, diem);
                    preparedStatement.setString(2,mssv);
                    preparedStatement.setString(3,name);
                    // Thực thi câu lệnh
                    preparedStatement.execute();
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel,"SELECT * FROM ketqua",new KetQuaMapper());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                String mssv = txtMSSV.getText();
                String maMH = txtName.getText();
                // Xóa dữ liệu trong Database
                try {
                    String sql = "DELETE FROM ketqua WHERE maSV = ? AND maMH = ?";
                    PreparedStatement preparedStatement = AbstractDao.getCon().prepareStatement(sql);// Set giá trị cho các tham số
                    // Set giá trị cho các tham số
                    preparedStatement.setString(1, mssv);
                    preparedStatement.setString(2, maMH);
                    // Thực thi câu lệnh
                    preparedStatement.execute();
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel,"SELECT * FROM ketQua",new KetQuaMapper());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                String mssv = txtMSSV.getText();
                String maMH = txtName.getText();
                // Xóa dữ liệu trong Database
                String sql = "SELECT * FROM ketqua WHERE maSV = ? AND maMH = ?";
                // Xóa dữ liệu trong JTable
                defaultTableModel.setRowCount(0);
                // Lấy dữ liệu từ Database vào JTable
                a.Query(defaultTableModel, sql, new KetQuaMapper(), mssv, maMH);
                // Thông báo
                JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");
            }
        });
        jFrame.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            GUIKetQua a = new GUIKetQua();
        } catch (SQLException ex) {
            Logger.getLogger(GUIKetQua.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
