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

@WebServlet("/CargoRetrieveServlet")
public class CargoRetrieveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int cargoId = Integer.parseInt(request.getParameter("cargoId"));

        try (Connection conn = DBConnection.getConnection()) {
            // Check if cargo exists and is not already retrieved
            String checkSql = "SELECT id, name, preferred_zone FROM cargo_items WHERE id=? AND retrieved=FALSE";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, cargoId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String cargoName = rs.getString("name");
                String container = rs.getString("preferred_zone");

                // Mark as retrieved
                String updateSql = "UPDATE cargo_items SET retrieved=TRUE WHERE id=?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, cargoId);
                updateStmt.executeUpdate();
                
                // Log the action
                String logSql = "INSERT INTO logs (action_type, details) VALUES ('Cargo Retrieved', ?)";
                PreparedStatement logStmt = conn.prepareStatement(logSql);
                logStmt.setString(1, "Cargo '" + cargoName + "' retrieved from " + container + ".");
                logStmt.executeUpdate();
                logStmt.close();
                
                out.println("<h3>Cargo '" + cargoName + "' retrieved from " + container + "!</h3>");
                updateStmt.close();
            } else {
                out.println("<h3>Cargo not found or already retrieved!</h3>");
            }

            rs.close();
            checkStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error retrieving cargo!</h3>");
        }
    }
}