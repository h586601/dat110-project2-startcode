package no.hvl.dat110.messages;


/**
 * Message sent from client to create publish a message on a topic
 * 
 * Implements object variables - a topic and a message is required as described
 * in the project text
 * 
 * Constructor, get/set-methods, and toString method as described in the project
 * 
 * @author gr.30
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

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return "PublishMsg [topic=" + topic + ", message=" + message + "]" + super.toString();
	}
	
	
}
