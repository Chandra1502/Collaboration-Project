package com.coding.blogandforum.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.coding.blogandforum.DAO.BlogDAO;
import com.coding.blogandforum.DAO.BlogDAOImpl;
import com.coding.blogandforum.DAO.ForumDAO;
import com.coding.blogandforum.DAO.ForumDAOImpl;
import com.coding.blogandforum.DAO.FriendDAO;
import com.coding.blogandforum.DAO.FriendDAOImpl;
import com.coding.blogandforum.DAO.JobDAO;
import com.coding.blogandforum.DAO.JobDAOImpl;
import com.coding.blogandforum.DAO.UserDAO;
import com.coding.blogandforum.DAO.UserDAOImpl;
import com.coding.blogandforum.model.Blog;
import com.coding.blogandforum.model.Forum;
import com.coding.blogandforum.model.Friend;
import com.coding.blogandforum.model.Job;
import com.coding.blogandforum.model.Users;

@Configuration
@EnableTransactionManagement
@ComponentScan("com")
public class DBConfig {
	
	@Bean("dataSource")
	public DataSource getDataSource()
	{
		//creating an Object for adding Database related properties
				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
				dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/XE");
				dataSource.setUsername("collab");
				dataSource.setPassword("collab");
				System.out.println("DataSource properties");
				return dataSource;
	}
	
	private Properties getHibernateProperties()
	{
		//creating an object for adding Hibernate related properties
				Properties properties = new Properties();
				properties.setProperty("hibernate.hbm2ddl.auto", "update");
				properties.put("hibernate.show_sql", "true");
				properties.put("hibernate.format_sql", "true");
				properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
				System.out.println("hibernate properties");
				return properties;
	}
	
	@Bean("sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		/*sessionBuilder.addAnnotatedClass(Users.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Friend.class);*/
		sessionBuilder.scanPackages("com.coding.blogandforum.model");
		System.out.println("Session Factory Object Created");
		return sessionBuilder.buildSessionFactory();
	}
	
	
	@Bean("transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("Transaction Manager");
		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
	
	/*@Bean("user")
	public Users getUsers()
	{
		System.out.println("user");
		return new Users();
	}
	
	@Bean("job")
	public Job getJob()
	{
		return new Job();
	}
	
	@Bean("blog")
	public Blog getBlog()
	{
		return new Blog();
	}
	
	@Bean("forum")
	public Forum getForum()
	{
		return new Forum();
	}
	
	@Bean("friend")
	public Friend getFriend()
	{
		return new Friend();
	}*/
	
	/*@Bean("userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory)
	{
		System.out.println("userDAO");
		return new UserDAOImpl(sessionFactory);
	}
	
	@Bean("jobDAO")
	public JobDAO getJobDAO(SessionFactory sessionFactory)
	{
		return new JobDAOImpl(sessionFactory);
	}
	
	@Bean("blogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory)
	{
		return new BlogDAOImpl(sessionFactory);
	}
	
	@Bean("forumDAO")
	public ForumDAO getForumDAO(SessionFactory sessionFactory)
	{
		return new ForumDAOImpl(sessionFactory);
	}
	
	@Bean("friendDAO")
	public FriendDAO getFriendDAO(SessionFactory sessionFactory)
	{
		return new FriendDAOImpl(sessionFactory);
	}*/
}
