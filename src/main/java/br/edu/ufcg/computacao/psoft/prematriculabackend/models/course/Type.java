package br.edu.ufcg.computacao.psoft.prematriculabackend.models.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonValue;

@Entity
@Table(name = "tb_type")
public enum Type {
    OBRIGATORIA("Obrigatoria"), OPTATIVA_GERAL("Optativa-Geral"), OPTATIVA_ESPECIFICA(
            "Optativa-Especifica");

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
    private String value;

    Type() {}
    
    private Type(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
