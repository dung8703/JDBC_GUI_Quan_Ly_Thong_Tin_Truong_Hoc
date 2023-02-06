package dao_DaTa;

import dao_Impl.IMonHocDao;
import javax.swing.table.DefaultTableModel;
import mapper.MonHocMapper;
import model.MonHoc;

public class MonHocDao extends AbstractDao<MonHoc> implements IMonHocDao{
    @Override
    public void add(String maMH, String tenMH, Integer soTiet) {
        String sql = "INSERT INTO monhoc VALUES (?, ?, ?)";
        save(sql, maMH, tenMH, soTiet);
    }
    
    @Override
    public void delete(String maMH) {
        String sql = "DELETE FROM monhoc WHERE maMH = ?";
        save(sql, maMH);
    }

     @Override
    public void update(String maMH, String tenMH, Integer soTiet) {
        String sql = "UPDATE monhoc SET tenMH = ? AND soTiet = ? WHERE maMH = ?";
        save(sql, tenMH, soTiet, maMH);
    }

    @Override
    public void findAll(DefaultTableModel defaultTableModel) {
        String sql = "SELECT * FROM monhoc";
        Query(defaultTableModel, sql, new MonHocMapper());
    }

    @Override
    public void find(String maMH, DefaultTableModel defaultTableModel) {
        String sql = "SELECT * FROM monhoc WHERE maMH = ?";
        Query(defaultTableModel, sql, new MonHocMapper(), maMH);
    }
}
