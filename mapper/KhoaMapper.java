package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Khoa;

public class KhoaMapper implements RowMapper<Khoa> {

    @Override
    public Khoa mapRow(ResultSet res) {
        try {
            Khoa khoa = new Khoa();
            khoa.setMaKhoa(res.getString("maKhoa"));
            khoa.setTenKhoa(res.getString("tenKhoa"));
            return khoa;
        } catch (SQLException ex) {
            Logger.getLogger(KhoaMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
}
