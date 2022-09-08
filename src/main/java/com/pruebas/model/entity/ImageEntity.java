package com.pruebas.model.entity;


import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Document(collection = "imagenes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImageEntity{
	
	  	@Id
	    private String id;
	    private String tipoImagen;
	    private String imagen;
}
