package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.edu.ufcg.computacao.psoft.prematriculabackend.controllers.AuthenticationController;

@Component
@Entity
@DiscriminatorValue(value = "tb_coordinator")
public class Coordinator extends User {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	public Coordinator() {
	}

	public Coordinator(String email) {
		super(null, email, null, Role.COORDINATOR);
		logger.info(email);
	}

}
