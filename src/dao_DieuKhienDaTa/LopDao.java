/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_DieuKhienDaTa;

import dao_Impl.ILopDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.LopMapper;
import model.Khoa;
import model.Lop;

/**
 *
 * @author PC ACER
 */
public class LopDao extends AbstractDao<Lop> implements ILopDao{
    public void add(Lop lop) throws SQLException{
        String sql = "INSERT INTO lop VALUES(?,?,?)";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, lop.getMaLop());
        ps.setString(2, lop.getTenLop());
        ps.setString(3, lop.getKhoa());
        ps.execute();
    }
    
    public void delete(String maLop) throws SQLException{
        String sql = "DELETE FROM lop WHERE maLop = ?";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, maLop);
        ps.execute();
    }
    
    public void update(Lop lop) throws SQLException{
        String sql = "UPDATE lop SET tenLop = ?, maKhoa = ? WHERE maLop = ?";
        PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
        ps.setString(1, lop.getMaLop());
        ps.setString(2, lop.getTenLop());
        ps.setString(3, lop.getKhoa());
        ps.execute();
    }
    //Tra ve thong tin cua cac lop
    @Override
    public List<Lop> findAll() {
        String sql = "SELECT * FROM lop";
        return query(sql, new LopMapper());
    } 
    
    //Tra ve thong tin cua 1 lop duoc tim kiem theo maLop
    @Override
    //Tra ve thong tin cua 1 khoa duoc tim kiem theo maKhoa
    public List<Lop> find(String maLop) {
        String sql = "SELECT * FROM lop WHERE maLop = ?";
        return query(sql, new LopMapper(), maLop);
    }
}
