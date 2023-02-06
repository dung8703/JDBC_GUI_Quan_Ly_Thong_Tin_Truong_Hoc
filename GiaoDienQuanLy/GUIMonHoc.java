package GiaoDienQuanLy;

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
import service.MonHoc_Service;

public class GUIMonHoc{
    // Khai báo các thuộc tính
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
            new Object[] {"Mã môn học", "Tên môn học", "Số tín"}
        );
        // Set model
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(20, 250, 600, 180);
        jFrame.add(jScrollPane);
        // Lấy dữ liệu từ Database vào JTable
        MonHoc_Service.findAllMonHoc(defaultTableModel);
        
        
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thêm dữ liệu vào Database
                MonHoc_Service.addMonHoc(txtMSSV.getText(), txtName.getText(), Integer.valueOf(txtAddress.getText()), defaultTableModel);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cập nhật dữ liệu vào Database
                MonHoc_Service.updateMonHoc(txtMSSV.getText(), txtName.getText(), Integer.valueOf(txtAddress.getText()), defaultTableModel);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa dữ liệu trong Database
                MonHoc_Service.deleteMonHoc(txtMSSV.getText(), defaultTableModel);
            }
        });
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tìm kiếm theo mã môn hocj
                MonHoc_Service.findMonHoc(txtMSSV.getText(), defaultTableModel);
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