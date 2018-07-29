package br.edu.ufcg.computacao.psoft.prematriculabackend.models.preenrollment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    VALIDA("Valida"), INVALIDA("Invalida");

    private String value;

    private Status(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
