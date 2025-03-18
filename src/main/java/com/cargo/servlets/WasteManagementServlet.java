package com.cargo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cargo.servlets.DBConnection;

@WebServlet("/WasteManagementServlet")
public class WasteManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<html><head>");
    out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
    out.println("<style>");
    out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; }");
    out.println(".message-box { margin: 50px auto; padding: 20px; width: 50%;");
    out.println("border: 3px solid #d8b3ff; box-shadow: 4px 4px 10px rgba(128, 0, 128, 0.5);");
    out.println("background-color: white; border-radius: 10px; }");
    out.println("h3 { color: black; font-weight: bold; }");
    out.println("</style>");
    out.println("</head><body>");

    try (Connection conn = DBConnection.getConnection()) {
        // Find expired or used-up items and mark them as waste
        String sql = "UPDATE cargo_items SET status='Waste' WHERE expiry_date < CURDATE() OR retrieved=TRUE";
        PreparedStatement stmt = conn.prepareStatement(sql);
        int rowsUpdated = stmt.executeUpdate();

        // Log the waste management action
        String logSql = "INSERT INTO logs (action_type, details) VALUES ('Waste Management', ?)";
        PreparedStatement logStmt = conn.prepareStatement(logSql);
        logStmt.setString(1, rowsUpdated + " items marked as waste.");
        logStmt.executeUpdate();
        logStmt.close();

        // Display message
        out.println("<div class='message-box'>");
        if (rowsUpdated > 0) {
            out.println("<h3>" + rowsUpdated + " items marked as Waste!</h3>");
        } else {
            out.println("<h3>No expired or used-up items found!</h3>");
        }
        out.println("</div>");

        stmt.close();
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<div class='message-box'><h3>Error updating waste items!</h3></div>");
    }

    out.println("</body></html>");
}
}