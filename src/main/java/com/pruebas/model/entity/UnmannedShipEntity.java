package com.pruebas.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "NoTripulada")
public class UnmannedShipEntity extends ShipEntity implements IAccion{
	
	@Column(name = "destino", length = 50, nullable = true)
	private String destino;
	@Column(name = "estadoMision", length = 50, nullable = true)
	private String estadoMision;
	
	
	public UnmannedShipEntity() {}
	
	public UnmannedShipEntity(Integer id, int costeProduccion, String nombre, String tipo, String peso,String paisFabricacion, String imagen, String destino, String estadoMision) {
		super(id, costeProduccion, nombre, tipo, peso, paisFabricacion, imagen);
		this.destino = destino;
		this.estadoMision = estadoMision;
	}
	
	public String getDestino() {return destino;}

	public void setDestino(String destino) {this.destino = destino;}

	public String getEstadoMision() {return estadoMision;}

	public void setEstadoMision(String estadoMision) {this.estadoMision = estadoMision;}
		
	
	@Override
	public void asignarMision() {String Objetivo = "Realizar alunizaje ";}

	@Override
	public void acelerar() {String acelera = "Comenzando a acelerar";}

	@Override
	public void marcarRumbo() {String rumbo = "macando proximo rumbo";}

	
	
	
	
	
	
	
	
	
}
