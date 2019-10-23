package com.alanlapierre.solarsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "planets")
public class Planet extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pla_id")
	private long id;
	
	@Column(name = "pla_displacement", nullable = false)
	private Double displacement;
	
	@NotNull
	@Column(name = "pla_name", nullable = false)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pco_id", referencedColumnName = "pco_id", nullable = false)
    private PolarCoordinate polarCoordinate;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cco_id", referencedColumnName = "cco_id", nullable = false)
    private CartesianCoordinate cartesianCoordinate;
	

	@NotNull
	@ManyToOne
	@JoinColumn(name="dir_id", nullable = false)
	private Direction direction;
	
	@ManyToOne
    @JoinColumn(name = "ssy_id", nullable = false)
    @JsonIgnore
	private SolarSystem solarSystem;
	
	
	public Planet() {
		super();
	}


	public Planet(Double displacement, @NotNull String name, PolarCoordinate polarCoordinate,
			CartesianCoordinate cartesianCoordinate, @NotNull Direction direction, SolarSystem solarSystem) {
		super();
		this.displacement = displacement;
		this.name = name;
		this.polarCoordinate = polarCoordinate;
		this.cartesianCoordinate = cartesianCoordinate;
		this.direction = direction;
		this.solarSystem = solarSystem;
	}


	public Planet(long id, Double displacement, @NotNull String name, PolarCoordinate polarCoordinate,
			CartesianCoordinate cartesianCoordinate, @NotNull Direction direction, SolarSystem solarSystem) {
		super();
		this.id = id;
		this.displacement = displacement;
		this.name = name;
		this.polarCoordinate = polarCoordinate;
		this.cartesianCoordinate = cartesianCoordinate;
		this.direction = direction;
		this.solarSystem = solarSystem;
	}



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Double getDisplacement() {
		return displacement;
	}


	public void setDisplacement(Double displacement) {
		this.displacement = displacement;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public PolarCoordinate getPolarCoordinate() {
		return polarCoordinate;
	}


	public void setPolarCoordinate(PolarCoordinate polarCoordinate) {
		this.polarCoordinate = polarCoordinate;
	}


	public CartesianCoordinate getCartesianCoordinate() {
		return cartesianCoordinate;
	}


	public void setCartesianCoordinate(CartesianCoordinate cartesianCoordinate) {
		this.cartesianCoordinate = cartesianCoordinate;
	}


	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	public SolarSystem getSolarSystem() {
		return solarSystem;
	}


	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}


	@Override
	public String toString() {
		return "Planet [id=" + id + ", displacement=" + displacement + ", name=" + name + ", polarCoordinate="
				+ polarCoordinate + ", cartesianCoordinate=" + cartesianCoordinate + ", direction=" + direction
				+ ", solarsystem=" + solarSystem + "]";
	}



}
