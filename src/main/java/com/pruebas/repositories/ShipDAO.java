package com.pruebas.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.pruebas.model.entity.ShipEntity;


@NoRepositoryBean //De esta interface no se pueden crear instancias
public interface ShipDAO <E extends ShipEntity, Long extends Serializable> extends JpaRepository<E, Long>{

	//RECIBE DOS TIPOS E QUE EXTIENDEN DE NADE_ENTITY
	//LUEGO ESTA INTERFACE EXTIENDE DE JPA REPOSITORY
}
