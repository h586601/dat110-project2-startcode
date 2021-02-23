package no.hvl.dat110.messages;

import no.hvl.dat110.common.TODO;

/**
 * Message sent from client to create publish a message on a topic
 * 
 * Implements object variables - a topic and a message is required as described
 * in the project text
 * 
 * Constructor, get/set-methods, and toString method as described in the project
 * 
 * @author frkmj
 *
 */
public class PublishMsg extends Message {
	
	String topic;
	String message;
	
	public PublishMsg(String user, String topic, String message) {
		super(MessageType.PUBLISH, user);
		this.topic = topic;
		this.message = message;
	}

	
	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * return message? Eller noe mer ettersom metoden lå klar?
	 * 
	 * @return
	 */
	public String getMessage() {
		
		throw new UnsupportedOperationException(TODO.method());
	}
}
