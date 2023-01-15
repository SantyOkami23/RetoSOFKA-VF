package com.pruebas.model.domain;

public class UnmannedShip extends Ship{
	
	private String destino;
	private String estadoMision;
	
	public UnmannedShip(){}
	
	public UnmannedShip(Integer id, int costeProduccion, String nombre, String paisFabricacion, String imagen,String tipo, String peso, String destino, String estadoMision) {
		super(id, costeProduccion, nombre, paisFabricacion, imagen, tipo, peso);
		this.destino = destino;
		this.estadoMision = estadoMision;	
	}


	public String getDestino() {return destino;}


	public void setDestino(String destino) {this.destino = destino;}


	public String getEstadoMision() {return estadoMision;}


	public void setEstadoMision(String estadoMision) {this.estadoMision = estadoMision;}

}
