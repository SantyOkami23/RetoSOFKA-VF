package com.pruebas.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.pruebas.model.entity.ShuttleShipEntity;

@Repository
public interface ShuttleShipDAO extends ShipDAO<ShuttleShipEntity,Integer>{
	
	List<ShuttleShipEntity> findByNombre(String nombre);
	List<ShuttleShipEntity> findByPaisFabricacion(String paisFabricacion);
	List<ShuttleShipEntity> findByCapacidadPropulsion(int capacidadPropulsion);

}
