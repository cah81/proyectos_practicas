package com.formacion.BS7_2.studentSubject.domain;

import com.formacion.BS7_2.studentSubject.infraestructure.dto.input.SubjectInputDto;
import com.formacion.BS7_2.student.domain.Student;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "subjects")
public class StudentSubject {
    @Id
    @GeneratedValue(generator = "subject-seq")
    @GenericGenerator(name= "subject-seq",
            strategy ="com.formacion.BS7_2.generator.Generator_ID" )
    @Column(name = "id_subject")
    private String id_subject;

    private String nameSubject;

    private String comments;

    private Date initial_date;

    private Date finish_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_subject_students",
            joinColumns = @JoinColumn(name = "student_subject_id_subject"),
            inverseJoinColumns = @JoinColumn(name = "students_id_student"))
    private List<Student> students = new ArrayList<>();

    public StudentSubject(SubjectInputDto subjectInputDto){
    setNameSubject(subjectInputDto.getNameSubject());
    setComments(subjectInputDto.getComments());
    setInitial_date(subjectInputDto.getInitial_date());
    setFinish_date(subjectInputDto.getFinish_date());

    }
    public void update(SubjectInputDto subjectInputDto){
        if(subjectInputDto.getNameSubject()!= null){
            setNameSubject((subjectInputDto.getNameSubject()));
        }
        if(subjectInputDto.getInitial_date()!=null){
            setInitial_date(subjectInputDto.getInitial_date());
        }
        if(subjectInputDto.getFinish_date()!=null){
            setFinish_date(subjectInputDto.getFinish_date());
        }
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }




    private static final long serialVersionUID = 1L;
}
