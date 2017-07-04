package com.coding.blogandforum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
@Component
public class Friend extends Status{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "generator")
	@SequenceGenerator(name = "generator" , sequenceName="friend_seq" , allocationSize=1)
	private int friend_id;
	
	private int user_id;
	
	private String status;
	
	/*@ManyToOne
	@JoinColumn(name="user_id",insertable=false, updatable=false)
	@JsonBackReference
	private Users user;*/

	public int getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}*/
	
}
