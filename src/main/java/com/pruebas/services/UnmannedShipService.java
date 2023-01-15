package com.pruebas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pruebas.exception.NoPeopleFound;
import com.pruebas.exception.ShipAlreadyCreatedException;
import com.pruebas.exception.ShipNotFoundException;
import com.pruebas.model.domain.UnmannedShip;
import com.pruebas.model.entity.UnmannedShipEntity;
import com.pruebas.repositories.UnmannedShipDAO;


@Service
@Transactional 
public class UnmannedShipService implements BaseShipService<UnmannedShip>{
	
	@Autowired
	private UnmannedShipDAO unmannedShipDAO;
	@Autowired
	private ImageService imageService;
	
	
	@Override
	public List<UnmannedShip> getAllShips(){

		String base64Img = "";
		List<UnmannedShip> shipImg = new ArrayList<>();
		List<UnmannedShip> unmannedShips = unmannedShipDAO.findAll()
				                                          .stream()
				                                          .map(this::mapUnmannedShip)
				                                          .collect(Collectors.toList());
		for(UnmannedShip unmanned: unmannedShips) {
			if(unmanned.getImagen() == null) {
				base64Img = null;
			}else {
				base64Img = imageService.getBase(unmanned.getImagen());
			}
			
			unmanned.setImagen(base64Img);
			shipImg.add(unmanned);			
		}
		
		return shipImg;
	}
	
	private UnmannedShip mapUnmannedShip(UnmannedShipEntity unmannedShipEntity){
    	return new UnmannedShip(unmannedShipEntity.getId(), 
				                unmannedShipEntity.getCosteProduccion(),
				                unmannedShipEntity.getNombre(),
				                unmannedShipEntity.getPaisFabricacion(),
				                unmannedShipEntity.getImagen(),
				                unmannedShipEntity.getTipo(),
				                unmannedShipEntity.getPeso(),
				                unmannedShipEntity.getDestino(),
				                unmannedShipEntity.getEstadoMision()
				);
    }
	
	@Override
	public UnmannedShip getShipById(Integer id) throws Exception {
		
		return unmannedShipDAO.findById(id)
				.map(this::mapUnmannedShip)
				.orElseThrow(() -> new ShipNotFoundException("The Ship is not registered"));
	}
	
	@Override
	public ResponseEntity<UnmannedShip> saveShip(UnmannedShip unmannedShip) throws Exception {
		
		if(unmannedShipDAO.existsById(unmannedShip.getId())) {
			throw new ShipAlreadyCreatedException("The ship with the id "+ unmannedShip.getId() + " has already been created");
		}else {
			unmannedShipDAO.save(new UnmannedShipEntity(unmannedShip.getId(),
												    unmannedShip.getCosteProduccion(), 
					                                unmannedShip.getNombre(), 
					                                unmannedShip.getTipo(), 
					                                unmannedShip.getPeso(), 
					                                unmannedShip.getPaisFabricacion(), 
					                                unmannedShip.getImagen(), 
					                                unmannedShip.getDestino(), 
					                                unmannedShip.getEstadoMision()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(unmannedShip);
	}
	
	
	@Override
	public UnmannedShip updateShip(Integer id, UnmannedShip unmannedShip) throws Exception {
		
		if(unmannedShipDAO.existsById(unmannedShip.getId())){
			
			unmannedShipDAO.save(new UnmannedShipEntity(unmannedShip.getId(),
												        unmannedShip.getCosteProduccion(), 
					                                    unmannedShip.getNombre(), 
					                                    unmannedShip.getTipo(), 
					                                    unmannedShip.getPeso(), 
					                                    unmannedShip.getPaisFabricacion(), 
					                                    unmannedShip.getImagen(), 
					                                    unmannedShip.getDestino(),
					                                    unmannedShip.getEstadoMision()));
			return unmannedShip;
		}else {
			throw new ShipNotFoundException("The Unmanned ship with the Id "+ unmannedShip.getId() + " does not exist");
		}
	}
	
	@Override
	public ResponseEntity<UnmannedShip> deleteShip(Integer id) throws Exception {
		if(unmannedShipDAO.existsById(id)) {
			UnmannedShip unmannedShip = getShipById(id);
			unmannedShipDAO.deleteById(unmannedShip.getId());
			return ResponseEntity.status(HttpStatus.OK).body(unmannedShip);
		}else {
			throw new ShipNotFoundException("The unmanned ship with the Id "+ id + " does not exist");
		}
	}
	
	
	@Override
	public ResponseEntity<String> getImageId(Integer id) {
		
		if(unmannedShipDAO.existsById(id)){
			
            UnmannedShipEntity unmannedShipEntity = unmannedShipDAO.findById(id).get();
            if(unmannedShipEntity.getImagen() == null){
                return ResponseEntity.ok("null");
            }
            else{
                return ResponseEntity.ok(unmannedShipDAO.findById(id).get().getImagen());
            }
        }else{
        	return ResponseEntity.ok("null");
        }
	}
	
	
	@Override
	public ResponseEntity<Boolean> assignImageId(Integer id, String imagenId) {
		
		if(unmannedShipDAO.existsById(id)){
	        UnmannedShipEntity unmannedShipEntity = unmannedShipDAO.findById(id).get();
	        unmannedShipEntity.setImagen(imagenId);
	        unmannedShipDAO.save(unmannedShipEntity);
	        return ResponseEntity.ok(true);
		 }else{
	        return ResponseEntity.ok(false);
	     }
	}
	
	
	public List<UnmannedShip> getUnmannedByDestiny(String destino){
		
		List<UnmannedShip> unmannedDestiny = unmannedShipDAO.findByDestino(destino)
															.stream()
															.map(this::mapUnmannedShip)
															.collect(Collectors.toList()); 
		if(unmannedDestiny.isEmpty()) 
		{
			throw new NoPeopleFound("Ship not Found");
		}
		return unmannedDestiny;
	}
	

}
