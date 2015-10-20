package ua.testmvn.app;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet{

   private String msg;

    public void init() throws ServletException{
        msg = "Hi Servlet!!!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

out.println(msg);
//out.println(request.getCookies());
//out.println(request.getAuthType());
//out.println(request.getContextPath());
//out.println(request.getMethod());
//out.println(request.getServletPath());
//out.println(request.getInputStream());
//out.println(request.getLocalAddr());
//out.println(request.getRemoteAddr());
//out.println(request.getServerName());
//
////        String site = new String("http://www.photofuntoos.com");
//
//        response.setStatus(response.SC_MOVED_TEMPORARILY);
//        response.setHeader("Location", "http://www.photofuntoos.com");
//




    }

    public void destroy(){}


}
