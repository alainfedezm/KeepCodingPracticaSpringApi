package com.pet.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pet.model.Pet;


public interface PetRepository extends PagingAndSortingRepository<Pet, Integer>  {

}
