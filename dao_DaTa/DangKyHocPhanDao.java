package dao_DaTa;

import dao_Impl.IDangKyHocPhan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author PC ACER
 */
public class DangKyHocPhanDao implements IDangKyHocPhan {

    @Override
    public void chon(JComboBox comboBox, JComboBox comboBox1) {
        Vector<String> x1 = new Vector<>();
        Vector<String> x2 = new Vector<>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        try{
            con = AbstractDao.getCon();
            statement = con.prepareStatement("SELECT maMH, tenMH FROM monhoc");
            res = statement.executeQuery();
            while(res.next()){
                x1.add(res.getString("maMH"));
                x2.add(res.getString("tenMH"));
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

        comboBox.setModel(new DefaultComboBoxModel(x1));
        comboBox.setBounds(280, 135, 180, 35);
        comboBox1.setModel(new DefaultComboBoxModel(x2));
        comboBox1.setBounds(280, 235, 180, 35);
    }

    @Override
    public void them(String maSV, JComboBox comboBox, JLabel lbl7) {
        Connection con = AbstractDao.getCon();
            try{
                String sql1 = "INSERT INTO dangkymonhoc VALUES(?, ?)";
                PreparedStatement stm1 = con.prepareStatement(sql1);           
                stm1.setString(1, maSV);
                stm1.setString(2, (String) comboBox.getSelectedItem());        
                stm1.execute();
                lbl7.setText("Successful Registration !");
            }
            catch(SQLException e){
                e.printStackTrace();
            }      
    }
    
}
