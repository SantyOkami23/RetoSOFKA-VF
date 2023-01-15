package com.pruebas.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.pruebas.model.entity.UnmannedShipEntity;

@Repository
public interface UnmannedShipDAO extends ShipDAO <UnmannedShipEntity,Integer>{
	
    List<UnmannedShipEntity> findByNombre(String nombre);
	List<UnmannedShipEntity> findBypaisFabricacion(String paisFabricacion);
	List<UnmannedShipEntity> findByDestino(String destino);

}
