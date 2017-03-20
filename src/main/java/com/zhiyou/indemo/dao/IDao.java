/**
 * 
 */
package com.zhiyou.indemo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author longWH E 这个单词可以随便写, 它只是个标识
 */
public interface IDao<E> {

	void creatTable();

	void add(E e);

	void addAll(List<E> list);

	void deleteById(String id);

	void deleteAllByIds(String... ids);

	void deleteByCondition(String whereClause);

	void update(String[] keys, Object[] newValues, String whereClause);

	E selectById(String id);

	E selectByCondition(String whereClause);

	/**
	 * @return 无条件查询表中所有的数据,所有的列
	 */
	List<E> selectAll();

	/**
	 * 有条件查询所有的列
	 * 
	 * @param whereClause
	 * @return
	 */
	List<E> selectAllByCondition(String whereClause);

	/**
	 * 查询整个表中的某些字段
	 * 
	 * @param columnsClause
	 * @return
	 */
	List<E> selectAllColumns(Class<E> cls,String columnsClause);
	
	List<E> selectAllColumns(Class<E> cls,String...columns);
	
	List<E> selectAllColumns(Class<E> cls,String[] columns,String whereClause);

	/**
	 * 根据条件查询表中的某些字段
	 * 
	 * @param columnsClause
	 * @param whereClause
	 * @return
	 */
	List<E> selectAllColumns(Class<E>cls,String columnsClause, String whereClause);

	void writeEntity(E e, PreparedStatement statement);

	E readEntity(ResultSet rst) throws SQLException;

	List<E> readAllEntities(String sql);
}
