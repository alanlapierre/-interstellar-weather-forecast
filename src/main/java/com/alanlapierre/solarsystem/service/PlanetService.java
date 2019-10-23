package com.alanlapierre.solarsystem.service;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.Planet;

public interface PlanetService {
	
	Planet getNewPlanetPositionByPlanetIdAndDay(Long planetId, Integer day) throws IllegalArgumentException, BusinessException;

}
