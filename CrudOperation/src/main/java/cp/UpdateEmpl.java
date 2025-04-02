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


@WebServlet("/UpdateEmpl")
public class UpdateEmpl extends HttpServlet {	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String newdesg=req.getParameter("desg");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","kumar123");
			PreparedStatement ps=con.prepareStatement("update oper set desg=? where id=?");
			ps.setString(1, newdesg);
			ps.setInt(2, id);
			int u=ps.executeUpdate();
			if(u==1)
			System.out.println("updated");
			PrintWriter pw=res.getWriter();
			pw.print("Employee details updated");
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
