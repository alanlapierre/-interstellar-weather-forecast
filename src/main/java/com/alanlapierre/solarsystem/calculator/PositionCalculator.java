package com.alanlapierre.solarsystem.calculator;

import java.util.ArrayList;
import java.util.List;

import com.alanlapierre.solarsystem.calculator.position.AlignedBetweenThem;
import com.alanlapierre.solarsystem.calculator.position.AlignedBetweenThemAndPositionZero;
import com.alanlapierre.solarsystem.calculator.position.PositionZeroOutsideTriangle;
import com.alanlapierre.solarsystem.calculator.position.PositionZeroInsideTriangle;

import com.alanlapierre.solarsystem.calculator.position.IPosition;
import com.alanlapierre.solarsystem.error.BusinessException;

public class PositionCalculator {
	
	
	public static IPosition determinePosition(List<IPositionable> listPositions) throws BusinessException {
		
		List<IPositionable> vectorsBetweenPositions = createVectorsBetweenPositions(listPositions);
		
		Boolean arePositionsAlignedBetweenThem = determineIfPositionsAreAlignedBetweenThem(vectorsBetweenPositions);
		Boolean arePositionsAlignedBetweenThemAndPositionZero = determineIfPositionsAreAlignedWithPositionZero(vectorsBetweenPositions, listPositions);

		if (arePositionsAlignedBetweenThem && arePositionsAlignedBetweenThemAndPositionZero) {
			return new AlignedBetweenThemAndPositionZero();
		} else if (arePositionsAlignedBetweenThem) {
			return new AlignedBetweenThem();
		} else {
			
			IPositionable p1 = listPositions.get(0);
			IPositionable p2 = listPositions.get(1);
			IPositionable p3 = listPositions.get(2);

			Boolean isPositionZeroInsideTriangle = positionZeroInsideTriangle(p1, p2, p3);
			if (isPositionZeroInsideTriangle) {
				return new PositionZeroInsideTriangle();
			} else {
				return new PositionZeroOutsideTriangle();
			}
		}
	}
	
	
	public static Double getTriangleArea(IPositionable p1, IPositionable p2, IPositionable p3) {

		return Math.abs((p1.getXposition() * (p2.getYposition() - p3.getYposition())
				+ p2.getXposition() * (p3.getYposition() - p1.getYposition())
				+ p3.getXposition() * (p1.getYposition() - p2.getYposition())) / 2.0);
	}
	
	
	private static List<IPositionable> createVectorsBetweenPositions(List<IPositionable> listPositions) {
		IPositionable p1 = null;
		IPositionable p2 = null;
		
		List<IPositionable> vectors = new ArrayList<IPositionable>();

		for (IPositionable position : listPositions) {
			if (p1 == null) {
				p1 = position;
			} else {
				p2 = position;
			}
			if (p1 != null && p2 != null) {
				Double xPosition = p1.getXposition() - p2.getXposition();
				Double yPosition = p1.getYposition() - p2.getYposition();
				VectorDefinitionVO vector = new VectorDefinitionVO(xPosition, yPosition);
				vectors.add(vector);
				p1 = p2;
				p2 = null;
			}
		}
		return vectors;
	}
	
	
	private static Boolean determineIfPositionsAreAlignedBetweenThem(List<IPositionable> vectorsBetweenPositions) {
		Boolean areAlignedBetweenThem = true;
		IPositionable v1 = null;
		IPositionable v2 = null;

		for (int i = 0; i < vectorsBetweenPositions.size(); i++) {
			if (v1 == null) {
				v1 = vectorsBetweenPositions.get(i);
			} else {
				v2 = vectorsBetweenPositions.get(i);
			}
			if (v1 != null && v2 != null) {
				Double value = (v1.getXposition() * v2.getYposition()) - (v1.getYposition() * v2.getXposition());
				v1 = v2;
				v2 = null;
				if (value != 0) {
					areAlignedBetweenThem = false;
				}
			}
		}
		return areAlignedBetweenThem;

	}
	
	
	
	private static Boolean determineIfPositionsAreAlignedWithPositionZero(List<IPositionable> vectorsBetweenPositions,	List<IPositionable> listPositions) {

		Boolean areAlignedWithPositionZero = true;
		// Un vector entre alguna posicion de la lista listPositions y el punto (0,0).
		VectorDefinitionVO vectorBetweenSomePositionAndPositionZero = new VectorDefinitionVO(
				listPositions.get(0).getXposition(),
				listPositions.get(0).getYposition());

		// Tomamos un vector de los que se calcularon entre posiciones y comprobamos el
		// valor de la determinante con el vector del paso anterior.
		Double value = (vectorsBetweenPositions.get(0).getXposition() * vectorBetweenSomePositionAndPositionZero.getYposition())
				- (vectorsBetweenPositions.get(0).getYposition() * vectorBetweenSomePositionAndPositionZero.getXposition());
		if (value != 0) {
			areAlignedWithPositionZero = false;
		}
		return areAlignedWithPositionZero;
	}
	
	
	private static Boolean positionZeroInsideTriangle(IPositionable p1, IPositionable p2, IPositionable p3) {

		// Punto P.
		IPositionable positionZero = new VectorDefinitionVO(0D, 0D);
		// Area de triangulo ABC
		double A = getTriangleArea(p1, p2, p3);
		// Area de triangulo PBC
		double A1 = getTriangleArea(positionZero, p2, p3);
		// Area de triangulo PAC
		double A2 = getTriangleArea(p1, positionZero, p3);
		// Area de triangulo PAB
		double A3 = getTriangleArea(p1, p2, positionZero);
		// Verificar si suma de A1, A2 y A3 es igual a A
		return (A == A1 + A2 + A3);
	}
	
}
