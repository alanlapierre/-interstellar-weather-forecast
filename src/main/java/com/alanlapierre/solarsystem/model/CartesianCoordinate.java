package com.alanlapierre.solarsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.alanlapierre.solarsystem.predictor.IPositionable;

@Entity
@Table(name = "cartesian_coordinates")
public class CartesianCoordinate extends AuditModel implements IPositionable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cco_id")
	private long id;
	
	@NotNull
	@Column(name = "cco_xposition", nullable = false)
	private Double xposition;

	@NotNull
	@Column(name = "cco_yposition", nullable = false)
	private Double yposition;
	
	
	@OneToOne(mappedBy = "cartesianCoordinate")
    private Planet planet;
	
	
	public CartesianCoordinate() {
		super();
	}

	public CartesianCoordinate(@NotNull Double xposition, @NotNull Double yposition) {
		super();
		this.xposition = xposition;
		this.yposition = yposition;
	}
	
	public CartesianCoordinate(@NotNull Double xposition, @NotNull Double yposition, Planet planet) {
		super();
		this.xposition = xposition;
		this.yposition = yposition;
		this.planet = planet;
	}

	
	public CartesianCoordinate(long id, @NotNull Double xposition, @NotNull Double yposition, Planet planet) {
		super();
		this.id = id;
		this.xposition = xposition;
		this.yposition = yposition;
		this.planet = planet;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getXposition() {
		return xposition;
	}

	public void setXposition(Double xposition) {
		this.xposition = xposition;
	}

	public Double getYposition() {
		return yposition;
	}

	public void setYposition(Double yposition) {
		this.yposition = yposition;
	}


	public Planet getPlanet() {
		return planet;
	}


	public void setPlanet(Planet planet) {
		this.planet = planet;
	}


	@Override
	public String toString() {
		return "CartesianCoordinate [id=" + id + ", xposition=" + xposition + ", yposition=" + yposition + ", planet="
				+ planet + "]";
	}

	

}
