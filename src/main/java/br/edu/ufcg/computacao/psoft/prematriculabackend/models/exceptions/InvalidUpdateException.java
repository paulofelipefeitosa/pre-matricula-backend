package br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidUpdateException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InvalidUpdateException(String message) {
        super(message);
    }
}
