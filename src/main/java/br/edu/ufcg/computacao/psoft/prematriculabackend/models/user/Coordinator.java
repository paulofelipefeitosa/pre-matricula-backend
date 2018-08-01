package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
@Entity
@DiscriminatorValue(value = "tb_coordinator")
public class Coordinator extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private final Logger log = LoggerFactory.getLogger(this.getClass());

	public Coordinator() {
	}

	@Autowired
	public Coordinator(CoordinatorProperties coordinatorProperties) {
		super(null, coordinatorProperties.getCoordinatorEmail(), null, Role.COORDINATOR);
		// log.info(coordinatorProperties.getCoordinatorEmail());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(super.getRole().toString());
	}

}
