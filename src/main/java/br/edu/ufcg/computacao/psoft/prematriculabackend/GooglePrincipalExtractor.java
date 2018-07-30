package br.edu.ufcg.computacao.psoft.prematriculabackend;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Anonymous;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.student.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@Component
public class GooglePrincipalExtractor implements PrincipalExtractor {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProviderTranslator googleTranslator;

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String email = this.googleTranslator.getEmail(map);
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            String userName = this.googleTranslator.getName(map);
            if (email.contains(Student.DOMAIN)) {
                user = new Student(email, userName, null, null, null, null);
            } else {
                user = new Anonymous(userName, email, null, null);
            }
            this.userService.create(user);
        }
        return user;
    }

}
