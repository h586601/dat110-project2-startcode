package no.hvl.dat110.messages;

/**
 * TODO:
 * Implements object variable - a topic is required
 * 
 * 
 * @author frkmj
 *
 */
public class DeleteTopicMsg extends Message {

	// message sent from client to create topic on the broker
	String topic;
	
	public DeleteTopicMsg(String user, String topic) {
		super(MessageType.DELETETOPIC, user);
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
		return super.toString() + "\nDeleteTopicMsg [topic=" + topic + "]";
	}
	
	

	public static void main(String[] args) {
		
		DeleteTopicMsg dt = new DeleteTopicMsg("Bruker", "Emne");
		System.out.println(dt);
	}
}
