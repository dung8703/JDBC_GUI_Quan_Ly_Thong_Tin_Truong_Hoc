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
import model.KetQua;
import model.MonHoc;
import model.SinhVien;

/**
 *
 * @author PC ACER
 */
public class KetQuaMapper implements RowMapper<KetQua>{

    @Override
    public KetQua mapRow(ResultSet res) {
        try {
            //set cho Sinh Vien
            SinhVien sv = new SinhVien();
            sv.setMaSV(res.getString("maSV"));
            //set cho Mon Hoc
            MonHoc mh = new MonHoc();
            mh.setMaMH(res.getString("maMH"));
            //sau đó mới set cho KetQua vì trong Ket Qua có thuộc tính SinhVien và Monhoc với quan hệ là has-A
            KetQua kq = new KetQua();
            kq.setSinhVien(sv);
            kq.setMonHoc(mh);
            kq.setDiemThi(res.getFloat("diemThi"));
            //thêm Ket Qua vào list
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(KetQuaMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
