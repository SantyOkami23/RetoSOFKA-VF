package com.pruebas.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "Naves")
//@Entity
public abstract class ShipEntity {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "coste_produccion")
    private int costeProduccion;
	@Column(name = "nombre", length = 50, nullable = true)
    private String nombre;
	@Column(name = "tipo", length = 50, nullable = true)
    private String tipo;
	@Column(name = "peso", length = 50, nullable = true)
    private String peso;
	@Column(name = "pais_fabricacion", length = 50, nullable = true)
    private String paisFabricacion;
	@Column(name = "imagen_nave")
    private String imagen = null;
	
	public abstract void asignarMision();
	
}
