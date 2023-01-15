package com.pruebas.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.pruebas.model.entity.MannedShipEntity;

@Repository
public interface MannedShipDAO extends ShipDAO <MannedShipEntity, Integer>{

	List<MannedShipEntity> findByNombre(String nombre);
	List<MannedShipEntity> findBypaisFabricacion(String paisFabricacion);
	List<MannedShipEntity> findByCapacidadPersonas(int capacidadPersonas);
	
}
