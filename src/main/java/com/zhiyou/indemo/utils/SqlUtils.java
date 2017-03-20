/**
 * 
 */
package com.zhiyou.indemo.utils;

import java.util.List;

import com.zhiyou.indemo.constants.DBKeys;

/**
 * @author longWH
 *
 */
public class SqlUtils {

	/**
	 * 创建表
	 * 
	 * @return
	 */
	public static String createGoods() {
		StringBuilder builder = new StringBuilder();
		builder.append("CREATE TABLE ");
		builder.append(DBKeys.tableGoods);
		builder.append(" (");
		builder.append(DBKeys.id);
		builder.append(" CHAR(10) NOT NULL,");
		builder.append(DBKeys.name);
		builder.append(" VARCHAR(20),");
		builder.append(DBKeys.brand);
		builder.append(" VARCHAR(20),");
		builder.append(DBKeys.price);
		builder.append(" FLOAT UNSIGNED,");
		builder.append(DBKeys.amount);
		builder.append(" SMALLINT UNSIGNED,");
		builder.append(" PRIMARY KEY");
		builder.append("(");
		builder.append(DBKeys.id);
		builder.append(")");
		builder.append(" );");

		return builder.toString();
	}

	/**
	 * 插入数据
	 * 
	 * @param tableName
	 * @param valuesClause
	 * @param columns
	 * @return
	 */
	public static String addSql(String tableName, String valuesClause, String[] columns) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(tableName);
		builder.append(" (");
		int len = columns.length;
		for (int index = 0; index < len; index++) {
			builder.append(columns[index]);
			if (index != len - 1) {
				builder.append(",");
			}
		}
		builder.append(")");
		builder.append(" VALUES ");
		builder.append("(");
		builder.append(valuesClause);
		builder.append(")");

		return builder.toString();
	}

	/**
	 * @param tableName
	 *            表的名字
	 * @param columns
	 *            : 需要查询的列的集合
	 * @param whereClause
	 *            查询的条件
	 * @return
	 */
	public static String querySql(List<String> columns,String tableName,  String whereClause) {
		StringBuilder builder = new StringBuilder();
		if (columns != null) {
			for (int index = 0, size = columns.size(); index < size; index++) {
				builder.append(columns.get(index));
				if (index != size - 1) {
					builder.append(",");
				}
			}
		}

		String columnClause = builder.toString();
		return querySql(tableName, columnClause, whereClause);
	}

	/**
	 * @param tableName
	 * @param columnsClause
	 *            需要查询的列的字符串
	 * @param whereClause
	 * @return
	 */
	public static String querySql(String tableName, String columnsClause, String whereClause) {

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		if (columnsClause != null && !"".equals(columnsClause)) {
			builder.append(columnsClause);
		} else {
			builder.append("*");
		}
		builder.append(" FROM ");
		builder.append(tableName);
		if (whereClause != null && !"".equals(whereClause)) {
			builder.append(" WHERE ");
			builder.append(whereClause);
		}
		builder.append(";");

		return builder.toString();
	}

	/**
	 * @param tableName
	 * @param whereClause
	 * @return
	 */
	public static String deleteSql(String tableName, String whereClause) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(tableName);
		if (whereClause != null && !whereClause.isEmpty()) {
			builder.append(" WHERE ");
			builder.append(whereClause);
		}
		return builder.toString();
	}

	/**
	 * @param tableName
	 * @param columnkeys
	 * @param whereClause
	 * @return
	 */
	public static String update(String tableName, String[] columnkeys, String whereClause) {
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE ");
		builder.append(tableName);
		builder.append(" SET ");
		int len = columnkeys.length;
		for (int i = 0; i < len; i++) {
			builder.append(columnkeys[i]);
			builder.append(" = ?");
			if (i != len - 1) {
				builder.append(",");
			}
		}
		builder.append(" WHERE ");
		builder.append(whereClause);
		
		return builder.toString();
	}
	
}
