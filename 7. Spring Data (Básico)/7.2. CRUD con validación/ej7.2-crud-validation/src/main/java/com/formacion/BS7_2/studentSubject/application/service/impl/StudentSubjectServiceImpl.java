package com.formacion.BS7_2.studentSubject.application.service.impl;

import com.formacion.BS7_2.exception.UnprocessableEntityException;
import com.formacion.BS7_2.studentSubject.application.service.IStudentSubjectService;
import com.formacion.BS7_2.studentSubject.infraestructure.dto.input.SubjectInputDto;
import com.formacion.BS7_2.studentSubject.infraestructure.dto.output.SubjectOutputDto;
import com.formacion.BS7_2.studentSubject.infraestructure.repository.SubjectDaoRepository;
import com.formacion.BS7_2.student.domain.Student;
import com.formacion.BS7_2.student.infraestructure.repository.StudentDaoRepository;
import com.formacion.BS7_2.studentSubject.domain.StudentSubject;
import com.formacion.BS7_2.exception.EntityNotFoundException;

import com.formacion.BS7_2.teacher.domain.model.Teacher;
import com.formacion.BS7_2.teacher.infraestructure.repository.TeacherDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
    public class StudentSubjectServiceImpl implements IStudentSubjectService {
    @Autowired
    SubjectDaoRepository studentSubjectDaoRepository;
    @Autowired
    TeacherDaoRepository teacherDaoRepository;

    @Autowired
    StudentDaoRepository studentDaoRepository;

    @Override
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto) throws Exception {
        StudentSubject studentSubject = new StudentSubject(subjectInputDto);
        if(subjectInputDto.getId_teacher()!=null){
            studentSubject.setTeacher(getTeacher(subjectInputDto.getId_teacher()));
        }
        List<Student> students = getStudentsIds(subjectInputDto.getIds_students());
        studentSubject.setStudents(students);
        studentSubjectDaoRepository.save(studentSubject);
        return new SubjectOutputDto(studentSubject);

    }

    @Override
    public SubjectOutputDto findById(String id) throws Exception {
        return new SubjectOutputDto(studentSubjectDaoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Not Found",404,new Date())));
    }

    @Override
    public SubjectOutputDto updateSubject(String id, SubjectInputDto subjectInputDto) throws Exception {
        StudentSubject studentSubject = studentSubjectDaoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Subject not exist",404,new Date()));
        studentSubject.update(subjectInputDto);
        if (subjectInputDto.getId_teacher() != null) {
            studentSubject.setTeacher(getTeacher(subjectInputDto.getId_teacher()));
        }
        List<String> students = new ArrayList<>(
                studentSubject.getStudents().stream().map(Student::getId_student).toList());
                students.addAll(subjectInputDto.getIds_students());
                studentSubject.setStudents(getStudentsIds(students.stream().distinct().toList()));
                studentSubjectDaoRepository.save(studentSubject);
        return new SubjectOutputDto(studentSubject);
    }

    @Override
    public String deleteSubject(String id) throws Exception {
        StudentSubject studentSubject =studentSubjectDaoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Not Exist",404,new Date()));
      if(studentSubject.getStudents().size() !=0){
             throw new UnprocessableEntityException("The subject has a student",422,new Date());
        }
        studentSubjectDaoRepository.delete(studentSubject);
        return "the subject has been deleted";
    }

    @Override
    public List<SubjectOutputDto> getSubjectStudentById(String idStudent) {
        Student student =studentDaoRepository.findById(idStudent).orElseThrow(()->new EntityNotFoundException("not Students",404,new Date()));
        return student.getStudentSubjects().stream().map(SubjectOutputDto::new).collect(Collectors.toList());
    }



    private List<Student> getStudentsIds(List<String> ids){
        List<Student> students = new ArrayList<>();
        if(ids!=null){
            students = studentDaoRepository.findAllById(ids);
            if(ids.size()!= students.size()){
                throw new EntityNotFoundException("Not Students found",404,new Date());
            }
        }
        return students;
    }

    private Teacher getTeacher(String id){
        return teacherDaoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Not found teacher",404,new Date()));
    }
}


















