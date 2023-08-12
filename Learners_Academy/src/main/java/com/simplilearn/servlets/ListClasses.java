package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.simplilearn.entities.Classes;
import com.simplilearn.hibernate.FactoryProvider;

/**
 * Servlet implementation class ListClasses
 */
public class ListClasses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListClasses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			Session session= new FactoryProvider().getFactory().openSession();
			
			List<Classes> list = session.createQuery("from Classes").list();
			
			out.println("<h2>Class List");
			
			out.println("<style> table,td,th{"
					+ "border:2px solid red; "
					+ "padding:2px;); "
					+ "</style>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>Class Id</th>");
			out.println("<th>Class Name</th>");
			out.println("<th>Section</th>");
			out.println("<th>Room No</th>");
			out.println("<tr>");
			
			for(Classes classdet : list) {
				out.println("<tr>");
				out.println("<td>"+ classdet.getId() +"</td>");
				out.println("<td>"+ classdet.getClassName() +"</td>");
				out.println("<td>"+ classdet.getClassSection()+"</td>");
				out.println("<td>"+ classdet.getClassRoomNo() +"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<a href='admin-page.html'>Back To MainMenu</a>");
			session.close();
		} catch (Exception e) {
			out.println("<h2 style='color:red'>Operation Failed<h2>");
			out.println("<a href='admin-page.html'>Back To MainMenu</a>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
