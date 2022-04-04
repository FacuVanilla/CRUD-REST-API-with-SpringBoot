package com.seun.sunday2.service.impl;

import com.seun.sunday2.exception.ResourceNotFoundException;
import com.seun.sunday2.model.Student;
import com.seun.sunday2.repository.StudentRepository;
import com.seun.sunday2.service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            return student.get();
        }else {
            throw new ResourceNotFoundException("student", "Id", id);
        }
    }

    @Override
    public Student updateStudent(Student student, long id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setEmail(student.getEmail());

        studentRepository.save(existingStudent);
        return existingStudent;
    }

    @Override
    public void deleteStudent(long id) {

        studentRepository.findById(id).orElseThrow( () ->
        new ResourceNotFoundException("student","Id",id));
        studentRepository.deleteById(id);
    }
}
