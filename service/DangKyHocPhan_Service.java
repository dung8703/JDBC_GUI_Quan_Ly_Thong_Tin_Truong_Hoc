package service;

import dao_DaTa.DangKyHocPhanDao;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DangKyHocPhan_Service {
    static DangKyHocPhanDao dangKy = new DangKyHocPhanDao();
    
    public static void luaChon(JComboBox comboBox, JComboBox comboBox1){
        dangKy.chon(comboBox, comboBox1);
    }
    
    public static void themThongTin(String maSV, JComboBox comboBox, JLabel lbl7){
        dangKy.them(maSV, comboBox, lbl7);
    }
}
