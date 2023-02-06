
package GiaoDienQuanLy;

import dao_DaTa.AbstractDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import service.SinhVien_Service;

public class GUISinhVien{
    AbstractDao a = new AbstractDao() {};
    // Khai báo các thuộc tính
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtAddress, txtPhone, txtMaLop;
    JButton btnAdd, btnUpdate, btnDelete, btnFind;

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

        //tạo list lựa chọn mã môn
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(SinhVien_Service.luaChon()));
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
        
        JCheckBox cbGioi = new JCheckBox("Giỏi");
        cbGioi.setBounds(80, 240, 80, 30);

        JCheckBox cbKha = new JCheckBox("Khá");
        cbKha.setBounds(160, 240, 80, 30);

        JCheckBox cbTrungBinh = new JCheckBox("Yếu");
        cbTrungBinh.setBounds(240, 240, 80, 30);

        jFrame.add(cbGioi);
        jFrame.add(cbKha);
        jFrame.add(cbTrungBinh);

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
        SinhVien_Service.findAllSinhVien(defaultTableModel);


        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thêm dữ liệu vào Database
                SinhVien_Service.addSinhVien(txtMSSV.getText(), txtName.getText(), txtAddress.getText(), txtPhone.getText(), (String) comboBox.getSelectedItem(), defaultTableModel);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cập nhật dữ liệu vào Database
                SinhVien_Service.updateSinhVien(txtMSSV.getText(), txtName.getText(), txtAddress.getText(), txtPhone.getText(), (String) comboBox.getSelectedItem(), defaultTableModel);
                defaultTableModel.setRowCount(0);
                SinhVien_Service.findAllSinhVien(defaultTableModel);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa dữ liệu trong Database
                SinhVien_Service.deleteSinhVien(txtMSSV.getText(), defaultTableModel);
            }
        });
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa dữ liệu trong Database
                SinhVien_Service.findSinhVien(txtMSSV.getText(), defaultTableModel);
            }
        });
        
        cbGioi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SinhVien_Service.Gioi(cbKha, cbTrungBinh, defaultTableModel);
            }
        });
        cbKha.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SinhVien_Service.Kha(cbGioi, cbTrungBinh, defaultTableModel);
            }
        });
        cbTrungBinh.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SinhVien_Service.TrungBinh(cbGioi, cbKha, defaultTableModel);
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