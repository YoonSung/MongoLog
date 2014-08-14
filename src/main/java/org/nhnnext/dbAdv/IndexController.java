package org.nhnnext.dbAdv;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class IndexController extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Request In IndexController");
		
		String nextJSP = "/index.jsp";
		request.setAttribute("name", "Parameter!!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		
		dispatcher.forward(request,response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
