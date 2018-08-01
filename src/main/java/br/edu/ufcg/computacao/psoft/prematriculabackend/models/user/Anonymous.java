package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@Entity
@DiscriminatorValue(value = "anonymous")
public class Anonymous extends User {

	public Anonymous() {
	}
	
    public Anonymous(String name, String email, String enrollmentNumber, Role role) {
        super(name, email, enrollmentNumber, role);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>();
    }

}
