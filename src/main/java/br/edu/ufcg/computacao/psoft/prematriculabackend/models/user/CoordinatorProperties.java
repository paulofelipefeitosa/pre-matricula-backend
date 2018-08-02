package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CoordinatorProperties {

    @Value("${coordinator_emails_list}")
    private String emailsList;
    
    private List<Coordinator> coordinatorsList = new ArrayList<Coordinator>();

    @PostConstruct
    private void checkProperties() {
        if (this.emailsList == null || this.emailsList.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "There is no coordinator email configuration in application config file");
        } else {
            String[] coordinatorEmails = this.emailsList.split(",");
            for (String coordEmail : coordinatorEmails) {
                coordEmail = coordEmail.trim();
                Coordinator coordinator = new Coordinator(coordEmail);
                this.coordinatorsList.add(coordinator);
            }
        }
    }
    
    public List<Coordinator> getCoordinatorsList() {
        return this.coordinatorsList;
    }
}
