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

@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {

    String error = "User registration failed";
    String errorPwd = "Password & Confirm Password do not match";
    String redirect;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello, From Servlet");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String cPassword = request.getParameter("cPassword");

        if(password.equals(cPassword)){

            EncryptPassword ePassword = new EncryptPassword();
            String encrypted = ePassword.encryptUserPassword(password);
            //System.out.println(password + " : " + encrypted);

            UserModel user = new UserModel(firstName, lastName, userName, encrypted);

            MySQLDb db = MySQLDb.createInstance();

            boolean doneRegUser = db.registerUser(user);

            if(doneRegUser){
                //System.out.println("Done");
                redirect = "login.jsp";
            }
            else{
                request.setAttribute("error", error);
                redirect = "signup.jsp";
            }
        }
        else{
            request.setAttribute("error", errorPwd);
            redirect = "signup.jsp";
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
