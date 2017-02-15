package org.ncidence.plaude.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.ncidence.plaude.messages.ChatMessage;
import org.ncidence.plaude.messages.IdentifyMessage;
import org.ncidence.plaude.messages.RosterMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	AtomicLong messageIdFactory = new AtomicLong();

	RosterMessage rosterMessage = new RosterMessage();

	Map<String, String> userMap = new HashMap<>();

	@MessageMapping("/identify")
	@SendTo("/topic/roster")
	public RosterMessage identify(@Payload IdentifyMessage identifyMessage, SimpMessageHeaderAccessor headerAccessor)
			throws Exception {
		String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
		return identify(identifyMessage, sessionId);
	}

	RosterMessage identify(IdentifyMessage identifyMessage, String sessionId) throws Exception {
		userMap.put(sessionId, identifyMessage.getName());
		rosterMessage.setNames(new ArrayList<>(userMap.values()));
		return rosterMessage;
	}

	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public ChatMessage chat(ChatMessage message) throws Exception {
		message.setMessageId(messageIdFactory.incrementAndGet());
		return message;
	}

}