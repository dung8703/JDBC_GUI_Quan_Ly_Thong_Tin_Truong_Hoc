package dao_Impl;

import javax.swing.table.DefaultTableModel;
import model.MonHoc;

public interface IMonHocDao extends GenericDao<MonHoc>{
    public void add(String maMH, String tenMH, Integer soTiet);
    public void delete(String maMH);
    public void update(String maMH, String tenMH, Integer soTiet);
    public void findAll(DefaultTableModel defaultTableModel);
    public void find(String maMH, DefaultTableModel defaultTableModel);
}
