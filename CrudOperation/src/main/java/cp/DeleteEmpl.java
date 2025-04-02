package cp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteEmpl")
public class DeleteEmpl extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		int id=Integer.parseInt(req.getParameter("id"));		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","kumar123");
			PreparedStatement ps=con.prepareStatement("delete from oper where id=?");
			ps.setInt(1,id);
			int d=ps.executeUpdate();
			if(d==1)
			System.out.println("deleted successfully");
			PrintWriter pw=res.getWriter();
			pw.print("Employee data deleted");
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
