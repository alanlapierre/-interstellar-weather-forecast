package com.alanlapierre.solarsystem.vo;

import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class WeatherConditionVO {
	
	private Long solarSystemId;
	private Integer day;
	private Long weatherConditionId;
	
	@JsonIgnore
	private Double triangleArea;
	
	private WeatherConditionTypeName weatherConditionDescription;
	
	public Long getSolarSystemId() {
		return solarSystemId;
	}
	public void setSolarSystemId(Long solarSystemId) {
		this.solarSystemId = solarSystemId;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Long getWeatherConditionId() {
		return weatherConditionId;
	}
	public void setWeatherConditionId(Long weatherConditionId) {
		this.weatherConditionId = weatherConditionId;
	}
	public void setTriangleArea(Double triangleArea) {
		this.triangleArea = triangleArea;
	}
	
	public Double getTriangleArea() {
		return triangleArea;
	}
	public WeatherConditionTypeName getWeatherConditionDescription() {
		return weatherConditionDescription;
	}
	public void setWeatherConditionDescription(WeatherConditionTypeName weatherConditionDescription) {
		this.weatherConditionDescription = weatherConditionDescription;
	}
	
}
