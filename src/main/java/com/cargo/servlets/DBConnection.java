package com.cargo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBConnection")
public class DBConnection extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database Credentials
    private static final String URL = "jdbc:mysql://localhost:3306/cargo_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Payal@123"; // Replace with your actual password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get input values from form
        String name = request.getParameter("name");
        int width = Integer.parseInt(request.getParameter("width"));
        int depth = Integer.parseInt(request.getParameter("depth"));
        int height = Integer.parseInt(request.getParameter("height"));
        double mass = Double.parseDouble(request.getParameter("mass"));
        int priority = Integer.parseInt(request.getParameter("priority"));
        String preferredZone = request.getParameter("preferredZone");
        int usageLimit = Integer.parseInt(request.getParameter("usageLimit"));
        String expiryDate = request.getParameter("expiryDate");

        // JDBC Code Inside doPost()
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL Driver
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); // Establish Connection
            
            // SQL Query
            String sql = "INSERT INTO cargo_items (name, width, depth, height, mass, priority, preferred_zone, usage_limit, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set Parameters
            stmt.setString(1, name);
            stmt.setInt(2, width);
            stmt.setInt(3, depth);
            stmt.setInt(4, height);
            stmt.setDouble(5, mass);
            stmt.setInt(6, priority);
            stmt.setString(7, preferredZone);
            stmt.setInt(8, usageLimit);
            stmt.setString(9, expiryDate);

            // Execute Query
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                out.println("<h3>Data Inserted Successfully!</h3>");
            } else {
                out.println("<h3>Insertion Failed!</h3>");
            }

            // Close Connections
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<h3>Database Connection Failed!</h3>");
            e.printStackTrace(out);
        }
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
