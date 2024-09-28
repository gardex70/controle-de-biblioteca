package br.edu.ifpr.foz.controledebiblioteca.controllers.author;

import br.edu.ifpr.foz.controledebiblioteca.repositories.AuthorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/authors/delete")
public class AuthorsDeleteController extends HttpServlet {

    AuthorRepository repository = new AuthorRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        repository.delete(id);
        resp.sendRedirect(req.getContextPath() + "/authors");
    }

}
