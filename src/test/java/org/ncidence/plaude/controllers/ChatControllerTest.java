package org.ncidence.plaude.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.ncidence.plaude.messages.ChatMessage;
import org.ncidence.plaude.messages.IdentifyMessage;

public class ChatControllerTest {

	ChatController chatController;

	@Before
	public void setUp() throws Exception {
		chatController = new ChatController();
	}

	@Test
	public void identifyIndex() throws Exception {
		chatController.identify(new IdentifyMessage("0"));
		chatController.identify(new IdentifyMessage("1"));
		chatController.identify(new IdentifyMessage("2"));
		chatController.identify(new IdentifyMessage("3"));
		assertEquals("2", chatController.rosterMessage.getNames().get(2));
	}
	
	@Test
	public void chatIdTest() throws Exception{
		chatController.chat(new ChatMessage("0"));
		chatController.chat(new ChatMessage("1"));
		chatController.chat(new ChatMessage("2"));
		ChatMessage chatMessage = chatController.chat(new ChatMessage("3"));
		Long expectedId = 4L;
		assertEquals(expectedId, chatMessage.getMessageId());
	}

}
