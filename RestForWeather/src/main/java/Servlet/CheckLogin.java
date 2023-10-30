package Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
public class CheckLogin extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        HttpSession session = request.getSession(false);
        if (session.getAttribute("id") != null)
            response.sendRedirect("http://localhost:8080/weather");
        else {
            out.print("Please login first");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        out.println("</html></body>");
        out.close();
    }
}
