package dom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dom.User;

import ser.*;

public class UserMapper {
	
	private UserMapper(){}
	
	public static User find(String userName){
		try{
		    ResultSet rs = UserTDG.find(userName);
			if(rs.next())
				return new User(rs.getString("firstName"),rs.getString("lastName"),rs.getString("userName"),rs.getString("password"),rs.getBoolean("isAdmin"),rs.getString("groupName"));
			else
				return null;		
		}
		catch (SQLException e){
			return null;
		}
		
	}
	
	public static List<User> findAll(){
		try{
			ResultSet rs = UserTDG.findAll();
			List<User> users = new ArrayList<User>();
			while(rs.next())
				users.add(new User(rs.getString("firstName"),rs.getString("lastName"),rs.getString("userName"),rs.getString("password"),rs.getBoolean("isAdmin"),rs.getString("groupName")));
			return users;
		}
		catch (SQLException e){
			return null;
		}	
	}
	
	public static void insert(User user){
		try {
			UserTDG.insert(user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword(), user.getGroupName(), user.isAdmin());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return;
		}
	}
	
	public static int update(User user){
		try {
			int temp = UserTDG.update(user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword(), user.getGroupName(), user.isAdmin());
			return temp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
	
	public static int delete(User user){
		try {
			int temp = UserTDG.delete(user.getUserName());
			return temp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

}
