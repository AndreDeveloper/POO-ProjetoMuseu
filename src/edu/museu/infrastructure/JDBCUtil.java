package edu.museu.infrastructure;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost/asgardprint01";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "sql1234";
	private static JDBCUtil instancia;
	private Connection con;

	public JDBCUtil() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static JDBCUtil getInstancia() {
		if (instancia == null) {
			instancia = new JDBCUtil();
		}
		return instancia;
	}

	public Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}

	public void close() {
		try {
			if (con != null) {
				con.close();
			}
			con = null;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
