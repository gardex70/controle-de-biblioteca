package br.edu.ifpr.foz.controledebiblioteca.controllers.book;

import br.edu.ifpr.foz.controledebiblioteca.models.Author;
import br.edu.ifpr.foz.controledebiblioteca.models.Book;
import br.edu.ifpr.foz.controledebiblioteca.models.BookStatus;
import br.edu.ifpr.foz.controledebiblioteca.repositories.AuthorRepository;
import br.edu.ifpr.foz.controledebiblioteca.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet("/books/create")
public class BooksCreateController extends HttpServlet {
	BookRepository repository = new BookRepository();
	AuthorRepository authorRepository = new AuthorRepository();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<Author> authors = authorRepository.getAll();
		req.setAttribute("authors", authors);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/books-edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateString = req.getParameter("book-date");
		LocalDate bookDate = LocalDate.parse(dateString, formatter);

		Book book = new Book();
		book.setName(req.getParameter("book-name"));
		book.setDate(bookDate);
		book.setStatus(BookStatus.valueOf(req.getParameter("book-status")));

		Author author = new Author();
		author.setId(Integer.parseInt(req.getParameter("author-id")));
		author.setName(req.getParameter("author-name"));
		book.setAuthor(author);

		repository.insert(book);
		resp.sendRedirect(req.getContextPath() + "/books");
	}
}
