/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_Impl;

import java.util.List;
import model.MonHoc;

/**
 *
 * @author PC ACER
 */
public interface IMonHocDao extends GenericDao<MonHoc>{
    public List<MonHoc> findAll();
    public List<MonHoc> find(String maMH);
}
