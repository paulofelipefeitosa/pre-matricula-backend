package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component class CoordinatorProperties {

    @Value("${coordinator_email}")
    private String email;

    public String getCoordinatorEmail() {
        return email;
    }

    @PostConstruct
    private void checkProperties() {
        if (this.email == null || this.email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "There is no coordinator email configuration in application config file");
        }
    }
}
