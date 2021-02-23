package no.hvl.dat110.messagetransport;

import java.util.Arrays;

public class TransportMessage {

	private byte[] payload;

	public TransportMessage(byte[] payload) {
		// Check for length within boundary
		if (payload == null || (payload.length + 1 > MessageConfig.SEGMENTSIZE)) {
			throw new RuntimeException(
					"Message: payload er null eller større enn " + (MessageConfig.SEGMENTSIZE-1) + " bytes");
		}

		this.payload = payload;
	}

	public TransportMessage() {
		super();
	}

	public byte[] getData() {
		return this.payload;
	}

	/**
	 * Encapsulates the payload of the message
	 * Add "header information"?
	 * 
	 * @return byte[]
	 */
	public byte[] encapsulate() {

		byte[] encoded;

		encoded = new byte[MessageConfig.SEGMENTSIZE];

		encoded[0] = (byte) (payload.length);

		for (int i = 0; i < payload.length; i++) {
			encoded[i + 1] = payload[i];
		}

		return encoded;

	}

	/**
	 * Decapsulates data and put it in payload bytearray
	 * 
	 * @param received
	 */
	public void decapsulate(byte[] received) {

		int len = received[0];

		payload = Arrays.copyOfRange(received, 1, len + 1);

	}
}
