package org.springframework.samples.petclinic.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.model.PetDetails;
import org.springframework.samples.petclinic.pet.repository.PetDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetDetailsService {

	@Autowired
	PetDetailsRepository petDetailsRepository;

	@Transactional
	public PetDetails save(PetDetails petDetails) {
		return petDetailsRepository.save(petDetails);
	}

	@Transactional(readOnly = true)
	public List<PetDetails> findAll() {
		return (List<PetDetails>) petDetailsRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<PetDetails> findByPetTypeId(Integer petTypeId) {
		return petDetailsRepository.findByPetTypeId(petTypeId);
	}

}
