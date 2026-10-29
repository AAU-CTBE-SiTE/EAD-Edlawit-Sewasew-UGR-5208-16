package com.craft;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main application entry point demonstrating Spring Core concepts:
 * 1. IoC Container bootstrap (ClassPathXmlApplicationContext)
 * 2. Setter-based injection (literals, Addis Ababa address, SE course collection)
 * 3. Constructor-based injection
 * 4. Singleton scope lifecycle and reference sharing
 * 5. Prototype scope lifecycle and instance isolation
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("        SPRING CORE: IOC & BEAN MANAGEMENT LAB DEMO         ");
        System.out.println("=============================================================\n");

        // 1. Initialize Spring IoC Container
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beansConfig.xml")) {

            // 2. Setter Injection Demonstration
            System.out.println(">>> 1. DEMONSTRATING SETTER INJECTION");
            Student setterStudent = context.getBean("setterStudent", Student.class);
            setterStudent.getDetails();
            System.out.println();

            // 3. Constructor Injection Demonstration
            System.out.println(">>> 2. DEMONSTRATING CONSTRUCTOR INJECTION");
            Student constructorStudent = context.getBean("constructorStudent", Student.class);
            constructorStudent.getDetails();
            System.out.println();

            // 4. Singleton Scope Demonstration
            System.out.println(">>> 3. DEMONSTRATING SINGLETON SCOPE");
            Student singleton1 = context.getBean("singletonStudent", Student.class);
            Student singleton2 = context.getBean("singletonStudent", Student.class);

            System.out.println("Singleton Instance 1 Hash: " + System.identityHashCode(singleton1));
            System.out.println("Singleton Instance 2 Hash: " + System.identityHashCode(singleton2));
            System.out.println("Are references equal (singleton1 == singleton2)? " + (singleton1 == singleton2));

            // Verify state mutation sharing across references
            System.out.println("Original name in singleton2: " + singleton2.getName());
            System.out.println("Modifying name via singleton1 reference to 'Dawit Modified'...");
            singleton1.setName("Dawit Modified");
            System.out.println("Name observed from singleton2 reference: " + singleton2.getName());
            System.out.println("Conclusion: Both variables reference the exact same singleton instance.\n");

            // 5. Prototype Scope Demonstration
            System.out.println(">>> 4. DEMONSTRATING PROTOTYPE SCOPE");
            Student prototype1 = context.getBean("prototypeStudent", Student.class);
            Student prototype2 = context.getBean("prototypeStudent", Student.class);

            System.out.println("Prototype Instance 1 Hash: " + System.identityHashCode(prototype1));
            System.out.println("Prototype Instance 2 Hash: " + System.identityHashCode(prototype2));
            System.out.println("Are references equal (prototype1 == prototype2)? " + (prototype1 == prototype2));

            // Verify instance isolation
            System.out.println("Original name in prototype2: " + prototype2.getName());
            System.out.println("Modifying name via prototype1 reference to 'Bethlehem Modified'...");
            prototype1.setName("Bethlehem Modified");
            System.out.println("Name in prototype1: " + prototype1.getName());
            System.out.println("Name in prototype2: " + prototype2.getName());
            System.out.println("Conclusion: Each getBean() returns an independent, isolated instance.\n");

            System.out.println("=============================================================");
            System.out.println("              ALL SPRING LAB TESTS COMPLETED                 ");
            System.out.println("=============================================================");
        }
    }
}
