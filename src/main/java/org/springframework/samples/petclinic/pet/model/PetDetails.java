package org.springframework.samples.petclinic.pet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.samples.petclinic.owner.PetType;

@Entity
@Table(name = "pet_details")
public class PetDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(length = 50, nullable = false)
	private String temperament;

	@Column(name = "length_cm")
	private Double length;

	@Column(name = "weight_kg")
	private Double weight;

	@OneToOne(optional = false)
	@JoinColumn(name = "pet_type_id")
	private PetType petType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemperament() {
		return temperament;
	}

	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}

}
