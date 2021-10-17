/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstulate file, choose Tools | Tstulates
 * and open the tstulate in the editor.
 */
package studentdatabasejpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nemishappy
 */
public class StudentDatabaseJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Student stu1 = new Student(3, "John", 12345.0);
       Student stu2 = new Student(4, "Marry", 45678.0);
       //insert
       StudentTable.insertStudent(stu1);
       StudentTable.insertStudent(stu2);
       Student stu;
       stu = StudentTable.findStudentById(2);
       if (stu != null) {
           stu.setName("Jack");
           //delete
           StudentTable.removeStudent(stu);
           //update
           StudentTable.updateStudent(stu);
       }
       //List<Student> stuList = StudentTable.findStudentByName("Marry"); 
       List<Student> stuList = StudentTable.findAllStudent();
       printAllStudent(stuList);
    }
    public static void printAllStudent(List<Student> studentList) {
        for(Student stu : studentList) {
           System.out.print(stu.getId() + " ");
           System.out.print(stu.getName() + " ");
           System.out.println(stu.getGpa() + " ");
       }
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
