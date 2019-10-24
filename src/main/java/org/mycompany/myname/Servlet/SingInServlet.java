package org.mycompany.myname.Servlet;

import org.mycompany.myname.Service.AccountService;
import org.mycompany.myname.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signin")
public class SingInServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String logout = req.getParameter("delete");
        if (logout != null) {
            doDelete(req, res);
            return;
        }

        String sessionId = req.getSession().getId();
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");


        if (login == null || pass == null || login == "" || pass == "") {
            res.setContentType("text/html;charset=utf-8");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = AccountService.getUserByLogin(login);
        if (profile == null || !profile.getPass().equals(pass)) {
            res.setContentType("text/html;charset=utf-8");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        AccountService.addSession(req.getSession().getId(), profile);
//        req.getRequestDispatcher("/index.jsp").forward(req, res);
        res.sendRedirect("http://localhost:8888/?path=" + login);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String sessionId = request.getSession().getId();
        AccountService.deleteSession(sessionId);
    }

}
