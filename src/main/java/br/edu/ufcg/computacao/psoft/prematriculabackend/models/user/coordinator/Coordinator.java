package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.coordinator;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;

public class Coordinator extends User {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Coordinator(String email, String name, String enrollmentNumber) {
        super(name, email, enrollmentNumber, Role.COORDINATOR);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(super.getRole().toString());
    }

}
