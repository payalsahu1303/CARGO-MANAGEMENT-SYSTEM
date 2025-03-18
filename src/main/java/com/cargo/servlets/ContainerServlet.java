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

@WebServlet("/ContainerServlet")
public class ContainerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get values from form
        String containerId = request.getParameter("containerId");
        String zone = request.getParameter("zone");
        int width = Integer.parseInt(request.getParameter("width"));
        int depth = Integer.parseInt(request.getParameter("depth"));
        int height = Integer.parseInt(request.getParameter("height"));

        // Insert into database
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO containers (container_id, zone, width, depth, height) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, containerId);
            stmt.setString(2, zone);
            stmt.setInt(3, width);
            stmt.setInt(4, depth);
            stmt.setInt(5, height);
            stmt.executeUpdate();
            
            // Log the action
            String logSql = "INSERT INTO logs (action_type, details) VALUES ('Container Added', ?)";
            PreparedStatement logStmt = conn.prepareStatement(logSql);
            logStmt.setString(1, "Container '" + containerId + "' added to the system.");
            logStmt.executeUpdate();
            logStmt.close();
            
            out.println("<h3 class='text-success'>Container Added Successfully!</h3>");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 class='text-danger'>Error Adding Container!</h3>");
        }
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    out.println("<html><head>");
    out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
    out.println("<style>");
    out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; }");
    out.println("h2 { color: black; font-weight: bold; }");
    out.println(".table-container { margin: 30px auto; width: 80%; }");
    out.println(".table { background-color: #d8b3ff; border: 2px solid #800080; }"); // Light purple background
    out.println("th { background-color: #800080; color: black; font-weight: bold; }"); // Dark purple header, black text
    out.println("td { color: black; font-weight: normal; }"); // Black table data
    out.println("</style>");
    out.println("</head><body>");
    
    out.println("<div class='container mt-5'><h2>Container List</h2>");
    out.println("<div class='table-container'>");
    out.println("<table class='table table-bordered table-hover text-center'><thead>");
    out.println("<tr><th>ID</th><th>Container ID</th><th>Zone</th><th>Size</th></tr></thead><tbody>");

    // Fetch containers from DB
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "SELECT * FROM containers";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        boolean hasData = false;
        while (rs.next()) {
            hasData = true;
            out.println("<tr>");
            out.println("<td>" + rs.getInt("id") + "</td>");
            out.println("<td>" + rs.getString("container_id") + "</td>");
            out.println("<td>" + rs.getString("zone") + "</td>");
            out.println("<td>" + rs.getInt("width") + "x" + rs.getInt("depth") + "x" + rs.getInt("height") + "</td>");
            out.println("</tr>");
        }
        if (!hasData) {
            out.println("<tr><td colspan='4'><strong>No containers found.</strong></td></tr>");
        }
        out.println("</tbody></table></div>");
        
        rs.close();
        stmt.close();
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<h3 class='text-danger'>Error Fetching Containers!</h3>");
    }

    out.println("<br><a href='index.jsp' class='btn btn-dark'>Back</a>");
    out.println("</div></body></html>");
}
}
