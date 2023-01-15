package com.pruebas.model.domain;

public class ShuttleShip extends Ship{
	
	private int capacidadPropulsion;
	private int capacidadCarga;
	
	
	public ShuttleShip() {}
	
	public ShuttleShip(Integer id, int costeProduccion, String nombre, String paisFabricacion, String imagen,String tipo, String peso, int capacidadPropulsion, int capacidadCarga) {
		super(id, costeProduccion, nombre, paisFabricacion, imagen, tipo, peso);
		this.capacidadPropulsion = capacidadPropulsion;
		this.capacidadCarga = capacidadCarga;
	}

	public int getCapacidadPropulsion() {return capacidadPropulsion;}

	public void setCapacidadPropulsion(int capacidadPropulsion) {this.capacidadPropulsion = capacidadPropulsion;}

	public int getCapacidadCarga() {return capacidadCarga;}

	public void setCapacidadCarga(int capacidadCarga) {this.capacidadCarga = capacidadCarga;}
	
}
