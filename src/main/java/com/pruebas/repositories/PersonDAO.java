//DAO es un patron que propone separar por completo la logica de negocio de nuestra aplicacion
//de la logica para acceder a la base de datos
package com.pruebas.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pruebas.model.entity.PersonEntity;

@Repository
public interface PersonDAO extends JpaRepository<PersonEntity, Integer> //JpaRepository<entidada a la que va hacer referencia, tipo de ID que declaramos>
{
	 //ArrayList<PersonaEntity> findAll();
	
}
