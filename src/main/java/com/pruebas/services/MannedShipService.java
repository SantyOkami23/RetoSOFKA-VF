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
import com.pruebas.model.domain.MannedShip;
import com.pruebas.model.entity.MannedShipEntity;
import com.pruebas.repositories.MannedShipDAO;

@Service
public class MannedShipService implements BaseShipService<MannedShip>{
	
	@Autowired
	private MannedShipDAO mannedShipDAO;
	@Autowired
    private ImageService imageService;

	
	@Override
	public List<MannedShip> getAllShips(){
		
		String base64Img = "";
		List<MannedShip> shipImg = new ArrayList<>();
		List<MannedShip> mannedShips = mannedShipDAO.findAll()
				                                  .stream()
				                                  .map(this::mapMannedShip)
				                                  .collect(Collectors.toList());
		for(MannedShip manned: mannedShips) {
			if(manned.getImagen() == null) {
				base64Img = null;
			}else {
				base64Img = imageService.getBase(manned.getImagen());
			}
			
			manned.setImagen(base64Img);
			shipImg.add(manned);			
		}
		
		return shipImg;
	}
	
	private MannedShip mapMannedShip(MannedShipEntity mannedShipEntity)
    {
    	return new MannedShip(mannedShipEntity.getId(), 
				mannedShipEntity.getCosteProduccion(),
				mannedShipEntity.getNombre(),
				mannedShipEntity.getPaisFabricacion(),
				mannedShipEntity.getImagen(),
				mannedShipEntity.getTipo(),
				mannedShipEntity.getPeso(),
				mannedShipEntity.getCapacidadPersonas(),
				mannedShipEntity.getVelocidad()
				);
    }

	@Override
	public MannedShip getShipById(Integer id){
		return mannedShipDAO.findById(id)
				.map(this::mapMannedShip)
				.orElseThrow(() -> new ShipNotFoundException("The Ship is not registered"));
	}
	

	@Override
	public ResponseEntity<MannedShip> saveShip(MannedShip mannedShip) throws Exception {
		
		if(mannedShipDAO.existsById(mannedShip.getId())) {
			throw new ShipAlreadyCreatedException("The ship with the id "+ mannedShip.getId() + " has already been created");
		}else {
			mannedShipDAO.save(new MannedShipEntity(mannedShip.getId(),
												    mannedShip.getCosteProduccion(), 
					                                mannedShip.getNombre(), 
					                                mannedShip.getTipo(), 
					                                mannedShip.getPeso(), 
					                                mannedShip.getPaisFabricacion(), 
					                                mannedShip.getImagen(), 
					                                mannedShip.getCapacidadPersonas(), 
					                                mannedShip.getVelocidad()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(mannedShip);
	}
	
	
	@Override
	public MannedShip updateShip(Integer id, MannedShip mannedShip) throws Exception{
		
		if(mannedShipDAO.existsById(mannedShip.getId())){
			
			mannedShipDAO.save(new MannedShipEntity(mannedShip.getId(),
												    mannedShip.getCosteProduccion(), 
					                                mannedShip.getNombre(), 
					                                mannedShip.getTipo(), 
					                                mannedShip.getPeso(), 
					                                mannedShip.getPaisFabricacion(), 
					                                mannedShip.getImagen(), 
					                                mannedShip.getCapacidadPersonas(), 
					                                mannedShip.getVelocidad()));
			return mannedShip;
		}else {
			throw new ShipNotFoundException("The manned ship with the Id "+ mannedShip.getId() + " does not exist");
		}
	}
	
	
	@Override
	public ResponseEntity<MannedShip> deleteShip(Integer id) throws Exception{
		
		if(mannedShipDAO.existsById(id)) {
			MannedShip mannedShip = getShipById(id);
			mannedShipDAO.deleteById(mannedShip.getId());
			return ResponseEntity.status(HttpStatus.OK).body(mannedShip);
		}else {
			throw new ShipNotFoundException("The manned ship with the Id "+ id + " does not exist");
		}
	}
	
	
	@Override
	public ResponseEntity<String> getImageId(Integer id) {
		if(mannedShipDAO.existsById(id)){
            MannedShipEntity mannedShipEntity = mannedShipDAO.findById(id).get();
            if(mannedShipEntity.getImagen() == null){
                return ResponseEntity.ok("null");
            }
            else{
                return ResponseEntity.ok(mannedShipDAO.findById(id).get().getImagen());
            }
        }
        else{
        	return ResponseEntity.ok("null");
        }
	}

	@Override
	public ResponseEntity<Boolean> assignImageId(Integer id, String imagenId) 
	{
		 if(mannedShipDAO.existsById(id)){
	        MannedShipEntity mannedShipEntity = mannedShipDAO.findById(id).get();
	        mannedShipEntity.setImagen(imagenId);
	        mannedShipDAO.save(mannedShipEntity);
	        return ResponseEntity.ok(true);
		 }else{
	        return ResponseEntity.ok(false);
	     }
	}
	
	
	public List<MannedShip> getShipsByCapacity(int capacidad){
		
		List<MannedShip> shipsByCapacity = mannedShipDAO.findByCapacidadPersonas(capacidad)
				                                        .stream()
				                                        .map(this::mapMannedShip)
				                                        .collect(Collectors.toList());
		if(shipsByCapacity.isEmpty()) {
			throw new NoPeopleFound("Ships no found");
		}
		return shipsByCapacity;
		
	}
	
}
