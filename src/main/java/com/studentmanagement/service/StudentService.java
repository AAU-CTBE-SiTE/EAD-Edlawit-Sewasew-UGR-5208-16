package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    public Student addStudent(Student student) {
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        return studentRepository.update(id, student)
                .orElseThrow(() -> new IllegalArgumentException("Cannot update. Student not found with id: " + id));
    }

    public void deleteStudent(Long id) {
        boolean deleted = studentRepository.deleteById(id);
        if (!deleted) {
            throw new IllegalArgumentException("Cannot delete. Student not found with id: " + id);
        }
    }
}
