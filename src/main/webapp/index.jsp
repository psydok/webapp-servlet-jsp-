<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>

<%
    String parentPath = (String)request.getAttribute("path");
    ArrayList<File> childAr = ( ArrayList<File>) request.getAttribute("directory");
    ArrayList<File> simpleFiles = ( ArrayList<File>) request.getAttribute("files");

%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lab3 Servlet</title>
    </head>
    <body>
            <div class="dirs">
                <%
                    out.println("<a href=\'?path=" + parentPath + "/'>" + parentPath + "</a>");
                    if (childAr != null && simpleFiles != null) {
                        for (File arg: childAr) {
                            out.println("<a href=\'?path=" + arg.getAbsolutePath() + "/'>" + arg.getName() + "</a>");
                            out.println("<br/>");
                        }
                        for (File arg: simpleFiles) {
                             out.println("<p>" + arg.getName() + "</p>");
                         }
                    }
                %>
            </div>

            <form method="post" name="delete">
            <a href="" style="position:relative; align=left; padding-top:25px;">ВЫХОД</a>
            </form>
    </body>
</html>
