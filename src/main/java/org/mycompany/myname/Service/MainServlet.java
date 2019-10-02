package org.mycompany.myname.Service;

import org.mycompany.myname.Servlet.ServiceDir;

import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getParameter("path");
        path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ServiceDir serviceDir = new ServiceDir();
        try {
            if (serviceDir.isChild(path)) {
                req.setAttribute("path", serviceDir.getCurrentDir());
                req.setAttribute("directory", serviceDir.getChildDir());
                req.setAttribute("files", serviceDir.getSimpleFiles());

                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (IOException e) {
            resp.getWriter().print("Hello from servlet do get");
        }
    }
}