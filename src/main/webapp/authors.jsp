<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifpr.foz.controledebiblioteca.models.Author" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Autores</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <!-- Incluindo o menu -->
    <jsp:include page="menu.jsp" />

    <h1>Lista de Autores</h1>
    <a href="<%= request.getContextPath() %>/authors/create" class="btn btn-primary">Adicionar Autor</a>
    <table class="table mt-3">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Author> authors = (List<Author>) request.getAttribute("authors");
            if (authors != null) {
                for (Author author : authors) {
        %>
        <tr>
            <td><%= author.getId() %></td>
            <td><%= author.getName() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/authors/update?id=<%= author.getId() %>" class="btn btn-warning">Editar</a>
                <form action="<%= request.getContextPath() + "/authors/delete" %>" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= author.getId() %>"/>
                    <button type="submit" class="btn btn-danger">Deletar</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3">Nenhum autor encontrado.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
