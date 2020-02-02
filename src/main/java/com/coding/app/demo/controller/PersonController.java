package com.coding.app.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.FetchType;
import javax.validation.Valid;

import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coding.app.demo.entity.Person;
import com.coding.app.demo.exception.PersonNotFoundException;
import com.coding.app.demo.repository.PersonRepository;

@RestController
public class PersonController {
	
	@Autowired
	private PersonRepository personRepo;
	
	@RequestMapping(value = "/people",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public List<Person> getPeople() {
		List<Person> pList = personRepo.findAll();
		return pList;
	}
	
	@RequestMapping(value = "/people/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET
			)
	public Person getPersonById(@PathVariable long id) {
		Optional<Person> p = personRepo.findById(id);
		if(!p.isPresent()) {
			throw new PersonNotFoundException("Person not found for the ID " + id);
		}
		return p.get();
	}	
	
	@RequestMapping(value = "/people",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)	
	public ResponseEntity<Object> addPerson(@Valid @RequestBody Person p) {
		Person personAdded = personRepo.save(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(personAdded.getPersonId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value = "people/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updatePerson(@PathVariable long id, @Valid @RequestBody Person p) {
		Optional<Person> personUpdated = personRepo.findById(id);
		if(personUpdated.isPresent()) {
			Person person = personUpdated.get();
			person.setFirst_name(p.getFirst_name());
			person.setLast_name(p.getLast_name());
			person.setAge(p.getAge());
			person.setFavorite_color(p.getFavorite_color());
			person.setHobby(p.getHobby());
			personRepo.save(person);
		}
		else {
			throw new PersonNotFoundException("Person not found");
		}
	}
	
	@RequestMapping(value = "/people/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable long id) {
		if(personRepo.getOne(id) != null) {
			personRepo.deleteById(id);
		}
		else {
			throw new PersonNotFoundException("Person not found");
		}
	}
}