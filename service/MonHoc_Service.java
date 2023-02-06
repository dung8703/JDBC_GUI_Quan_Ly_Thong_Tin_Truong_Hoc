package service;

import dao_DaTa.MonHocDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MonHoc_Service {
    static MonHocDao monHocDao = new MonHocDao();
    
    public static void addMonHoc(String maMH, String tenMH, Integer soTiet, DefaultTableModel defaultTableModel){
        //Thêm dữ liệu vào database
        monHocDao.add(maMH, tenMH, soTiet);
        // Thông báo
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        // Xóa dữ liệu trong JTable
        defaultTableModel.setRowCount(0);
        // Lấy dữ liệu từ Database vào JTable
        monHocDao.findAll(defaultTableModel);
        
    }
    
    public static void deleteMonHoc(String maMH, DefaultTableModel defaultTableModel){
        monHocDao.delete(maMH);
        JOptionPane.showMessageDialog(null, "Xóa thành công");
        defaultTableModel.setRowCount(0);
        monHocDao.findAll(defaultTableModel);
    }
    
    public static void updateMonHoc(String maMH, String tenMH, Integer soTiet, DefaultTableModel defaultTableModel){
        monHocDao.update(maMH, tenMH, soTiet);
        JOptionPane.showMessageDialog(null, "Sửa thành công");
        defaultTableModel.setRowCount(0);
        monHocDao.findAll(defaultTableModel);
    }
    
    public static void findAllMonHoc(DefaultTableModel defaultTableModel){
        monHocDao.findAll(defaultTableModel);
    }
    
    public static void findMonHoc(String maMH, DefaultTableModel defaultTableModel){
        defaultTableModel.setRowCount(0);
        monHocDao.find(maMH, defaultTableModel);
        JOptionPane.showMessageDialog(null, "Tìm kiếm thành công"); 
    }

}
