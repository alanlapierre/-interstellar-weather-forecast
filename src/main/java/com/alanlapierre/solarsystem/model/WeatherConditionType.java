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

import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;


@Entity
@Table(name = "weather_condition_types")
public class WeatherConditionType extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wct_id")
	private long id;
	
	@NotNull
	@Column(name = "wct_name", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private WeatherConditionTypeName name;
	
	
	public WeatherConditionType() {
		super();
	}

	public WeatherConditionType(@NotNull WeatherConditionTypeName name) {
		super();
		this.name = name;
	}

	public WeatherConditionType(long id, @NotNull WeatherConditionTypeName name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public WeatherConditionTypeName getName() {
		return name;
	}

	public void setName(WeatherConditionTypeName name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "WeatherConditionType [id=" + id + ", name=" + name + "]";
	}
	

}
