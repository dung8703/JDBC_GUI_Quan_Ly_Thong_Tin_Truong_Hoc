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
import service.Khoa_Service;

public class GUIKhoa{
    AbstractDao a = new AbstractDao() {};
    // Khai báo các thuộc tính
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtAddress, txtPhone, txtMaLop;
    JButton btnAdd, btnUpdate, btnDelete, btnFind;

    // Khởi tạo constructor
    public GUIKhoa() throws SQLException {   
        jFrame = new JFrame();
        jFrame.setSize(750, 500);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setTitle("Quản lý khoa");

        JLabel lblMSSV = new JLabel("Mã khoa: ");
        lblMSSV.setBounds(20, 70, 100, 40);
        jFrame.add(lblMSSV);

        txtMSSV = new JTextField();
        txtMSSV.setBounds(80, 70, 180, 40);
        jFrame.add(txtMSSV);

        JLabel lblName = new JLabel("Tên khoa: ");
        lblName.setBounds(20, 160, 100, 40);
        jFrame.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(80, 160, 180, 40);
        jFrame.add(txtName);

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
            new Object[] { "Mã khoa", "Tên khoa"}
        );
        // Set model
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(20, 250, 650, 180);
        jFrame.add(jScrollPane);
        Khoa_Service.findAllKhoa(defaultTableModel);
        
        
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Khoa_Service.addKhoa(txtMSSV.getText(), txtName.getText(), defaultTableModel);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cập nhật dữ liệu vào Database
                Khoa_Service.updateKhoa(txtName.getText(), txtMSSV.getText(), defaultTableModel);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Khoa_Service.deleteKhoa(txtMSSV.getText(), defaultTableModel);
            }
        });
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Khoa_Service.findKhoa(txtMSSV.getText(), defaultTableModel);
            }
        });
        jFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        try {
            GUIKhoa guiKhoa = new GUIKhoa();
        } catch (SQLException ex) {
            Logger.getLogger(GUIKhoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
