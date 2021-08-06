import daos.BookDAO;
import daos.ConnectionFactory;
import models.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MainApplication {

    public static void main(String[] args) {
        //executeQuery("DROP DATABASE IF EXISTS JDBC_Lab;");
        //executeQuery("CREATE DATABASE IF NOT EXISTS JDBC_Lab;");
        executeQuery("USE JDBC_Lab;");
        executeQuery(new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS Book (")
                .append("id INT AUTO_INCREMENT PRIMARY KEY, ")
                .append("author VARCHAR(255), ")
                .append("title VARCHAR(255), ")
                .append("genre VARCHAR(255), ")
                .append("pages INT);")
                .toString());
        BookDAO dao = new BookDAO();
        dao.create(new Book(0, "JRR Tolkien", "Lord of the Rings", "Fantasy", 1178));
        System.out.println(dao.findById(2));
        System.out.println(dao.findAll());
        dao.delete(3);
        dao.update(14, new Book(0, "JK Rowling", "The Chamber Of Secrets", "Fantasy", 341));
    }

    private static void executeQuery(String query) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
