package com.pruebas.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Tripulada")
public class MannedShipEntity extends ShipEntity implements IAccion{
    
	@Column(name ="capacidad_personas")
	private int capacidadPersonas;
	
	@Column(name = "velocidad")
	private int velocidad; 

	public MannedShipEntity() {}

	public MannedShipEntity(Integer id, int costeProduccion, String nombre, String tipo, String peso,String paisFabricacion, String imagen, int capacidadPersonas, int velocidad) {
		super(id, costeProduccion, nombre, tipo, peso, paisFabricacion, imagen);
		this.capacidadPersonas = capacidadPersonas;
		this.velocidad = velocidad;
	}

	public int getCapacidadPersonas() { return capacidadPersonas; }

    public void setCapacidadPersonas(int capacidadPersonas) { this.capacidadPersonas = capacidadPersonas;}
    
    public int getVelocidad() { return velocidad;}

    public void setVelocidad(int velocidad) {this.velocidad = velocidad;}

    //Metodo clase padre abstracta
    public void asignarMision() {String objetivo = "Realizar tareas de reparacion y de recoleccion de muestras";}
    
    //Metodo interface
    @Override
    public void acelerar() { String acelera = "La nave tripulada esta comenzando a acelerar";}
    
    //Metodo interface 
    @Override
    public void marcarRumbo() { String rumbo = "Espacio exterior"; }
	
}
       
