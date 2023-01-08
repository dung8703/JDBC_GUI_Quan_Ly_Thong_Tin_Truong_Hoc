/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_Impl;

import java.util.List;
import model.Lop;

/**
 *
 * @author PC ACER
 */
public interface ILopDao extends GenericDao<Lop>{
    public List<Lop> findAll();
    public List<Lop> find(String maLop);
}
