package com.alanlapierre.solarsystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "solar_systems")
public class SolarSystem extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ssy_id")
	private long id;
	
	@NotNull
	@Column(name = "ssy_name", nullable = false, unique = true)
	private String name;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "solarSystem")
    private List<Planet> planets;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "solarSystem")
    private List<WeatherCondition> weatherconditions;
	
	
	public SolarSystem() {
		super();
	}


	public SolarSystem(@NotNull String name, List<Planet> planets, List<WeatherCondition> weatherconditions) {
		super();
		this.name = name;
		this.planets = planets;
		this.weatherconditions = weatherconditions;
	}



	public SolarSystem(long id, @NotNull String name, List<Planet> planets, List<WeatherCondition> weatherconditions) {
		super();
		this.id = id;
		this.name = name;
		this.planets = planets;
		this.weatherconditions = weatherconditions;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Planet> getPlanets() {
		return planets;
	}


	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}


	public List<WeatherCondition> getWeatherconditions() {
		return weatherconditions;
	}


	public void setWeatherconditions(List<WeatherCondition> weatherconditions) {
		this.weatherconditions = weatherconditions;
	}


	@Override
	public String toString() {
		return "SolarSystem [id=" + id + ", name=" + name + ", planets=" + planets + ", weatherconditions="
				+ weatherconditions + "]";
	}

	

}
