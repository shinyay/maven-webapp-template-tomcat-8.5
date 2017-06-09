package com.oracle.jp.shinyay.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        writeBasicTagBeforeBody(out);
        out.println("<h1>Hello</h1>");
        writeBasicTagAfterBody(out);
        out.close();
    }

    private void writeBasicTagBeforeBody(PrintWriter out) {
        out.println("<!doctype html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("  <meta charset=\"utf-8\">");
        out.println("  <title>Hello</title>");
        out.println("  <meta name=\"author\" content=\"shinyay\">");
        out.println("  <link rel=\"stylesheet\" href=\"css/styles.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("  <script src=\"js/scripts.js\"></script>");
    }

    private void writeBasicTagAfterBody(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
}