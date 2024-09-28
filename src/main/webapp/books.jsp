<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.edu.ifpr.foz.controledebiblioteca.models.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Livros</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <!-- Incluindo o menu -->
    <jsp:include page="menu.jsp" />

    <h1>Lista de Livros</h1>
    <a href="<%= request.getContextPath() + "/books/create" %> " class="btn btn-primary">Adicionar Livro</a>
    <table class="table mt-3">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Status</th>
            <th>Autor</th>
            <th>Data</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
            for (Book book : books) {
        %>
        <tr>
            <td>
                <%= book.getId() %>
            </td>
            <td>
                <%= book.getName() %>
            </td>
            <td>
                <%= book.getStatus() %>
            </td>
            <td>
                <%= book.getAuthor().getName() %>
            </td>
            <td>
                <%
                LocalDate date = book.getDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = date.format(formatter);
                %>
                <%= formattedDate %>
            </td>
            <td>
                <a href="<%= request.getContextPath() + "/books/update?id=" + book.getId() %> " class="btn btn-warning">Editar</a>
                <form action="<%= request.getContextPath() + "/books/delete" %>" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= book.getId() %>"/>
                    <button type="submit" class="btn btn-danger">Deletar</button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
