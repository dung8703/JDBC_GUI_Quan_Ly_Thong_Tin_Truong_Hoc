/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_DieuKhienDaTa;

import dao_Impl.IKhoaDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import mapper.KhoaMapper;
import model.Khoa;

/**
 *
 * @author PC ACER
 */
public class KhoaDao extends AbstractDao<Khoa> implements IKhoaDao{
    
    public void add(Khoa khoa) throws SQLException{
        String sql = "INSERT INTO khoa(maKhoa, tenKhoa) VALUES (?,?)";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, khoa.getMaKhoa());
        ps.setString(2, khoa.getTenKhoa());
        ps.execute();//Thuc hien cau lenh insert
    }
    
    public void delete(String maKhoa) throws SQLException{
        String sql = "DELETE FROM khoa WHERE maKhoa = ?";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, maKhoa);
        ps.execute();//Thuc hien cau lenh delete
    }
    
    public void update(Khoa khoa) throws SQLException{
        String sql = "UPDATE khoa SET tenKhoa = ? WHERE maKhoa = ?";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, khoa.getMaKhoa());
        ps.setString(2, khoa.getTenKhoa());
        ps.execute();//Thuc hien cau lenh update
    }
    
    //Tra ve thong tin cua cac khoa
    @Override
    public List<Khoa> findAll() {
        String sql = "SELECT * FROM khoa";
        return query(sql, new KhoaMapper());
    }

    @Override
    //Tra ve thong tin cua 1 khoa duoc tim kiem theo maKhoa
    public List<Khoa> find(String maKhoa) {
        String sql = "SELECT * FROM khoa WHERE maKhoa = ?";
        return query(sql, new KhoaMapper(), maKhoa);
    }
}
