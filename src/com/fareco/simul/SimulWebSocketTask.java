package com.fareco.simul;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

import com.fareco.logger.ILog;

@Service
public class SimulWebSocketTask implements Runnable, ILog {

	private boolean notFin = true;
	private List<SimulKistlerVeh> listVeh = new ArrayList<>();

	public SimulWebSocketTask() {

		setLevel("Simulateur", Level.ALL);
		Thread tache = new Thread(this, "WSServer");
		tache.start();
	}

	@Override
	public void run() {
		listVeh.add(new SimulKistlerVeh(1, new Date().getTime(), 80., 3., 1, 800.));
		listVeh.add(new SimulKistlerVeh(0, new Date().getTime(), 120., 5., 1, 800.));
		listVeh.add(new SimulKistlerVeh(1, new Date().getTime(), 90., 3., 1, 800.));
		listVeh.add(new SimulKistlerVeh(0, new Date().getTime(), 70., 4., 1, 1500.));
		listVeh.add(new SimulKistlerVeh(1, new Date().getTime(), 55., 3., 1, 800.));
		listVeh.add(new SimulKistlerVeh(0, new Date().getTime(), 40., 12., 1, 12000.));
		while (notFin) {
			for (SimulKistlerVeh veh : listVeh) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
				veh.setDate(new Date().getTime());
				try {
					SimulSocketHandler.broadcast(veh);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
