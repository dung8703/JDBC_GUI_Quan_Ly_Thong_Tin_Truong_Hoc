package service;

import dao_DaTa.SinhVienDao;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SinhVien_Service {
    static SinhVienDao sinhVienDao = new SinhVienDao();
    
    public static void addSinhVien(String maSV, String hoTen, String gioiTinh, String ngaySinh, String maLop, DefaultTableModel defaultTableModel){
        sinhVienDao.add(maSV, hoTen, gioiTinh, ngaySinh, maLop);
        // Thông báo
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        // Xóa dữ liệu trong JTable
        defaultTableModel.setRowCount(0);
        // Lấy dữ liệu từ Database vào JTable
        sinhVienDao.findAll(defaultTableModel);
    }
    
    public static void deleteSinhVien(String maSV, DefaultTableModel defaultTableModel){
        sinhVienDao.delete(maSV);
        JOptionPane.showMessageDialog(null, "Xóa thành công");
        defaultTableModel.setRowCount(0);
        sinhVienDao.findAll(defaultTableModel);
    }
    
    public static void updateSinhVien(String maSV, String hoTen, String gioiTinh, String ngaySinh, String maLop, DefaultTableModel defaultTableModel){
        sinhVienDao.update(maSV, hoTen, gioiTinh, ngaySinh, maLop);
        JOptionPane.showMessageDialog(null, "Sửa thành công");
    }
    
    public static void findAllSinhVien(DefaultTableModel defaultTableModel){
        sinhVienDao.findAll(defaultTableModel);
    }
    
    public static void findSinhVien(String maSV, DefaultTableModel defaultTableModel){
        defaultTableModel.setRowCount(0);
        sinhVienDao.find(maSV, defaultTableModel);
        JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");       
    }
    
    public static Vector<String> luaChon(){
        return sinhVienDao.chon();
    }
    
    public static void Gioi(JCheckBox cbKha, JCheckBox cbTrungBinh, DefaultTableModel defaultTableModel){
        sinhVienDao.locGioi(cbKha, cbTrungBinh, defaultTableModel);
    }
    
    public static void Kha(JCheckBox cbGioi, JCheckBox cbTrungBinh, DefaultTableModel defaultTableModel){
        sinhVienDao.locKha(cbGioi, cbTrungBinh, defaultTableModel);
    }
    
    public static void TrungBinh(JCheckBox cbGioi, JCheckBox cbKha, DefaultTableModel defaultTableModel){
        sinhVienDao.locTrungBinh(cbGioi, cbKha, defaultTableModel);
    }
    
    public static void thongTinSinhVien(String maSV, JFrame jFrame, JTextField txtMSSV, JTextField txtName, JTextField txtAddress, JTextField txtPhone, JComboBox comboBox){
        sinhVienDao.findSV(maSV, jFrame, txtMSSV, txtName, txtAddress, txtPhone, comboBox);
    }
    
    public static void danhSachDiemThi(String maSV, DefaultTableModel defaultTableModel){
        sinhVienDao.findDiemThi(maSV, defaultTableModel);
    }
}
