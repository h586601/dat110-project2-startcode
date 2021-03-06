package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.PublishMsg;

public class DisplayDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		System.out.println("Display starting ...");

		// create a client object and use it to
		Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);

		// - connect to the broker
		client.connect();

		// - create the temperature topic on the broker
		client.createTopic(Common.TEMPTOPIC);

		// - subscribe to the topic
		client.subscribe(Common.TEMPTOPIC);

		// - receive messages on the topic
		int count = COUNT;
		do {
			PublishMsg msg = (PublishMsg) client.receive();
			String message = msg.getMessage();
			count--;

			System.out.println("DISPLAY: " + message);
		} while (count > 0);

		client.unsubscribe(Common.TEMPTOPIC);

		client.disconnect();

		System.out.println("Display stopping ... ");

	}
}
