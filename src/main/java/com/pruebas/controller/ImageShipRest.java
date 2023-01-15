package com.pruebas.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.pruebas.services.ImageMannedShipService;
import com.pruebas.services.ImageShuttleShipService;
import com.pruebas.services.ImageUnmannedShipService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping(path = "imagenesShip")
@AllArgsConstructor
public class ImageShipRest {
    
	@Autowired
    private ImageMannedShipService imagenShipServicio;
    
    @Autowired
    private ImageUnmannedShipService imageUnmannedShipService;
    
    @Autowired
    private ImageShuttleShipService imageShuttleShipService;


    @GetMapping()
    public ResponseEntity<Image> getImagen(@RequestParam("service") String service, @RequestParam("id") Integer id) {
        
    	if (service.equals("unmanned")) {
           return imageUnmannedShipService.getImagen(id);
        } else if (service.equals("manned")) {
            return imagenShipServicio.getImagen(id);
        }else if (service.equals("shuttle")) {
        	return imageShuttleShipService.getImagen(id);
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
    }

    
    @PostMapping()
    public ResponseEntity<Image> uploadImagen(@RequestParam("service") String service, @RequestParam("id") Integer id, @RequestParam("file") MultipartFile imagen ) throws IOException {
    	
    	imagen.getBytes();
    	if (service.equals("unmanned")) {
            return imageUnmannedShipService.uploadImagen(id, imagen);
         } else if (service.equals("manned")) {
             return imagenShipServicio.uploadImagen(id, imagen);
         }else if (service.equals("shuttle")) {
         	return imageShuttleShipService.uploadImagen(id, imagen);
         }else {
        	System.out.print(service);
         	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         }
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteImen(@RequestParam("service") String service, @RequestParam("id") Integer id) {
    	
    	if (service.equals("unmanned")) {
            return imageUnmannedShipService.deleteImagen(id);
         } else if (service.equals("manned")) {
             return imagenShipServicio.deleteImagen(id);
         }else if (service.equals("shuttle")) {
         	return imageShuttleShipService.deleteImagen(id);
         }else {
         	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         }
    }
    
    
 
    @PutMapping()
    public ResponseEntity<Boolean> updateImagen(@RequestParam("service") String service, @RequestParam("id") Integer id, @RequestParam("file") MultipartFile imagen) throws IOException {
    	
    	if (service.equals("unmanned")) {
            return imageUnmannedShipService.updateImagen(id, imagen);
         } else if (service.equals("manned")) {
             return imagenShipServicio.updateImagen(id, imagen);
         }else if (service.equals("shuttle")) {
         	return imageShuttleShipService.updateImagen(id, imagen);
         }else {
         	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         }
    }
}