
package GiaoDienQuanLy;

import dao_DaTa.AbstractDao;
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
import service.SinhVien_Service;

public class GUIThongTinSinhVien{
    AbstractDao a = new AbstractDao() {};
    // Khai báo các thuộc tính
    JFrame jFrame;
    JTable jTable;
    JTextField txtMSSV, txtName, txtAddress, txtPhone, txtMaLop;
    JButton btnAdd, btnUpdate;
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
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(SinhVien_Service.luaChon()));
        comboBox.setBounds(100, 180, 180, 35);
        jFrame.add(comboBox);

        JLabel lblDiem = new JLabel("Điểm thi từng môn: ");
        lblDiem.setBounds(40, 220, 150, 35);
        jFrame.add(lblDiem);
        
        btnAdd = new JButton("Cập nhật");
        btnAdd.setBounds(420, 60, 150, 50);
        jFrame.add(btnAdd);
        
        btnUpdate = new JButton("Đăng kí HP");
        btnUpdate.setBounds(420, 130, 150, 50);
        jFrame.add(btnUpdate);

        
        SinhVien_Service.thongTinSinhVien(maSV, jFrame, txtMSSV, txtName, txtAddress, txtPhone, comboBox);
        
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

        SinhVien_Service.danhSachDiemThi(maSV, defaultTableModel);
        
        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update dữ liệu vào Database
                SinhVien_Service.updateSinhVien(txtMSSV.getText(), txtName.getText(), txtAddress.getText(), txtPhone.getText(), (String) comboBox.getSelectedItem(), defaultTableModel);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            private String[] args;
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIDangKyHocPhan.main(args);
                GUIDangKyHocPhan.setMaSV(maSV);
            }
        });
//        làm thêm phần đăng kí học phần
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
