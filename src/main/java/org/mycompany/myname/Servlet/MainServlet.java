package org.mycompany.myname.Servlet;

import org.mycompany.myname.Service.AccountService;
import org.mycompany.myname.Service.ServiceDir;
import org.mycompany.myname.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (AccountService.getUserBySessionId(req.getSession().getId()) == null) {
            req.getRequestDispatcher("/signin.html").forward(req, res);
            return;
        }

        String path = req.getParameter("path");
        path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        String d = AccountService.getUserBySessionId(req.getSession().getId()).getLogin();
        if (path.contains(AccountService.getUserBySessionId(req.getSession().getId()).getLogin())) {
            ServiceDir serviceDir = new ServiceDir();
            try {
                if (serviceDir.isChild(path)) {
                    req.setAttribute("path", serviceDir.getCurrentDir());
                    req.setAttribute("directory", serviceDir.getChildDir());
                    req.setAttribute("files", serviceDir.getSimpleFiles());

                    getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);
                }
            } catch (IOException e) {
                res.getWriter().print("Hello from servlet do get");
            }
        }
    }
}