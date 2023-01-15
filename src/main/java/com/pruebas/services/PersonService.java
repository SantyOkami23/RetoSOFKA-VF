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
import com.pruebas.exception.PersonAlreadyCreatedException;
import com.pruebas.exception.PersonNotFoundException;
import com.pruebas.model.domain.Person;
import com.pruebas.model.entity.PersonEntity;
import com.pruebas.repositories.PersonDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional 
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class PersonService implements BasePersonService<Person>
{
    @Autowired
    private PersonDAO personRepository;
    @Autowired
    private ImageService imageService;
    
    
    @Override
    @Transactional
    public List<Person> getPeople()  
    {
    		log.debug("Get people list");
    		String base = "";
    	    List<Person> personImg = new ArrayList<>();
    		List<Person> persons = personRepository.findAll()
    														 .stream()
            												 .map(this::mapPerson)
            												 .collect(Collectors.toList());
    		for(Person person : persons)
    		{
    		   if(person.getFoto() == null)
    		   {
    			   base = null;
    		   }else
    		   {
    			   base = imageService.getBase(person.getFoto());
    		   }
    		  
    		   person.setFoto(base);
    		   personImg.add(person);
    		}
    		
    		return personImg;
    }
    
    public List<Person> getPersonsAge(Integer age) throws NoPeopleFound 
    {
		List<Person> personGreater = personRepository.findByEdadGreaterThanEqual(age).stream().map(this::mapPerson).collect(Collectors.toList()); 
		if(personGreater.isEmpty()) 
		{
			throw new NoPeopleFound("People no found");
		}
		return personGreater;
    }
    
    
    private Person mapPerson(PersonEntity personEntity)
    {
    	return new Person(personEntity.getId(), 
				personEntity.getNombre(), 
				personEntity.getApellidos(), 
				personEntity.getTipodoc(), 
				personEntity.getDocumento(),
				personEntity.getEdad(), 
				personEntity.getCiudad(), 
				personEntity.getFoto(), 
				personEntity.getDireccion());
    }

    @Override
    @Transactional
    public Person getPersonById(Integer id)
    {
    	   log.debug("find Person with Id = {}", id);
    	   return personRepository.findById(id)
    	    		.map(this::mapPerson)
    	    		.orElseThrow(() -> new PersonNotFoundException("Unregistered Person"));
    }

    @Override
    @Transactional
    public ResponseEntity<Person> savePerson(Person entity)
    {
    	
    	if(personRepository.existsById(entity.getId()))
    	{
    		throw new PersonAlreadyCreatedException("The person with the ID" + entity.getId()+ "was already created.");
    	}else 
    	{
    		
    	
       		 log.debug("Saving Person");
             personRepository.save(
			 new PersonEntity(entity.getId(), 
				               entity.getNombre(), 
						       entity.getApellidos(), 
						       entity.getTipodoc(), 
						       entity.getDocumento(),
						       entity.getEdad(), 
						       entity.getCiudad(), 
						       entity.getFoto(), 
						       entity.getDireccion()));
             
             return ResponseEntity.status(HttpStatus.OK).body(entity);
    	   
    	}
    }
    
   

    @Override
    @Transactional
    public Person updatePerson(Integer id, Person entity)
    {
        
    	if(personRepository.existsById(entity.getId()))
    	{  
    		
            log.debug("updated person");
            personRepository.save(
            		new PersonEntity(entity.getId(), 
            				entity.getNombre(), 
            				entity.getApellidos(), 
            				entity.getTipodoc(), 
            				entity.getDocumento(),
            				entity.getEdad(), 
            				entity.getCiudad(), 
            				entity.getFoto(), 
            				entity.getDireccion()));
            
            return entity;
    	}else
    	{
    		throw new PersonNotFoundException("The person with the ID" + entity.getId()+"Does not exist.");
    	}
    }

    @Override
    @Transactional
    public ResponseEntity<Person> deletePerson(Integer id) throws Exception 
    {
            if(personRepository.existsById(id))
            {
            	log.debug("Delete Person number = {}", id);
            	Person person = getPersonById(id);
                personRepository.deleteById(person.getId());
                return ResponseEntity.status(HttpStatus.OK).body(person);
                
            }else
            {
                throw new PersonNotFoundException("The person with the ID" + id +"does not exist");
            }
    }

	@Override
	@Transactional
	public ResponseEntity<String> getImageId(Integer id) 
	{
		if(personRepository.existsById(id))
		{
            PersonEntity personEntity = personRepository.findById(id).get();
            if(personEntity.getFoto() == null)
            {
                return ResponseEntity.ok("null");
            }
            else
            {
                return ResponseEntity.ok(personRepository.findById(id).get().getFoto());
            }
        }
        else
        {
        	return ResponseEntity.ok("null");
        }
	}

	@Override
	@Transactional
	public ResponseEntity<Boolean> assignImageId(Integer id, String imageId) 
	{
		 if(personRepository.existsById(id))
		 {
	        PersonEntity personEntity = personRepository.findById(id).get();
	        personEntity.setFoto(imageId);
	        personRepository.save(personEntity);
	        return ResponseEntity.ok(true);
	     }
	     else
	     {
	        return ResponseEntity.ok(false);
	     }
	}
	
	
}    


