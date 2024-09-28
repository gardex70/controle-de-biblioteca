package br.edu.ifpr.foz.controledebiblioteca.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/librarydb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                logConnection("Conectado ao banco de dados."); // Log de conexão bem-sucedida
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver não encontrado: " + e.getMessage(), e);
        }

        return connection; // Retorna a conexão existente ou nova
    }


    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    logConnection("Conexão fechada com sucesso.");
                }
            } catch (SQLException e) {
                logConnection("Não foi possível encerrar a conexão: " + e.getMessage());
            }
        } else {
            logConnection("A conexão já estava fechada ou não foi inicializada.");
        }
    }

    private static void logConnection(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callerMethod = stackTrace[3].getMethodName(); // Obtemos o método chamador
        System.out.println(message + " a partir de " + callerMethod);
    }
}
