package service;

import dao_DaTa.KhoaDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Khoa_Service {
    static KhoaDao khoaDao = new KhoaDao();
    
    public static void addKhoa(String maKhoa, String tenKhoa,DefaultTableModel defaultTableModel){
        //Thêm vào db
        khoaDao.add(maKhoa, tenKhoa);
        // Thông báo
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        // Xóa dữ liệu trong JTable
        defaultTableModel.setRowCount(0);
        // Lấy dữ liệu từ Database vào JTable
        khoaDao.findAll(defaultTableModel);
    }
    
    
    public static void deleteKhoa(String maKhoa, DefaultTableModel defaultTableModel){
        khoaDao.delete(maKhoa);
        JOptionPane.showMessageDialog(null, "Xóa thành công");
        defaultTableModel.setRowCount(0);
        khoaDao.findAll(defaultTableModel);
    }
    
    
    public static void updateKhoa(String maKhoa, String tenKhoa, DefaultTableModel defaultTableModel){
        khoaDao.update(maKhoa, tenKhoa);
        JOptionPane.showMessageDialog(null, "Sửa thành công");
        defaultTableModel.setRowCount(0);
        khoaDao.findAll(defaultTableModel);
    }
    
    
    public static void findAllKhoa(DefaultTableModel defaultTableModel){
        khoaDao.findAll(defaultTableModel);
    }
    
    
    public static void findKhoa(String maKhoa, DefaultTableModel defaultTableModel){
        defaultTableModel.setRowCount(0);
        khoaDao.find(defaultTableModel, maKhoa);
        JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");      
    }   
}
