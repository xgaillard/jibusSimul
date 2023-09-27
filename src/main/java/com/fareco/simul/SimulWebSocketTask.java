package com.fareco.simul;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

import com.fareco.logger.ILog;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class SimulWebSocketTask implements Runnable, ILog {

	private long cycle = 15000;
	private boolean notFin = true;
	private List<SimulKistlerVeh> vehList = new ArrayList<>();

	public SimulWebSocketTask() {

		Thread tache = new Thread(this, "WSServer");
		tache.start();
	}

	private void broadcast(SimulKistlerVeh veh) {
		try {
			veh.setDate(new Date().getTime());
			SimulSocketHandler.broadcast(veh);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private boolean waitNext(long periode) {
		boolean interrupt=false;
		long decalage = 100;
		long dateCour = new Date().getTime();
		long delta = periode - dateCour % periode + decalage;
		try {
			synchronized (vehList) {
				vehList.wait(delta);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			interrupt=true;
		}
		return interrupt;
	}

	@Override
	public void run() {
		long periode = 5000;
		/* Pour ne pas tomber sur la seconde pile.... */
		long epsilon = 20;
		long delta;
		vehList.add(new SimulKistlerVeh(0, new Date().getTime(), 80., 3., 1, 800., periode));
		vehList.add(new SimulKistlerVeh(0, new Date().getTime(), 120., 8., 1, 800., periode));
		while (notFin) {
			List<SimulKistlerVeh> copie;
			boolean interrupted = false;
			delta = cycle - new Date().getTime() % cycle;
			if(Math.abs(delta)>10) {
			synchronized (vehList) {
				try {
					vehList.wait(delta+epsilon);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					interrupted = true;
				}
			}
			}
			if (!interrupted) {
				synchronized (vehList) {
					copie = new ArrayList<>(vehList);
				}
				for (SimulKistlerVeh veh : copie) {
					broadcast(veh);

					/* Si modification de la liste, on resort de suite... */
					if(waitNext(veh.getIntervale())) {
						break;
					}
				}
			}
		}
	}

	public void add(SimulKistlerVeh veh2) {
		synchronized (vehList) {
			vehList.add(veh2);
		}
	}
}
