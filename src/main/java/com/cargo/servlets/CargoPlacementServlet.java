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

@WebServlet("/CargoPlacementServlet")
public class CargoPlacementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        int cargoId = Integer.parseInt(request.getParameter("cargoId"));

        try (Connection conn = DBConnection.getConnection()) {
            // Find the best container for this cargo based on available space and preferred zone
            String sql = "SELECT container_id FROM containers WHERE zone = (SELECT preferred_zone FROM cargo_items WHERE id=?) LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cargoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String containerId = rs.getString("container_id");

                // Update Cargo Placement
                String updateSql = "UPDATE cargo_items SET preferred_zone = ? WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, containerId);
                updateStmt.setInt(2, cargoId);
                updateStmt.executeUpdate();
                
                // Log the placement action
                String logSql = "INSERT INTO logs (action_type, details) VALUES ('Cargo Placed', ?)";
                PreparedStatement logStmt = conn.prepareStatement(logSql);
                logStmt.setString(1, "Cargo ID '" + cargoId + "' placed in container '" + containerId + "'.");
                logStmt.executeUpdate();
                logStmt.close();
                
                out.println("<h3>Cargo placed in " + containerId + "!</h3>");
                updateStmt.close();
            } else {
                out.println("<h3>No suitable container found!</h3>");
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error in Cargo Placement!</h3>");
        }
    }
}