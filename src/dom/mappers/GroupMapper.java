package dom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ser.GroupTDG;
import dom.Group;

public class GroupMapper {

	private GroupMapper() {
	}

	public Group find(String groupName) {
		try {
			ResultSet rs = GroupTDG.find(groupName);
			if (rs.next())
				return new Group(rs.getString("groupName"),
						rs.getString("groupDescription"));
			else
				return null;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Group> findAll() {
		try {
			ResultSet rs = GroupTDG.findAll();
			List<Group> users = new ArrayList<Group>();
			while (rs.next())
				users.add(new Group(rs.getString("groupName"), rs
						.getString("groupDescription")));
			return users;
		} catch (SQLException e) {
			return null;
		}
	}

	public static void insert(Group group) {
		try {
			GroupTDG.insert(group.getName(), group.getDesc());
		} catch (SQLException e) {
			return;
		}
	}

	public static int update(Group group) {
		try {
			return GroupTDG.update(group.getName(), group.getDesc());
		} catch (SQLException e) {
			return 0;
		}
	}

	public static int delete(Group group) {
		try {
			return GroupTDG.delete(group.getName());
		} catch (SQLException e) {
			return 0;
		}
	}

}
