package dao_DaTa;

import dao_Impl.ISinhVienDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mapper.SinhVienMapper;
import model.SinhVien;


public class SinhVienDao extends AbstractDao<SinhVien> implements ISinhVienDao{
    
    @Override
    public void add(String maSV, String hoTen, String gioiTinh, String ngaySinh, String maLop) {
        String sql = "INSERT INTO sinhvien VALUES (?,?,?,?,?)";
        save(sql, maSV, hoTen, gioiTinh, ngaySinh, maLop);
    }
    
    @Override
    public void delete(String maSV){
        String sql = "DELETE FROM sinhvien WHERE maSV = ?";
        save(sql, maSV);
    }

    @Override
    public void update(String maSV, String hoTen, String gioiTinh, String ngaySinh, String maLop) {
	String sql = "UPDATE sinhvien SET hoten = ?, gioitinh = ?, ngaysinh = ?, malop = ? WHERE maSV = ?";
        save(sql, hoTen, gioiTinh, ngaySinh, maLop, maSV);
    }

    @Override
    public void findAll(DefaultTableModel defaultTableModel) {
        String sql = "SELECT * FROM sinhvien";
        Query(defaultTableModel, sql, new SinhVienMapper());
    }

    @Override
    public void find(String maSV, DefaultTableModel defaultTableModel) {
        String sql = "SELECT * FROM sinhvien WHERE maSV = ?";
        Query(defaultTableModel, sql, new SinhVienMapper(), maSV);
    }

    @Override
    public Vector<String> chon() {
        Vector<String> x = new Vector<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        try{
            con = AbstractDao.getCon();
            statement = con.prepareStatement("SELECT maLop FROM lop");
            res = statement.executeQuery();
            while(res.next()){
                x.add(res.getString("maLop"));
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

    @Override
    public void locGioi(JCheckBox cbKha, JCheckBox cbTrungBinh, DefaultTableModel defaultTableModel) {
        cbKha.setSelected(false);
        cbTrungBinh.setSelected(false);
        // Xóa dữ liệu trong JTable
        defaultTableModel.setRowCount(0);
        String sql = "SELECT sinhvien.maSV, hoTen, gioiTinh,ngaySinh, maLop FROM ketqua INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV GROUP BY maSV HAVING AVG(diemThi) > 8";
        Query(defaultTableModel, sql, new SinhVienMapper() );
    }

    @Override
    public void locKha(JCheckBox cbGioi, JCheckBox cbTrungBinh, DefaultTableModel defaultTableModel) {
        cbGioi.setSelected(false);
        cbTrungBinh.setSelected(false);
        // Xóa dữ liệu trong JTable
        defaultTableModel.setRowCount(0);
        String sql = "SELECT sinhvien.maSV, hoTen, gioiTinh,ngaySinh, maLop FROM ketqua INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV GROUP BY maSV HAVING AVG(diemThi) BETWEEN 5 AND 8 ";
        Query(defaultTableModel, sql, new SinhVienMapper() );    
    }
    
    @Override
    public void locTrungBinh(JCheckBox cbGioi, JCheckBox cbKha, DefaultTableModel defaultTableModel) {
        cbGioi.setSelected(false);
        cbKha.setSelected(false);
        // Xóa dữ liệu trong JTable
        defaultTableModel.setRowCount(0);
        String sql = "SELECT sinhvien.maSV, hoTen, gioiTinh,ngaySinh, maLop FROM ketqua INNER JOIN sinhvien ON sinhvien.maSV = ketqua.maSV GROUP BY maSV HAVING AVG(diemThi) BETWEEN 5 AND 8 ";
        Query(defaultTableModel, sql, new SinhVienMapper() );
    }

    @Override
    public void findSV(String maSV, JFrame jFrame, JTextField txtMSSV, JTextField txtName, JTextField txtAddress, JTextField txtPhone, JComboBox comboBox) {
        try {
            String sql = "SELECT * FROM sinhvien WHERE maSV = ?";
            PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
            ps.setString(1, maSV);
            // Lấy dữ liệu
            ResultSet resultSet = ps.executeQuery();
            // Lặp dữ liệu
            while (resultSet.next()) {
                // Thêm dữ liệu vào txt
                jFrame.setTitle("Thông tin của sinh viên " + resultSet.getString("hoTen"));
                txtMSSV.setText(resultSet.getString("maSV"));
                txtName.setText(resultSet.getString("hoTen"));
                txtAddress.setText(resultSet.getString("gioiTinh"));
                txtPhone.setText(resultSet.getString("ngaySinh"));     
                comboBox.setSelectedItem(resultSet.getString("maLop"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findDiemThi(String maSV, DefaultTableModel defaultTableModel) {
        try {
            String sql = "SELECT tenMH, diemThi FROM ketqua INNER JOIN monhoc ON ketqua.maMH = monhoc.maMH WHERE maSV = ?";
            PreparedStatement ps = AbstractDao.getCon().prepareStatement(sql);
            ps.setString(1, maSV);
            // Lấy dữ liệu
            ResultSet resultSet = ps.executeQuery();
            // Lặp dữ liệu
            while (resultSet.next()) {
                // Thêm dữ liệu vào model
                defaultTableModel.addRow(new Object[] { resultSet.getString("tenMH"), resultSet.getFloat("diemThi")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
