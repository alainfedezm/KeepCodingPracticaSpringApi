package com.pet.web;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pet.data.PetRepository;
import com.pet.model.Pet;

@RestController
@RequestMapping(path = "/rest", produces = "application/json")
public class PetController {

	@Autowired
	private PetRepository petRepo;

	@PatchMapping(path = "/update/{id}", consumes = "application/json")
	public ResponseEntity<Pet> update(@PathVariable(value = "id") int id, @Valid @RequestBody Pet pet) {
		try {
			Pet newPet = petRepo.findById(id).get();
			newPet.setNombre(pet.getNombre());
			newPet.setPeso(pet.getPeso());
			newPet.setRaza(pet.getRaza());
			newPet.setFecha(pet.getFecha());
			newPet.setTiene_chip(pet.getTiene_chip());
			final Pet updatedUser = petRepo.save(newPet);
			return ResponseEntity.ok(updatedUser);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pet> petById(@PathVariable("id") int id) {
		Optional<Pet> fidnedDog = petRepo.findById(id);
		if (fidnedDog.isPresent())
			return new ResponseEntity<>(fidnedDog.get(), HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/all")
	public Iterable<Pet> getAllPet() {
		Iterable<Pet> fidnedDog = petRepo.findAll();
		return fidnedDog;
	}

	@GetMapping(path = "/recent")
	public Iterable<Pet> getAllPetPage() {
		PageRequest page = PageRequest.of(0, 20, Sort.by("fecha").descending());
		return petRepo.findAll(page).getContent();
	}

	@PostMapping(path = "/new", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Pet postPet(@RequestBody Pet pet) {
		return petRepo.save(pet);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePet(@PathVariable("id") int id) {
		petRepo.deleteById(id);
	}



}
