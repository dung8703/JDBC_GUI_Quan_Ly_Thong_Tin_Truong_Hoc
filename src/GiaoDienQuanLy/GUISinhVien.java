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
import mapper.SinhVienMapper;
import model.SinhVien;

public class GUISinhVien{
    AbstractDao a = new AbstractDao() {};
    // Khai báo các thuộc tính
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtAddress, txtPhone, txtMaLop;
    JButton btnAdd, btnUpdate, btnDelete, btnFind;

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
    public GUISinhVien() throws SQLException {   
        jFrame = new JFrame();
        jFrame.setSize(750, 500);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setTitle("Quản lý sinh viên");

        JLabel lblMSSV = new JLabel("MSSV: ");
        lblMSSV.setBounds(20, 20, 100, 35);
        jFrame.add(lblMSSV);

        txtMSSV = new JTextField();
        txtMSSV.setBounds(80, 20, 180, 35);
        jFrame.add(txtMSSV);

        JLabel lblName = new JLabel("Họ và tên: ");
        lblName.setBounds(20, 60, 100, 35);
        jFrame.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(80, 60, 180, 35);
        jFrame.add(txtName);

        JLabel lblAddress = new JLabel("GIới tính: ");
        lblAddress.setBounds(20, 100, 100, 35);
        jFrame.add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(80, 100, 180, 35);
        jFrame.add(txtAddress);

        JLabel lblPhone = new JLabel("Ngày Sinh: ");
        lblPhone.setBounds(20, 140, 100, 35);
        jFrame.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(80, 140, 180, 35);
        jFrame.add(txtPhone);
        
        JLabel lblMaLop = new JLabel("Mã lớp: ");
        lblMaLop.setBounds(20, 180, 100, 35);
        jFrame.add(lblMaLop);

        Vector<String> x = new Vector<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        try{
            con = AbstractDao.getCon();
            statement = con.prepareStatement("SELECT maLop FROM lop");
            res = statement.executeQuery();
            while(res.next()){
                x.add(res.getString("maLop"));
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
        comboBox.setBounds(80, 180, 180, 35);
        jFrame.add(comboBox);
                
                
        JLabel lblLoc = new JLabel("Lọc: ");
        lblLoc.setBounds(20, 240, 100, 35);
        jFrame.add(lblLoc);

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
        
        JCheckBox cbGiỏi = new JCheckBox("Giỏi");
        cbGiỏi.setBounds(80, 240, 80, 30);

        JCheckBox cbKhá = new JCheckBox("Khá");
        cbKhá.setBounds(160, 240, 80, 30);

        JCheckBox cbTrungBình = new JCheckBox("Yếu");
        cbTrungBình.setBounds(240, 240, 80, 30);

        jFrame.add(cbGiỏi);
        jFrame.add(cbKhá);
        jFrame.add(cbTrungBình);

        // Khởi tạo JTable
        jTable = new JTable();
        // Tạo model cho JTable
        DefaultTableModel defaultTableModel = new DefaultTableModel(
            new Object[][] {},
            new Object[] { "MSSV", "Họ và tên", "Giới tính", "Ngày sinh", "Mã lớp" }
        );
        // Set model
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(20, 300, 650, 140);
        jFrame.add(jScrollPane);
        // Lấy dữ liệu từ Database vào JTable
//        try {
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
            a.Query(defaultTableModel,"SELECT * FROM sinhvien",new SinhVienMapper());
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                String mssv = txtMSSV.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();
                String phone = txtPhone.getText();
                String maLop = (String) comboBox.getSelectedItem();
                // Thêm dữ liệu vào Database
                try {
                    // Set giá trị cho các tham số
                    String sql = "INSERT INTO sinhvien VALUES (?,?,?,?,?)";
                    PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
                    ps.setString(1, mssv);
                    ps.setString(2, name);
                    ps.setString(3, address);
                    ps.setString(4, phone);
                    ps.setString(5, maLop);
                    ps.executeUpdate();
                    // Thực thi câu lệnh
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel,"SELECT * FROM sinhvien",new SinhVienMapper());
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
                String address = txtAddress.getText();
                String phone = txtPhone.getText();
                String maLop = (String) comboBox.getSelectedItem();
                // Cập nhật dữ liệu vào Database
                try {
                    // Khởi tạo Connection
                    String sql = "UPDATE sinhvien SET hoten = ?, gioitinh = ?, ngaysinh = ?, malop = ? WHERE maSV = ?";
                    PreparedStatement preparedStatement = AbstractDao.getCon().prepareStatement(sql);// Set giá trị cho các tham số
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, address);
                    preparedStatement.setString(3, phone);
                    preparedStatement.setString(4, maLop);
                    preparedStatement.setString(5,mssv);
                    // Thực thi câu lệnh
                    preparedStatement.execute();
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel,"SELECT * FROM sinhvien",new SinhVienMapper());
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
                    String sql = "DELETE FROM sinhvien WHERE maSV = ?";
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
                    a.Query(defaultTableModel,"SELECT * FROM sinhvien",new SinhVienMapper());
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
                String sql = "SELECT * FROM sinhvien WHERE maSV = ?";
                // Xóa dữ liệu trong JTable
                defaultTableModel.setRowCount(0);
                // Lấy dữ liệu từ Database vào JTable
                a.Query(defaultTableModel, sql, new SinhVienMapper(), mssv);
                // Thông báo
                JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");
            }
        });
        cbGiỏi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cbKhá.setSelected(false);
                cbTrungBình.setSelected(false);
                // Xóa dữ liệu trong JTable
                defaultTableModel.setRowCount(0);
                String sql = "SELECT sinhvien.maSV, hoTen, gioiTinh,ngaySinh, maLop FROM ketqua INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV GROUP BY maSV HAVING AVG(diemThi) > 8";
                a.Query(defaultTableModel, sql, new SinhVienMapper() );
            }
        });

        cbKhá.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cbGiỏi.setSelected(false);
                cbTrungBình.setSelected(false);
                // Xóa dữ liệu trong JTable
                defaultTableModel.setRowCount(0);
                String sql = "SELECT sinhvien.maSV, hoTen, gioiTinh,ngaySinh, maLop FROM ketqua INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV GROUP BY maSV HAVING AVG(diemThi) BETWEEN 5 AND 8 ";
                a.Query(defaultTableModel, sql, new SinhVienMapper() );
            }
        });

        cbTrungBình.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cbGiỏi.setSelected(false);
                cbKhá.setSelected(false);
                // Xóa dữ liệu trong JTable
                defaultTableModel.setRowCount(0);
                String sql = "SELECT sinhvien.maSV, hoTen, gioiTinh,ngaySinh, maLop FROM ketqua INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV GROUP BY maSV HAVING AVG(diemThi) BETWEEN 5 AND 8 ";
                a.Query(defaultTableModel, sql, new SinhVienMapper() );
            }
        });
        jFrame.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            GUISinhVien a = new GUISinhVien();
        } catch (SQLException ex) {
            Logger.getLogger(GUISinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

//Done class ADMIN