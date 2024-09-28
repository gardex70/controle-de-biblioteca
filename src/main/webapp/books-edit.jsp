<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.edu.ifpr.foz.controledebiblioteca.models.Book" %>
<%@ page import="br.edu.ifpr.foz.controledebiblioteca.models.BookStatus" %>
<%@ page import="br.edu.ifpr.foz.controledebiblioteca.models.Author" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getAttribute("book") != null ? "Editar Livro" : "Adicionar Livro" %></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center"><%= request.getAttribute("book") != null ? "Editar Livro" : "Adicionar Livro" %></h1>

    <form action="<%= request.getAttribute("book") != null ? request.getContextPath() + "/books/update" : request.getContextPath() + "/books/create" %>" method="post" class="mt-4">
        <%
			Book book = (Book) request.getAttribute("book");
            if (book != null) {
        %>
        <input type="hidden" name="book-id" value="<%= book.getId() %>"/>
        <% } %>

        <div class="form-group">
            <label for="book-name">Nome do livro:</label>
            <input type="text" id="book-name" name="book-name" class="form-control" value="<%= book != null ? book.getName() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="book-status">Status do Livro:</label>
            <select id="book-status" name="book-status" class="form-control" required>
                <option value="AVAILABLE" <%= book != null && book.getStatus() == BookStatus.AVAILABLE ? "selected" : "" %>>Disponível</option>
                <option value="UNAVAILABLE" <%= book != null && book.getStatus() == BookStatus.UNAVAILABLE ? "selected" : "" %>>Indisponível</option>
                <option value="BORROWED" <%= book != null && book.getStatus() == BookStatus.BORROWED ? "selected" : "" %>>Emprestado</option>
            </select>
        </div>

        <div class="form-group">
            <label for="book-date">Data de publicação:</label>
            <input type="date" id="book-date" name="book-date" class="form-control" value="<%= book != null ? book.getDate() : "" %>" required>
        </div>

        <div class="form-group">
            <label for="author-id">Autor:</label>
            <select id="author-id" name="author-id" class="form-control" required>
                <option value="">Selecione um autor</option>
                <%
                    ArrayList<Author> authors = (ArrayList<Author>) request.getAttribute("authors");
                    if (authors != null) {
                        for (Author author : authors) {
                %>
                <option value="<%= author.getId() %>"
                        <%= (book != null && book.getAuthor().getId() == author.getId()) ? "selected" : "" %>>
                    <%= author.getName() %>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">
            <%= book != null ? "Atualizar" : "Criar" %>
        </button>
    </form>

    <br>
    <a href="<%= request.getContextPath() + "/books" %>" class="btn btn-secondary">Voltar para a lista de autores</a>
</div>
</body>
</html>
