package com.pruebas.services;


import java.util.List;
import org.springframework.http.ResponseEntity;

import com.pruebas.model.domain.Person;


public interface BasePersonService<Entity> 
{
	
  public List<Entity> getPeople() throws Exception;
  public Entity getPersonById(Integer id) throws Exception;  
  public ResponseEntity<Person> savePerson(Entity entity) throws Exception;
  public Entity updatePerson(Integer id, Entity entity) throws Exception;
  public ResponseEntity<Person> deletePerson(Integer id) throws Exception;
  ResponseEntity<String> getImageId(Integer id);
  ResponseEntity<Boolean> assignImageId(Integer id, String imagenId);
}