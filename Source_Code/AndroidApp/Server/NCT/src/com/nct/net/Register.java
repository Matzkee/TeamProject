package com.nct.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.ResultSetMetaData;
import com.nct.cla.Garage;
import com.nct.cla.TestFeedback;
import com.nct.dbclass.BookingHistory;
import com.nct.dbclass.ResultDateInfo;

public class Register extends HttpServlet {

	
	
	String str ="";
	String isOk ="";
	
	String Owner_Id_his = "";
	
	
	 
	
	public String returnValue(String Type,String Name,String LoginName,String PasseWord,String GarageId,String UserType_id){
		
		   java.sql.Connection conn = null;{
			   str="";
			    try {

			        String sql;
			        String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";

			    	// String url = "jdbc:mysql://localhost:3306/test_jasper?user=root&password=654321&useUnicode=true&characterEncoding=UTF8";
			        Class.forName("com.mysql.jdbc.Driver");
			        conn = DriverManager.getConnection(url);
			        java.sql.Statement stmt = conn.createStatement();
			        java.sql.Statement stmt1 = conn.createStatement();
			        Gson gson = new Gson();
			        ResultSetMetaData m =null;
			        if("Garage".equals(Type)){
			        	List<Garage> resultGarageInfoList = new ArrayList<Garage>();
			        	String sqlGarage = " SELECT * FROM `Garage` ORDER BY Garage_Id ASC ";
			        	ResultSet resultGarage = stmt.executeQuery(sqlGarage);			        	
			        	while(resultGarage.next())
				        {	
			        		
			        		Garage garage = new Garage();
			        		garage.setGarageId(resultGarage.getString(1));
			        		garage.setAddress(resultGarage.getString(2));
			        		
			        		resultGarageInfoList.add(garage);				        		            
				        }
			        	str= gson.toJson(resultGarageInfoList);
			        }else{
			        	sql = "insert into Users set Type_id='"+UserType_id+"',Name='"+Name+"',LoginName='"+LoginName+"',Password='"+PasseWord+"',Garage_Id='"+GarageId+"'";
			        	int result = stmt1.executeUpdate(sql);
			        	if(result>0){
			        		str ="[{State:succeed}]";
			        	}
			        }
			        
					
			    } catch (SQLException e1) {
			        System.out.println("MySQLErro");
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
		   System.out.println("Check Result--Register----¡·"+str);
		   return str;
		   
	   }
	
	/**
	 * Constructor of the object.
	 */
	public Register() {
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
		/** 
		 * Get garage info
		 */
		String Type = request.getParameter("Type");
		System.out.println("Type ======"+Type);		
		/**
		 * Get register info
		 */
		String Name = request.getParameter("Name")== null?"":request.getParameter("Name");
		String LoginName = request.getParameter("LoginName")== null?"":request.getParameter("LoginName");
		String PasseWord = request.getParameter("PasseWord")== null?"":request.getParameter("PasseWord");
		String GarageId = request.getParameter("GarageId")== null?"":request.getParameter("GarageId");
		String UserType_id = request.getParameter("TypeId")== null?"":request.getParameter("TypeId");
		
    	response.setContentType("application/json");
    	PrintWriter writer = response.getWriter();  
    	String result = returnValue(Type,Name,LoginName,PasseWord,GarageId,UserType_id);
    	//String result = "Name="+Name+" LoginName="+LoginName+" PasseWord="+PasseWord+" GarageId="+GarageId;
    	System.out.println(result);
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
