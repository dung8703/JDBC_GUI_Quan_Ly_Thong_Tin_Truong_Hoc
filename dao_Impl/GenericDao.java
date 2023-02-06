package dao_Impl;

import javax.swing.table.DefaultTableModel;
import mapper.RowMapper;

public interface GenericDao<T> {
    void Query(DefaultTableModel defaultTableModel, String sql, RowMapper<T> rowMapper, Object... parameters);
    void save(String sql, Object... parameters);
}
