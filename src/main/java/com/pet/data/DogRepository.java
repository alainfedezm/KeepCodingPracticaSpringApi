package com.pet.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pet.model.Dog;


public interface DogRepository extends PagingAndSortingRepository<Dog, Integer>  {

}
