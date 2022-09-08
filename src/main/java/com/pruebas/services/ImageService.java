package com.pruebas.services;

import java.io.IOException;
import java.util.Base64;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.pruebas.exception.PersonNotFoundException;
import com.pruebas.model.domain.Image;
import com.pruebas.model.entity.ImageEntity;
import com.pruebas.repositories.ImageDAO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Transactional 
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service 
public class ImageService implements BaseImgService<Image>
{
	
	@Autowired
    private ImageDAO imageRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

	@Override
	public ResponseEntity<Image> getImagen(Integer id) 
	{
		log.debug("Cargando Imagen");
		ImageEntity imageEntity = imageRepository.findById(personService.getImageId(id).getBody()).orElseThrow(() -> new PersonNotFoundException("La  imagen no existe."));
        return ResponseEntity.ok(modelMapper.map(imageEntity, Image.class));
	}

	@Override
	public ResponseEntity<Image> uploadImagen(Integer id, MultipartFile image) throws IOException 
	{
		
		String imageId = personService.getImageId(id).getBody();
		
        if(imageId == "null")
        {
        	log.debug("Guardando imagen");
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setTipoImagen(image.getContentType());
            imageEntity.setImagen(Base64.getEncoder().encodeToString(image.getBytes()));
            
            imageRepository.save(imageEntity);
            personService.assignImageId(id, imageEntity.getId());
            return ResponseEntity.ok(modelMapper.map(imageEntity, Image.class));
        }
        else
        {
            return null;
        }
	}

	@Override
	public ResponseEntity<Boolean> deleteImagen(Integer id) 
	{
		 String imageId = personService.getImageId(id).getBody();
	        if(imageId != "null")
	        {
	        	log.debug("Eliminando Imagen");
	            imageRepository.deleteById(imageId);
	            personService.assignImageId(id, null);
	            return ResponseEntity.ok(true);
	        }
	        else
	        {
	            return ResponseEntity.ok(false);
	        }
		
	}

	@Override
	public ResponseEntity<Boolean> updateImagen(Integer id, MultipartFile imagen) throws IOException 
	{
		String imageId = personService.getImageId(id).getBody();
		
        if(imageId != "null"){
        	log.debug("Actualizando Imagen");
            ImageEntity imageEntity = imageRepository.findById(imageId).get();
            imageEntity.setImagen(Base64.getEncoder().encodeToString(imagen.getBytes()));
            imageRepository.save(imageEntity);
            return ResponseEntity.ok(true);
        }
        else{
            return null;
        }
		
	}
	
}
