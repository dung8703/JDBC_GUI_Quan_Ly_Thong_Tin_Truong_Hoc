package GiaoDienQuanLy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import service.Lop_Service;

public class GUILopHoc{
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

        //tạo lựa chọn mã khoa
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(Lop_Service.luachon()));
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
        Lop_Service.findAllLop(defaultTableModel);
        
        
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                String maLop = (String) comboBox.getSelectedItem();
                // Thêm dữ liệu vào Database
                Lop_Service.addLop(txtMSSV.getText(), txtName.getText(), maLop, defaultTableModel);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ JTextField
                String maKhoa = (String) comboBox.getSelectedItem();
                // Cập nhật dữ liệu vào Database
                Lop_Service.updateLop(txtMSSV.getText(), txtName.getText(), maKhoa, defaultTableModel);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa dữ liệu trong Database
                Lop_Service.deleteLop(txtMSSV.getText(), defaultTableModel);
            }
        });
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa dữ liệu trong Database
                Lop_Service.findLop(txtMSSV.getText(), defaultTableModel);
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
