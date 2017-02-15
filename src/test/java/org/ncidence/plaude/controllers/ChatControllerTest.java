package org.ncidence.plaude.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.ncidence.plaude.messages.ChatMessage;
import org.ncidence.plaude.messages.IdentifyMessage;

public class ChatControllerTest {

	ChatController chatController;

	static final Integer INDENTIFY_TEST_NUMBER = 100;

	@Before
	public void setUp() throws Exception {
		chatController = new ChatController();
	}

	@Test
	public void identifyAlterTest() throws Exception {
		for (Integer userNumber = 0; userNumber < INDENTIFY_TEST_NUMBER; userNumber++) {
			chatController.identify(new IdentifyMessage(userNumber.toString()), userNumber.toString());
		}

		String idToAlter = "0";
		String newValue = "a";

		chatController.identify(new IdentifyMessage(newValue), idToAlter);

		String actualIdentity = chatController.userMap.get(idToAlter);

		assertEquals(newValue, actualIdentity);
	}

	@Test
	public void chatIdTest() throws Exception {
		chatController.chat(new ChatMessage("0"));
		chatController.chat(new ChatMessage("1"));
		chatController.chat(new ChatMessage("2"));
		ChatMessage chatMessage = chatController.chat(new ChatMessage("3"));
		Long expectedId = 4L;
		assertEquals(expectedId, chatMessage.getMessageId());
	}

}
