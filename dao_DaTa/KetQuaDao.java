package dao_DaTa;

import dao_Impl.IKetQuaDao;
import javax.swing.table.DefaultTableModel;
import mapper.KetQuaMapper;
import model.KetQua;

public class KetQuaDao extends AbstractDao<KetQua> implements IKetQuaDao{
    
    @Override
    public void add(String maSV, String maMH, Float diem) {
        String sql = "INSERT INTO ketqua VALUES (?, ?, ?)";
        save(sql, maSV, maMH, diem);
    }

    @Override
    public void update(String maSV, String maMH, Float diem) {
        String sql = "UPDATE ketqua SET diemThi = ? WHERE maSV = ? AND maMH = ?";
        save(sql, diem, maSV,maMH);
    }
    
    @Override
    public void delete(String maSV,String maMH){
        String sql = "DELETE FROM ketqua WHERE maSV = ? AND maMH = ?";
        save(sql, maSV, maMH);
    }
    
    @Override
    public void findAll(DefaultTableModel defaultTableModel){
        String sql = "SELECT ketqua.maSV, sinhvien.hoTen, ketqua.maMH, monhoc.tenMH, diemThi FROM ketqua INNER JOIN monhoc ON ketqua.maMH = monhoc.maMH INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV";
        Query(defaultTableModel,sql,  new KetQuaMapper());
    }
    
    @Override
    public void find(DefaultTableModel defaultTableModel,String msv, String mmh){
        String sql = "SELECT ketqua.maSV, sinhvien.hoTen, ketqua.maMH, monhoc.tenMH, diemThi FROM ketqua INNER JOIN monhoc ON ketqua.maMH = monhoc.maMH INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV WHERE ketqua.maSV = ? and ketqua.maMH = ?";
        Query(defaultTableModel, sql, new KetQuaMapper(), msv, mmh);
    }

}
