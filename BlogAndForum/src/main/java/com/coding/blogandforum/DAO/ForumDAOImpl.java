package com.coding.blogandforum.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.coding.blogandforum.model.Forum;

@EnableTransactionManagement
@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public ForumDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public boolean addOrUpdateForum(Forum forum) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in addOrUpdateForum of ForumDAOImpl");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteForum(Forum forum) {
		try
		{
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in deleteForum of ForumDAOImpl");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<Forum> getListOfForum() {
		try
		{
			String hql = "from Forum";
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			List<Forum> forumlist = query.list();
			session.close();
			return forumlist;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getListOfForum of ForumDAOImpl");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Forum getParticularForum(int forumid) {
		try
		{
			return (Forum) sessionFactory.getCurrentSession().get(Forum.class, forumid);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getParticularForum of ForumDAOImpl");
			e.printStackTrace();
			return null;
		}
	}

}
