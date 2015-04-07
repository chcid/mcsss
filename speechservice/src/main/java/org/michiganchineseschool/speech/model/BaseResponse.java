package org.michiganchineseschool.speech.model;

import java.io.Serializable;
import java.util.List;

public class BaseResponse implements Serializable {
	static final long serialVersionUID = 1l;
	List<String> messages;
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
