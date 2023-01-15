package com.pruebas.model.domain;

public class Ship {
	
	
    private Integer id;
    private int costeProduccion;
    private String nombre;
    private String tipo;
    private String peso;
    private String paisFabricacion;
    private String imagen;
    
    public Ship(){}

    public Ship(Integer id, int costeProduccion, String nombre, String paisFabricacion, String imagen, String tipo, String peso) {
        this.id = id;
        this.costeProduccion = costeProduccion;
        this.nombre = nombre;
        this.tipo = tipo;
        this.peso = peso;
        this.paisFabricacion = paisFabricacion;
        this.imagen = imagen;
        
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCosteProduccion() {
		return costeProduccion;
	}

	public void setCosteProduccion(int costeProduccion) {
		this.costeProduccion = costeProduccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getPaisFabricacion() {
		return paisFabricacion;
	}

	public void setPaisFabricacion(String paisFabricacion) {
		this.paisFabricacion = paisFabricacion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}       
    
}
