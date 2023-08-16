package com.fareco.simul;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.fareco.model.detect.Detector;
import com.fareco.model.vehicle.IVehicle;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class SimulDetectorSocketClient extends Detector implements Runnable {

	public SimulDetectorSocketClient(String infos,String[] com,String [] params) {
		super(infos,com,params);
	}

	private void parseJson(InputStream is) throws IOException {

		// Create and configure an ObjectMapper instance
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// Create a JsonParser instance
		try (JsonParser jsonParser = mapper.getFactory().createParser(is)) {
			boolean notFin=true;

			// Check the first token
			while (notFin) {

				// Read a contact instance using ObjectMapper and do something with it
				IVehicle contact = mapper.readValue(jsonParser, IVehicle.class);
				dispashVehicle(contact);
			}
		}
	}

	public static void main(String args[]) {
		String[] params = { "localhost", "33340" };
		SimulDetectorSocketClient client = new SimulDetectorSocketClient("Simul",params,params);
	}

	@Override
	public void run() {

		while (true) {
			try (Socket socket = new Socket()) {
				if (!socket.isConnected()) {
					socket.connect(new InetSocketAddress("localhost", 33335), 10000);
				}
				if (socket.isConnected() && (null != socket.getInputStream())) {
					parseJson(socket.getInputStream());

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}
}
