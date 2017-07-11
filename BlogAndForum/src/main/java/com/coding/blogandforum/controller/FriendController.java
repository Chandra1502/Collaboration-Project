package com.coding.blogandforum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coding.blogandforum.DAO.FriendDAO;
import com.coding.blogandforum.DAO.UserDAO;
import com.coding.blogandforum.model.Friend;

@RestController
public class FriendController {
	
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	Friend friend;
	
	// To get the List of Particular User's Friends
	@RequestMapping(value="/getListOfMyFriends", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getListOfMyFriends()
	{
		logger.debug("->->->->-> Inside getListOfMyFriends method ->->->->->");
		String loggedInUserName = (String) httpSession.getAttribute("loggedInUserName");
		logger.debug("UserName :"+loggedInUserName);
		List<Friend> myFriends = new ArrayList<Friend>();
		
		// To check if the User is Logged In or not
		if(loggedInUserName==null){
			friend.setErrCode("404");
			friend.setErrMessage("Please login to know who all are your friends!");
			myFriends.add(friend);
			return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
		}
		
		logger.debug("getting friends of : " + loggedInUserName);
		myFriends = friendDAO.getListOfMyFriends(loggedInUserName);
		
		// To check if the User has friends or not
		if (myFriends.isEmpty()) {
			logger.debug("Friends does not exsit for the user : " + loggedInUserName);
			friend.setErrCode("404");
			friend.setErrMessage("You does not have any friends");
			myFriends.add(friend);
		}
		
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
	}
	
	// To add a friend
	@RequestMapping(value="/addFriend/{friendName}", method=RequestMethod.POST)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendName") String friendName)
	{
		logger.debug("->->->->-> calling method sendFriendRequest ->->->->->");
		String loggedInUserName = (String) httpSession.getAttribute("loggedInUserName");
		logger.debug(loggedInUserName+" is sending friend request to "+friendName);
		
		// Check if the friend is available in Users table or not 
		if(isUserExist(friendName)==false){
			friend.setErrCode("404");
			friend.setErrMessage("No user exist with the id : "+friendName);
		}
		
		// Check if the user has already sent the friend request
		if(isRequestAlreadySent(friendName)){
			friend.setErrCode("404");
			friend.setErrMessage("You have already sent the friend request to : "+friendName);
		}
		
		// If previously not sent, send a new friend request
		else{
			logger.debug("->->->->-> Sending Friend Request ->->->->->");
			friend.setFriendName(friendName);
			friend.setUserName(loggedInUserName);
			friend.setStatus("NEW");
			friend.setIsOnline("NO");
			if(friendDAO.addOrUpdateFriend(friend)){
				logger.debug("->->->-> Friend Request sent successfully ->->->->");
				friend.setErrCode("200");
				friend.setErrMessage("Friend request sent Successfully");
			}
			else{
				logger.debug("->->->->-> Could not send Friend request ->->->->->");
				friend.setErrCode("404");
				friend.setErrMessage("Could not send Friend request. Please contact Admin!");
			}
			//logger.debug("->->->->-> Friend request sent Successfully ->->->->->");
		}
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	// Check if the friend is available in Users table or not 
	private boolean isUserExist(String friendName)
	{
		logger.debug("->->->->-> Inside isUserExist method ->->->->->");
		if(userDAO.getParticularUserbyUserName(friendName)==null){
			return false;
		}
		else{
			return true;
		}
	}
	
	// Check if the user has already sent the friend request
	private boolean isRequestAlreadySent(String friendName)
	{
		logger.debug("->->->->-> Inside isUserExist method ->->->->->");
		String loggedInUserName = (String) httpSession.getAttribute("loggedInUserName");
		logger.debug("UserName in isRequestAlreadySent :"+loggedInUserName);
		if(friendDAO.getFriendWithUserNameAndFriendName(loggedInUserName,friendName)==null){
			return false;
		}	
		else{
			return true;
		}
	}
	
	// Accepted or Reject or Un-friend a particular Friend request
	/*@PutMapping(value="/acceptFriend/")*/
	
	
	// Controller to update a friend
	@RequestMapping(value="/updateFriend/{friendid}", method=RequestMethod.PUT)
	public ResponseEntity<Friend> updateFriend(@PathVariable("friendid") int friendid, @RequestBody Friend friend)
	{
		Friend updatefriend = friendDAO.getParticularFriend(friendid);
		//updatefriend.setUser_id(friend.getUser_id());
		updatefriend.setStatus(friend.getStatus());
		friendDAO.addOrUpdateFriend(updatefriend);
		return new ResponseEntity<Friend>(updatefriend,HttpStatus.OK);
	}
}
