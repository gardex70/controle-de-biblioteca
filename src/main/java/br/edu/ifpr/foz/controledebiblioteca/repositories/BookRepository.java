package br.edu.ifpr.foz.controledebiblioteca.repositories;

import br.edu.ifpr.foz.controledebiblioteca.connection.ConnectionFactory;
import br.edu.ifpr.foz.controledebiblioteca.exceptions.DatabaseException;
import br.edu.ifpr.foz.controledebiblioteca.models.Author;
import br.edu.ifpr.foz.controledebiblioteca.models.Book;
import br.edu.ifpr.foz.controledebiblioteca.models.BookStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookRepository {

    private Connection connection;

    public BookRepository() {
        connection = ConnectionFactory.getConnection();
    }

    public Book insert(Book book) {
        String sql = "INSERT INTO book(name, status, author_id, date) VALUES (?,?,?,?)";

        try {
            if (connection.isClosed() || connection == null) {
                connection = ConnectionFactory.getConnection();
            }

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, book.getName());
            statement.setString(2, book.getStatus().name());
            statement.setInt(3, book.getAuthor().getId());
            statement.setDate(4, java.sql.Date.valueOf(book.getDate()));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet id = statement.getGeneratedKeys();
                id.next();

                book.setId(id.getInt(1));
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }


        return book;
    }

    public Book update(Book book) {
        String sql = "UPDATE book SET name = ?, status = ?, author_id = ?, date = ? WHERE id = ?";

        try {
            if (connection.isClosed() || connection == null) {
                connection = ConnectionFactory.getConnection();
            }

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, book.getName());
            statement.setString(2, book.getStatus().name());
            statement.setInt(3, book.getAuthor().getId());
            statement.setDate(4, java.sql.Date.valueOf(book.getDate()));
            statement.setInt(5, book.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return book;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM book WHERE id = ?";
        try {
            if (connection.isClosed() || connection == null) {
                connection = ConnectionFactory.getConnection();
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public Book getById(int id) {
        String sql = "SELECT book.id AS book_id, book.name AS book_name, book.date, book.status, " +
                "author.id AS author_id, author.name AS author_name " +
                "FROM book " +
                "INNER JOIN author ON book.author_id = author.id " +
                "WHERE book.id = ?";

        Book book = null;

        try {
            if (connection.isClosed() || connection == null) {
                connection = ConnectionFactory.getConnection();
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book = instantiateBook(resultSet);
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return book;
    }

    public ArrayList<Book> getAll() {
        String sql = "SELECT book.id AS book_id, book.name AS book_name, book.date, book.status, author.id AS author_id, author.name AS author_name " +
                "FROM book " +
                "INNER JOIN author ON book.author_id = author.id";
        ArrayList<Book> books = new ArrayList<>();

        try {
            if (connection.isClosed() || connection == null) {
                connection = ConnectionFactory.getConnection();
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                books.add(instantiateBook(resultSet));
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return books;
    }

    public static Book instantiateBook(ResultSet resultSet) throws Exception {
        Book book = new Book();

        book.setId(resultSet.getInt("book_id"));
        book.setName(resultSet.getString("book_name"));
        book.setDate(resultSet.getDate("date").toLocalDate());

        Author author = new Author();
        author.setId(resultSet.getInt("author_id"));
        author.setName(resultSet.getString("author_name"));
        book.setAuthor(author);

        book.setStatus(BookStatus.valueOf(resultSet.getString("status")));

        return book;
    }

}