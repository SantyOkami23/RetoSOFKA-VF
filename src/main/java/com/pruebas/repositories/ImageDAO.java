package com.pruebas.repositories;

import com.pruebas.model.entity.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


//@Repository
public interface ImageDAO extends MongoRepository<ImageEntity, String> {

}
