package com.alanlapierre.solarsystem.service;

import java.util.List;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.Planet;

public interface PlanetService {
	
	Planet getNewPlanetPositionByPlanetIdAndDay(Long planetId, Integer day) throws IllegalArgumentException, BusinessException;
	public List<Planet> getNewPlanetPositionsByDay(List<Planet> planets, Integer day)throws IllegalArgumentException, BusinessException;

}
