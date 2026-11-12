package com.studentmanagement;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentManagementApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    @Order(1)
    void contextLoads() {
        assertNotNull(studentService);
    }

    @Test
    @Order(2)
    void testGetAllStudents() {
        List<Student> students = studentService.getAllStudents();
        assertFalse(students.isEmpty());
    }

    @Test
    @Order(3)
    void testGetStudentById() {
        Student student = studentService.getStudentById(1L);
        assertNotNull(student);
        assertEquals("Alice Johnson", student.getName());
    }

    @Test
    @Order(4)
    void testAddStudent() {
        Student newStudent = new Student(null, "David Miller", "david@example.com", "Mathematics");
        Student saved = studentService.addStudent(newStudent);
        assertNotNull(saved.getId());
        assertEquals("David Miller", saved.getName());
    }

    @Test
    @Order(5)
    void testUpdateStudent() {
        Student updatedInfo = new Student(null, "Alice Smith", "alice.smith@example.com", "Computer Science");
        Student updated = studentService.updateStudent(1L, updatedInfo);
        assertEquals("Alice Smith", updated.getName());
    }

    @Test
    @Order(6)
    void testDeleteStudent() {
        studentService.deleteStudent(2L);
        assertThrows(IllegalArgumentException.class, () -> studentService.getStudentById(2L));
    }

    @Test
    @Order(7)
    void testAfterThrowingAspectTrigger() {
        assertThrows(IllegalArgumentException.class, () -> studentService.getStudentById(999L));
    }
}
