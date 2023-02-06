package dao_DaTa;

import dao_Impl.IKhoaDao;
import javax.swing.table.DefaultTableModel;
import mapper.KhoaMapper;
import model.Khoa;

public class KhoaDao extends AbstractDao<Khoa> implements IKhoaDao{
    
    //Thêm thông tin 1 khoa mới
    @Override
    public void add(String maKhoa, String tenKhoa){
        String sql = "INSERT INTO khoa(maKhoa, tenKhoa) VALUES (?,?)";
        save(sql, maKhoa, tenKhoa);
    }
    
    //Xóa thông tin 1 khoa
    @Override
    public void delete(String maKhoa){
        String sql = "DELETE FROM khoa WHERE maKhoa = ?";
        save(sql, maKhoa);
    }
    
    //Update thông tin của 1 khoa
    @Override
    public void update(String maKhoa, String tenKhoa){
        String sql = "UPDATE khoa SET tenKhoa = ? WHERE maKhoa = ?";
        save(sql, maKhoa, tenKhoa);
    }
    
    //Tra ve thong tin cua cac khoa
    @Override
    public void findAll(DefaultTableModel defaultTableModel) {
        String sql = "SELECT * FROM khoa";
        Query(defaultTableModel, sql, new KhoaMapper());
    }
    
    //Tra ve thong tin cua 1 khoa duoc tim kiem theo maKhoa
    @Override   
    public void find(DefaultTableModel defaultTableModel,String maKhoa) {
        String sql = "SELECT * FROM khoa WHERE maKhoa = ?";
        Query(defaultTableModel, sql, new KhoaMapper(), maKhoa);
    }
}
