package com.coding.blogandforum.DAO;

import java.util.List;

import com.coding.blogandforum.model.Friend;

public interface FriendDAO {
	
public boolean addOrUpdateFriend(Friend friend);
	
	public boolean deleteFriend(String userName, String friendName);
	
	public List<Friend> getListOfMyFriends(String userName);
	
	public Friend getParticularFriend(int friend_id);
	
	public Friend getFriendWithUserNameAndFriendName(String userName, String friendName);
	
	

}
