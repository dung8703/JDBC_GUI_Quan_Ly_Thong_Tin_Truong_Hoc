
package dao_Impl;

import javax.swing.table.DefaultTableModel;
import model.Khoa;

public interface IKhoaDao extends GenericDao<Khoa>{
    public void add(String maKhoa, String tenKhoa);
    public void delete(String maKhoa);
    public void update(String maKhoa, String tenKhoa);
    public void findAll(DefaultTableModel defaultTableModel);
    public void find(DefaultTableModel defaultTableModel, String maKhoa);
}
