package com.formacion.BS7_2.teacher.infraestructure.dto.input;

import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Teacher} entity
 */
@Data
public class TeacherInputDto implements Serializable {

    private final String comments;
    private final String branch;
    private final Integer id_person;

    public Teacher transformTeacherInputIntoEntity(Person person){
       Teacher teacher = new Teacher();
       teacher.setPerson(person);
       teacher.setComments(this.getComments());
       teacher.setBranch(this.getBranch());
       return teacher;



    }
}