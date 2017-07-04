package com.coding.blogandforum;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;

import com.coding.blogandforum.DAO.UserDAO;
import com.coding.blogandforum.model.Users;

@WebAppConfiguration
public class UserTest {
	
	public static void main(String[] args) 
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		
		Users user = (Users)context.getBean("user");
		UserDAO userDAO = (UserDAO)context.getBean("userDAO");
		
		// Add an User
		//user.setUser_id(1002);
		user.setName("User3");
		user.setPassword("user3");
		user.setEmail("user3@gmail.com");
		user.setRole("Student");
		user.setStatus("APPROVED");
		user.setIsOnline("Yes");
		userDAO.addOrUpdateUser(user);
		System.out.println("user added successfully");
		
		/*//Delete an User
		Users deleteuser = userDAO.getParticularUser(1002);
		userDAO.deleteUser(deleteuser);
		System.out.println("user deleted successfully");
		
		// To show list of Users
		List<Users> list = userDAO.getListOFUsers();
		for(Users item : list){
			System.out.println(item.getUser_id());
			System.out.println(item.getName());
			System.out.println(item.getEmail());
			System.out.println(item.getPassword());
			System.out.println(item.getRole());
			System.out.println(item.getStatus());
			System.out.println(item.getIsOnline()+"\n");
		}
		
		// Update an user
		Users updateuser = userDAO.getParticularUser(1001);
		//updateuser.setUser_id(1005);
		updateuser.setName("User5");
		updateuser.setPassword("user5");
		updateuser.setEmail("user5@gmail.com");
		updateuser.setRole("Student");
		updateuser.setStatus("APPROVED");
		updateuser.setIsOnline("Yes");
		userDAO.addOrUpdateUser(updateuser);
		System.out.println("user updated successfully");*/
		
	}	
}