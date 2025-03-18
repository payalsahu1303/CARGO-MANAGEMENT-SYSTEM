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

@WebServlet("/CargoServlet")
public class CargoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get values from form
        String name = request.getParameter("name");
        int width = Integer.parseInt(request.getParameter("width"));
        int depth = Integer.parseInt(request.getParameter("depth"));
        int height = Integer.parseInt(request.getParameter("height"));
        double mass = Double.parseDouble(request.getParameter("mass"));
        int priority = Integer.parseInt(request.getParameter("priority"));
        String preferredZone = request.getParameter("preferredZone");
        int usageLimit = Integer.parseInt(request.getParameter("usageLimit"));
        String expiryDate = request.getParameter("expiryDate");

        // Insert into database
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO cargo_items (name, width, depth, height, mass, priority, preferred_zone, usage_limit, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, width);
            stmt.setInt(3, depth);
            stmt.setInt(4, height);
            stmt.setDouble(5, mass);
            stmt.setInt(6, priority);
            stmt.setString(7, preferredZone);
            stmt.setInt(8, usageLimit);
            stmt.setString(9, expiryDate);
            stmt.executeUpdate();
            
            // Log the action
            String logSql = "INSERT INTO logs (action_type, details) VALUES ('Cargo Added', ?)";
            PreparedStatement logStmt = conn.prepareStatement(logSql);
            logStmt.setString(1, "Cargo '" + name + "' added to the system.");
            logStmt.executeUpdate();
            logStmt.close();
            
            out.println("<h3>Cargo Added Successfully!</h3>");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error Adding Cargo!</h3>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
    out.println("<title>Cargo List</title>");
    out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
    out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
    out.println("<style>");
    out.println("body { font-family: 'Outfit', sans-serif; background-color: #f4f4f9; }");
    out.println(".container { max-width: 900px; margin-top: 40px; }");
    out.println(".table th { background-color: #6a0dad; color: white; text-align: center; }");
    out.println(".table td { text-align: center; }");
    out.println(".back-btn { margin-bottom: 15px; }");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");

    out.println("<div class='container'>");
    out.println("<h2 class='text-center mb-4'>Cargo Management System</h2>");

    // Back Button
    out.println("<a href='index.jsp' class='btn btn-secondary back-btn'> Back to Home</a>");

    // Search bar
    out.println("<input class='form-control mb-3' id='searchInput' type='text' placeholder='Search Cargo...' onkeyup='filterTable()'>");

    out.println("<table class='table table-striped table-bordered' id='cargoTable'>");
    out.println("<thead>");
    out.println("<tr><th>ID</th><th>Name</th><th>Width</th><th>Depth</th><th>Height</th><th>Mass (kg)</th><th>Priority</th><th>Zone</th><th>Usage Limit</th><th>Expiry Date</th></tr>");
    out.println("</thead>");
    out.println("<tbody>");

    // Fetch cargo from DB
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "SELECT * FROM cargo_items";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            out.println("<tr>");
            out.println("<td>" + rs.getInt("id") + "</td>");
            out.println("<td>" + rs.getString("name") + "</td>");
            out.println("<td>" + rs.getInt("width") + "</td>");
            out.println("<td>" + rs.getInt("depth") + "</td>");
            out.println("<td>" + rs.getInt("height") + "</td>");
            out.println("<td>" + rs.getDouble("mass") + "</td>");
            out.println("<td>" + rs.getInt("priority") + "</td>");
            out.println("<td>" + rs.getString("preferred_zone") + "</td>");
            out.println("<td>" + rs.getInt("usage_limit") + "</td>");
            out.println("<td>" + rs.getString("expiry_date") + "</td>");
            out.println("</tr>");
        }
        rs.close();
        stmt.close();
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<h3 class='text-danger text-center'>❌ Error Fetching Cargo Data!</h3>");
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("</div>");

    // JavaScript for filtering table rows
    out.println("<script>");
    out.println("function filterTable() {");
    out.println("  let input = document.getElementById('searchInput').value.toLowerCase();");
    out.println("  let rows = document.getElementById('cargoTable').getElementsByTagName('tr');");
    out.println("  for (let i = 1; i < rows.length; i++) {");
    out.println("    let cols = rows[i].getElementsByTagName('td');");
    out.println("    let match = false;");
    out.println("    for (let j = 0; j < cols.length; j++) {");
    out.println("      if (cols[j].innerText.toLowerCase().includes(input)) {");
    out.println("        match = true;");
    out.println("        break;");
    out.println("      }");
    out.println("    }");
    out.println("    rows[i].style.display = match ? '' : 'none';");
    out.println("  }");
    out.println("}");
    out.println("</script>");

    out.println("</body>");
    out.println("</html>");
}
}