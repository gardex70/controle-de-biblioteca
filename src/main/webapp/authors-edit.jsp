<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.edu.ifpr.foz.controledebiblioteca.models.Author" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getAttribute("author") != null ? "Editar Autor" : "Adicionar Autor" %></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center"><%= request.getAttribute("author") != null ? "Editar Autor" : "Adicionar Autor" %></h1>

    <form action="<%= request.getAttribute("author") != null ? request.getContextPath() + "/authors/update" : request.getContextPath() + "/authors/create" %>" method="post" class="mt-4">
        <%
            Author author = (Author) request.getAttribute("author");
            if (author != null) {
        %>
        <input type="hidden" name="id" value="<%= author.getId() %>"/>
        <% } %>

        <div class="form-group">
            <label for="name">Nome:</label>
            <input type="text" id="name" name="name" class="form-control" value="<%= author != null ? author.getName() : "" %>" required>
        </div>

        <button type="submit" class="btn btn-primary">
            <%= author != null ? "Atualizar" : "Criar" %>
        </button>
    </form>

    <br>
    <a href="<%= request.getContextPath() + "/authors" %>" class="btn btn-secondary">Voltar para a lista de autores</a>
</div>
</body>
</html>
