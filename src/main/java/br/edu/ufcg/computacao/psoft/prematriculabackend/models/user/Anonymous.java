package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "anonymous")
@PrimaryKeyJoinColumn(name = "anonymous_id", referencedColumnName="enrollmentNumber")
public class Anonymous extends User {

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
