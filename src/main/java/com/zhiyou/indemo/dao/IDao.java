/**
 * 
 */
package com.zhiyou.indemo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author longWH E ������ʿ������д, ��ֻ�Ǹ���ʶ
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
	 * @return ��������ѯ�������е�����,���е���
	 */
	List<E> selectAll();

	/**
	 * ��������ѯ���е���
	 * 
	 * @param whereClause
	 * @return
	 */
	List<E> selectAllByCondition(String whereClause);

	/**
	 * ��ѯ�������е�ĳЩ�ֶ�
	 * 
	 * @param columnsClause
	 * @return
	 */
	List<E> selectAllColumns(Class<E> cls,String columnsClause);
	
	List<E> selectAllColumns(Class<E> cls,String...columns);
	
	List<E> selectAllColumns(Class<E> cls,String[] columns,String whereClause);

	/**
	 * ����������ѯ���е�ĳЩ�ֶ�
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
