/**
 * 
 */
package com.zhiyou.indemo.dao;

/**
 * @author longWH
 *
 */
public interface ISql {
	String sqlCreateTable();

	String sqlDeleteById(String id);

	String sqlDeleteByIds(String... ids);

	String getTableName();
	
	String[] getColumns();
}
