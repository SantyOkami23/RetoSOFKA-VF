package com.pruebas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pruebas.exception.NoPeopleFound;
import com.pruebas.exception.ShipAlreadyCreatedException;
import com.pruebas.exception.ShipNotFoundException;
import com.pruebas.model.domain.ShuttleShip;
import com.pruebas.model.entity.ShuttleShipEntity;
import com.pruebas.repositories.ShuttleShipDAO;

@Service
public class ShuttleShipService implements BaseShipService<ShuttleShip>{

	@Autowired
	private ShuttleShipDAO shuttleShipDAO;
	@Autowired
	private ImageService imagenService;
	
	@Override
	public List<ShuttleShip> getAllShips() throws Exception {
		
		
		String base64Img = "";
		List<ShuttleShip> shuttleImg = new ArrayList<>();
		List<ShuttleShip> shuttleShips = shuttleShipDAO.findAll()
				                                  .stream()
				                                  .map(this::mapShuttleShip)
				                                  .collect(Collectors.toList());
		for(ShuttleShip shuttle: shuttleShips) {
			if(shuttle.getImagen() == null) {
				base64Img = null;
			}else {
				base64Img = imagenService.getBase(shuttle.getImagen());
			}
			
			shuttle.setImagen(base64Img);
			shuttleImg.add(shuttle);			
		}
		
		return shuttleImg;
	}
	
	
	private ShuttleShip mapShuttleShip(ShuttleShipEntity shuttleShipEntity)
    {
    	return new ShuttleShip(shuttleShipEntity.getId(), 
				shuttleShipEntity.getCosteProduccion(),
				shuttleShipEntity.getNombre(),
				shuttleShipEntity.getPaisFabricacion(),
				shuttleShipEntity.getImagen(),
				shuttleShipEntity.getTipo(),
				shuttleShipEntity.getPeso(),
				shuttleShipEntity.getCapacidadPropulsion(),
				shuttleShipEntity.getCapacidadCarga()
				);
    }

	@Override
	public ShuttleShip getShipById(Integer id) throws Exception {
		return shuttleShipDAO.findById(id)
				.map(this::mapShuttleShip)
				.orElseThrow(() -> new ShipNotFoundException("The Ship is not registered"));
	}

	@Override
	public ResponseEntity<ShuttleShip> saveShip(ShuttleShip shuttleShip) throws Exception {
		
		if(shuttleShipDAO.existsById(shuttleShip.getId())) {
			throw new ShipAlreadyCreatedException("The ship with the id "+ shuttleShip.getId() + " has already been created");
		}else {
			shuttleShipDAO.save(new ShuttleShipEntity(shuttleShip.getId(),
					                                  shuttleShip.getCosteProduccion(), 
					                                  shuttleShip.getNombre(), 
					                                  shuttleShip.getTipo(), 
					                                  shuttleShip.getPeso(), 
					                                  shuttleShip.getPaisFabricacion(), 
					                                  shuttleShip.getImagen(), 
					                                  shuttleShip.getCapacidadPropulsion(), 
					                                  shuttleShip.getCapacidadCarga()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(shuttleShip);
	}

	@Override
	public ShuttleShip updateShip(Integer id, ShuttleShip shuttleShip) throws Exception {


			if(shuttleShipDAO.existsById(shuttleShip.getId())){
			
			   shuttleShipDAO.save(new ShuttleShipEntity(shuttleShip.getId(),
					                                     shuttleShip.getCosteProduccion(), 
					                                     shuttleShip.getNombre(), 
					                                     shuttleShip.getTipo(), 
					                                     shuttleShip.getPeso(), 
					                                     shuttleShip.getPaisFabricacion(), 
					                                     shuttleShip.getImagen(), 
					                                     shuttleShip.getCapacidadPropulsion(), 
					                                     shuttleShip.getCapacidadCarga()));
			return shuttleShip;
		}else {
			throw new ShipNotFoundException("The manned ship with the Id "+ shuttleShip.getId() + " does not exist");
		}
	}

	@Override
	public ResponseEntity<ShuttleShip> deleteShip(Integer id) throws Exception {
		
		if(shuttleShipDAO.existsById(id)) {
			ShuttleShip shuttleShip = getShipById(id);
			shuttleShipDAO.deleteById(shuttleShip.getId());
			return ResponseEntity.status(HttpStatus.OK).body(shuttleShip);
		}else {
			throw new ShipNotFoundException("The manned ship with the Id "+ id + " does not exist");
		}
	}

	@Override
	public ResponseEntity<String> getImageId(Integer id){
		
		if(shuttleShipDAO.existsById(id)){
			ShuttleShipEntity shuttleShipEntity = shuttleShipDAO.findById(id).get();
            if(shuttleShipEntity.getImagen() == null){
                return ResponseEntity.ok("null");
            }
            else{
                return ResponseEntity.ok(shuttleShipDAO.findById(id).get().getImagen());
            }
        }
        else{
        	return ResponseEntity.ok("null");
        }
	}

	@Override
	public ResponseEntity<Boolean> assignImageId(Integer id, String imagenId) {
		
		 if(shuttleShipDAO.existsById(id)){
			ShuttleShipEntity shuttleShipEntity = shuttleShipDAO.findById(id).get();
			shuttleShipEntity.setImagen(imagenId);
	        shuttleShipDAO.save(shuttleShipEntity);
	        return ResponseEntity.ok(true);
		 }else{
	        return ResponseEntity.ok(false);
	     }
	}
	
	public List<ShuttleShip> getByPropulsionCapacity(int capacidadPropulsion){
		
		List<ShuttleShip> shuttleCapacity = shuttleShipDAO.findByCapacidadPropulsion(capacidadPropulsion)
				                                          .stream()
				                                          .map(this::mapShuttleShip)
				                                          .collect(Collectors.toList()); 
		if(shuttleCapacity.isEmpty()) {
			throw new NoPeopleFound("People no found");
		}
		return shuttleCapacity;
	}

}
