package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	public static Connection getConnection()
	{
		Connection conn=null;
		Properties prop=new Properties();
		String fileName=JDBCTemplate.class.getResource("./driver.properties").getPath();
		/*System.out.println(fileName);*/
		try {
			prop.load(new FileReader(fileName));
			Class.forName(prop.getProperty("driver"));
			conn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pw"));
			conn.setAutoCommit(false);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return conn;		
	}
	
	public static void close(Connection conn)
	{
		try {
			if(!conn.isClosed()&&conn!=null)
			{
				conn.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt)
	{
		try {
			if(!stmt.isClosed()&&stmt!=null)
			{
				stmt.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs)
	{
		try {
			if(!rs.isClosed()&&rs!=null)
			{
				rs.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn)
	{
		try {
			if(!conn.isClosed()&&conn!=null)
			{
				conn.commit();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn)
	{
		try {
			if(!conn.isClosed()&&conn!=null)
			{
				conn.rollback();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}









