package com.pruebas.services;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface BaseImgService<E> 
{
	    ResponseEntity<E> getImagen(Integer id);
	    ResponseEntity<E> uploadImagen(Integer id, MultipartFile imagen) throws IOException;
	    ResponseEntity<Boolean> deleteImagen(Integer id);
	    ResponseEntity<Boolean> updateImagen(Integer id, MultipartFile imagen) throws IOException;
}
