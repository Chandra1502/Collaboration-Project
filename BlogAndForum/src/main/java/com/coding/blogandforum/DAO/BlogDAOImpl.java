package com.coding.blogandforum.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.coding.blogandforum.model.Blog;

@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	/*public BlogDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public BlogDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}*/
	
	@Transactional
	public boolean addOrUpdateBlog(Blog blog) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in addOrUpdateBlog of BlogDAOImpl");
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean deleteBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in deleteBlog of BlogDAOImpl");
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public List<Blog> getListOfBlog() {
		try
		{
			String hql = "from Blog";
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			List<Blog> bloglist = query.list();
			session.close();
			return bloglist;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getListOfBlog of BlogDAOImpl");
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public Blog getParticularBlog(int blogid) {
		try
		{
			return (Blog) sessionFactory.getCurrentSession().get(Blog.class, blogid);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getParticularBlog of BlogDAOImpl");
			e.printStackTrace();
			return null;
		}
	}

}
