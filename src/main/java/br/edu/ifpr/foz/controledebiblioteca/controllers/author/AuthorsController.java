package br.edu.ifpr.foz.controledebiblioteca.controllers.author;

import br.edu.ifpr.foz.controledebiblioteca.models.Author;
import br.edu.ifpr.foz.controledebiblioteca.repositories.AuthorRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/authors")
public class AuthorsController extends HttpServlet {

    private AuthorRepository repository;

    public AuthorsController(){
        repository = new AuthorRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Author> authors = repository.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/authors.jsp");
        req.setAttribute("authors", authors);

        dispatcher.forward(req, resp);

    }
}
