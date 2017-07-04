package com.coding.blogandforum.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coding.blogandforum.model.Friend;

@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public FriendDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public FriendDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public boolean addOrUpdateFriend(Friend friend) {
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in addOrUpdateFriend of FriendDAOImpl");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteFriend(Friend friend) {
		try
		{
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in deleteFriend of FriendDAOImpl");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<Friend> getListOfFriend() {
		try
		{
			String hql = "from Friend";
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			List<Friend> friendlist = query.list();
			session.close();
			return friendlist;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getListOfFriend of FriendDAOImpl");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Friend getParticularFriend(int friend_id) {
		try
		{
			return (Friend) sessionFactory.getCurrentSession().get(Friend.class, friend_id);	
		}
		catch(Exception e)
		{
			System.out.println("Exception in getParticularFriend of FriendDAOImpl");
			e.printStackTrace();
			return null;
		}
	}

}
