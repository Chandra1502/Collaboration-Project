
package com.coding.blogandforum;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;

import com.coding.blogandforum.DAO.BlogDAO;
import com.coding.blogandforum.model.Blog;

@WebAppConfiguration
public class BlogTest {

	public static void main(String[] args) {


		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		
		Blog blog = (Blog)context.getBean("blog");
		BlogDAO blogDAO = (BlogDAO)context.getBean("blogDAO");
		
		blog.setBlog_name("Demo");
		blog.setBlog_content("Demo content");
		blog.setCreate_date(new Date());
		blog.setLikes(100);
		blog.setStatus("APPROVED");
		blogDAO.addOrUpdateBlog(blog);
		System.out.println("Blog added Successfully");

	}

}