/**
 * 
 */
package com.zhiyou.indemo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhiyou.indemo.bean.User;

/**
 * @author longWH
 *
 */
public class DaoUser extends AbstractDao<User>{

	/* (non-Javadoc)
	 * @see com.zhiyou.indemo.dao.IDao#writeEntity(java.lang.Object, java.sql.PreparedStatement)
	 */
	public void writeEntity(User e, PreparedStatement statement) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.zhiyou.indemo.dao.IDao#readEntity(java.sql.ResultSet)
	 */
	public User readEntity(ResultSet rst) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.zhiyou.indemo.dao.ISql#sqlCreateTable()
	 */
	public String sqlCreateTable() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.zhiyou.indemo.dao.ISql#getTableName()
	 */
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.zhiyou.indemo.dao.ISql#getColumns()
	 */
	public String[] getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
