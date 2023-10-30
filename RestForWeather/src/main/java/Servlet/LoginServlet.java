package Servlet;

import dao.PersonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        try {
            if(PersonDAO.findByNameAndPassword(name, password)){
                HttpSession session = request.getSession();
                session.setAttribute("id", PersonDAO.getUserByName(name));
                response.sendRedirect("http://localhost:8080/weather");
            }
            else{
                out.print("Sorry, username or password error!");
                request.getRequestDispatcher("login.html").include(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("</html></body>");
        out.close();
    }
}
