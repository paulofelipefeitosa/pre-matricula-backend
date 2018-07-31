package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "coordinator")
@PrimaryKeyJoinColumn(name = "coordinator_id", referencedColumnName="enrollmentNumber")
public class Coordinator extends User {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public Coordinator(CoordinatorProperties coordinatorProperties) {
        super(null, coordinatorProperties.getCoordinatorEmail(), null, Role.COORDINATOR);
        log.info(coordinatorProperties.getCoordinatorEmail());
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(super.getRole().toString());
    }

}
