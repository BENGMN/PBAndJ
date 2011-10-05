package dom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ser.InviteTDG;
import dom.Invite;

public class InviteMapper {

	private InviteMapper() {
	}

	public Invite find(String userName, String groupName) {
		try {
			ResultSet rs = InviteTDG.find(userName, groupName);
			if (rs.next())
				return new Invite(rs.getString("userName"),
						rs.getString("groupName"));
			else
				return null;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Invite> findAllofUser(String userName) {
		try {
			ResultSet rs = InviteTDG.findAllInvitesOfUser(userName);
			List<Invite> users = new ArrayList<Invite>();
			while (rs.next())
				users.add(new Invite(rs.getString("userName"), rs
						.getString("groupName")));
			return users;
		} catch (SQLException e) {
			return null;
		}
	}

	public static void insert(Invite invite) {
		try {
			InviteTDG.insert(invite.getUserName(), invite.getGroupName());
		} catch (SQLException e) {
			return;
		}
	}

	/*
	 * public static int update(Invite invite) throws SQLException{ return
	 * InviteTDG.update(invite.getUserName(), invite.getGroupName()); }
	 */
	public static int delete(Invite invite) {
		try {
			return InviteTDG
					.delete(invite.getUserName(), invite.getGroupName());
		} catch (SQLException e) {
			return 0;
		}
	}

}
