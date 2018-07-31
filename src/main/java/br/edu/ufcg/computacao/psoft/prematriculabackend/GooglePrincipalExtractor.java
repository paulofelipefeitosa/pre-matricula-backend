package br.edu.ufcg.computacao.psoft.prematriculabackend;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Anonymous;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Coordinator;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Student;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@Component
public class GooglePrincipalExtractor implements PrincipalExtractor {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoTranslator googleUserInfo;

    @Autowired
    private Coordinator coordinator;

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String email = this.googleUserInfo.getEmail(map);
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            String userName = this.googleUserInfo.getName(map);
            if (email.equals(this.coordinator.getEmail())) {
            	log.info("coordenador");
                user = coordinator;
                user.setUsername(userName);
            } else if (email.contains(Student.DOMAIN)) {
            	log.info("student");
                user = new Student(email, userName, null, null, null, null);
            } else {
            	log.info("anonimo");
                user = new Anonymous(userName, email, null, null);
            }
            this.userService.save(user);
        }
        log.info("nao tem");
        return user;
    }

}
