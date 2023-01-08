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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mapper.MonHocMapper;
import mapper.SinhVienMapper;
import model.SinhVien;

public class GUIMonHoc{
    // Khai báo các thuộc tính
    AbstractDao a = new AbstractDao() {};
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtAddress, txtPhone, txtMaLop;
    JButton btnAdd, btnUpdate, btnDelete,btnFind;

    // Khởi tạo constructor
    public GUIMonHoc() throws SQLException {
           
        jFrame = new JFrame();
        jFrame.setSize(750, 500);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setTitle("Quản lý môn học");

        JLabel lblMSSV = new JLabel("Mã môn học: ");
        lblMSSV.setBounds(20, 40, 100, 35);
        jFrame.add(lblMSSV);

        txtMSSV = new JTextField();
        txtMSSV.setBounds(100, 40, 180, 35);
        jFrame.add(txtMSSV);

        JLabel lblName = new JLabel("Tên môn học: ");
        lblName.setBounds(20, 100, 100, 35);
        jFrame.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(100, 100, 180, 35);
        jFrame.add(txtName);

        JLabel lblAddress = new JLabel("Số tín: ");
        lblAddress.setBounds(20, 160, 100, 35);
        jFrame.add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(100, 160, 180, 35);
        jFrame.add(txtAddress);

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
            new Object[] { "Mã môn học", "Tên môn học", "Số tín"}
        );
        // Set model
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(20, 250, 600, 180);
        jFrame.add(jScrollPane);
        // Lấy dữ liệu từ Database vào JTable
        a.Query(defaultTableModel, "SELECT * FROM monhoc", new MonHocMapper());
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                String mssv = txtMSSV.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();
                // Thêm dữ liệu vào Database
                try {
                    // Set giá trị cho các tham số
                    String sql = "INSERT INTO monhoc VALUES (?,?,?)";
                    PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
                    ps.setString(1, mssv);
                    ps.setString(2, name);
                    ps.setString(3, address);
                    ps.executeUpdate();
                    // Thực thi câu lệnh
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel, "SELECT * FROM monhoc", new MonHocMapper());
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
                // Cập nhật dữ liệu vào Database
                try {
                    // Khởi tạo Connection
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
                    // Tạo PreparedStatement
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE monhoc SET tenMH = ?, soTiet = ? WHERE maMH = ?");
                    // Set giá trị cho các tham số
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, address);
                    preparedStatement.setString(3, mssv);
                    // Thực thi câu lệnh
                    preparedStatement.execute();
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel, "SELECT * FROM monhoc", new MonHocMapper());
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
                    // Khởi tạo Connection
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
                    // Tạo PreparedStatement
                    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM monhoc WHERE maMH = ?");
                    // Set giá trị cho các tham số
                    preparedStatement.setString(1, mssv);
                    // Thực thi câu lệnh
                    preparedStatement.execute();
                    // Thông báo
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                    // Xóa dữ liệu trong JTable
                    defaultTableModel.setRowCount(0);
                    // Lấy dữ liệu từ Database vào JTable
                    a.Query(defaultTableModel, "SELECT * FROM monhoc", new MonHocMapper());
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
                String sql = "SELECT * FROM monhoc WHERE maMH = ?";
                // Xóa dữ liệu trong JTable
                defaultTableModel.setRowCount(0);
                // Lấy dữ liệu từ Database vào JTable
                a.Query(defaultTableModel, sql, new MonHocMapper(), mssv);
                // Thông báo
                JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");
            }
        });
        jFrame.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            GUIMonHoc a = new GUIMonHoc();
        } catch (SQLException ex) {
            Logger.getLogger(GUISinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
//Done class GUIMonHoc