package br.edu.ifpr.foz.controledebiblioteca.controllers.book;

import br.edu.ifpr.foz.controledebiblioteca.models.Book;
import br.edu.ifpr.foz.controledebiblioteca.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BooksController extends HttpServlet {

    private final BookRepository repository;

    public BooksController(){
        repository = new BookRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = repository.getAll();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books.jsp");
        req.setAttribute("books", books);

        dispatcher.forward(req, resp);

    }
}
