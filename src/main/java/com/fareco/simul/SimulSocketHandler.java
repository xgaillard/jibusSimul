package com.fareco.simul;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class SimulSocketHandler implements WebSocketHandler {

	static List<WebSocketSession> conns = new ArrayList<>();

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		conns.remove(session);

	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		conns.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	public static void broadcast(SimulKistlerVeh message) throws IOException {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(message);
		WebSocketMessage<String> wsm=new TextMessage(json.getBytes());
		conns.forEach(endpoint -> {
				try {
					
					endpoint.sendMessage(wsm);
				} catch (IOException e) {
					e.printStackTrace();
				}
		});
	}


}
