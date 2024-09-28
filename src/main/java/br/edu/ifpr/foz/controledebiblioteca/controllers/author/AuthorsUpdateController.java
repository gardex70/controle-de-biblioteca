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

@WebServlet("/authors/update")
public class AuthorsUpdateController extends HttpServlet{

    AuthorRepository repository = new AuthorRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        Author author = repository.getById(id);

        req.setAttribute("author", author);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/authors-edit.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");

        Author author = new Author();
        author.setId(id);
        author.setName(name);

        repository.update(author);

        resp.sendRedirect(req.getContextPath() + "/authors");
    }

}
