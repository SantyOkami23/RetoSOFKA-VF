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
@RequestMapping(path = "personas")
@AllArgsConstructor
public class PersonRest
{
   
   @Autowired
   private PersonService personService;

   @Operation(summary = "Obtener listado de todos los objetos persona")
   @GetMapping()
   public ResponseEntity<?> getAll()
   {
	     try 
	     {
	        return ResponseEntity.status(HttpStatus.OK).body(personService.getPeople());
	     } catch (Exception e) 
	     {
	       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
	     }
   }
   
   @Operation(summary = "Obtener un objeto persona mediante un Id de identificacion")
   @GetMapping("/{id}")
   public ResponseEntity<Person> getPerson(@PathVariable("id") Integer id)
   {
	   Person entity = personService.getPersonById(id);
	   
	   if(entity == null)
	   {
		   return ResponseEntity.notFound().build();
	   }else 
	   {
		   return ResponseEntity.ok(entity);
	   }
   }  

   @Operation(summary = "Guardar un objeto persona")
   @PostMapping()
   public ResponseEntity<?> savePerson(@RequestBody @Valid Person entity) throws Exception
   {
       return ResponseEntity.status(HttpStatus.OK).body(personService.savePerson(entity));
   }

  
   @Operation(summary = "Actualizar un objeto persona")
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
  
   @Operation(summary = "Borrar un objeto persona")
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deletePerson(@PathVariable("id") Integer id)
   {
	    try 
	    {
	      return ResponseEntity.status(HttpStatus.OK).body(personService.deletePerson(id));    
	    } catch (Exception e) 
	    {
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
	    }

   }

}