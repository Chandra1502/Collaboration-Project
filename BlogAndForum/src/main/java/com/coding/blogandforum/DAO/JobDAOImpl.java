package com.coding.blogandforum.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coding.blogandforum.model.Job;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	/*public JobDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public JobDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}*/
	
	@Transactional
	public boolean addOrUpdateJob(Job job) {
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(job);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in addOrUpdateJob of JobDAOImpl");
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean deleteJob(Job job) {
		try
		{
			//Users user = (Users) sessionFactory.getCurrentSession().get(Users.class, user_id);
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in deleteJob of JobDAOImpl");
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public List<Job> getListOfJob() {
		try
		{
			String hql = "from Job";
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			List<Job> joblist = query.list();
			session.close();
			return joblist;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getListOfJob of JobDAOImpl");
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public Job getParticularJob(int job_id) {
		try
		{
			return (Job)sessionFactory.getCurrentSession().get(Job.class, job_id);	
		}
		catch(Exception e)
		{
			System.out.println("Exception in getParticularJob of JobDAOImpl");
			e.printStackTrace();
			return null;
		}
	}

}
