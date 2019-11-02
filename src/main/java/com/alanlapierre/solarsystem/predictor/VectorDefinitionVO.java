package com.alanlapierre.solarsystem.predictor;

public class VectorDefinitionVO implements IPositionable{
	
	private Double xposition;
	private Double yposition;
	
	
	public VectorDefinitionVO() {
		super();
	}

	public VectorDefinitionVO(Double xposition, Double yposition) {
		super();
		this.xposition = xposition;
		this.yposition = yposition;
	}


	public Double getXposition() {
		return xposition;
	}

	public void setXposition(Double xposition) {
		this.xposition = xposition;
	}

	public Double getYposition() {
		return yposition;
	}

	public void setYposition(Double yposition) {
		this.yposition = yposition;
	}


}
