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
import mapper.LopMapper;
import mapper.SinhVienMapper;
import model.SinhVien;

public class GUILopHoc{
    AbstractDao a = new AbstractDao() {};
    // Khai báo các thuộc tính
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtAddress, txtPhone, txtMaLop;
    JButton btnAdd, btnUpdate, btnDelete, btnFind;

    // Khởi tạo constructor
    public GUILopHoc() throws SQLException {   
        jFrame = new JFrame();
        jFrame.setSize(750, 500);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setTitle("Quản lý lớp học");

        JLabel lblMSSV = new JLabel("Mã lớp: ");
        lblMSSV.setBounds(20, 40, 100, 35);
        jFrame.add(lblMSSV);

        txtMSSV = new JTextField();
        txtMSSV.setBounds(80, 40, 180, 35);
        jFrame.add(txtMSSV);

        JLabel lblName = new JLabel("Tên lớp: ");
        lblName.setBounds(20, 100, 100, 35);
        jFrame.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(80, 100, 180, 35);
        jFrame.add(txtName);
        
        JLabel lblMaLop = new JLabel("Mã khoa: ");
        lblMaLop.setBounds(20, 160, 100, 35);
        jFrame.add(lblMaLop);

        Vector<String> x = new Vector<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        try{
            con = AbstractDao.getCon();
            statement = con.prepareStatement("SELECT maKhoa FROM khoa");
            res = statement.executeQuery();
            while(res.next()){
                x.add(res.getString("maKhoa"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(con != null){
                    con.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(res != null){
                    res.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }  
        }
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel((Vector) x));
        comboBox.setBounds(80, 160, 180, 35);
        jFrame.add(comboBox);

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
            new Object[] { "Mã lớp", "Tên lớp", "Mã khoa" }
        );
        // Set model
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(20, 250, 650, 180);
        jFrame.add(jScrollPane);
        a.Query(defaultTableModel,"SELECT * FROM lop",new LopMapper());
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                String mssv = txtMSSV.getText();
                String name = txtName.getText();
                String maLop = (String) comboBox.getSelectedItem();
                // Thêm dữ liệu vào Database
                try {
                    // Set giá trị cho các tham số
                    String sql = "INSERT INTO lop VALUES (?,?,?)";
                    PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
                    ps.setString(1, mssv);
                    ps.setString(2, name);
                    ps.setString(3, maLop);
                    ps.executeUpdate();
                    // Thực thi câu lệnh
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel,"SELECT * FROM lop",new LopMapper());
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
                String address = (String) comboBox.getSelectedItem();
                // Cập nhật dữ liệu vào Database
                try {
                    // Khởi tạo Connection
                    String sql = "UPDATE lop SET tenLop = ?, maKhoa = ? WHERE maLop = ?";
                    PreparedStatement preparedStatement = AbstractDao.getCon().prepareStatement(sql);// Set giá trị cho các tham số
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, address);
                    preparedStatement.setString(3,mssv);
                    // Thực thi câu lệnh
                    preparedStatement.execute();
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel,"SELECT * FROM lop",new LopMapper());
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
                // Xóa dữ liệu trong Database
                try {
                    String sql = "DELETE FROM lop WHERE maLop = ?";
                    PreparedStatement preparedStatement = AbstractDao.getCon().prepareStatement(sql);// Set giá trị cho các tham số
                    // Set giá trị cho các tham số
                    preparedStatement.setString(1, mssv);
                    // Thực thi câu lệnh
                    preparedStatement.execute();
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel,"SELECT * FROM lop",new LopMapper());
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
                // Xóa dữ liệu trong Database
                String sql = "SELECT * FROM lop WHERE maLop = ?";
                // Xóa dữ liệu trong JTable
                defaultTableModel.setRowCount(0);
                // Lấy dữ liệu từ Database vào JTable
                a.Query(defaultTableModel, sql, new LopMapper(), mssv);
                // Thông báo
                JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");
            }
        });
        jFrame.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            GUILopHoc a = new GUILopHoc();
        } catch (SQLException ex) {
            Logger.getLogger(GUILopHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
