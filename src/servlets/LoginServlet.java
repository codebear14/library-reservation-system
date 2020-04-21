package servlets;

import models.EncryptPassword;
import models.UserModel;
import services.MySQLDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    String error = "Please check entered username or password";
    String redirect;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        EncryptPassword ePassword = new EncryptPassword();
        String encrypted = ePassword.encryptUserPassword(password);

        //System.out.println(password + " : " + encrypted);
        MySQLDb db = MySQLDb.createInstance();

        UserModel user = db.loginUser(userName, encrypted);

        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            redirect = "home.jsp";
        }
        else{
            redirect = "login.jsp";
            request.setAttribute("error", error);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
