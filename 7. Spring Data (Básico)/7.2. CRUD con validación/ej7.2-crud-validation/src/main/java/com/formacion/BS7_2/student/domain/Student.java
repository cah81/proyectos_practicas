package com.formacion.BS7_2.student.domain;


import com.formacion.BS7_2.studentSubject.domain.StudentSubject;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.student.infraestructure.dto.input.StudentInputDto;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(generator = "student-seq")
    @GenericGenerator(name= "student-seq",
            strategy ="com.formacion.BS7_2.generator.Generator_ID" )
    @Column(name = "id_student")
    private String id_student;

    @Column(nullable = false)
    private int num_hours_week;
    @Column( nullable = false)
    private String comments;
    @NotNull
    @Column(name = "branch")
    private String branch;

   /* @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "subjects")
    private List<Student> students = new ArrayList<>();*/

    @OneToOne
    @JoinColumn(name="id_person")
    private Person person;

    @ManyToOne
    @JoinColumn(name="id_teacher")
    private Teacher teacher;

    @ManyToMany(mappedBy = "students")
    private List<StudentSubject> studentSubjects;


    public Student(StudentInputDto studentInputDto){
        setNum_hours_week(studentInputDto.getNum_hours_week());
        setComments(studentInputDto.getComments());
        setBranch(studentInputDto.getBranch());

    }
    public void update(StudentInputDto studentInputDto){
        setNum_hours_week(studentInputDto.getNum_hours_week());
        setComments(studentInputDto.getComments());
        setBranch(studentInputDto.getBranch());
    }
   // public void addStudent(Student student) {
    //    students.add(student);
   // }

  ///  public void deleteStudent(Student student) {
   //     students.remove(student);
   // }












}
