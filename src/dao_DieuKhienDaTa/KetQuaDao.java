/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_DieuKhienDaTa;

import dao_Impl.IKetQuaDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.KetQuaMapper;
import model.KetQua;
import model.MonHoc;
import model.SinhVien;

/**
 *
 * @author PC ACER
 */
public class KetQuaDao extends AbstractDao<KetQua> implements IKetQuaDao{
    
    public void add(KetQua kq) throws SQLException{
        String sql = "INSERT INTO ketqua VALUES (?, ?, ?)";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, kq.getSinhVien());
        ps.setString(2, kq.getMonHoc());
        ps.setFloat(3, kq.getDiemThi());
        ps.execute();
    }
    
    public void delete(String maSV,String maMH) throws SQLException{
        String sql = "DELETE FROM ketqua WHERE maSV = ? AND maMH = ?";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, maSV);
        ps.setString(2, maMH);
        ps.execute();
    }

    public void update(KetQua kq) throws SQLException{
        String sql = "UPDATE ketqua SET maMH = ?, diemThi = ? WHERE maSV = ?";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);     
        ps.setString(1, kq.getMonHoc());
        ps.setFloat(2, kq.getDiemThi());
        ps.setString(3, kq.getSinhVien());
        ps.execute();
    }
    
    @Override
    public List<KetQua> findAll(){
        String sql = "SELECT ketqua.maSV, sinhvien.hoTen, ketqua.maMH, monhoc.tenMH, diemThi FROM ketqua INNER JOIN monhoc ON ketqua.maMH = monhoc.maMH INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV";
        return query(sql,  new KetQuaMapper());
    }
    
    @Override
    public List<KetQua> find(String msv, String mmh){
        String sql = "SELECT ketqua.maSV, sinhvien.hoTen, ketqua.maMH, monhoc.tenMH, diemThi FROM ketqua INNER JOIN monhoc ON ketqua.maMH = monhoc.maMH INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV WHERE ketqua.maSV = ? and ketqua.maMH = ?";
        return query(sql, new KetQuaMapper(), msv, mmh);
    }
}
