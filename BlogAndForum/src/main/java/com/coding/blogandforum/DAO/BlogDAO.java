package com.coding.blogandforum.DAO;

import java.util.List;

import com.coding.blogandforum.model.Blog;

public interface BlogDAO {
	
	public boolean addOrUpdateBlog(Blog blog);
	
	public boolean deleteBlog(Blog blog);
	
	public List<Blog> getListOfBlog();
	
	public Blog getParticularBlog(int blogid);

}
