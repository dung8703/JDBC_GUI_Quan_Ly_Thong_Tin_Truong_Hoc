/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_DieuKhienDaTa;

import dao_Impl.IMonHocDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.MonHocMapper;
import model.MonHoc;

/**
 *
 * @author PC ACER
 */
public class MonHocDao extends AbstractDao<MonHoc> implements IMonHocDao{
    public void add(MonHoc mh) throws SQLException{
        String sql = "INSERT INTO monhoc VALUES (?,?,?)";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, mh.getMaMH());
        ps.setString(2, mh.getTenMH());
        ps.setInt(3, mh.getSoTiet());
        ps.execute();
    }
    
    public void delete(String maMH) throws SQLException{
        String sql = "DELETE FROM monhoc WHERE maMH = ?";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, maMH);
        ps.execute();
    }
    
    public void update(MonHoc mh) throws SQLException{
        String sql = "UPDATE monhoc SET tenMH = ?, soTiet = ? WHERE maMH = ?";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);      
        ps.setString(1, mh.getTenMH());
        ps.setInt(2, mh.getSoTiet());
        ps.setString(3, mh.getMaMH());
        ps.execute();
    }
    
    @Override
    public List<MonHoc> findAll() {
            String sql = "SELECT * FROM monhoc";
            return query(sql, new MonHocMapper());
    }

    @Override
    public List<MonHoc> find(String maMH) {
            String sql = "SELECT * FROM monhoc WHERE maMH = ?";
            return query(sql, new MonHocMapper(), maMH);
    }
}
