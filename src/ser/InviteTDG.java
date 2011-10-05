package ser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class InviteTDG {

	public static final String BASE_NAME  = "invite";
	public static final String TABLE      = DbRegistry.getTablePrefix()+BASE_NAME;
	public static final String SELECT_ALL_G_INVITES = "SELECT * FROM " + TABLE + " WHERE `groupName` = ?;";
	public static final String SELECT_ALL_U_INVITES = "SELECT * FROM " + TABLE + " WHERE `userName` = ?;";
	public static final String SELECT     = "SELECT * FROM " + TABLE + " WHERE userName = ? AND groupName = ?;";
	public static final String INSERT     = "INSERT INTO " + TABLE + " (`userName`, `groupName`) VALUES(?,?);"; 
	//public static final String UPDATE     = "UPDATE " + TABLE + " SET groupId = ? WHERE userName = ?;";
	public static final String DELETE	  = "DELETE FROM " + TABLE + " WHERE userName = ? AND groupName = ?;";
	
	private InviteTDG(){}

	
	public static ResultSet findAllInvitesOfUser(String userName) throws SQLException{
		
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL_U_INVITES);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();

		return rs;
	}

	public static ResultSet findAllInvitesOfGroup(String groupName) throws SQLException{
		
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL_G_INVITES);
		ps.setString(1, groupName);
		ResultSet rs = ps.executeQuery();

		return rs;
	}
	
	public static ResultSet find(String userName, String groupName) throws SQLException{
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ResultSet rs = ps.executeQuery();
		ps.setString(1, userName);
		ps.setString(2, groupName);
		return rs;
	}
	
	
	public static void insert(String userName, String groupName) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setString(1, userName);
		ps.setString(2, groupName);
		ps.execute();
		ps.close();
	}
/*
	public static int update(String userName, String groupName) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		ps.setString(2, userName);
		ps.setString(1, groupName);
		return ps.executeUpdate();		
	}
*/
	public static int delete(String userName, String groupName) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setString(1, userName);
		ps.setString(2, groupName);
		return ps.executeUpdate();		
	}
}
