package dao_DaTa;

import dao_Impl.GenericDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import mapper.KetQuaMapper;
import mapper.KhoaMapper;
import mapper.LopMapper;
import mapper.MonHocMapper;
import mapper.RowMapper;
import mapper.SinhVienMapper;
import model.KetQua;
import model.Khoa;
import model.Lop;
import model.MonHoc;
import model.SinhVien;


public class AbstractDao<T> implements GenericDao<T>{
    
    public static Connection getCon() {
        final String url = "jdbc:mysql://localhost:3306/quanlysinhvien";
        final String user = "root";
        final String pass = "";
        try{
            Connection connect = DriverManager.getConnection(url, user, pass);
            return connect;
        }
        catch(SQLException e){
            System.out.println("Loi ket noi DB");
        }
        return null;
    }
    
    @Override
    public void Query(DefaultTableModel defaultTableModel, String sql, RowMapper<T> rowMapper, Object... parameters){
        try {
            Connection con = getCon();
            PreparedStatement statement = con.prepareStatement(sql);
            //set(truyen) cac thuoc tinh vao tung dau ?
            setParameter(statement, parameters);
            // Lấy dữ liệu
            ResultSet resultSet = statement.executeQuery();
            // Lặp dữ liệu
            
            while (resultSet.next()) {
                if(rowMapper.mapRow(resultSet) instanceof SinhVien){
                    SinhVienMapper svm = new SinhVienMapper();
                    SinhVien sv = svm.mapRow(resultSet);
                    // Thêm dữ liệu vào model
                    defaultTableModel.addRow(new Object[] { sv.getMaSV(), sv.getHoTen(), sv.getGioiTinh(), sv.getNgaySinh(), sv.getLop() });
                }
                else if(rowMapper.mapRow(resultSet) instanceof Khoa){
                    KhoaMapper km = new KhoaMapper();
                    Khoa khoa = km.mapRow(resultSet);
                    // Thêm dữ liệu vào model
                    defaultTableModel.addRow(new Object[] { khoa.getMaKhoa(), khoa.getTenKhoa()});
                }
                else if(rowMapper.mapRow(resultSet) instanceof Lop){
                    LopMapper svm = new LopMapper();
                    Lop lop = svm.mapRow(resultSet);
                    // Thêm dữ liệu vào model
                    defaultTableModel.addRow(new Object[] {lop.getMaLop(), lop.getTenLop(), lop.getKhoa() });
                }
                else if(rowMapper.mapRow(resultSet) instanceof MonHoc){
                    MonHocMapper svm = new MonHocMapper();
                    MonHoc mh = svm.mapRow(resultSet);
                    // Thêm dữ liệu vào model
                    defaultTableModel.addRow(new Object[] {mh.getMaMH(), mh.getTenMH(), mh.getSoTiet() });
                }
                else if(rowMapper.mapRow(resultSet) instanceof KetQua){
                    KetQuaMapper svm = new KetQuaMapper();
                    KetQua kq = svm.mapRow(resultSet);
                    // Thêm dữ liệu vào model
                    defaultTableModel.addRow(new Object[] { kq.getSinhVien(), kq.getMonHoc(), kq.getDiemThi() });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(String sql, Object... parameters) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getCon();
            //Tắt auto commit
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            setParameter(ps,parameters);
            ps.executeUpdate();
            //commit
            con.commit();
        }
        catch(SQLException e){
            if(con != null){
                try {
                    con.rollback();
                    System.out.println("Dữ liệu thêm vào bị lỗi, rollback");
                } catch (SQLException ex) {
                    Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        finally{
            try{
                if(con != null){
                    con.close();
                }
                if(ps != null){
                    ps.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }  
        }
    }

    private void setParameter(PreparedStatement statement, Object... parameters) {
        try {
            for(int i = 0 ; i < parameters.length; i++){
                Object parameter = parameters[i];
                int index = i + 1;
                if(parameter instanceof Long){
                    statement.setLong(index, (Long)parameter);
                } 
                else if(parameter instanceof String){
                    statement.setString(index, (String)parameter);
                }
                else if(parameter instanceof Integer){
                    statement.setInt(index, (Integer)parameter);
                }
                else if(parameter instanceof Timestamp){
                    statement.setTimestamp(index, (Timestamp)parameter);
                }
                else if(parameter instanceof Float){
                    statement.setFloat(index, (Float)parameter);
                }
            }    
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}