package com.coding.blogandforum.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coding.blogandforum.model.BlogComment;

@Repository("blogCommentDAO")
public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public boolean addOrUpdateBlogComment(BlogComment blogComment) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
			return true;
		}
		catch(Exception e){
			System.out.println("Exception arised in addOrUpdateBlogComment");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteBlogComment(BlogComment blogComment) {
		try{
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e){
			System.out.println("Exception arised in deleteBlogComment");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public BlogComment getParticulatBlogComment(int bComment_id) {
		try{
			return (BlogComment) sessionFactory.getCurrentSession().get(BlogComment.class, bComment_id);
		}
		catch(Exception e){
			System.out.println("Exception arised in getParticulatBlogComment");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public List<BlogComment> getParticularBlogCommentsList(int blog_id) {
		try{
			return sessionFactory.getCurrentSession().createCriteria(BlogComment.class)
					.add(Restrictions.eq("blog_id", blog_id)).list();
		}
		catch(Exception e){
			System.out.println("Exception arised in getParticularBlogCommentsList");
			e.printStackTrace();
			return null;
		}
	}

}
