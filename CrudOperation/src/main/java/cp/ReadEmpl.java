package cp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ReadEmpl")
public class ReadEmpl extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","kumar123");
			PreparedStatement ps=con.prepareStatement("select * from oper where id=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			PrintWriter pw=res.getWriter();
			if (rs.next()) 
			{
                pw.println("<p><strong>ID:</strong> " + rs.getInt(1) + "</p>");
                pw.println("<p><strong>Name:</strong> " + rs.getString(2) + "</p>");
                pw.println("<p><strong>Salary:</strong> " + rs.getFloat(3) + "</p>");
                pw.println("<p><strong>Designation:</strong> " + rs.getString(4) + "</p>");
            } 
			else 
			{
                pw.println("<p style='color:red;'>No employee found with ID " + id + "</p>");
            }
			ps.close();
			con.close();
			pw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
