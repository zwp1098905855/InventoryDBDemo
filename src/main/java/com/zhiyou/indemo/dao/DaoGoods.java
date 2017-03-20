/**
 * 
 */
package com.zhiyou.indemo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhiyou.indemo.bean.Goods;
import com.zhiyou.indemo.constants.DBKeys;
import com.zhiyou.indemo.utils.SqlUtils;

/**
 * @author longWH
 *
 */
public class DaoGoods extends AbstractDao<Goods> implements IDao<Goods> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhiyou.indemo.dao.AbstractDao#getCreateSql()
	 */
	public String sqlCreateTable() {
		return SqlUtils.createGoods();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhiyou.indemo.dao.ISql#getColumns()
	 */
	public String[] getColumns() {
		String[] columns = { DBKeys.id, DBKeys.name, DBKeys.brand, DBKeys.price, DBKeys.amount };
		return columns;
	}
	
	/**
	 * @param rst
	 * @param index
	 * @return
	 * @throws SQLException
	 */
	public Goods readEntity(ResultSet rst) throws SQLException {
		Goods goods = new Goods();
		goods.setId(rst.getString(index));
		goods.setName(rst.getString(index + 1));
		goods.setBrand(rst.getString(index + 2));
		goods.setPrice(rst.getFloat(index + 3));
		goods.setAmount(rst.getInt(index + 4));
		return goods;
	}
	
	/**
	 * 向statement中写数据(把数据存到statement)
	 * 
	 * @param goods
	 *            单个产品
	 * @param statement
	 * @throws SQLException
	 */
	public void writeEntity(Goods goods, PreparedStatement statement) {
		try {
			statement.setString(index, goods.getId());
			statement.setString(index + 1, goods.getName());
			statement.setString(index + 2, goods.getBrand());
			statement.setFloat(index + 3, goods.getPrice());
			statement.setInt(index + 4, goods.getAmount());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.zhiyou.indemo.dao.AbstractDao#getTableName()
	 */
	public String getTableName() {
		return DBKeys.tableGoods;
	}
}
