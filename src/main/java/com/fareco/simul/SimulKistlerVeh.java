package com.fareco.simul;

import java.util.ArrayList;
import java.util.List;

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
		this.axles=new ArrayList<>();
	}
	public void addAxle(Double weight,Double distance)  {
		this.axles.add(new SimulKistlerAxle(weight, distance));
	}
	public void addAxle(SimulKistlerAxle axle)  {
		this.axles.add(new SimulKistlerAxle(axle.getWeight(), axle.getDistance()));
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
	@JsonProperty("Axles")
	List<SimulKistlerAxle> axles;
}
