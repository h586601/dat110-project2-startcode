package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();
		
		System.out.println("Temperature device started");

		// create a client object and use it
		Client client = new Client(Common.TEMPTOPIC, Common.BROKERHOST, Common.BROKERPORT);

		client.connect();

		// - publish the temperature(s)
		int count = COUNT;

		do {
			int temp = sn.read();
			System.out.println("READING: " + temp);
			client.publish(Common.TEMPTOPIC, "" + temp);
			count--;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} while (count > 7);

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		client.disconnect();

		System.out.println("Temperature device stopping ... ");

	}
}
