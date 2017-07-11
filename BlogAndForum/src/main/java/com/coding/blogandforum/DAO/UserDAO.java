package com.coding.blogandforum.DAO;

import java.util.List;

import com.coding.blogandforum.model.Users;

public interface UserDAO {

	public boolean addOrUpdateUser(Users user);
	
	public boolean deleteUser(Users user);
	
	public List<Users> getListOFUsers();
	
	public Users getParticularUserbyUserName(String userName);
	
	public Users getParticularUser(int user_id);
	
	public Users validateUser(String email, String password);
}
