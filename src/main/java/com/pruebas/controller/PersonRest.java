package com.pruebas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pruebas.model.domain.Person;
import com.pruebas.services.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "persons")
@AllArgsConstructor
public class PersonRest
{
   
   @Autowired
   private PersonService personService;

   
   
   @Operation(summary = "Get list of all person objects")
   @GetMapping()
   public ResponseEntity<?> getAll()
   {   
	 return ResponseEntity.status(HttpStatus.OK).body(personService.getPeople()); 
   }
   
   
   
   @Operation(summary = "Get a person object by means of an identification Id")
   @GetMapping("/{id}")
   public ResponseEntity<Person> getPerson(@PathVariable("id") Integer id)
   {
	   Person entity = personService.getPersonById(id);
	   
	   if(entity == null){
		   return ResponseEntity.notFound().build();
	   }else {
		   return ResponseEntity.ok(entity);
	   }
   } 
   
   
   
    @Operation(summary = "get a list of person objects greater than age")
    @GetMapping("age/{age}")
	public ResponseEntity<?> getPersonsGreater(@PathVariable Integer age) throws Exception
    {
		return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonsAge(age));
	}
   

   @Operation(summary = "Save a person object")
   @PostMapping()
   public ResponseEntity<?> savePerson(@RequestBody @Valid Person entity) throws Exception
   {
       return ResponseEntity.status(HttpStatus.OK).body(personService.savePerson(entity));
   }

  
   @Operation(summary = "Update a person object")
   @PutMapping("/{id}")
   public ResponseEntity<Person> updatePerson(@PathVariable("id") Integer id, @RequestBody @Valid Person entity)
   {
	   Person entityUpdated = personService.updatePerson(id, entity);
	   if(entityUpdated == null)
	   {
		   return ResponseEntity.notFound().build();
	   }else 
	   {
		   return ResponseEntity.ok(entityUpdated);
	   }
   }
  
   @Operation(summary = "Delete a person object")
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deletePerson(@PathVariable("id") Integer id) throws Exception
   {
	      return ResponseEntity.status(HttpStatus.OK).body(personService.deletePerson(id));    
   }
}