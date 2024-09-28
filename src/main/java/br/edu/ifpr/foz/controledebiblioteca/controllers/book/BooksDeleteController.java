package br.edu.ifpr.foz.controledebiblioteca.controllers.book;

import br.edu.ifpr.foz.controledebiblioteca.repositories.BookRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/books/delete")
public class BooksDeleteController extends HttpServlet {

	BookRepository repository = new BookRepository();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));

		repository.delete(id);
		resp.sendRedirect(req.getContextPath() + "/books");
	}
}
