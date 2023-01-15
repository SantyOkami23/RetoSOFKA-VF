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
import com.pruebas.model.domain.ShuttleShip;
import com.pruebas.services.ShuttleShipService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lanzadora")
public class ShuttleShipRest {
	
	@Autowired
	private ShuttleShipService shuttleShipService;
	
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(shuttleShipService.getAllShips());
	}
	
	@PostMapping
	public ResponseEntity<?> saveShuttleShip(@RequestBody @Valid ShuttleShip shuttleShip) throws Exception{
		
		return ResponseEntity.status(HttpStatus.CREATED).body(shuttleShipService.saveShip(shuttleShip));
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<ShuttleShip> getShuttleShip(@PathVariable("id") Integer id) throws Exception{
		
		ShuttleShip shuttleShip = shuttleShipService.getShipById(id);
		return ResponseEntity.status(HttpStatus.OK).body(shuttleShip);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ShuttleShip> updateMannedShip(@PathVariable("id") Integer id, @RequestBody @Valid ShuttleShip shuttleShip) throws Exception{
		   ShuttleShip shuttleShipUpdate = shuttleShipService.updateShip(id, shuttleShip);
		   if(shuttleShipUpdate == null){
			   return ResponseEntity.notFound().build();
		   }else {
			   return ResponseEntity.ok(shuttleShipUpdate);
		   }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteShuttleShip(@PathVariable("id") Integer id) throws Exception{
		  return ResponseEntity.status(HttpStatus.OK).body(shuttleShipService.deleteShip(id));    
	 }
	
	
	@GetMapping("propulsion/{propulsion}")
	public ResponseEntity<?> getPersonsGreater(@PathVariable int propulsion) throws Exception
    {
		return ResponseEntity.status(HttpStatus.OK).body(shuttleShipService.getByPropulsionCapacity(propulsion));
	}
	

}
