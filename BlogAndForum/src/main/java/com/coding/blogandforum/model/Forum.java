package com.coding.blogandforum.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@Component
public class Forum extends Status{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "generator")
	@SequenceGenerator(name = "generator" , sequenceName="forum_seq" , allocationSize=1)
	private int forum_id;
	
	private String forum_name,forum_content,status;
	
	private int user_id;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date create_date;
	
	/*@ManyToOne
	@JoinColumn(name="user_id",insertable=false, updatable=false)
	@JsonBackReference
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}*/

	public int getForum_id() {
		return forum_id;
	}

	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}

	public String getForum_name() {
		return forum_name;
	}

	public void setForum_name(String forum_name) {
		this.forum_name = forum_name;
	}

	public String getForum_content() {
		return forum_content;
	}

	public void setForum_content(String forum_content) {
		this.forum_content = forum_content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
