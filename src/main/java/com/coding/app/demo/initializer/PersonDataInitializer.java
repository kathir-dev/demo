package com.coding.app.demo.initializer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.coding.app.demo.entity.Person;
import com.coding.app.demo.repository.PersonRepository;

@Component
public class PersonDataInitializer implements CommandLineRunner{
	
	@Autowired
	private PersonRepository personRepo;	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Executing CommandLineRunner");
		personRepo.deleteAll();
		Set<String> h1 = new HashSet<>();
		h1.add("Reading");
		h1.add("Music");
		
		Person p = new Person("John", "Smith", 32, "Red", h1);	
		
		
		personRepo.save(p);
		
		Set<String> h2 = new HashSet<>();
		h2.add("Cricket");
		h2.add("Music");
		Person p1 = new Person("Barry", "Johnson", 38, "Black", h2);
		personRepo.saveAndFlush(p1);
		
		
		List<Person> pList = personRepo.findAll();
		System.out.println(pList.size());
		
	}
	
}
