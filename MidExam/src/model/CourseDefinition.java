package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CourseDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseDefinitionId;
    private String courseDefinitionCode;
    private String courseDefinitionDescription;

    @ManyToOne
    private Course course;

    public CourseDefinition() {
      
    }

    public CourseDefinition(String courseDefinitionCode, String courseDefinitionDescription, Course course) {
        this.courseDefinitionCode = courseDefinitionCode;
        this.courseDefinitionDescription = courseDefinitionDescription;
        this.course = course;
    }


    public Long getCourseDefinitionId() {
        return courseDefinitionId;
    }

    public void setCourseDefinitionId(Long courseDefinitionId) {
        this.courseDefinitionId = courseDefinitionId;
    }

    public String getCourseDefinitionCode() {
        return courseDefinitionCode;
    }

    public void setCourseDefinitionCode(String courseDefinitionCode) {
        this.courseDefinitionCode = courseDefinitionCode;
    }

    public String getCourseDefinitionDescription() {
        return courseDefinitionDescription;
    }

    public void setCourseDefinitionDescription(String courseDefinitionDescription) {
        this.courseDefinitionDescription = courseDefinitionDescription;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

