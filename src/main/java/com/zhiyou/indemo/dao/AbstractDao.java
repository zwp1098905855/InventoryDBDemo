/**
 * 
 */
package com.zhiyou.indemo.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.zhiyou.indemo.constants.DBKeys;
import com.zhiyou.indemo.utils.DBUtils;
import com.zhiyou.indemo.utils.SqlUtils;

/**
 * @author longWH
 *
 */
public abstract class AbstractDao<E> implements IDao<E>, ISql {
	public final int index = 1;

	public void creatTable() {
		String sql = sqlCreateTable();
		System.out.println("����goods��sql: " + sql);
		DBUtils.execute(sql);
	}

	/**
	 * ����һ������
	 */
	public void add(E e) {
		String sql = getInsertSql();
		System.out.println("add: " + sql);
		Connection connection = null;
		connection = DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			writeEntity(e, statement);
			statement.execute();
			statement.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DBUtils.closeConn(connection);
		}

	}

	/**
	 * ʹ�������������,һ����Ӷ�������
	 */
	public void addAll(List<E> list) {
		String sql = getInsertSql();
		System.out.println("sql:" + sql);
		Connection connection = null;
		connection = DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			connection.setAutoCommit(false);// ȡ���Զ��ύ
			for (E e : list) {
				writeEntity(e, statement);
				statement.addBatch();// ��ӵ�������,Ŀ����Ϊ�˻���
			}
			statement.executeBatch();// ��������ύ,�ǰ���һ��������ִ��
			statement.close();// �ر�statement
			connection.commit();// �ύ��������
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();// ������ִ�������ع�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);// �ָ��Զ��ύ
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtils.closeConn(connection);
		}
	}

	/**
	 * ����idɾ��ĳ������
	 */
	public void deleteById(String id) {
		String sql = sqlDeleteById(id);
		System.out.println("deleteById: "+sql);
		DBUtils.execute(sql);
	}

	/*
	 * ɾ������id�� ids�е�����
	 *
	 */
	public void deleteAllByIds(String... ids) {
		String sql = sqlDeleteByIds(ids);
		System.out.println("sql: " + sql);
		DBUtils.execute(sql);
	}

	/**
	 * ��������ɾ��
	 */
	public void deleteByCondition(String whereClause) {
		String sql = SqlUtils.deleteSql(getTableName(), whereClause);
		DBUtils.execute(sql);
	}

	/**
	 * ��������
	 */
	public void update(String[] keys, Object[] newValues, String whereClause) {
		String sql = SqlUtils.update(getTableName(), keys, whereClause);
		System.out.println("update:"+sql);
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			for (int i = 0, len = newValues.length; i < len; i++) {
				statement.setObject(i + 1, newValues[i]);
			}
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConn(connection);
		}
	}

	/**
	 * ����id��ѯ
	 */
	public E selectById(String id) {
		String sql = SqlUtils.querySql(getTableName(), null, String.format("%s=%s%s%s", DBKeys.id, "'",id,"'"));
		return doQuery(sql);
	}

	/**
	 * ����������ѯһ��
	 */
	public E selectByCondition(String whereClause) {
		String sql = SqlUtils.querySql(getTableName(), null, whereClause);
		return doQuery(sql);
	}

	/**
	 * ��ѯ����
	 */
	public List<E> selectAll() {
		String sql = SqlUtils.querySql(getTableName(), null, null);
		System.out.println("selectAll: " + sql);
		return readAllEntities(sql);
	}

	/**
	 * ��ѯĳЩ��
	 */
	public List<E> selectAllColumns(Class<E> cls, String columnsClause) {
		return selectAllColumns(cls, columnsClause, null);
	}

	/**
	 * ʹ���ֶβ�ѯĳЩ��
	 */
	public List<E> selectAllColumns(Class<E> cls, String... columns) {
		return selectAllColumns(cls, columns,null);
	}

	/**
	 * ʹ���ֶ������������ѯĳЩ��
	 */
	public List<E> selectAllColumns(Class<E> cls, String[] columns, String whereClause) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0, len = columns.length; i < len; i++) {
			builder.append(columns[i]);
			if (i != len - 1) {
				builder.append(",");
			}
		}

		return selectAllColumns(cls, builder.toString(), whereClause);
	}

	/*
	 * ʹ�÷����ѯĳЩ��ȷ������
	 */
	public List<E> selectAllColumns(Class<E> cls, String columnsClause, String whereClause) {
		String sql = SqlUtils.querySql(getTableName(), columnsClause, whereClause);
		System.out.println("selectAllColumns: "+sql);
		Connection connection = null;
		List<E> list = new ArrayList<E>();
		try {
			connection = DBUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rst = statement.executeQuery();
			ResultSetMetaData metaData = rst.getMetaData();
			int count = metaData.getColumnCount();// Ԫ����
			if (count == 0) {
				return list;
			}
			String[] columnNames = new String[count];
			for (int index = 1; index <= count; index++) {
				columnNames[index - 1] = metaData.getColumnName(index);
			}
			while (rst.next()) {
				E e = cls.newInstance();
				for (int i = 0, len = columnNames.length; i < len; i++) {
					Field field = cls.getDeclaredField(columnNames[i]);
					field.setAccessible(true);
					field.set(e, rst.getObject(i + 1));
				}
				list.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConn(connection);
		}

		return list;
	}

	/**
	 * The method querySql(String, List<String>, String) is ambiguous for the
	 * type SqlUtils ���SqlUtils.querySql() �����ĵڶ���������null,
	 * ���޷�ʶ����,��ΪSqlUtils�������������ƥ��
	 */
	public List<E> selectAllByCondition(String whereClause) {
		String sql = SqlUtils.querySql(DBKeys.tableGoods, "", whereClause);
		List<E> list = readAllEntities(sql);
		return list;
	}

	/**
	 * @param sql
	 * @return
	 */
	public List<E> readAllEntities(String sql) {
		Connection connection = null;
		connection = DBUtils.getConnection();
		List<E> list = new ArrayList<E>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rst = statement.executeQuery();

			while (rst.next()) {
				E e = readEntity(rst);
				list.add(e);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConn(connection);
		}
		return list;
	}

	private E doQuery(String sql) {
		Connection connection = null;
		connection = DBUtils.getConnection();
		System.out.println("doQuery: "+sql);
		E e = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rst = statement.executeQuery();
			if (rst.next()) {
				e = readEntity(rst);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			DBUtils.closeConn(connection);
		}

		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhiyou.indemo.dao.ISql#sqlDeleteById(java.lang.String)
	 */
	public String sqlDeleteById(String id) {
		return SqlUtils.deleteSql(getTableName(),
				String.format("%s=%s%s%s", DBKeys.id, "'", id, "'"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zhiyou.indemo.dao.AbstractDao#getDeleteByIdsSql(java.lang.String[])
	 */
	public String sqlDeleteByIds(String... ids) {
		StringBuilder builder = new StringBuilder();
		builder.append(DBKeys.id);
		builder.append(" IN ");
		builder.append("(");
		for (int index = 0, len = ids.length; index < len; index++) {
			builder.append("'");
			builder.append(ids[index]);
			builder.append("'");
			if (index != len - 1) {
				builder.append(",");
			}
		}
		builder.append(");");
		return SqlUtils.deleteSql(getTableName(), builder.toString());
	}

	/**
	 * ��������ظ�,�������
	 * 
	 * @return
	 */
	public String getInsertSql() {
		String[] columns = getColumns();
		StringBuilder builder = new StringBuilder();
		for (int i = 0, len = columns.length; i < len; i++) {
			builder.append("?");
			if (i != len - 1) {
				builder.append(", ");
			}
		}
		return SqlUtils.addSql(getTableName(), builder.toString(), columns);
	}
}
