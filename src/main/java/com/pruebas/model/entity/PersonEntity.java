//Estos es un modelo o entidad
package com.pruebas.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.envers.Audited;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity 
@Table(name = "persona")
@Audited
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonEntity implements Serializable 
{ 
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="Nombre",length = 50,nullable = false) 
	private String nombre;
	@Column(name="Apellidos",length = 50,nullable = false)
	private String apellidos;
	@Column(name="Tipodoc")
	private String tipodoc;
	@Column(name="Documento",length = 50,nullable = false)
	private String documento;
	@Column(name="Edad",nullable = false)
	private int edad;
	@Column(name="Ciudad",length = 50,nullable = false)
	private String ciudad;
	@Column(name="foto")
	private String foto = null;
	@Column(name="Direccion")
	private String direccion;
	
}
