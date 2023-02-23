package com.formacion.BS7_2.teacher.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.student.domain.Student;
import com.formacion.BS7_2.studentSubject.domain.StudentSubject;
import com.formacion.BS7_2.teacher.infraestructure.dto.input.TeacherInputDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "teachers")
public class Teacher  {
    @Id
    @GeneratedValue(generator = "teacher-seq")
    @GenericGenerator(name= "teacher-seq",
            strategy ="com.formacion.BS7_2.generator.Generator_ID" )
    @Column(name = "id_teacher")
    String id_teacher;
    private String comments;
    @NotNull
    @Column(name = "branch")
    private String branch;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="id_person")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany
    private List<StudentSubject> studentSubjects;


    public Teacher(TeacherInputDto teacherInputDto){
        setComments(teacherInputDto.getComments());
        setBranch(teacherInputDto.getBranch());
    }

    public void update(TeacherInputDto teacherInputDto){
        if (teacherInputDto.getComments() != null) {
            setComments(teacherInputDto.getComments());
        }
        if (teacherInputDto.getBranch() != null) {
            setBranch(teacherInputDto.getBranch());
        }
    }





}
