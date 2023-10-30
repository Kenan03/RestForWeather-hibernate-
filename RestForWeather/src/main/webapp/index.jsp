<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<html>
<body>
<%!
    String getFormatted()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy hh:mm:ss");
        return sdf.format(new Date());
    }
%>
<H4>Welcome!     <i>Today <%= getFormatted() %></i> </H4>
<a href="SignUp.html">Sign up</a> |
<a href="login.html">Login </a> |
<a href="/checkLogin">Profile</a> |
<%--<a href="/LogoutServlet">Logout </a> |--%>
<%--<p>If you wanna to check weather: - <a href="/weather">Check!</a></p>--%>

</body>
</html>