package br.edu.ufcg.computacao.psoft.prematriculabackend;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@Component
public class GoogleAuthoritiesExtractor implements AuthoritiesExtractor {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoTranslator googleTranslator;

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
        String email = this.googleTranslator.getEmail(map);
        User user = this.userService.getUserByEmail(email);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        if (authorities.isEmpty()) {
            throw new BadCredentialsException("User does not belong to CCC organization.");
        }
        return (List<GrantedAuthority>) authorities;
    }

}
