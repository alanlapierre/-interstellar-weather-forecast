package com.alanlapierre.solarsystem.service;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlapierre.solarsystem.error.BusinessException;
import com.alanlapierre.solarsystem.model.CartesianCoordinate;
import com.alanlapierre.solarsystem.model.Direction;
import com.alanlapierre.solarsystem.model.Planet;
import com.alanlapierre.solarsystem.model.PolarCoordinate;
import com.alanlapierre.solarsystem.repository.PlanetRepository;
import com.alanlapierre.solarsystem.util.DirectionName;
import com.alanlapierre.solarsystem.util.ParamValidator;

import static com.alanlapierre.solarsystem.util.Constants.*;

@Service("planetService")
@Transactional(readOnly = true)
public class PlanetServiceImpl implements PlanetService {
	
	Logger logger = LogManager.getLogger(PlanetServiceImpl.class);

	private final PlanetRepository planetRepository;


	public PlanetServiceImpl(PlanetRepository planetRepository) {
		this.planetRepository = planetRepository;
	}
	
	
	public Planet getNewPlanetPositionByPlanetIdAndDay(Long planetId, Integer day) throws IllegalArgumentException, BusinessException {
		
		ParamValidator.test(day, (i)-> i == null || i <= 0);
		ParamValidator.test(planetId, (i)-> i == null || i <= 0);

		
		Planet result = null;
		
		try {
			Planet planetSaved = planetRepository.findById(planetId).get();
		
			Double newPlanetAngle = calculatePlanetAngle(day, planetSaved);
			
			setNewPlanetValues(planetSaved, newPlanetAngle);
			
			result = planetSaved;
			
		} catch (NoSuchElementException nse) {
			throw new BusinessException("Planet not found");
		}
		
		return result;
		
	}


	private void setNewPlanetValues(Planet planet, Double newPlanetAngle) {
		PolarCoordinate polarCoordinate = planet.getPolarCoordinate();
		CartesianCoordinate cartesianCoordinate = planet.getCartesianCoordinate();
				
		polarCoordinate.setAngle(newPlanetAngle);
		cartesianCoordinate.setXposition(polarCoordinate.getDistance() * Math.cos(polarCoordinate.getAngle()));
		cartesianCoordinate.setYposition(polarCoordinate.getDistance() * Math.sin(polarCoordinate.getAngle()));
	}


	private Double calculatePlanetAngle(Integer day, Planet planet) {
		
		Double newPlanetAngle = planet.getDisplacement() * day; 
		
		if(newPlanetAngle >= getMaxAngle()) {
			newPlanetAngle -= getMaxAngle() * ((int)(newPlanetAngle / getMaxAngle()));
		}
		
		Direction planetDirection = planet.getDirection();
		
		if(planetDirection.getName() == DirectionName.CLOCKWISE) {
			if(newPlanetAngle != 0) {
				newPlanetAngle = getMaxAngle() - newPlanetAngle;
			}	
		}
		return newPlanetAngle;
	}
	
	

}
