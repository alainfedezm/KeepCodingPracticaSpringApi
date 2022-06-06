package com.pet.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pet.data.DogRepository;
import com.pet.model.Dog;

@RestController
@RequestMapping(path = "/rest", produces = "application/json")
public class DogController {
	
	
	@Autowired
	private DogRepository dogRepo;
	
	@GetMapping("/{id}")
	public ResponseEntity<Dog> tacoById(@PathVariable("id") int id) {
		Optional<Dog> fidnedDog = dogRepo.findById(id);
		if(fidnedDog.isPresent())return new ResponseEntity<>(fidnedDog.get(),HttpStatus.OK);
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path="/doge", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Dog postDog(@RequestBody Dog newDog) {
		return dogRepo.save(newDog);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDog(@PathVariable("id") int id) {
		dogRepo.deleteById(id);
	}
	
	@GetMapping("/all")
	public Iterable<Dog> getAllDog() {
		Iterable<Dog> fidnedDog = dogRepo.findAll();	
		return fidnedDog;
	}
	
	@GetMapping(path = "/recent")
	public Iterable<Dog> getAllDogPage(){
		PageRequest page = PageRequest.of(0, 20,Sort.by("fecha").descending());
		return dogRepo.findAll(page).getContent();
	}
	
	

}
