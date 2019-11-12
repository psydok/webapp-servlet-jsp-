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

            <form action="${pageContext.request.contextPath}/signin" method="post">
             <div><input type="submit" name="exit_button" value="EXIT" style="position:absolute; top:0; right:0;"></div>
            </form>
    </body>
</html>
