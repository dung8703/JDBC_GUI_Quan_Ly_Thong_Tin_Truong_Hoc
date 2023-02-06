package dao_Impl;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.Lop;

public interface ILopDao extends GenericDao<Lop>{
    public void add(String maLop, String tenLop, String maKhoa);
    public void delete(String maLop);
    public void update(String maLop, String tenLop, String maKhoa);
    public void findAll(DefaultTableModel defaultTableModel);
    public void find(String maLop, DefaultTableModel defaultTableModel);
    public Vector<String> chon();
}
