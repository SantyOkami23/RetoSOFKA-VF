package com.pruebas.servicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.pruebas.model.domain.Image;
import com.pruebas.model.entity.ImageEntity;
import com.pruebas.model.entity.PersonEntity;
import com.pruebas.repositories.ImageDAO;
import com.pruebas.services.ImageService;
import com.pruebas.services.PersonService;



@RunWith(MockitoJUnitRunner.class)
class ImagenServiceTest 
{
	@Mock //objeto mock que va a ser crado por mockito 
	private ImageDAO imageRepository;
	@Mock
	private PersonService personService;
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks  //mocks que van a ser inyectados
	private ImageService imageService;
	
	
	private Image image = new Image();
	private ImageEntity imageEntity = new ImageEntity();
	private PersonEntity personEntity;
	private MultipartFile inputFile;
	
	@BeforeEach
    void setUp()
	{
		MockitoAnnotations.openMocks(this); 
		
		//Cargar imagen de prueba
		inputFile  = new MockMultipartFile("patricia.jpg", "patricia.jpg",null,"contenido".getBytes());
		
		
		//Persona de prueba 
		String id = "6308e674828750481a57b93a";
		String tipoImagen = "image/jpeg";
		String imagen = "/9j/4AAQSkZJRgABAQEAY";
		
		Integer idp = 1;
		String nombre = "Miguel";
		String apellidos = "Rosales"; 
		String tipoDocumento = "Cedula";
		String documento = "4190";
		int  edad =27;
		String ciudad = "Armenia";
		String foto = id;
		String direccion = "Consota";
	
		
		//Variable para realizar el test 
		imageEntity = mock(ImageEntity.class);
		image = mock(Image.class);

		imageEntity = new ImageEntity(id,tipoImagen,imagen);
		image = new Image(id,tipoImagen,imagen);	
		personEntity = new PersonEntity(idp,nombre,apellidos,tipoDocumento,documento,edad,ciudad,foto,direccion);
		
	}
	
	
	@Test
	void getImagen() 
	{
		when(imageRepository.findById(any())).thenReturn(Optional.of(imageEntity));
		when(personService.getImageId(any())).thenReturn(ResponseEntity.ok(personEntity.getFoto()));	
		when(modelMapper.map(imageEntity,Image.class)).thenReturn(image);
	
	
		assertNotNull(imageService.getImagen(personEntity.getId()));
	}
	
	@Test
	void uploadImage() throws Exception
	{
		when(personService.getImageId(any())).thenReturn(ResponseEntity.ok(personEntity.getFoto()));
				
	    imageService.uploadImagen(1, inputFile);
	    assertEquals(image.getId(), imageEntity.getId());        
	}
	
	@Test
	void updateImage() throws IOException
	{
		when(personService.getImageId(any())).thenReturn(ResponseEntity.ok(personEntity.getFoto()));
		when(imageRepository.findById(any())).thenReturn(Optional.of(imageEntity));
	
		assertNotNull(imageService.updateImagen(1, inputFile));
	}
	
	@Test
	void deleteImage()
	{
		when(personService.getImageId(any())).thenReturn(ResponseEntity.ok(personEntity.getFoto()));
		
		assertNotNull(imageService.deleteImagen(1));		
	}
}



