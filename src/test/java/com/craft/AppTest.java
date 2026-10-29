package com.craft;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Automated test suite verifying Spring IoC container operations,
 * dependency injection mechanisms, and bean scopes with Ethiopian student data.
 */
public class AppTest {

    private ClassPathXmlApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new ClassPathXmlApplicationContext("beansConfig.xml");
    }

    @AfterEach
    void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    @DisplayName("Should successfully create and load Spring ApplicationContext")
    void testContextCreation() {
        assertNotNull(context, "Spring ApplicationContext should not be null");
        assertTrue(context.containsBean("setterStudent"));
        assertTrue(context.containsBean("constructorStudent"));
        assertTrue(context.containsBean("singletonStudent"));
        assertTrue(context.containsBean("prototypeStudent"));
    }

    @Test
    @DisplayName("Should verify setter injection of literals, Bole address, and SE courses")
    void testSetterInjection() {
        Student student = context.getBean("setterStudent", Student.class);
        assertNotNull(student);
        assertEquals(101, student.getId());
        assertEquals("Yohannes Haile", student.getName());
        assertEquals(22, student.getAge());

        // Object dependency
        assertNotNull(student.getAddress());
        assertEquals("Bole, Addis Ababa", student.getAddress().getCity());
        assertEquals("Ethiopia", student.getAddress().getCountry());

        // Collection dependency
        List<Subject> subjects = student.getSubjects();
        assertNotNull(subjects);
        assertEquals(2, subjects.size());
        assertEquals("Enterprise Java & Spring Framework", subjects.get(0).getSubjectName());
        assertEquals("Database Systems & Management", subjects.get(1).getSubjectName());
    }

    @Test
    @DisplayName("Should verify constructor injection of literals, Arat Kilo address, and SE courses")
    void testConstructorInjection() {
        Student student = context.getBean("constructorStudent", Student.class);
        assertNotNull(student);
        assertEquals(102, student.getId());
        assertEquals("Selamawit Tadesse", student.getName());
        assertEquals(21, student.getAge());

        // Object dependency
        assertNotNull(student.getAddress());
        assertEquals("Arat Kilo, Addis Ababa", student.getAddress().getCity());
        assertEquals("Ethiopia", student.getAddress().getCountry());

        // Collection dependency
        List<Subject> subjects = student.getSubjects();
        assertNotNull(subjects);
        assertEquals(2, subjects.size());
        assertEquals("Software Architecture & Design", subjects.get(0).getSubjectName());
        assertEquals("Web Application Engineering", subjects.get(1).getSubjectName());
    }

    @Test
    @DisplayName("Should verify Singleton scope returns the exact same instance and shares state")
    void testSingletonScope() {
        Student s1 = context.getBean("singletonStudent", Student.class);
        Student s2 = context.getBean("singletonStudent", Student.class);

        // Reference equality
        assertSame(s1, s2, "Singleton beans must be the exact same object instance");
        assertEquals("Dawit Alemu", s1.getName());

        // Mutating s1 must reflect in s2
        String updatedName = "Dawit Modified";
        s1.setName(updatedName);
        assertEquals(updatedName, s2.getName(), "State change on singleton instance should reflect on all references");
    }

    @Test
    @DisplayName("Should verify Prototype scope returns distinct instances with isolated state")
    void testPrototypeScope() {
        Student p1 = context.getBean("prototypeStudent", Student.class);
        Student p2 = context.getBean("prototypeStudent", Student.class);

        // Reference inequality
        assertNotSame(p1, p2, "Prototype beans must be distinct object instances");
        assertEquals("Bethlehem Girma", p1.getName());
        assertEquals("Bethlehem Girma", p2.getName());

        // Mutating p1 must NOT affect p2
        p1.setName("Bethlehem Modified");

        assertEquals("Bethlehem Modified", p1.getName());
        assertEquals("Bethlehem Girma", p2.getName(), "Prototype instance state must remain isolated");
    }
}
