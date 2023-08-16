package com.fareco.simul;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimulKistlerVeh {
	@JsonProperty("LaneNo")
	Integer laneNo; 
	@JsonProperty("StartTime")
	Long date;
	@JsonProperty("Velocity")
	Double speed;
	@JsonProperty("VehicleLength")
	Double length;
	@JsonProperty("MappedClassCategoryID")
	Integer vehClass;
	@JsonProperty("GrossWeight")
	Double weight;
}
