/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lop;
import model.SinhVien;

/**
 *
 * @author PC ACER
 */
public class SinhVienMapper implements RowMapper<SinhVien> {

    @Override
    public SinhVien mapRow(ResultSet res) {
        try {
            Lop lop = new Lop();
            lop.setMaLop(res.getString("malop"));
            
            SinhVien sv = new SinhVien();
            sv.setMaSV(res.getString("masv"));
            sv.setHoTen(res.getString("hoten"));
            sv.setGioiTinh(res.getString("gioitinh"));
            sv.setNgaySinh(res.getString("ngaysinh"));
            sv.setLop(lop);
            return sv;
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
}
