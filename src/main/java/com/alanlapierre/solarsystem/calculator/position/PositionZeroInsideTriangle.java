package com.alanlapierre.solarsystem.calculator.position;

import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;

public class PositionZeroInsideTriangle implements IPosition{

	@Override
	public WeatherConditionTypeName getWeatherConditionTypeName() {
		return WeatherConditionTypeName.RAINY;
	}

}
