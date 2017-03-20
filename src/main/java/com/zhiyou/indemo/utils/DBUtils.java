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
 * @author longWH ���ݿ⹤����
 */
public class DBUtils {
	interface Keys {
		/**
		 * ������
		 */
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String userPwd = "7777777";
		/**
		 * ���ݿ����ӵ�url
		 */
		String url = "jdbc:mysql://localhost:3306/" + DBKeys.dbName+"?useSSL=false";
	}

	/**�������ݿ�����
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			
			/*Driver driver = (Driver) Class.forName(Keys.driver).newInstance();// ���÷������������ļ���
			DriverManager.registerDriver(driver);*/ //��Class.forName(Keys.driver);��һ����˼
			
			Class.forName(Keys.driver);
			connection = (Connection) DriverManager.getConnection(Keys.url, Keys.userName, Keys.userPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/**�ر�����
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
