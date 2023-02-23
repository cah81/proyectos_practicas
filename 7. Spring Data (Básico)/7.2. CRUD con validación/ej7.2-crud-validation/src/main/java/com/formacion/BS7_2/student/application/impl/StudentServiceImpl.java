package com.formacion.BS7_2.student.application.impl;




import com.formacion.BS7_2.exception.EntityNotFoundException;
import com.formacion.BS7_2.exception.UnprocessableEntityException;
import com.formacion.BS7_2.person.infraestructure.repository.PersonDaoRepository;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.student.application.IStudentService;

import com.formacion.BS7_2.student.domain.Student;
import com.formacion.BS7_2.student.infraestructure.dto.input.StudentInputDto;
import com.formacion.BS7_2.student.infraestructure.dto.output.StudentOutputDto;
import com.formacion.BS7_2.student.infraestructure.repository.StudentDaoRepository;
import com.formacion.BS7_2.studentSubject.domain.StudentSubject;
import com.formacion.BS7_2.studentSubject.infraestructure.repository.SubjectDaoRepository;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import com.formacion.BS7_2.teacher.infraestructure.repository.TeacherDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    StudentDaoRepository studentDaoRepository;

    @Autowired
    PersonDaoRepository personDaoRepository;

    @Autowired
    TeacherDaoRepository teacherDaoRepository;

    @Autowired
    SubjectDaoRepository subjectDaoRepository;

    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto) throws Exception {
        Person person = personDaoRepository.findById(studentInputDto.getId_person()).orElseThrow(() -> new EntityNotFoundException("Person does not exist", 404, new Date()));
        Teacher teacher = null;
        if (studentInputDto.getId_teacher() != null) {
            teacher = teacherDaoRepository.findById(studentInputDto.getId_teacher()).
                    orElseThrow(() -> new EntityNotFoundException("Teacher not found", 404, new Date()));
        }

        //validacion de si ya esta como profesor o teacher

        Optional<Student> studentOptional = studentDaoRepository.findByPerson(person);
        Optional<Teacher> teacherOptional = teacherDaoRepository.findByPerson(person);
        if (teacherOptional.isPresent() || studentOptional.isPresent()) {
            throw new UnprocessableEntityException("The person es already a student or teacher", 422, new Date());
        }
        List<StudentSubject> subjectsList = getSubjectIds(studentInputDto.getSubjects());
        Student student = new Student(studentInputDto);
        student.setPerson(person);
        student.setTeacher(teacher);
        student.setStudentSubjects(subjectsList);
        subjectsList.forEach(subject -> subject.addStudent(student));
        studentDaoRepository.save(student);
        return new StudentOutputDto(student);

    }

    //buscar por id
    @Override
    public StudentOutputDto findById(String id) throws Exception {
        return new StudentOutputDto(studentDaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Person found", 404, new Date())));
    }


    @Override
    public StudentOutputDto updateStudent(String id, StudentInputDto studentInputDto) throws Exception {
        Student student = studentDaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found", 404, new Date()));

        Person person = personDaoRepository.findById(studentInputDto.getId_person()).orElseThrow(() ->
        {
            throw new EntityNotFoundException("The person does not exist ", 404, new Date());
        });


        student.update(studentInputDto);
        student.setPerson(person);
        studentDaoRepository.save(student);
        return new StudentOutputDto(student);
    }

    @Override
    public void deleteStudent(String id) throws Exception {
        Student student = studentDaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("the id does not exist", 404, new Date()));
        studentDaoRepository.delete(student);

    }


    @Override
    public List<StudentOutputDto> findALl() {
        List<StudentOutputDto> studentList = new ArrayList<>();
        studentDaoRepository.findAll().forEach(student -> {
            StudentOutputDto studentOutputDto = new StudentOutputDto(student);
            studentList.add(studentOutputDto);
        });
        return studentList;
    }

    @Override
    public Student getStudentId(String id) {
        return studentDaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("Student not found"), 404, new Date()));
    }

    @Override
    public String addSubjectFromStudent(String id, List<String> ids_asig) {
        Student student = studentDaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found", 404, new Date()));
        List<String> idsSubjects = student.getStudentSubjects().stream().map(StudentSubject::getId_subject).collect(Collectors.toList());
        idsSubjects.addAll(ids_asig);
        List<StudentSubject> studentSubjects = getSubjectIds(idsSubjects.stream().distinct().collect(Collectors.toList()));
        student.setStudentSubjects(studentSubjects);
        studentSubjects.forEach(subject -> subject.addStudent(student));
        studentDaoRepository.save(student);
        return "subjects add";
    }


    @Override
    public String deleteSubjectFromStudent(String idStudent, List<String> idSubject) {
        Student student = studentDaoRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException("Student not found", 404, new Date()));
        for(String subjectId : idSubject){
            StudentSubject subject = subjectDaoRepository.findById(subjectId).orElseThrow(()->new EntityNotFoundException("Not exist",404,new Date()));

            if(!subject.getStudents().contains(student))
            {throw new EntityNotFoundException("The person with Id: does not exist in the database", 404,new Date());};

            subject.getStudents().remove(student);

            subjectDaoRepository.save(subject);
        }

        return "Subjects have been deleted";
    }



    private List<StudentSubject> getSubjectIds (List<String> ids) {
        List<StudentSubject> studentSubjects = new ArrayList<>();
        if (ids != null) {
            studentSubjects = subjectDaoRepository.findAllById(ids);
            if (ids.size() != studentSubjects.size()) {
                throw new EntityNotFoundException("No courses found with ids: ", 404, new Date());
            }
        }
        return studentSubjects;
    }
}





























