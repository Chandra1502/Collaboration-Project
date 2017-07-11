package com.coding.blogandforum.DAO;

import java.util.List;

import com.coding.blogandforum.model.BlogComment;

public interface BlogCommentDAO {
	
	public boolean addOrUpdateBlogComment(BlogComment blogComment);
	
	public boolean deleteBlogComment(BlogComment blogComment);
	
	public BlogComment getParticulatBlogComment(int bComment_id);
	
	public List<BlogComment> getParticularBlogCommentsList(int blog_id);

}
