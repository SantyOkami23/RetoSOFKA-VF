package com.pruebas.model.domain;

public class MannedShip extends Ship{

	private int  capacidadPersonas;
	private int velocidad;
	
	public MannedShip() {}

	public MannedShip(Integer id, int costeProduccion, String nombre, String paisFabricacion, String imagen,
			String tipo, String peso,int capacidadPersonas,int velocidad) {
		super(id, costeProduccion, nombre, paisFabricacion, imagen, tipo, peso);
		this.capacidadPersonas = capacidadPersonas;
		this.velocidad = velocidad;
	}

	public int getCapacidadPersonas() {return capacidadPersonas;}

	public void setCapacidadPersonas(int capacidadPersonas) {this.capacidadPersonas = capacidadPersonas;}
	
	public int getVelocidad() {return velocidad;}
	
	public void setVelocidad(Integer velocidad) {this.velocidad = velocidad;}
	
}
