package hello;

public class ChatMessage {
	private String text;
	private String name;
	private Long messageId;

    public ChatMessage() {
    }

    public ChatMessage(String text) {
        this.text = text;
    }

    

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
    
    
}
