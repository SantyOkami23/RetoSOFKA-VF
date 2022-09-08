package com.pruebas.model.domain;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person 
{

	private Integer id;
	@NotBlank
	@Size(min = 2, max = 50)
	private String nombre;
	@NotBlank
	@Size(min = 2, max = 50)
	private String apellidos;
	@NotBlank
	@Size(max = 50)
	private String tipodoc;  
	@NotBlank
	@Pattern(regexp =  "[0-9]+")
	private String documento;
	@NotNull
	@Digits(integer = 2, fraction = 0)
	private int edad;
	@NotBlank
	@Size(min = 4, max = 50)
	private String ciudad;
	private String foto;
	@NotBlank
	private String direccion;
	
}
