package com.fareco.simul;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimulKistlerVeh {
	public SimulKistlerVeh(Integer laneNo, Long date, Double speed, Double length, Integer vehClass, Double weight,
			Long intervale) {
		super();
		this.laneNo = laneNo;
		this.date = date;
		this.speed = speed;
		this.length = length;
		this.vehClass = vehClass;
		this.weight = weight;
		this.intervale = intervale;
	}

	public Integer getLaneNo() {
		return laneNo;
	}
	public void setLaneNo(Integer laneNo) {
		this.laneNo = laneNo;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Integer getVehClass() {
		return vehClass;
	}
	public void setVehClass(Integer vehClass) {
		this.vehClass = vehClass;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Long getIntervale() {
		return intervale;
	}
	public void setIntervale(Long intervale) {
		this.intervale = intervale;
	}
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
	@JsonIgnore
	Long intervale;
}
