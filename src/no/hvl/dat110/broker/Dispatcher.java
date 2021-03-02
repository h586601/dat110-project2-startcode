package no.hvl.dat110.broker;

import java.util.Set;
import java.util.Collection;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.common.Stopable;
import no.hvl.dat110.messages.*;
import no.hvl.dat110.messagetransport.Connection;

public class Dispatcher extends Stopable {

	private Storage storage;

	public Dispatcher(Storage storage) {
		super("Dispatcher");
		this.storage = storage;

	}

	@Override
	public void doProcess() {

		Collection<ClientSession> clients = storage.getSessions();

		Logger.lg(".");
		for (ClientSession client : clients) {

			Message msg = null;

			if (client.hasData()) {
				msg = client.receive();
			}

			// a message was received
			if (msg != null) {
				dispatch(client, msg);
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Invokes the appropriate handler method
	 * 
	 * @param client
	 * @param msg
	 */
	public void dispatch(ClientSession client, Message msg) {

		MessageType type = msg.getType();

		switch (type) {

		case DISCONNECT:
			onDisconnect((DisconnectMsg) msg);
			break;

		case CREATETOPIC:
			onCreateTopic((CreateTopicMsg) msg);
			break;

		case DELETETOPIC:
			onDeleteTopic((DeleteTopicMsg) msg);
			break;

		case SUBSCRIBE:
			onSubscribe((SubscribeMsg) msg);
			break;

		case UNSUBSCRIBE:
			onUnsubscribe((UnsubscribeMsg) msg);
			break;

		case PUBLISH:
			onPublish((PublishMsg) msg);
			break;

		default:
			Logger.log("broker dispatch - unhandled message type");
			break;

		}
	}

	/**
	 * Called from Broker after having established the underlying connection. Prints
	 * the user connected and adds it to ClientSession storage
	 * 
	 * @param msg
	 * @param connection
	 */
	public void onConnect(ConnectMsg msg, Connection connection) {

		String user = msg.getUser();

		Logger.log("onConnect:" + msg.toString());

		storage.addClientSession(user, connection);

	}

	/**
	 * Called by dispatch upon receiving a disconnect message
	 * 
	 * @param msg
	 */
	public void onDisconnect(DisconnectMsg msg) {

		String user = msg.getUser();

		Logger.log("onDisconnect:" + msg.toString());

		storage.removeClientSession(user);

	}

	/**
	 * Creates the topic in the broker storage and topic is contained in the create
	 * topic message
	 * 
	 * @param msg
	 */
	public void onCreateTopic(CreateTopicMsg msg) {

		Logger.log("onCreateTopic:" + msg.toString());

		String topic = msg.getTopic();
		storage.createTopic(topic);


	}

	/**
	 * Deletes the topic from the broker storage and the topic is contained in the
	 * delete topic message
	 * 
	 * @param msg
	 */
	public void onDeleteTopic(DeleteTopicMsg msg) {

		Logger.log("onDeleteTopic:" + msg.toString());

		String topic = msg.getTopic();
		storage.deleteTopic(topic);

	}

	/**
	 * Subscribes user to the topic. User and topic is contained in the subscribe
	 * message
	 * 
	 * @param msg
	 */
	public void onSubscribe(SubscribeMsg msg) {

		Logger.log("onSubscribe:" + msg.toString());

		storage.addSubscriber(msg.getUser(), msg.getTopic());


	}

	/**
	 * Unsubscribes user from the topic
	 * 
	 * @param msg
	 */
	public void onUnsubscribe(UnsubscribeMsg msg) {

		Logger.log("onUnsubscribe:" + msg.toString());
		
		storage.removeSubscriber(msg.getUser(), msg.getTopic());

	}

	/**
	 * Publishes the message to clients subsribed to the topic
	 * 
	 * @param msg
	 */
	public void onPublish(PublishMsg msg) {

		Logger.log("onPublish:" + msg.toString());

		// messages must be sent using the corresponding client session objects
		Set<String> subscribers = storage.getSubscribers(msg.getTopic());
		for(String sub : subscribers) {
			ClientSession cs = storage.getSession(sub);
			if(cs != null)
				cs.send(msg);
		}

	}
}
