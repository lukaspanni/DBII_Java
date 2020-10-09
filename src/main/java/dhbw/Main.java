package dhbw;

import javax.persistence.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("polyeder.odb");
        EntityManager em = emf.createEntityManager();

        // Store Point+Edge objects in the database:
        /*
        em.getTransaction().begin();
        for (int i = 0; i < 100; i++) {
            Point p0 = new Point(i, i, i);
            em.persist(p0);
            Point p1 = new Point(i + 10, i + 20, i + 30);
            em.persist(p1);
            Edge e = new Edge(p0, p1);
            em.persist(e);
        }
        em.getTransaction().commit();
        */

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.getX()) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }

        // Retrieve all Edge objects from the database:
        TypedQuery<Edge> edgQuery = em.createQuery("SELECT e FROM Edge e", Edge.class);
        List<Edge> edges = edgQuery.getResultList();
        for (Edge e : edges) {
            System.out.println(e);
        }

        // Close the database connection:
        em.close();
        emf.close();
    }
}
