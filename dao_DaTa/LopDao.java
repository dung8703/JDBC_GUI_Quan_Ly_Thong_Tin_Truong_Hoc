package dao_DaTa;

import dao_Impl.ILopDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import mapper.LopMapper;
import model.Lop;

/**
 *
 * @author PC ACER
 */
public class LopDao extends AbstractDao<Lop> implements ILopDao{

    @Override
    public void add(String maLop, String tenLop, String maKhoa) {
        String sql = "INSERT INTO lop VALUES (?, ?, ?)";
        save(sql, maLop, tenLop, maKhoa);
    }
    
    @Override
    public void delete(String maLop){
        String sql = "DELETE FROM lop WHERE maLop = ?";
        save(sql, maLop);
    }

    @Override
    public void update(String maLop, String tenLop, String maKhoa) {
        String sql = "UPDATE lop SET tenLop = ? AND maKhoa = ? WHERE maLop = ?";
        save(sql, tenLop, maKhoa, maLop);
    }

    @Override
    public void findAll(DefaultTableModel defaultTableModel) {
        String sql = "SELECT * FROM lop";
        Query(defaultTableModel, sql, new LopMapper());
    }

    @Override
    public void find( String maLop, DefaultTableModel defaultTableModel) {
        String sql = "SELECT * FROM lop WHERE maLop = ?";
        Query(defaultTableModel, sql, new LopMapper(), maLop);
    }

    @Override
    public Vector<String> chon() {
        Vector<String> x = new Vector<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        try{
            con = AbstractDao.getCon();
            statement = con.prepareStatement("SELECT maKhoa FROM khoa");
            res = statement.executeQuery();
            while(res.next()){
                x.add(res.getString("maKhoa"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(con != null){
                    con.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(res != null){
                    res.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }  
        }
        return x;
    }
}
