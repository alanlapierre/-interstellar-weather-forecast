package com.alanlapierre.solarsystem.calculator.position;

import com.alanlapierre.solarsystem.util.WeatherConditionTypeName;

public class AlignedBetweenThemAndPositionZero implements IPosition{

	@Override
	public WeatherConditionTypeName getWeatherConditionTypeName() {
		return WeatherConditionTypeName.DROUGHT;
	}

}
