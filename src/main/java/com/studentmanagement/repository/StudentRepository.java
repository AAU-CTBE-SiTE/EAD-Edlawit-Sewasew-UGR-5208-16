package com.studentmanagement.repository;

import com.studentmanagement.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(3);

    public StudentRepository() {
        students.add(new Student(1L, "Alice Johnson", "alice@example.com", "Computer Science"));
        students.add(new Student(2L, "Bob Smith", "bob@example.com", "Information Systems"));
        students.add(new Student(3L, "Charlie Brown", "charlie@example.com", "Software Engineering"));
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Optional<Student> findById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(idGenerator.incrementAndGet());
        }
        students.add(student);
        return student;
    }

    public Optional<Student> update(Long id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            Student existing = students.get(i);
            if (existing.getId().equals(id)) {
                existing.setName(updatedStudent.getName());
                existing.setEmail(updatedStudent.getEmail());
                existing.setDepartment(updatedStudent.getDepartment());
                return Optional.of(existing);
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(Long id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
}
