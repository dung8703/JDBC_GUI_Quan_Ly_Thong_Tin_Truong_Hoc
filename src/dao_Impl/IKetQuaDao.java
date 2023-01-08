/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_Impl;

import java.util.List;
import model.KetQua;

/**
 *
 * @author PC ACER
 */
public interface IKetQuaDao extends GenericDao<KetQua>{
    public List<KetQua> findAll();
    public List<KetQua> find(String maSV, String maMH);
}
