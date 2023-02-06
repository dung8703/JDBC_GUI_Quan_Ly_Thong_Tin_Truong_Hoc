package service;

import dao_DaTa.LopDao;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Lop_Service {
    static LopDao lopDao = new LopDao();
    
    public static void addLop(String maLop, String tenLop, String maKhoa, DefaultTableModel defaultTableModel){
        //Thêm vào database
        lopDao.add(maLop, tenLop, maKhoa);
        //Thông báo
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        //Xóa dữ liệu trong JTable
        defaultTableModel.setRowCount(0);
        //Lấy dữ liệu từ database vào JTable
        lopDao.findAll(defaultTableModel);
    }
    
    public static void deleteLop(String maLop, DefaultTableModel defaultTableModel){
        lopDao.delete(maLop);
        JOptionPane.showMessageDialog(null, "Xóa thành công");
        defaultTableModel.setRowCount(0);
        lopDao.findAll(defaultTableModel);
    }
    
    public static void updateLop(String maLop, String tenLop, String maKhoa, DefaultTableModel defaultTableModel){
        lopDao.update(maLop, tenLop, maKhoa);
        JOptionPane.showMessageDialog(null, "Sửa thành công");
        defaultTableModel.setRowCount(0);
        lopDao.findAll(defaultTableModel);        
    }
    
    public static void findAllLop(DefaultTableModel defaultTableModel){
        lopDao.findAll(defaultTableModel);      
    }
    
    public static void findLop(String maLop, DefaultTableModel defaultTableModel){
        defaultTableModel.setRowCount(0);
        lopDao.find(maLop, defaultTableModel);
        JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");   
    }
    
    public static Vector<String> luachon(){
        return lopDao.chon();
    }
}
