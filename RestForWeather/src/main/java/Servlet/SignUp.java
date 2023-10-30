package Servlet;
import dao.PersonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
public class SignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        try {
            if(!PersonDAO.findByName(name)){
                Person person = new Person(name, email, password);
                PersonDAO.save(person);
                HttpSession session = request.getSession();
                session.setAttribute("id", PersonDAO.getUserId(person));
                response.sendRedirect("http://localhost:8080/weather");
            }
            else {
                out.print("Sorry, such username exist");
                request.getRequestDispatcher("login.html").include(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println("</html></body>");
        out.close();
    }
}
