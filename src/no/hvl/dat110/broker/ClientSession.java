package no.hvl.dat110.broker;

import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.MessageUtils;
import no.hvl.dat110.messagetransport.Connection;

/**
 * Used to represent a session with a currently connected client on the broker
 * side. Whenever a client (user) connects, a corresponding ClientSession-object
 * will be created on the broker-side representing the underlying message
 * transport connection.
 * 
 * The methods in this class must be used when the broker is to receive and send
 * messages to a connected client.
 * 
 * @author gr.30
 *
 */
public class ClientSession {

	private String user;

	// underlying message transport connection
	private Connection connection;

	public ClientSession(String user, Connection connection) {
		this.user = user;
		this.connection = connection;

	}

	public void disconnect() {

		if (connection != null) {
			connection.close();
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void send(Message message) {

		MessageUtils.send(connection, message);
	}

	/**
	 * Checks whether there is some message on the connection
	 * 
	 * @return boolean
	 */
	public boolean hasData() {

		return connection.hasData();
	}

	public Message receive() {

		Message msg = MessageUtils.receive(connection);

		return msg;
	}

}
