package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DSUtils {
	private static ComboPooledDataSource cp=new ComboPooledDataSource();
	private  static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	public static Connection getConnection(){
		if(tl.get()!=null){
			return tl.get();
		}
		try {
			return cp.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static DataSource getDataSource(){
		return cp;
	}
	public static QueryRunner getQueryRunner(){
		return new QueryRunner(getDataSource());
	}
	
	public static void tBegin() throws SQLException{
		tl.set(cp.getConnection());
		tl.get().setAutoCommit(false);
	}
	
	public static void tCommit() throws SQLException{
		Connection con=tl.get();
		if(con==null){
			throw new SQLException("���ݿ����Ӳ���Ϊ��");
		}
		con.commit();
		con.close();
		tl.remove();
	}
	
	public static void tRollBack() throws SQLException {
		Connection con=tl.get();
		if(con==null){
			throw new SQLException("���ݿ����Ӳ���Ϊ��");
		}
		con.rollback();
		con.close();
		tl.remove();
	}
	
}
