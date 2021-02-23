package no.hvl.dat110.messages;

/**
 * Message sent from client to unsubscribe on a topic
 * 
 * Implement object variables - a topic is required
 * 
 * Constructor, get/set-methods, and toString method as described in the project
 * 
 * @author frkmj
 *
 */
public class UnsubscribeMsg extends Message {
	
	String topic;
	
	public UnsubscribeMsg(String user, String topic) {
		super(MessageType.UNSUBSCRIBE, user);
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return super.toString() + "\nUnsubscribeMsg [topic=" + topic + "]";
	}

	
}
