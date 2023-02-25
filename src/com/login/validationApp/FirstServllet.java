package com.login.validationApp;

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

@WebServlet("/fs")
public class FirstServllet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("fn");
		String lname = req.getParameter("ln");
		String email = req.getParameter("em");
		String username = req.getParameter("un");
		String password = req.getParameter("ps");

		// PRESENTAION LOGIC
		PrintWriter out = resp.getWriter();
		out.println(
				"<html><body bgcolor='#D06565'><h1>Dear " + fname + " " + lname + " You have successfully registerd</h1></body></html>");
		out.flush();
		out.close();

		// PERSISTENCE LOGIC
		Connection con = null;
		PreparedStatement pstmt = null;
		String iQry = "insert into pentagon.login values(?,?,?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstmt = con.prepareStatement(iQry);
			// set values before executing the pstmt
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, email);
			pstmt.setString(4, username);
			pstmt.setString(5, password);

			// executing the query
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
