package com.gy.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	private static String driverName;
	private static String url;
	private static String username;
	private static String password;
	
	/**
	 * 获得连接 
	 * @return
	 */
	public static Connection getConnection() {
		try {
		  //用流读入properties配置文件
          InputStream inputStream = JDBCUtil.class.getClassLoader()
                  .getResourceAsStream("jdbc.properties");
          Properties properties = new Properties();
          
          //从输入字节流读取属性列表（键和元素对）
          properties.load(inputStream);
          
          //用此属性列表中指定的键搜索属性，获取驱动，url，username，password
          driverName = properties.getProperty("driverName").trim();
          url = properties.getProperty("url").trim();
          username = properties.getProperty("username").trim();
          password = properties.getProperty("password").trim();
        
          Class.forName(driverName);
          return DriverManager.getConnection(url, username, password);
          
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 关闭连接，无返回集
	 */
	public static void close(Statement stmt,Connection conn){
		if(stmt != null){
			try {
				stmt.close();
				stmt = null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		if(conn != null){
			try {
				conn.close();
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 关闭连接，有返回集
	 */
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		if(rs != null){
			try {
				rs.close();
				rs = null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		if(stmt != null){
			try {
				stmt.close();
				stmt = null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		if(conn != null){
			try {
				conn.close();
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}