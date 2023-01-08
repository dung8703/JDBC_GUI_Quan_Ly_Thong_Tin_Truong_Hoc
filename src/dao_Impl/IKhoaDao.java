/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_Impl;

import java.util.List;
import model.Khoa;

/**
 *
 * @author PC ACER
 */
public interface IKhoaDao extends GenericDao<Khoa>{
    public List<Khoa> findAll();
    public List<Khoa> find(String maKhoa);
}
