package org.springframework.samples.petclinic.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.pet.model.PetDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetDetailsRepository extends JpaRepository<PetDetails, Integer> {

	@Query("SELECT p FROM PetDetails p WHERE p.petType.id = :petTypeId")
	Optional<PetDetails> findByPetTypeId(Integer petTypeId);

}
