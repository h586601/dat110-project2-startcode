package no.hvl.dat110.messages;

/**
 * TODO Implement object variables - a topic is required
 * 
 * Constructor, get/set-methods, and toString method as described in the project
 * text
 * 
 * @author frkmj
 *
 */
public class CreateTopicMsg extends Message {

	// message sent from client to create topic on the broker
	private String topic;

	public CreateTopicMsg(String user, String topic) {
		super(MessageType.CREATETOPIC, user);
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
		return super.toString() + "\nCreateTopicMsg [topic=" + topic + "]";
	}

	public static void main(String[] args) {
		//Til hjelp for å sjekke klassen
		CreateTopicMsg ct = new CreateTopicMsg("Ida", "Project2");
		System.out.println(ct.getUser());
		System.out.println(MessageUtils.toJson(ct));
		
	}
}
