package com.seun.sunday2.controller;

import com.seun.sunday2.model.Student;
import com.seun.sunday2.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId) {
        return new ResponseEntity<Student>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student){
        return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<String>("Student deleted Successfully", HttpStatus.OK);
    }
}
