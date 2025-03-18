package com.cargo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cargo.servlets.DBConnection;

@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<div class='container mt-5'><h2>System Logs</h2>");
        out.println("<table class='table table-striped'><thead>");
        out.println("<tr><th>ID</th><th>Action</th><th>Details</th><th>Timestamp</th></tr></thead><tbody>");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM logs ORDER BY timestamp DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("action_type") + "</td>");
                out.println("<td>" + rs.getString("details") + "</td>");
                out.println("<td>" + rs.getTimestamp("timestamp") + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody></table></div>");
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error Fetching Logs!</h3>");
        }
    }
}