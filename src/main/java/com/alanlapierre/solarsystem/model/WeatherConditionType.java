package com.alanlapierre.solarsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.alanlapierre.solarsystem.predictor.WeatherConditionPrediction;


@Entity
@Table(name = "weather_condition_types")
public class WeatherConditionType extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wct_id")
	private long id;
	
	@NotNull
	@Column(name = "wct_prediction", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private WeatherConditionPrediction prediction;
	
	
	public WeatherConditionType() {
		super();
	}

	public WeatherConditionType(@NotNull WeatherConditionPrediction prediction) {
		super();
		this.prediction = prediction;
	}

	public WeatherConditionType(long id, @NotNull WeatherConditionPrediction prediction) {
		super();
		this.id = id;
		this.prediction = prediction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public WeatherConditionPrediction getPrediction() {
		return prediction;
	}

	public void setPrediction(WeatherConditionPrediction prediction) {
		this.prediction = prediction;
	}

	@Override
	public String toString() {
		return "WeatherConditionType [id=" + id + ", prediction=" + prediction + "]";
	}
	

}
