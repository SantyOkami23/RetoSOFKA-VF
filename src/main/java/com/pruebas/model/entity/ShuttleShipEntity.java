package com.pruebas.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Lanzadera")
public class ShuttleShipEntity extends ShipEntity implements IAccion{

	@Column(name ="capacida_produccion")
	private int capacidadPropulsion;
	
	@Column(name = "capacidad_carga")
	private int capacidadCarga;
	
	public ShuttleShipEntity() {}

	public ShuttleShipEntity(Integer id, int costeProduccion, String nombre, String tipo, String peso,String paisFabricacion, String imagen, int capacidadPropulsion, int capacidadCarga) {
		super(id, costeProduccion, nombre, tipo, peso, paisFabricacion, imagen);
		this.capacidadPropulsion = capacidadPropulsion;
		this.capacidadCarga = capacidadCarga;
	}

	public int getCapacidadPropulsion() {return capacidadPropulsion;}

	public void setCapacidadPropulsion(int capacidadPropulsion) {this.capacidadPropulsion = capacidadPropulsion;}

	public int getCapacidadCarga() {return capacidadCarga;}

	public void setCapacidadCarga(int capacidadCarga) {this.capacidadCarga = capacidadCarga;}
	
	@Override
	public void asignarMision() {String objetivo = "Realizar tareas de desacople";}
	
	@Override
	public void acelerar() {String acelera = "La nave lanzadora ha comenzado a acelerar";}

	@Override
	public void marcarRumbo() {String rumbo = "Orbita lunar";}


}
