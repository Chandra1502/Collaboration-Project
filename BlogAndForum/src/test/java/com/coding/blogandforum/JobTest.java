package com.coding.blogandforum;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coding.blogandforum.DAO.JobDAO;
import com.coding.blogandforum.model.Job;

public class JobTest {
	
	public static void main(String[] args) 
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		
		Job job = (Job)context.getBean("job");
		JobDAO jobDAO = (JobDAO)context.getBean("jobDAO");
		
		// Add a Job Object
		job.setDescription("Architect");
		job.setPost_date(new Date());
		job.setProfile("Software");
		job.setQualification("B.E in CSE");
		job.setStatus("APPROVED");
		jobDAO.addOrUpdateJob(job);
		System.out.println("Job added successfully");
		
		// Delete a Job Object
		Job deletejob = jobDAO.getParticularJob(24);
		jobDAO.deleteJob(deletejob);
		System.out.println("Job deleted successfully");
		
		// List of all the job objects
		List<Job> joblist = jobDAO.getListOfJob();
		for(Job list : joblist){
			System.out.println(list.getJob_id());
			System.out.println(list.getDescription());
			System.out.println(list.getProfile());
			System.out.println(list.getQualification());
			System.out.println(list.getStatus());
			System.out.println(list.getPost_date());
		}
		
		// Update a Job Object
		Job updatejob = jobDAO.getParticularJob(22);
		updatejob.setPost_date(new Date());
		updatejob.setDescription("Architect");
		jobDAO.addOrUpdateJob(updatejob);
		System.out.println("Job updated successfully");
	}	
	
}
