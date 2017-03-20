/**
 * 
 */
package com.zhiyou.indemo.utils;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.zhiyou.indemo.constants.DBKeys;

/**
 * @author longWH 数据库工具类
 */
public class DBUtils {
	interface Keys {
		/**
		 * 驱动类
		 */
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String userPwd = "7777777";
		/**
		 * 数据库连接的url
		 */
		String url = "jdbc:mysql://localhost:3306/" + DBKeys.dbName+"?useSSL=false";
	}

	/**建立数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			
			/*Driver driver = (Driver) Class.forName(Keys.driver).newInstance();// 利用反射完成驱动类的加载
			DriverManager.registerDriver(driver);*/ //和Class.forName(Keys.driver);是一个意思
			
			Class.forName(Keys.driver);
			connection = (Connection) DriverManager.getConnection(Keys.url, Keys.userName, Keys.userPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/**关闭连接
	 * @param connection
	 */
	public static void closeConn(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param sql
	 */
	public static void execute(String sql) {
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Sql: "+sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConn(connection);
		}
	}

}
