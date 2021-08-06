package daos;

import models.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements DAOInterface<Book> {

    Connection connection = ConnectionFactory.getConnection();
    List<Book> books = new ArrayList<Book>();

    public Book findById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM JDBC_Lab.Book WHERE id = " + id + ";");
            return getBookFromResultSet(resultSet);
        } catch (SQLException throwables) {
            throw new RuntimeException("Unsuccessful query", throwables);
        }
    }

    public List findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM JDBC_Lab.Book;");
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
            return books;
        } catch (SQLException throwables) {
            throw new RuntimeException("Unsuccessful query", throwables);
        }
    }

    public Book update(int id, Book dto) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format(new StringBuilder()
                    .append("UPDATE JDBC_Lab.Book SET ")
                    .append("author = '%s',")
                    .append(" title = '%s',")
                    .append(" genre = '%s',")
                    .append(" pages = %d ")
                    .append("WHERE id = %d;")
                    .toString(),
            dto.getAuthor(),
            dto.getTitle(),
            dto.getGenre(),
            dto.getPages(),
            id);
            statement.execute(query);
            //ResultSet resultSet = statement.executeQuery(query);
            return dto;
        } catch (SQLException throwables) {
            throw new RuntimeException("Unsuccessful query", throwables);
        }
    }

    public Book create(Book dto) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format(new StringBuilder()
                    .append("INSERT INTO JDBC_Lab.Book (")
                    .append("author, ")
                    .append("title, ")
                    .append("genre, ")
                    .append("pages) ")
                    .append("VALUES (")
                    .append("'%s', '%s', '%s', %d);")
                    .toString(),
            dto.getAuthor(),
            dto.getTitle(),
            dto.getGenre(),
            dto.getPages());
            statement.execute(query);
            //ResultSet resultSet = statement.executeQuery(query);
            return dto;
        } catch (SQLException throwables) {
            throw new RuntimeException("Unsuccessful query", throwables);
        }
    }

    public void delete(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM JDBC_Lab.Book WHERE id = " + id + ";";
            statement.execute(query);
        } catch (SQLException throwables) {
            throw new RuntimeException("Unsuccessful query", throwables);
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) {
        Book book = new Book();
        try {
            while (resultSet.next()) {
                book = new Book(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
            }
            return book;
        } catch (SQLException throwables) {
            throw new RuntimeException("Could not find Book", throwables);
        }
    }
}
