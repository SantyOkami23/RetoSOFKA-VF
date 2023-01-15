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
import com.pruebas.model.domain.UnmannedShip;
import com.pruebas.services.UnmannedShipService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/noTripulada")
public class UnmannedShipRest {
	
	@Autowired
	private UnmannedShipService unmannedShipService;
	
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(unmannedShipService.getAllShips());
	}
	
	
	@PostMapping
	public ResponseEntity<?> saveUnmannedShip(@RequestBody @Valid UnmannedShip unmannedShip) throws Exception{
		
		return ResponseEntity.status(HttpStatus.CREATED).body(unmannedShipService.saveShip(unmannedShip));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UnmannedShip> getUnmannedShip(@PathVariable("id") Integer id) throws Exception{
		
		UnmannedShip unmannedShip = unmannedShipService.getShipById(id);
		return ResponseEntity.status(HttpStatus.OK).body(unmannedShip);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<UnmannedShip> updateUnmannedShip(@PathVariable("id") Integer id, @RequestBody @Valid UnmannedShip unmannedShip) throws Exception{
		   UnmannedShip unmannedShipUpdated = unmannedShipService.updateShip(id, unmannedShip);
		   if(unmannedShipUpdated == null){
			   return ResponseEntity.notFound().build();
		   }else {
			   return ResponseEntity.ok(unmannedShipUpdated);
		   }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUnmannedShip(@PathVariable("id") Integer id) throws Exception{
		  return ResponseEntity.status(HttpStatus.OK).body(unmannedShipService.deleteShip(id));    
	 }
	
	
	@GetMapping("destino/{destino}")
	public ResponseEntity<?>  getUnmannedByDestiny(@PathVariable String destino){
		return ResponseEntity.status(HttpStatus.OK).body(unmannedShipService.getUnmannedByDestiny(destino));
	}
	
	
}
