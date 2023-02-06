package service;

import dao_DaTa.KetQuaDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KetQua_Service {
    static KetQuaDao ketQuaDao = new KetQuaDao();
    
    public static void addKetQua(String maSV, String maMH, Float diem, DefaultTableModel defaultTableModel){
        //Thêm vào db
        ketQuaDao.add(maSV, maMH, diem);
        // Thông báo
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        // Xóa dữ liệu trong JTable
        defaultTableModel.setRowCount(0);
        // Lấy dữ liệu từ Database vào JTable
        ketQuaDao.findAll(defaultTableModel);
    }
    
    public static void deleteKetQua(String maSV, String maMH, DefaultTableModel defaultTableModel){
        ketQuaDao.delete(maSV, maMH);
        JOptionPane.showMessageDialog(null, "Xóa thành công");
        defaultTableModel.setRowCount(0);
        ketQuaDao.findAll(defaultTableModel);
    }
    
    public static void updateKetQua(String maSV, String maMH, Float diem, DefaultTableModel defaultTableModel){
        ketQuaDao.update(maSV, maMH, diem);
        JOptionPane.showMessageDialog(null, "Sửa thành công");
        defaultTableModel.setRowCount(0);
        ketQuaDao.findAll(defaultTableModel);
    }
    
    public static void findAllKetQua(DefaultTableModel defaultTableModel){
        ketQuaDao.findAll(defaultTableModel);
    }
    
    public static void findKetQua(String maSV, String maMH, DefaultTableModel defaultTableModel){
        defaultTableModel.setRowCount(0);
        ketQuaDao.find(defaultTableModel, maSV, maMH);
        JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");     
    }
}
