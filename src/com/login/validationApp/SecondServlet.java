package com.login.validationApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ss")
public class SecondServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String username = req.getParameter("un");
		String password = req.getParameter("ps");
		
		PrintWriter out = resp.getWriter();
		
		
		//get the data from database
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sQry = "select * from pentagon.login where username=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstmt = con.prepareStatement(sQry);
			
			//Set values before executing the query
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String duser = rs.getString("username");
				String dpass = rs.getString("password");
				if(password.equals(dpass)) {
					out.println("<html><body><h1>the username is "+duser+" password is "+dpass+"<h1></body></html>");
					out.println("<html><body><h1> Login Successfull </h1></body><html>");
				}
				
			}else {
				out.println("<html><body><h1>the username is not found. ");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
