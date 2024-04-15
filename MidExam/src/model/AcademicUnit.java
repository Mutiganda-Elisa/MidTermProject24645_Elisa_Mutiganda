package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class AcademicUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long academicId;
    private String academicCode;
    private String academicName;

    @Enumerated(EnumType.STRING)
    private EAcademicUnit type;

    @ManyToOne
    private AcademicUnit parent; 

  
    public AcademicUnit() {
       
    }

    public AcademicUnit(String academicCode, String academicName, EAcademicUnit type, AcademicUnit parent) {
        this.academicCode = academicCode;
        this.academicName = academicName;
        this.type = type;
        this.parent = parent;
    }

    public Long getAcademicId() {
        return academicId;
    }

    public void setAcademicId(Long academicId) {
        this.academicId = academicId;
    }

    public String getAcademicCode() {
        return academicCode;
    }

    public void setAcademicCode(String academicCode) {
        this.academicCode = academicCode;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }

    public EAcademicUnit getType() {
        return type;
    }

    public void setType(EAcademicUnit type) {
        this.type = type;
    }

    public AcademicUnit getParent() {
        return parent;
    }

    public void setParent(AcademicUnit parent) {
        this.parent = parent;
    }
    
    public static AcademicUnit[] values() {
        return new AcademicUnit[] { };
    }
    
    
}

