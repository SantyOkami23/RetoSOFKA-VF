package com.pruebas.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.pruebas.model.domain.Image;
import com.pruebas.services.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;


@RestController
@CrossOrigin(origins = "*")  //esto es para que acepte peticiones de diferentes locaciones
@RequestMapping(path = "imagenes")
@AllArgsConstructor
public class ImageRest 
{
    @Autowired
    private ImageService imagenServicio;

    @Operation(summary = "Obtiener un objeto imagen mediante su id")
    @GetMapping()
    public ResponseEntity<Image> getImagen(@RequestParam("id") Integer id)
    {
        return imagenServicio.getImagen(id);
    }

    @Operation(summary = "Guardar un objeto imagen")
    @PostMapping()
    public ResponseEntity<Image> uploadImagen(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile imagen ) throws IOException 
    {
        imagen.getBytes();
    	return imagenServicio.uploadImagen(id, imagen);
    }

    @Operation(summary = "Obtiene un objeto imagen mediante su id")
    @DeleteMapping()
    public ResponseEntity<Boolean> deleteImagen(@RequestParam("id") Integer id)
    {
        return imagenServicio.deleteImagen(id);
    }
    
    @Operation(summary = "Actualiza un objeto imagen mediante su id")
    @PutMapping()
    public ResponseEntity<Boolean> updateImagen(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile imagen) throws IOException 
    {
        return imagenServicio.updateImagen(id, imagen);
    }

}
