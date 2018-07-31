package br.edu.ufcg.computacao.psoft.prematriculabackend.models.user;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    STUDENT("STUDENT"), COORDINATOR("COORDINATOR");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
