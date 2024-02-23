package dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import entity.Book;
import util.HibernateUtil;

public class BookDAOImpl implements BookDAO {

	@Override
	public void create(Scanner sc) {
		
		// Get user input
		sc.nextLine();
		System.out.println("Enter Book Title");
		String bookTitle = sc.nextLine();
		
		System.out.println("Enter Book Author");
		String bookAuthor = sc.nextLine();
		
		Book bk = new Book(bookTitle, bookAuthor);
		
		// Open session and persist into DB
		Session session = HibernateUtil.buildSessionFactory().openSession();
		
		session.beginTransaction();
		session.persist(bk);
		session.getTransaction().commit();
		session.close();
		
		
	}

	@Override
	public Book read(Scanner sc) {
		
		// Get book id from user
		System.out.println("Enter Book ID");
		Long bookId = sc.nextLong();
		
		// Find the book in DB
		Session session = HibernateUtil.buildSessionFactory().openSession();
		Book book = (Book)session.find(Book.class, bookId);
		
		System.out.println(book);
		session.close();
		
		return book;
		
	}

	@Override
	public void readAll() {
		
		Session session = HibernateUtil.buildSessionFactory().openSession();
		session.beginTransaction();
		
		// Using HQL to fetch all books
	    List<Book> books = session.createQuery("FROM Book", Book.class).getResultList();
	    
		session.getTransaction().commit();
		
		// Printing all books
	    for(Book bk : books) {
	        System.out.println(bk);
	    }
	    
		session.close();
		
	}

	@Override
	public void update(Scanner sc) {
		
		// Get the book from read method
		Book bk = read(sc);
		
		// Get other input from user
		sc.nextLine();
		System.out.println("Enter Book Title");
		String bookTitle = sc.nextLine();
		
		System.out.println("Enter Book Author");
		String bookAuthor = sc.nextLine();
		
		// Set the new attributes
		bk.setBook_author(bookAuthor);
		bk.setBook_title(bookTitle);
		
		// Open session
		Session session = HibernateUtil.buildSessionFactory().openSession();
		
		session.beginTransaction();
		
		// Merge the sessions
		session.merge(bk);
		session.getTransaction().commit();
		System.out.println("Book Updated!");
		session.close();
		
	}

	@Override
	public void delete(Scanner sc) {
		
		// Get the book obj
		Book bk = read(sc);
		
		Session session = HibernateUtil.buildSessionFactory().openSession();
		
		session.beginTransaction();
		
		// Remove the book from DB
		session.remove(bk);
		session.getTransaction().commit();
		System.out.println("Book Deleted!");
		session.close();
		
	}

}
