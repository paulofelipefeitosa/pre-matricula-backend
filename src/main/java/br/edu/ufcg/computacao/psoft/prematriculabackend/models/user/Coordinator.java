package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Entity
@DiscriminatorValue(value = "tb_coordinator")
public class Coordinator extends User {

	public Coordinator() {
	}

	@Autowired
	public Coordinator(CoordinatorProperties coordinatorProperties) {
		super(null, coordinatorProperties.getCoordinatorEmail(), null, Role.COORDINATOR);
	}

}
