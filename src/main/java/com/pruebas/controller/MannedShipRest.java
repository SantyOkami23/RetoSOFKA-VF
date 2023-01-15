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
import com.pruebas.model.domain.MannedShip;
import com.pruebas.services.MannedShipService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tripulada")
public class MannedShipRest {
	
	@Autowired
	private MannedShipService mannedShipService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(mannedShipService.getAllShips());
	}
	
	
	@PostMapping
	public ResponseEntity<?> saveMannedShip(@RequestBody @Valid MannedShip mannedShip) throws Exception{
		
		return ResponseEntity.status(HttpStatus.CREATED).body(mannedShipService.saveShip(mannedShip));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MannedShip> getMannedShip(@PathVariable("id") Integer id){
		
		MannedShip mannedShip = mannedShipService.getShipById(id);
		return ResponseEntity.status(HttpStatus.OK).body(mannedShip);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<MannedShip> updateMannedShip(@PathVariable("id") Integer id, @RequestBody @Valid MannedShip mannedShip) throws Exception{
		   MannedShip mannedShipUpdated = mannedShipService.updateShip(id, mannedShip);
		   if(mannedShipUpdated == null){
			   return ResponseEntity.notFound().build();
		   }else {
			   return ResponseEntity.ok(mannedShipUpdated);
		   }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUnmannedShip(@PathVariable("id") Integer id) throws Exception{
		  return ResponseEntity.status(HttpStatus.OK).body(mannedShipService.deleteShip(id));    
	 }
	
	
	
	@GetMapping("capacidad/{capacidad}")
	public ResponseEntity<?> getShipsByCapacity(@PathVariable int capacidad) throws Exception
    {
		return ResponseEntity.status(HttpStatus.OK).body(mannedShipService.getShipsByCapacity(capacidad));
	}
	

}
