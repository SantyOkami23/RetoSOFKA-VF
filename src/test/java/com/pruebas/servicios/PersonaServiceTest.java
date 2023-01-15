package com.pruebas.servicios;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.pruebas.exception.PersonNotFoundException;
import com.pruebas.model.domain.Person;
import com.pruebas.model.entity.PersonEntity;
import com.pruebas.repositories.PersonDAO;
import com.pruebas.services.ImageService;
import com.pruebas.services.PersonService;


@RunWith(MockitoJUnitRunner.class)
class PersonaServiceTest 
{
	@Mock //objeto mock que va a ser crado por mockito 
	private PersonDAO personRepository;
	@Mock
	private ImageService imageService;
	@InjectMocks  //mocks que van a ser inyectados 
	private PersonService personService;
	
	private Person person = new Person();
	private PersonEntity personEntity = new PersonEntity();
	
	
	@BeforeEach
    void setUp()
	{
		MockitoAnnotations.openMocks(this); 
		
		//Persona de prueba 
		Integer id = 1;
		String nombre = "Miguel";
		String apellidos = "Rosales"; 
		String tipoDocumento = "Cedula";
		String documento = "4190";
		int  edad =27;
		String ciudad = "Armenia";
		String foto = null;
		String direccion = "Consota";
		
		//Variable para realizar el test 
		personEntity = mock(PersonEntity.class);
		person = mock(Person.class);

		personEntity = new PersonEntity(id,nombre,apellidos,tipoDocumento,documento,edad,ciudad,foto,direccion);
		person = new Person(id,nombre,apellidos,tipoDocumento,documento,edad,ciudad,foto,direccion);	
		
	}
	
	@Test
	void getPeople()
	{
		 when(personRepository.findAll()).thenReturn(Collections.singletonList(personEntity));
		 when(imageService.getBase(any())).thenReturn(person.getFoto());

	     List<Person> People = personService.getPeople();

	     assertEquals(personEntity.getId(), People.get(0).getId());
	     assertEquals(personEntity.getNombre(), People.get(0).getNombre());
	}
	
	@Test
    void savePerson() throws Exception
	{
		
		when(personRepository.save(any())).thenReturn(personEntity);

        personService.savePerson(person);
        
        assertNotNull(personService.savePerson(person));
        assertEquals(person.getId(), personEntity.getId());
        assertEquals(person.getNombre(), personEntity.getNombre());
        

    }
	
	@Test
	void getPersonById()
	{
		
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(personEntity));

        person = personService.getPersonById(personEntity.getId());

        assertEquals(person.getId(), personEntity.getId());
        assertEquals(person.getNombre(), personEntity.getNombre());	
	}
	
	@Test
	void updatePerson()
	{
		when(personRepository.existsById(1)).thenReturn(true);
		when(personRepository.save(any())).thenReturn(personEntity);
		
		personService.updatePerson(personEntity.getId(),person);
		
		assertEquals(person,personService.updatePerson(1, person));		
	}
	
	
	@Test
	void deletePerson() throws Exception 
	{
		when(personRepository.existsById(1)).thenReturn(true);
		when(personRepository.findById(1)).thenReturn(Optional.of(personEntity));
		doNothing().when(personRepository).deleteById(1);
	
        personService.deletePerson(personEntity.getId());

        assertNotNull(personEntity);
    }
	
	@Test
    void deleteDemoError() 
	{

        when(personRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class,() -> personService.deletePerson(1));
	}
	
	@Test
    void getPersonsAge() 
	{

        when(personRepository.findByEdadGreaterThanEqual(anyInt())).thenReturn(Collections.singletonList(personEntity));
        assertNotNull(personRepository.findByEdadGreaterThanEqual(anyInt()));
	}
	
	
}