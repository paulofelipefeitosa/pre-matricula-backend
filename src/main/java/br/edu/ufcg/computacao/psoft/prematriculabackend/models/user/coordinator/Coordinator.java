package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.coordinator;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;

@Component
public class Coordinator extends User {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public Coordinator(CoordinatorProperties coordinatorProperties) {
        super(null, coordinatorProperties.getCoordinatorEmail(), null, Role.COORDINATOR);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(super.getRole().toString());
    }

}
