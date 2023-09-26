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

	SimulWebSocketTask task=null;
	public ControllerMain(SimulWebSocketTask task,SimulStarter instance) {
		this.task=task;
	}

	@GetMapping(PATHROOT)
	public String pageRecueilInfo(Model model,@RequestParam(name = "typeveh", required = false) String typeVeh) {
		if(null!=typeVeh) {
			switch(typeVeh) {
			case "VL":
				SimulKistlerVeh veh=new SimulKistlerVeh(0, new Date().getTime(), 90., 4.0, 1, 1050.);
				getVehList().add(veh);
				break;
			case "PL":
				SimulKistlerVeh veh2=new SimulKistlerVeh(0, new Date().getTime(), 50., 14.0, 4, 10050.);
				getVehList().add(veh2);
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
