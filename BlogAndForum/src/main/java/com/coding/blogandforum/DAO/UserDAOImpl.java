package com.coding.blogandforum.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.coding.blogandforum.model.Users;


@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	/*public UserDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}*/
	
	@Transactional
	public boolean addOrUpdateUser(Users user) {
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in addOrUpdateUser of UserDAOImpl");
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean deleteUser(Users user) {
		try
		{
			//Users user = (Users) sessionFactory.getCurrentSession().get(Users.class, user_id);
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in deleteUser of UserDAOImpl");
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public List<Users> getListOFUsers() {
		try
		{
			String hql = "from Users";
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			List<Users> userlist = query.list();
			session.close();
			return userlist;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getListOFUsers of UserDAOImpl");
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public Users getParticularUser(int user_id) {
		try
		{
			return (Users) sessionFactory.getCurrentSession().get(Users.class, user_id);	
		}
		catch(Exception e)
		{
			System.out.println("Exception in getParticularUser of UserDAOImpl");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@Transactional
	public Users validateUser(String email, String password) {
		try
		{
			Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Users.class)
						.add(Restrictions.eq("email", email))
						.add(Restrictions.eq("password", password));
			Users user = (Users) cr.uniqueResult();
			session.close();
			return user;
		}
		catch(Exception e)
		{
			System.out.println("Exception in validateUser of UserDAOImpl");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Users getParticularUserbyUserName(String userName) {
		try{
			return (Users) sessionFactory.getCurrentSession().createCriteria(Users.class)
					.add(Restrictions.eq("userName", userName)).uniqueResult();
		}
		catch(Exception e){
			System.out.println("Exception in getParticularUserbyUserName");
			e.printStackTrace();
			return null;
		}
	}

	
}
