package com.coding.blogandforum.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coding.blogandforum.model.ForumComment;

@Repository("forumCommentDAO")
public class ForumCommentDAOImpl implements ForumCommentDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public boolean addOrUpdateForumComment(ForumComment forumComment) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(forumComment);
			return true;
		}
		catch(Exception e){
			System.out.println("Exception in addOrUpdateForumComment");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteForumComment(ForumComment forumComment) {
		try{
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		}
		catch(Exception e){
			System.out.println("Exception in deleteForumComment");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ForumComment getParticularForumComment(int fComment_id) {
		try{
			return (ForumComment) sessionFactory.getCurrentSession().get(ForumComment.class, fComment_id);
		}
		catch(Exception e){
			System.out.println("Exception in getParticularForumComment");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public List<ForumComment> getParticularForumCommentsList(int forum_id) {
		try{
			return sessionFactory.getCurrentSession().createCriteria(ForumComment.class)
			.add(Restrictions.eq("forum_id", forum_id)).list();
		}
		catch(Exception e){
			System.out.println("Exception in getParticularForumCommentsList");
			e.printStackTrace();
			return null;
		}
	}

}
