package no.hvl.dat110.messages;

/**
 * Message sent from client to subsbribe on a topic
 * 
 * @author gr.30
 *
 */
public class SubscribeMsg extends Message {

	String topic;

	public SubscribeMsg(String user, String topic) {
		super(MessageType.SUBSCRIBE, user);
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
		return "SubscribeMsg [topic=" + topic + "]" + super.toString();
	}

}
