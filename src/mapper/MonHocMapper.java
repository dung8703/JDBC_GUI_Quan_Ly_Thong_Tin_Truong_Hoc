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
import model.MonHoc;

/**
 *
 * @author PC ACER
 */
public class MonHocMapper implements RowMapper<MonHoc> {

    @Override
    public MonHoc mapRow(ResultSet res) {
        try {
            MonHoc mh = new MonHoc();
            mh.setMaMH(res.getString("mamh"));
            mh.setTenMH(res.getString("tenmh"));
            mh.setSoTiet(res.getInt("sotiet"));
            return mh;
        } catch (SQLException ex) {
            Logger.getLogger(MonHocMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
