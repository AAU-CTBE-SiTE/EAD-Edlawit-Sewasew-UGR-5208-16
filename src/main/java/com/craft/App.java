package com.craft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class App {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // 1. Create - persist a new student
    public static void createStudent(Student student) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(student);
            transaction.commit();
            System.out.println("Created Student: " + student);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // 2. Read - find student by ID
    public static Student getStudentById(int id) {
        EntityManager em = getEntityManager();
        try {
            Student student = em.find(Student.class, id);
            return student;
        } finally {
            em.close();
        }
    }

    // 3. Update - update student's name
    public static void updateStudentName(String newName, int id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                student.setName(newName);
                System.out.println("Updated Student Name to: " + newName);
            } else {
                System.out.println("Student with ID " + id + " not found for update.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // 4. Update - update student's name and tech
    public static void updateStudentNameAndTech(String newName, String newTech, int id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                student.setName(newName);
                student.setTech(newTech);
                System.out.println("Updated Student Name to: " + newName + ", Tech to: " + newTech);
            } else {
                System.out.println("Student with ID " + id + " not found for update.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // 5. Delete - delete student by ID
    public static void deleteStudentById(int id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
                System.out.println("Deleted Student with ID: " + id);
            } else {
                System.out.println("Student with ID " + id + " not found for deletion.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // 6. Demonstrate contains() method of EntityManager
    public static void testContainsMethod() {
        System.out.println("\n========== Testing EntityManager.contains() Method ==========");
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            // Transient state: not yet associated with persistence context
            Student tempStudent = new Student();
            tempStudent.setName("Testing Contains");
            tempStudent.setTech("DevOps");
            System.out.println("1. Before persist: em.contains(tempStudent) = " + em.contains(tempStudent));

            // Persist the entity: becomes managed
            transaction.begin();
            em.persist(tempStudent);
            transaction.commit();
            System.out.println("2. After persist: em.contains(tempStudent) = " + em.contains(tempStudent));

            // Detach the entity: no longer managed
            em.detach(tempStudent);
            System.out.println("3. After em.detach(): em.contains(tempStudent) = " + em.contains(tempStudent));

            // Find entity from database: newly returned reference is managed
            Student fetched = em.find(Student.class, tempStudent.getId());
            System.out.println("4. After em.find(): em.contains(fetched) = " + em.contains(fetched));

            // Clean up test entity
            transaction.begin();
            em.remove(fetched);
            transaction.commit();
            System.out.println("5. After em.remove(): em.contains(fetched) = " + em.contains(fetched));
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        System.out.println("=============================================================\n");
    }

    public static void main(String[] args) {
        try {
            System.out.println("================= Starting JPA CRUD Operations =================");

            // Step 1: Create students
            System.out.println("\n--- Step 1: CREATE ---");
            Student s1 = new Student();
            s1.setName("John Snow");
            s1.setTech("Java");
            createStudent(s1);

            Student s2 = new Student();
            s2.setName("Mohammed ali");
            s2.setTech("Python");
            createStudent(s2);

            Student s3 = new Student();
            s3.setName("Sara");
            s3.setTech("React & TypeScript");
            createStudent(s3);

            // Step 2: Read / Find student
            System.out.println("\n--- Step 2: READ (find by ID) ---");
            Student fetched1 = getStudentById(s1.getId());
            System.out.println("Fetched by ID (" + s1.getId() + "): " + fetched1);

            Student fetched2 = getStudentById(s2.getId());
            System.out.println("Fetched by ID (" + s2.getId() + "): " + fetched2);

            Student fetched3 = getStudentById(s3.getId());
            System.out.println("Fetched by ID (" + s3.getId() + "): " + fetched3);

            // Step 3: Update student name
            System.out.println("\n--- Step 3: UPDATE NAME ---");
            updateStudentName("Alemayehu", s2.getId());
            System.out.println("After name update: " + getStudentById(s2.getId()));

            // Step 4: Update student name and tech
             System.out.println("\n--- Step 4: UPDATE NAME AND TECH ---");
            updateStudentNameAndTech("Alemayehu G.", "Kotlin & Spring Boot", s2.getId());
            System.out.println("After name & tech update: " + getStudentById(s2.getId()));

            // Step 5: Check EntityManager.contains()
            testContainsMethod();

            // Step 6: Delete student
            System.out.println("\n--- Step 6: DELETE ---");
            deleteStudentById(s1.getId());
            System.out.println("Verification after deletion of ID " + s1.getId() + ": " + getStudentById(s1.getId()));

            System.out.println("\n================= JPA CRUD Operations Completed =================");
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
        }
    }
}
