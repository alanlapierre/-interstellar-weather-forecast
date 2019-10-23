package com.alanlapierre.solarsystem.vo;

import java.util.List;

public class PeriodWeatherConditionVO {
	
	private Long solarSystemId;
	private Integer years;
	private Integer droughtPeriods;
	private Integer	rainyPeriods;
	private Integer	optimalPeriods;
	private Integer dayWithMaximumRainfallIntensity;
	private List<WeatherConditionVO> weatherConditions;
	
	public Long getSolarSystemId() {
		return solarSystemId;
	}
	public void setSolarSystemId(Long solarSystemId) {
		this.solarSystemId = solarSystemId;
	}
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}
	public Integer getDroughtPeriods() {
		return droughtPeriods;
	}
	public void setDroughtPeriods(Integer droughtPeriods) {
		this.droughtPeriods = droughtPeriods;
	}
	public Integer getRainyPeriods() {
		return rainyPeriods;
	}
	public void setRainyPeriods(Integer rainyPeriods) {
		this.rainyPeriods = rainyPeriods;
	}
	public Integer getOptimalPeriods() {
		return optimalPeriods;
	}
	public void setOptimalPeriods(Integer optimalPeriods) {
		this.optimalPeriods = optimalPeriods;
	}
	public Integer getDayWithMaximumRainfallIntensity() {
		return dayWithMaximumRainfallIntensity;
	}
	public void setDayWithMaximumRainfallIntensity(Integer dayWithMaximumRainfallIntensity) {
		this.dayWithMaximumRainfallIntensity = dayWithMaximumRainfallIntensity;
	}
	public List<WeatherConditionVO> getWeatherConditions() {
		return weatherConditions;
	}
	public void setWeatherConditions(List<WeatherConditionVO> weatherConditions) {
		this.weatherConditions = weatherConditions;
	}
	
	
	
}
