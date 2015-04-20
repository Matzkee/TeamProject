package com.nct.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.ResultSetMetaData;
import com.nct.cla.Booking;
import com.nct.cla.Person;
import com.nct.dbclass.BookingResult;

public class BookingList extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	String str ="";
	public String returnValue(String Garage_Id,String User_Id){
		
		   java.sql.Connection conn = null;{

			    try {

			        String sql;
			        String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8"; 
			        //String url = "jdbc:mysql://localhost:3306/test_jasper?user=root&password=654321&useUnicode=true&characterEncoding=UTF8";
			    	// String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";
			        Class.forName("com.mysql.jdbc.Driver");

			        conn = DriverManager.getConnection(url);
			        java.sql.Statement stmt = conn.createStatement();
			        //sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
			        //sql = " select * from Booking where Garage_Id = '"+Garage_Id+"' order by BDate DESC";
			        sql = "SELECT Booking.Book_Id,Booking.Car_Reg,Car.Model,Booking.BDate,Booking.BTime FROM `Booking`,Car where Booking.Garage_Id ='"+Garage_Id+"' AND Booking.Car_Reg = Car.Car_Reg  ORDER BY Booking.BDate DESC";
			        //sql = "SELECT booking.Book_Id,booking.Car_Reg,car.Model,booking.BDate,booking.BTime FROM `booking`,car where booking.Garage_Id ='14' AND booking.Car_Reg = car.Car_Reg  ORDER BY booking.BDate DESC";
			      //  System.out.println("sql===="+sql);
			        ResultSetMetaData m =null;
			        ResultSet result = stmt.executeQuery(sql);// 
			        m=(ResultSetMetaData) result.getMetaData();

			        int columns=m.getColumnCount();

			        Gson gson = new Gson();  
		        	List<BookingResult> bookingResultList = new ArrayList<BookingResult>(); 
			        while(result.next())
			        {

			            BookingResult bookingResult = new BookingResult();
			            bookingResult.setBook_Id(result.getString(1));
			            bookingResult.setCar_Reg(result.getString(2));
			            bookingResult.setModel(result.getString(3));
			            String Data = result.getString(4)+" "+result.getString(5).substring(0,5);
			            bookingResult.setBDate(Data);
			            bookingResult.setUser_Id(User_Id);
			            bookingResultList.add(bookingResult);
			             str = gson.toJson(bookingResultList);
			           // System.out.println("JSON=== "+str);
			        }
			        
			        
			    } catch (SQLException e1) {
			        System.out.println("MySQLError");
			        e1.printStackTrace();
			    } catch (Exception e2) {
			        e2.printStackTrace();
			    } finally {
			        try {
			            conn.close();
			        } catch (java.sql.SQLException e) {
			            e.printStackTrace();
			        }}
			    }
		   System.out.println("Check Result--BookingList----¡·"+str);
		   return str;
		   
	   }
	
	public BookingList() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Garage_Id = request.getParameter("Garage_Id");
		String User_Id = request.getParameter("User_Id");
		response.setContentType("application/json");
    	PrintWriter writer = response.getWriter();  
    	String result = returnValue(Garage_Id,User_Id);
    	System.out.println("Check Result------¡·"+result);
		writer.write(result);
		writer.flush();
    	writer.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
