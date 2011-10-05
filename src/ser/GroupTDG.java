package ser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GroupTDG {

	public static final String BASE_NAME  = "group";
	public static final String TABLE      = DbRegistry.getTablePrefix()+BASE_NAME;
	public static final String SELECT_ALL = "SELECT * FROM " + TABLE + ";";
	public static final String SELECT     = "SELECT * FROM " + TABLE + " WHERE `groupName` = ?;";
	public static final String INSERT     = "INSERT INTO " + TABLE + " (`groupName`, `groupDescription`) VALUES(?,?);"; 
	public static final String UPDATE     = "UPDATE " + TABLE + " SET `groupDescription` = ?  WHERE `groupName` = ?;";
	public static final String DELETE	  = "DELETE FROM " + TABLE + " WHERE groupName = ?;";
	
	private GroupTDG(){}

	
	public static ResultSet findAll() throws SQLException{
		
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
	public static ResultSet find(String groupName) throws SQLException{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ResultSet rs = ps.executeQuery();
		ps.setString(1, groupName);
		return rs;
	}
	
	public static void insert(String groupName, String groupDescription) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setString(1, groupName);
		ps.setString(2, groupDescription);
		ps.execute();
	}

	public static int update(String groupName, String groupDescription) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		ps.setString(1, groupDescription);
		ps.setString(2, groupName);
		return ps.executeUpdate();		
	}

	public static int delete(String groupName) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setString(1, groupName);
		return ps.executeUpdate();		
	}
	

}
