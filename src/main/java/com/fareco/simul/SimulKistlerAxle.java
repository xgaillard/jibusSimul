package com.fareco.simul;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimulKistlerAxle {
	public SimulKistlerAxle(Double weight,Double distance) {
		setWeight(weight);
		setDistance(distance);
	}
	@JsonProperty("Weight")
	Double weight;
	@JsonProperty("Distance")
	Double distance;
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
}
