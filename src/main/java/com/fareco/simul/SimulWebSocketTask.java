package com.fareco.simul;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

import com.fareco.logger.ILog;

import lombok.Getter;

@Service
@Getter
public class SimulWebSocketTask implements Runnable, ILog {

	private boolean notFin = true;
	private List<SimulKistlerVeh> vehList = new ArrayList<>();

	public SimulWebSocketTask() {

		Thread tache = new Thread(this, "WSServer");
		tache.start();
	}

	@Override
	public void run() {
		long periode=5000;
		long decalage=100;
		long delta=periode;
		vehList.add(new SimulKistlerVeh(0, new Date().getTime(), 80., 3., 1, 800.));
		vehList.add(new SimulKistlerVeh(0, new Date().getTime(), 120., 8., 1, 800.));
//		vehList.add(new SimulKistlerVeh(0, new Date().getTime(), 130., 10., 1, 800.));
		while (notFin) {
			List<SimulKistlerVeh> copie;
			synchronized (vehList) {
				copie=new ArrayList<>(vehList);
			}
			for (SimulKistlerVeh veh : copie) {
				try {
					synchronized (vehList) {
						vehList.wait(delta);
						long dateCour=new Date().getTime();
						veh.setDate(dateCour);
						delta=5000-dateCour%5000+decalage;
						try {
							SimulSocketHandler.broadcast(veh);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} catch (InterruptedException e) {
					break;
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
