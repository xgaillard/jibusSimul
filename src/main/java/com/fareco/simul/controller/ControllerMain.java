package com.fareco.simul.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fareco.simul.SimulKistlerVeh;
import com.fareco.simul.SimulStarter;
import com.fareco.simul.SimulWebSocketTask;


@Controller
public class ControllerMain {

	private static final String PAGEMAIN 		= "main";
	private static final String ATTRVEHICLES 	= "vehlist";
	private static final String PATHROOT = "/";
	private static final String PATHSIMUL = "/simul";

	SimulWebSocketTask task=null;
	public ControllerMain(SimulWebSocketTask task,SimulStarter instance) {
		this.task=task;
	}

	@GetMapping(PATHROOT)
	public String pageRecueilInfo(Model model) {
		model.addAttribute(ATTRVEHICLES, getVehList());
		return PAGEMAIN;
	}

	@GetMapping(PATHSIMUL)
	public String pageRecueilInfo2(Model model,@RequestParam(name = "typeveh", required = false) String typeVeh) {
		if(null!=typeVeh) {
			switch(typeVeh) {
			case "VL":
				task.setCycle(5000);
				task.getVehList().clear();
				SimulKistlerVeh veh=new SimulKistlerVeh(0, new Date().getTime(), 90., 4.0, 1, 1050.,5000L);
				task.add(veh);
				break;
			case "PL":
				task.setCycle(5000);
				task.getVehList().clear();
				SimulKistlerVeh veh2=new SimulKistlerVeh(0, new Date().getTime(), 50., 14.0, 4, 10050.,5000L);
				task.add(veh2);
				break;
			case "VC":
				task.setCycle(60000);
				task.getVehList().clear();
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 50., 14.0, 4, 10050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 50., 14.0, 4, 10050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 50., 14.0, 4, 10050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 50., 14.0, 4, 10050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 100., 14.0, 4, 10050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 100., 14.0, 4, 10050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 100., 14.0, 4, 10050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 150., 14.0, 4, 10050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 150., 14.0, 4, 10050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 200., 14.0, 4, 10050.,2000L));
				break;
			case "LC":
				task.setCycle(20000);
				task.getVehList().clear();
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 50., 5.0, 4, 2050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 50., 5.0, 4, 2050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 50., 5.0, 4, 2050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 80., 8.0, 4, 2050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 80., 8.0, 4, 2050.,2000L));
				task.add(new SimulKistlerVeh(0, new Date().getTime(), 100., 10.0, 4, 2050.,2000L));

				break;
			default:
				break;
			}
		}
		model.addAttribute(ATTRVEHICLES, getVehList());
		return PAGEMAIN;
	}

	private List<SimulKistlerVeh> getVehList() {
		return task.getVehList();
	}
}
