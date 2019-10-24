package org.mycompany.myname.Servlet;

import org.mycompany.myname.Service.AccountService;
import org.mycompany.myname.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signout")
public class SignOutServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        String email = req.getParameter("email");
        UserProfile userProfile = new UserProfile(login, password, email);
        AccountService.addNewUser(userProfile);
        req.getRequestDispatcher("/signin.html").forward(req, res);

    }


}
