package br.edu.ifpr.foz.controledebiblioteca.repositories;

import br.edu.ifpr.foz.controledebiblioteca.connection.ConnectionFactory;
import br.edu.ifpr.foz.controledebiblioteca.models.Author;
import br.edu.ifpr.foz.controledebiblioteca.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;

public class AuthorRepository {

    public Author insert(Author author) {
        Connection connection = null;
        String sql = "INSERT INTO author (name) VALUES (?)";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, author.getName());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet id = statement.getGeneratedKeys();
                id.next();

                author.setId(id.getInt(1));
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }


        return author;
    }

    public Author update(Author author) {
        Connection connection = null;
        String sql = "UPDATE author SET name = ? WHERE id = ?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, author.getName());
            statement.setInt(2, author.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DatabaseException("Nenhum autor encontrado com o ID fornecido.");
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return author;
    }
    
    public boolean delete(int id) {
        Connection connection = null;
        String sql = "DELETE FROM author WHERE id = ?";

        try {
            connection = ConnectionFactory.getConnection();

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

    public Author getById(int id) {
        Connection connection = null;
        String sql = "SELECT * FROM author WHERE id = ?";
        Author author = null;

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                author = instantiateAuthor(resultSet);
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return author;
    }

    public ArrayList<Author> getAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM author;";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Author author = instantiateAuthor(resultSet);
                authors.add(author);
            }
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
			try {
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			ConnectionFactory.closeConnection();
        }

        return authors;
    }


    public static Author instantiateAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id"));
        author.setName(resultSet.getString("name"));
        return author;
    }


}
