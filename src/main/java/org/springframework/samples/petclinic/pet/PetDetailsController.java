package org.springframework.samples.petclinic.pet;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.pet.model.PetDetails;
import org.springframework.samples.petclinic.pet.service.PetDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/petdetails")
public class PetDetailsController {

	private final PetDetailsService petDetailsService;

	public PetDetailsController(PetDetailsService petDetailsService) {
		this.petDetailsService = petDetailsService;
	}

	@GetMapping
	public List<PetDetails> findAll() {
		return petDetailsService.findAll();
	}

	@PostMapping
	public ResponseEntity<Void> createPetDetails(@RequestBody PetDetails petDetails) {
		PetDetails saved = petDetailsService.save(petDetails);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(saved.getId())
			.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PetDetails> getByPetTypeId(@PathVariable Integer id) {
		return petDetailsService.findByPetTypeId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/hateoas/{petTypeId}")
	public ResponseEntity<EntityModel<PetDetails>> getWithHateoas(@PathVariable Integer petTypeId) {
		return petDetailsService.findByPetTypeId(petTypeId).map(details -> {
			EntityModel<PetDetails> resource = EntityModel.of(details,
					linkTo(methodOn(PetDetailsController.class).getWithHateoas(petTypeId)).withSelfRel(),
					linkTo(methodOn(PetDetailsController.class).findAll()).withRel("all"));
			return ResponseEntity.ok(resource);
		}).orElse(ResponseEntity.notFound().build());
	}

}
