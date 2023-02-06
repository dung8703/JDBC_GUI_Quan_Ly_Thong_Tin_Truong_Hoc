package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Khoa;
import model.Lop;

public class LopMapper implements RowMapper<Lop>{

    @Override
    public Lop mapRow(ResultSet res) {
        try {
            //set Khoa
            Khoa khoa = new Khoa();
            khoa.setMaKhoa(res.getString("maKhoa"));
            //sau đó mới set cho Lop vì trong Lop có thuộc tính Khoa với quan hệ has-A
            Lop lop = new Lop();
            lop.setMaLop(res.getString("maLop"));
            lop.setTenLop(res.getString("tenLop"));    
            lop.setKhoa(khoa);
            return lop;
        } catch (SQLException ex) {
            Logger.getLogger(LopMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
