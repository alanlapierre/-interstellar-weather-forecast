package com.alanlapierre.solarsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "polar_coordinates")
public class PolarCoordinate extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pco_id")
	private long id;
	
	@NotNull
	@Column(name = "pco_distance", nullable = false)
	private Double distance;

	@NotNull
	@Column(name = "pco_angle", nullable = false)
	private Double angle;
	
	@OneToOne(mappedBy = "polarCoordinate")
    private Planet planet;

	
	public PolarCoordinate() {
		super();
	}


	public PolarCoordinate(@NotNull Double distance, @NotNull Double angle, Planet planet) {
		super();
		this.distance = distance;
		this.angle = angle;
		this.planet = planet;
	}
	
	
	public PolarCoordinate(long id, @NotNull Double distance, @NotNull Double angle, Planet planet) {
		super();
		this.id = id;
		this.distance = distance;
		this.angle = angle;
		this.planet = planet;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getAngle() {
		return angle;
	}

	public void setAngle(Double angle) {
		this.angle = angle;
	}


	public Planet getPlanet() {
		return planet;
	}


	public void setPlanet(Planet planet) {
		this.planet = planet;
	}


	@Override
	public String toString() {
		return "PolarCoordinate [id=" + id + ", distance=" + distance + ", angle=" + angle + ", planet=" + planet + "]";
	}

	

}
