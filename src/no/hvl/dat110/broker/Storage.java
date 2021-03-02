package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;

/**
 * Managing subscriptions and currently connected clients
 * 
 * @author frkmj
 *
 */
public class Storage {
	
	protected ConcurrentHashMap<String, Set<String>> subscriptions; // <topic, set of users>
	protected ConcurrentHashMap<String, ClientSession> clients;		// <user, client session object>

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {
		return subscriptions.keySet();
	}

	/**
	 * Get the session object for a given user.
	 * 
	 * Session object can be used to send a message to the user.
	 */
	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);
		return session;
	}

	/**
	 * Returns the set of users that subscribe to a topic
	 * 
	 * @param topic
	 * @return Set<String> users
	 */
	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	/**
	 * Adds corresponding client session to the storage
	 * 
	 * @param user
	 * @param connection
	 */
	public void addClientSession(String user, Connection connection) {
		
		ClientSession cs = new ClientSession(user, connection);
		clients.put(user, cs);
		Logger.log(user + " registered as a connected client");
		
	}

	/**
	 * Removes client session for user from the storage 
	 * 
	 * @param user
	 */
	public void removeClientSession(String user) {

		clients.remove(user);
		Logger.log(user + " removed as a connected client");
		
	}

	/**
	 * Creates a topic in the storage
	 * 
	 * @param topic
	 */
	public void createTopic(String topic) {

		Set<String> subscribers = new HashSet<String>();
		subscriptions.put(topic, subscribers);
		Logger.log(topic + " created");
	
	}

	/**
	 * Deletes topic from the storage 
	 * 
	 * @param topic
	 */
	public void deleteTopic(String topic) {

		subscriptions.remove(topic);
		Logger.log(topic + " deleted");
	}

	/**
	 * Adds the user as subscriber to the topic
	 * 
	 * @param user
	 * @param topic
	 */
	public void addSubscriber(String user, String topic) {

		Set<String> subscribers = getSubscribers(topic);
		subscribers.add(user);
		
	}

	/**
	 * Removes the user as subscriber to the topic
	 * 
	 * @param user
	 * @param topic
	 */
	public void removeSubscriber(String user, String topic) {

		Set<String> subscribers = getSubscribers(topic);
		subscribers.remove(user);
		
	}
}
