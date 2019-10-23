package com.alanlapierre.solarsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "weather_conditions")
public class WeatherCondition extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wco_id")
	private long id;
	
	@NotNull
	@Column(name = "wco_day", nullable = false)
	private Integer day;

	
	@NotNull
	@ManyToOne
	@JoinColumn(name="wct_id", nullable = false)
	private WeatherConditionType weatherConditionType;
	
	@ManyToOne
    @JoinColumn(name = "ssy_id", nullable = false)
    @JsonIgnore
	private SolarSystem solarSystem;
	
	@Transient
	private Double triangleArea;
	
	public WeatherCondition() {
		super();
	}


	public WeatherCondition(@NotNull Integer day, @NotNull WeatherConditionType weatherConditionType,
			SolarSystem solarsystem) {
		super();
		this.day = day;
		this.weatherConditionType = weatherConditionType;
		this.solarSystem = solarsystem;
	}


	public WeatherCondition(long id, @NotNull Integer day, @NotNull WeatherConditionType weatherConditionType,
			SolarSystem solarsystem) {
		super();
		this.id = id;
		this.day = day;
		this.weatherConditionType = weatherConditionType;
		this.solarSystem = solarsystem;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Integer getDay() {
		return day;
	}


	public void setDay(Integer day) {
		this.day = day;
	}


	public WeatherConditionType getWeatherConditionType() {
		return weatherConditionType;
	}


	public void setWeatherConditionType(WeatherConditionType weatherConditionType) {
		this.weatherConditionType = weatherConditionType;
	}


	public SolarSystem getSolarSystem() {
		return solarSystem;
	}


	public void setSolarSystem(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}


	@Override
	public String toString() {
		return "WeatherCondition [id=" + id + ", day=" + day + ", weatherConditionType=" + weatherConditionType
				+ ", solarsystem=" + solarSystem + "]";
	}


	public void setTriangleArea(Double triangleArea) {
		this.triangleArea = triangleArea;
		
	}
	
	public Double getTriangleArea() {
		return triangleArea;
	}


}
