package services;

import models.Book;
import models.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDb {

    private Connection connection = null;
    private static MySQLDb instance = null;
    String url = "jdbc:mysql://localhost:3306/library_catalog";
    String username = "root";
    String password = "123456";

    public MySQLDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized MySQLDb createInstance(){
        if (instance == null){
            instance = new MySQLDb();
        }

        return instance;
    }

    public boolean registerUser(UserModel user) {

        try {
            String qRegisterUser = "INSERT INTO users VALUES (default, ?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(qRegisterUser);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public UserModel loginUser(String userName, String ePassword) {
        UserModel userModel = null;
        try {
            String qLoginUser = "SELECT * from users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(qLoginUser);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, ePassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int dUserId = resultSet.getInt("user_id");
                String dFirstName = resultSet.getString("fname");
                String dLastName = resultSet.getString("lname");
                String dUserName = resultSet.getString("username");
                userModel = new UserModel(dUserId, dFirstName, dLastName, dUserName);
            }
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return userModel;
    }

    public List<Book> fetchBooks(String qFetchBooks) {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(qFetchBooks);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int bId = resultSet.getInt("book_id");
                String bName = resultSet.getString("book_name");
                String tName = resultSet.getString("topic_name");
                String aName = resultSet.getString("author_name");
                int bAvailability = resultSet.getInt("is_available");

                Book book = new Book(bId, bName, tName, aName, bAvailability);
                books.add(book);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    public boolean reserveBook(int userId, int bookId) {

        try {
            String qReserveBook = "INSERT INTO reservations VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(qReserveBook);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();

            String qUpdateAvailability = "UPDATE books SET is_available = 0 WHERE book_id = ?";
            preparedStatement = connection.prepareStatement(qUpdateAvailability);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public List<Book> fetchReservedBooks(int userId) {

        List<Book> books = new ArrayList<>();
        try {
            String qFetchReservedBooks = "SELECT book_name, author_name  " +
                    "FROM books b, authors a, reservations r " +
                    "WHERE b.author_id = a.author_id AND b.book_id = r.book_id AND user_id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(qFetchReservedBooks);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String bName = resultSet.getString("book_name");
                String aName = resultSet.getString("author_name");

                Book reservedBook = new Book(bName, aName);
                books.add(reservedBook);
            }
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return books;
    }
}
