package no.hvl.dat110.messages;

/**
 * Base class for messages exchanged between broker and clients
 * 
 * All messages will contain info about a user and have a type as defined in
 * MessageType.java. The user is assumed to uniquely identify a connected
 * client.
 * 
 * @author gr.30
 *
 */
public abstract class Message {

	private MessageType type;
	private String user;

	public Message() {

	}

	public Message(MessageType type, String user) {
		this.type = type;
		this.user = user;
	}

	public MessageType getType() {
		return this.type;
	}

	public String getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "Message [type=" + type + ", user=" + user + "]";
	};

}
