package dao_Impl;

import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.SinhVien;

public interface ISinhVienDao extends GenericDao<SinhVien>{
    public void add(String maSV, String hoTen, String gioiTinh, String ngaySinh, String maLop);
    public void delete(String maSV);
    public void update(String maSV, String hoTen, String gioiTinh, String ngaySinh, String maLop);
    public void findAll(DefaultTableModel defaultTableModel);
    public void find(String maSV, DefaultTableModel defaultTableModel);
    public void findSV(String maSV, JFrame jFrame, JTextField txtMSSV, JTextField txtName, JTextField txtAddress, JTextField txtPhone, JComboBox comboBox);
    public void findDiemThi(String maSV, DefaultTableModel defaultTableModel);
    public Vector<String> chon();
    public void locGioi(JCheckBox cbKha, JCheckBox cbTrungBinh, DefaultTableModel defaultTableModel);
    public void locKha(JCheckBox cbGioi, JCheckBox cbTrungBinh, DefaultTableModel defaultTableModel);
    public void locTrungBinh(JCheckBox cbGioi, JCheckBox cbKha, DefaultTableModel defaultTableModel);
}

