package dao_Impl;

import javax.swing.table.DefaultTableModel;
import model.KetQua;

public interface IKetQuaDao extends GenericDao<KetQua>{
    public void add(String maSV, String maMH, Float diem);
    public void delete(String maSV, String maMH);
    public void update(String maKhoa, String tenKhoa, Float diem);
    public void findAll(DefaultTableModel defaultTableModel);
    public void find(DefaultTableModel defaultTableModel, String maSV, String maMH);
}
