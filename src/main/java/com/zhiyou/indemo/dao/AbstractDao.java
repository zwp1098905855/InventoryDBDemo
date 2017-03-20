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
		System.out.println("创建goods表sql: " + sql);
		DBUtils.execute(sql);
	}

	/**
	 * 插入一条数据
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
	 * 使用事务和批处理,一次添加多条数据
	 */
	public void addAll(List<E> list) {
		String sql = getInsertSql();
		System.out.println("sql:" + sql);
		Connection connection = null;
		connection = DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			connection.setAutoCommit(false);// 取消自动提交
			for (E e : list) {
				writeEntity(e, statement);
				statement.addBatch();// 添加到批处理,目的是为了缓存
			}
			statement.executeBatch();// 这个不是提交,是把这一批的数据执行
			statement.close();// 关闭statement
			connection.commit();// 提交整个事务
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();// 如果出现错误事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);// 恢复自动提交
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtils.closeConn(connection);
		}
	}

	/**
	 * 根据id删除某条数据
	 */
	public void deleteById(String id) {
		String sql = sqlDeleteById(id);
		System.out.println("deleteById: "+sql);
		DBUtils.execute(sql);
	}

	/*
	 * 删除所有id在 ids中的数据
	 *
	 */
	public void deleteAllByIds(String... ids) {
		String sql = sqlDeleteByIds(ids);
		System.out.println("sql: " + sql);
		DBUtils.execute(sql);
	}

	/**
	 * 根据条件删除
	 */
	public void deleteByCondition(String whereClause) {
		String sql = SqlUtils.deleteSql(getTableName(), whereClause);
		DBUtils.execute(sql);
	}

	/**
	 * 更新数据
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
	 * 根据id查询
	 */
	public E selectById(String id) {
		String sql = SqlUtils.querySql(getTableName(), null, String.format("%s=%s%s%s", DBKeys.id, "'",id,"'"));
		return doQuery(sql);
	}

	/**
	 * 根据条件查询一条
	 */
	public E selectByCondition(String whereClause) {
		String sql = SqlUtils.querySql(getTableName(), null, whereClause);
		return doQuery(sql);
	}

	/**
	 * 查询所有
	 */
	public List<E> selectAll() {
		String sql = SqlUtils.querySql(getTableName(), null, null);
		System.out.println("selectAll: " + sql);
		return readAllEntities(sql);
	}

	/**
	 * 查询某些列
	 */
	public List<E> selectAllColumns(Class<E> cls, String columnsClause) {
		return selectAllColumns(cls, columnsClause, null);
	}

	/**
	 * 使用字段查询某些列
	 */
	public List<E> selectAllColumns(Class<E> cls, String... columns) {
		return selectAllColumns(cls, columns,null);
	}

	/**
	 * 使用字段数组和条件查询某些列
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
	 * 使用反射查询某些不确定的列
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
			int count = metaData.getColumnCount();// 元数据
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
	 * type SqlUtils 如果SqlUtils.querySql() 方法的第二个参数是null,
	 * 则无法识别了,因为SqlUtils里的两个方法都匹配
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
	 * 处理代码重复,插入语句
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
