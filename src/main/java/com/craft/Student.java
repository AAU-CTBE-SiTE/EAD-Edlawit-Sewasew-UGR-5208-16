package com.craft;

import java.util.ArrayList;
import java.util.List;

/**
 * Student entity demonstrating literal injection, object dependency injection,
 * collection injection, and bean lifecycle scopes.
 */
public class Student {
    private int id;
    private String name;
    private int age;
    private Address address;
    private List<Subject> subjects = new ArrayList<>();

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(int id, String name, int age, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Student(int id, String name, int age, Address address, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    /**
     * Formatted output matching the lecture demonstration pattern.
     */
    public void getDetails() {
        System.out.println("Student ID   : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Student Age  : " + age);
        System.out.println("Address      : " + (address != null ? address : "None"));
        if (subjects != null && !subjects.isEmpty()) {
            System.out.println("Enrolled Subjects:");
            int index = 1;
            for (Subject subject : subjects) {
                System.out.println("  Subject " + index + ": " + subject.getSubjectName());
                index++;
            }
        } else {
            System.out.println("Enrolled Subjects: None");
        }
    }

    @Override
    public String toString() {
        return "Student[id=" + id + ", name='" + name + "', age=" + age + 
               ", address=" + address + ", subjects=" + subjects + "]";
    }
}
