package com.coding.blogandforum.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.coding.blogandforum.model.Message;
import com.coding.blogandforum.model.OutputMessage;

@Controller
public class ChatController {

	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	@MessageMapping("/chat_forum")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message){
		logger.debug("->->->->-> Calling the method sendMessage ->->->->->");
		System.out.println("Message: "+message.getMessage());
		return new OutputMessage(message,new Date());  //appending current date
		
}
}
