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


@WebServlet("/InsertEmpl")
public class InsertEmpl extends HttpServlet {
		
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		float sal=Float.parseFloat(req.getParameter("sal"));
		String desg=req.getParameter("desg");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","kumar123");
			PreparedStatement ps=con.prepareStatement("insert into oper values(?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setFloat(3, sal);
			ps.setString(4, desg);
			int u=ps.executeUpdate();
			if(u==1)
			System.out.println("inserted");
			PrintWriter pw=res.getWriter();
			pw.print("Data inserted Successfully!!");
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
