package ser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.SoenEAConnection;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserTDG {
	
	public static final String BASE_NAME  = "user";
	public static final String TABLE      = DbRegistry.getTablePrefix()+BASE_NAME;
	public static final String SELECT_ALL = "SELECT * FROM " + TABLE + ";";
	public static final String SELECT     = "SELECT * FROM " + TABLE + " WHERE userName = ?;";
	public static final String INSERT     = "INSERT INTO " + TABLE + " (`userName`, `firstName`, `lastName`, `password`, `isAdmin`, `groupName`) VALUES(?,?,?,?,?,?);"; 
	public static final String UPDATE     = "UPDATE " + TABLE + " SET (`firstName` = ?, `lastName` = ?, `userName` = ?, `password` = ?, `isAdmin` = ? ) WHERE `userName` = ?;";
	public static final String DELETE	  = "DELETE FROM " + TABLE + " WHERE `userName` = ?;";
	
			
			
	private UserTDG() {
	}

	public static ResultSet findAll()throws SQLException {

		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		ResultSet rs = ps.executeQuery();

		return rs;
	}
	
	public static ResultSet find(String userName)	throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	
	public static void insert(String firstName, String lastName, String userName, String password, String groupName, boolean isAdmin)	throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, userName);
		ps.setString(4, password);
		ps.setBoolean(5, isAdmin);
		ps.setString(6, groupName);

		ps.execute();
	}
	
	public static int update(String firstName, String lastName, String userName, String password, String groupName, boolean isAdmin)	throws SQLException {
			
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, userName);
		ps.setString(4, password);
		ps.setBoolean(5, isAdmin);
		ps.setString(6, userName);
		return ps.executeUpdate();
	}

	public static int delete(String userName) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setString(1, userName);
		return ps.executeUpdate();
	}
}
