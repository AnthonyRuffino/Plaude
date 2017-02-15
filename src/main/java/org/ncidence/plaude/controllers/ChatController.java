package org.ncidence.plaude.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.ncidence.plaude.messages.ChatMessage;
import org.ncidence.plaude.messages.IdentifyMessage;
import org.ncidence.plaude.messages.RosterMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	
	private AtomicLong messageIdFactory = new AtomicLong();

	private RosterMessage rosterMessage = new RosterMessage();

	@MessageMapping("/identify")
	@SendTo("/topic/roster")
	public RosterMessage identify(IdentifyMessage message) throws Exception {
		rosterMessage.getNames().add(message.getName());
		return rosterMessage;
	}

	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public ChatMessage chat(ChatMessage message) throws Exception {
		message.setMessageId(messageIdFactory.incrementAndGet());
		return message;
	}

}