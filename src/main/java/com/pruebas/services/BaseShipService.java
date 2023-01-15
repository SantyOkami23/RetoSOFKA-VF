package com.pruebas.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface BaseShipService<Entity> {
	
	  public List<Entity> getAllShips() throws Exception;
	  public Entity getShipById(Integer id) throws Exception;  
	  public ResponseEntity<Entity> saveShip(Entity entity) throws Exception;
	  public Entity updateShip(Integer id, Entity entity) throws Exception;
	  public ResponseEntity<Entity> deleteShip(Integer id) throws Exception;
	  ResponseEntity<String> getImageId(Integer id);
	  ResponseEntity<Boolean> assignImageId(Integer id, String imagenId);

}
