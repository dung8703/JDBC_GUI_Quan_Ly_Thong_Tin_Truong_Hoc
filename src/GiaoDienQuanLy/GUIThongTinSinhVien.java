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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
import mapper.SinhVienMapper;
import model.KetQua;

public class GUIThongTinSinhVien{
    AbstractDao a = new AbstractDao() {};
    // Khai báo các thuộc tính
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtAddress, txtPhone, txtMaLop;
    JButton btnAdd, btnUpdate, btnDelete, btnFind;
    JLabel mssv, hoTen, gioiTinh, ngaySinh, maLop;
    public static String maSV;

    public String getMaSV() {
        return maSV;
    }   

    public static void setMaSV(String maSV) {
        GUIThongTinSinhVien.maSV = maSV;
    }
    

    // Khởi tạo constructor
    public GUIThongTinSinhVien() throws SQLException {   
        jFrame = new JFrame();
        jFrame.setSize(750, 500);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
//        jFrame.setTitle("Thông tin của sinh viên");

        JLabel lblMSSV = new JLabel("MSSV: ");
        lblMSSV.setBounds(40, 20, 100, 35);
        jFrame.add(lblMSSV);

        txtMSSV = new JTextField();
        txtMSSV.setBounds(100, 20, 180, 35);
        jFrame.add(txtMSSV);

        JLabel lblName = new JLabel("Họ và tên: ");
        lblName.setBounds(40, 60, 100, 35);
        jFrame.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(100, 60, 180, 35);
        jFrame.add(txtName);

        JLabel lblAddress = new JLabel("GIới tính: ");
        lblAddress.setBounds(40, 100, 100, 35);
        jFrame.add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(100, 100, 180, 35);
        jFrame.add(txtAddress);
        
        JLabel lblPhone = new JLabel("Ngày Sinh: ");
        lblPhone.setBounds(40, 140, 100, 35);
        jFrame.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(100, 140, 180, 35);
        jFrame.add(txtPhone);
        
        JLabel lblMaLop = new JLabel("Mã lớp: ");
        lblMaLop.setBounds(40, 180, 100, 35);
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
        comboBox.setBounds(100, 180, 180, 35);
        jFrame.add(comboBox);

        JLabel lblDiem = new JLabel("Điểm thi từng môn: ");
        lblDiem.setBounds(40, 220, 150, 35);
        jFrame.add(lblDiem);
        
        btnAdd = new JButton("Cập nhật");
        btnAdd.setBounds(420, 50, 200, 130);
        jFrame.add(btnAdd);

        try {
            String sql = "SELECT * FROM sinhvien WHERE maSV = ?";
            PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
            ps.setString(1, maSV);
            // Lấy dữ liệu
            ResultSet resultSet = ps.executeQuery();
            // Lặp dữ liệu
            while (resultSet.next()) {
                // Thêm dữ liệu vào txt
                jFrame.setTitle("Thông tin của sinh viên " + resultSet.getString("hoTen"));
                txtMSSV.setText(resultSet.getString("maSV"));
                txtName.setText(resultSet.getString("hoTen"));
                txtAddress.setText(resultSet.getString("gioiTinh"));
                txtPhone.setText(resultSet.getString("ngaySinh"));     
                comboBox.setSelectedItem(resultSet.getString("maLop"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Khởi tạo JTable
        jTable = new JTable();
        // Tạo model cho JTable
        DefaultTableModel defaultTableModel = new DefaultTableModel(
            new Object[][] {},
            new Object[] { "Môn học", "Điểm thi"}
        );
        // Set model
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(20, 250, 650, 180);
        jFrame.add(jScrollPane);
//        a.Query(defaultTableModel,"SELECT * FROM ketqua WHERE maSV = ?",new KetQuaMapper(), "2021600222");
        try {
            String sql = "SELECT maMH, diemThi FROM ketqua WHERE maSV = ?";
            PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
            ps.setString(1, maSV);
            // Lấy dữ liệu
            ResultSet resultSet = ps.executeQuery();
            // Lặp dữ liệu
            while (resultSet.next()) {
                // Thêm dữ liệu vào model
                defaultTableModel.addRow(new Object[] { resultSet.getString("maMH"), resultSet.getFloat("diemThi")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                // Lấy dữ liệu từ JTextField
                String mssv = txtMSSV.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();
                String phone = txtPhone.getText();
                String maLop = (String) comboBox.getSelectedItem();
                // Thêm dữ liệu vào Database
                try {
                    // Set giá trị cho các tham số
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
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jFrame.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            GUIThongTinSinhVien a = new GUIThongTinSinhVien();
        } catch (SQLException ex) {
            Logger.getLogger(GUIThongTinSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
