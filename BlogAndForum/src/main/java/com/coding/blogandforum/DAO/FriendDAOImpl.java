package com.coding.blogandforum.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coding.blogandforum.model.Friend;

@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	/*public FriendDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public FriendDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}*/

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
	public boolean deleteFriend(String userName, String friendName) {
		try
		{
			Friend friend = new Friend();
			friend.setUserName(userName);
			friend.setFriendName(friendName);
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
	public List<Friend> getListOfMyFriends(String userName) {
		try
		{
			/*String hql = "from Friend";
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			List<Friend> friendlist = query.list();
			session.close();
			return friendlist;*/
			
			String hql1 = "select friendName  from Friend where userName='" + userName + "' and status = 'ACCEPTED' ";
			String hql2 = "select userName  from Friend where friendName='" + userName + "' and status = 'ACCEPTED' ";
			
			System.out.println("getListOfMyFriends hql1 :"+hql1);
			System.out.println("getListOfMyFriends hql2 :"+hql2);
			
			List<Friend> list1 = sessionFactory.getCurrentSession().createQuery(hql1).list();
			List<Friend> list2 = sessionFactory.getCurrentSession().createQuery(hql2).list();
			list1.addAll(list2);
			
			return list1;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getListOfMyFriends of FriendDAOImpl");
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

	@Override
	@Transactional
	public Friend getFriendWithUserNameAndFriendName(String userName, String friendName) {
		try{
			return (Friend) sessionFactory.getCurrentSession().createCriteria(Friend.class)
					.add(Restrictions.eq("userName", userName))
					.add(Restrictions.eq("friendName", friendName)).uniqueResult();
		}
		catch(Exception e){
			System.out.println("Exception in getFriendWithUserNameAndFriendName of FriendDAOImpl");
			e.printStackTrace();
			return null;
		}
	}

}
