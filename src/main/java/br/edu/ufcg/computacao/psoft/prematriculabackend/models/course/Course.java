package br.edu.ufcg.computacao.psoft.prematriculabackend.models.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_course")
public class Course {
	
	@Column(name = "name")
    private String name;
	
	@Id
	@Column(name = "code")
    private String code;
	
	@Column(name = "credits")
    private Integer credits;
	
	@Column(name = "classLoad")
    private Integer classLoad;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
    private Type type;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ppc")
    private PPC ppc;

	public Course() {}
	
    public Course(String name, String code, Integer credits, Integer classLoad, Type type,
            PPC ppc) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.classLoad = classLoad;
        this.type = type;
        this.ppc = ppc;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getCredits() {
        return credits;
    }

    public Integer getClassLoad() {
        return classLoad;
    }

    public Type getType() {
        return type;
    }

    public PPC getPpc() {
        return ppc;
    }

}
