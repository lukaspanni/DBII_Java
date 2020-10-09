package dhbw;

import java.util.List;

import javax.persistence.*;


public class Main
{
	private static EntityManager entityManager;

	public static void main(String[] args)
	{
		// Open a database connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("polyeder");
		entityManager = emf.createEntityManager();

		//listPolyeders();
		System.out.println();
		getPerimeter("poly#88322");
		System.out.println();
		movePoly("poly#88322", 0,0,0);

	}

	private static void listPolyeders() {
		try {
			entityManager.getTransaction().begin();
			@SuppressWarnings("unchecked")
			List<Polyeder> polyeders = entityManager.createQuery("SELECT p FROM Polyeder p").getResultList();
			for (Polyeder p : polyeders) {
				System.out.println(p);
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERROR(listPolyeders): " + e.getMessage());
			entityManager.getTransaction().rollback();
		}
	}

	private static void getPerimeter(String polyName) {
		try {
			entityManager.getTransaction().begin();
			TypedQuery<Polyeder> query = entityManager.createQuery(
					"SELECT p FROM Polyeder p WHERE p.name=:name", Polyeder.class);
			Polyeder p = query.setParameter("name", polyName).getSingleResult();
			System.out.println(
					String.format("Perimeter for %s is %f", polyName, p.getPerimeter()));
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERROR(getPerimeter): " + e.getMessage());
			entityManager.getTransaction().rollback();
		}
	}

	private static void movePoly(String polyName, double dx, double dy, double dz) {
		try {
			entityManager.getTransaction().begin();
			TypedQuery<Polyeder> query = entityManager.createQuery(
					"SELECT p FROM Polyeder p WHERE p.name=:name", Polyeder.class);
			Polyeder p = query.setParameter("name", polyName).getSingleResult();
			System.out.println(String.format("Point before: %s", p.faces.get(0).edges.get(0).p1));
			p.move(dx,dy,dz);
			System.out.println(String.format("Point after: %s", p.faces.get(0).edges.get(0).p1));
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERROR(getPerimeter): " + e.getMessage());
			entityManager.getTransaction().rollback();
		}
	}

}
