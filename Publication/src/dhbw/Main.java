
package dhbw;

import java.util.Date;

import javax.persistence.*;


public class Main
{
	private static EntityManager entityManager;
	
	private static Author author;

	public static void main(String[] args)
	{
		// Open a database connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("publication");
		entityManager = emf.createEntityManager();

		deleteAll();
		createAuthor();
		createBooks();
		createBlogPosts();
		
		System.out.println("done.");
	}
	
	private static void deleteAll() {
		try {
			entityManager.getTransaction().begin();
			entityManager.createQuery("DELETE FROM Book b").executeUpdate();
			entityManager.createQuery("DELETE FROM BlogPost b").executeUpdate();
			entityManager.createQuery("DELETE FROM Publication p").executeUpdate();
			entityManager.createQuery("DELETE FROM Author a").executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERROR(createAuthor): " + e.getMessage());
			entityManager.getTransaction().rollback();
		}
	}

	private static void createAuthor() {
		try {
			entityManager.getTransaction().begin();
			author = new Author();
			author.name = "Yoda";
			entityManager.persist(author);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERROR(createAuthor): " + e.getMessage());
			entityManager.getTransaction().rollback();
		}
	}

	private static void createBooks() {
		try {
			entityManager.getTransaction().begin();
			// Book 1
			Book b = new Book();
			b.author = author;
			b.title = "The Force Compendium, Part 1";
			b.publishingDate = new Date();
			b.numPages = 739;
			b.isHardcover = true;
			entityManager.persist(b);
			author.publications.add(b);
			// Book 2
			b = new Book();
			b.author = author;
			b.title = "The Force Compendium, Part 2";
			b.publishingDate = new Date();
			b.numPages = 834;
			b.isHardcover = true;
			entityManager.persist(b);
			author.publications.add(b);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERROR(createAuthor): " + e.getMessage());
			entityManager.getTransaction().rollback();
		}
	}

	private static void createBlogPosts() {
		try {
			entityManager.getTransaction().begin();
			// BlogPost 1
			BlogPost b = new BlogPost();
			b.author = author;
			b.title = "How to recognize a Sith Lord";
			b.publishingDate = new Date();
			b.url = "http://jedi.org/blog/1";
			entityManager.persist(b);
			author.publications.add(b);
			// BlogPost 2
			b = new BlogPost();
			b.author = author;
			b.title = "May the Force be with you";
			b.publishingDate = new Date();
			b.url = "http://jedi.org/blog/2";
			entityManager.persist(b);
			author.publications.add(b);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERROR(createAuthor): " + e.getMessage());
			entityManager.getTransaction().rollback();
		}
	}
}

