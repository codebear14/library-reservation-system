package servlets;

import models.Book;
import services.MySQLDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FetchBooksServlet")
public class FetchBooksServlet extends HttpServlet {

    String redirect;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topic = request.getParameter("topic");

        MySQLDb db = MySQLDb.createInstance();
        String qFetchBooks;

        if(topic.equals("all")){
            qFetchBooks = "SELECT book_id, book_name, topic_name, author_name, is_available FROM books b, authors a, topics t Where b.author_id = a.author_id AND t.topic_id= b.topic_id";
        }
        else if(topic.equals("xml")){
            qFetchBooks = "SELECT book_id, book_name, topic_name, author_name, is_available FROM books b, authors a, topics t Where b.author_id = a.author_id AND t.topic_id= b.topic_id and t.topic_id=1";
        }
        else{
            qFetchBooks = "SELECT book_id, book_name, topic_name, author_name, is_available FROM books b, authors a, topics t Where b.author_id = a.author_id AND t.topic_id= b.topic_id and t.topic_id=2";
        }

        List<Book> books = db.fetchBooks(qFetchBooks);
        for(Book b : books){
            System.out.println(b.getBookName() + " - " + b.getTopicName() + " - " + b.getAuthorName() + " - " + b.getBookAvailability());
        }

        request.setAttribute("listOfBooks", books);
        if(request.getParameter("page") == null){
            redirect = "home.jsp";
            //System.out.println("H");
        }
        else{
            redirect = "index.jsp";
            //System.out.println("I");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
        requestDispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
