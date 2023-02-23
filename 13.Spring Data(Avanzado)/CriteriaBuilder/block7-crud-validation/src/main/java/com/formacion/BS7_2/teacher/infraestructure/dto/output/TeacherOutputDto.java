package com.formacion.BS7_2.teacher.infraestructure.dto.output;


import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Teacher} entity
 */
@Data
@NoArgsConstructor
public class TeacherOutputDto  {
    private String idTeacher;
    private Integer idPerson;
    private String comments;
    private String branch;

    public TeacherOutputDto(@NotNull Teacher teacher){
        setIdTeacher(teacher.getId_teacher());
        setIdPerson(teacher.getPerson().getId_person());
        setComments(teacher.getComments());
        setBranch(teacher.getBranch());
    }

}