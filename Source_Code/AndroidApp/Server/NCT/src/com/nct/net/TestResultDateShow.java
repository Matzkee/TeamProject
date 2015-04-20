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
import com.nct.dbclass.TestResultDate;
import com.nct.dbclass.ResultDateInfo;

public class TestResultDateShow extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	
	String str ="";
	String Car_Reg ="";
			
	public String returnValue(String Car_Reg,String Owner_Id){
		
		   java.sql.Connection conn = null;{

			    try {

			    	//System.out.println("测试--》 username="+username+" password="+password);
			        String sqlOwner;
			        String sqlCar;
			        String sqlTestresults; 
			        String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";

			        // String url = "jdbc:mysql://localhost:3306/test_jasper?user=root&password=654321&useUnicode=true&characterEncoding=UTF8";
			    	// String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";
			        Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			        // or:
			        // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			        // or：
			        // new com.mysql.jdbc.Driver();

			       
			        
			        conn = DriverManager.getConnection(url);
			        java.sql.Statement stmt = conn.createStatement();
			        java.sql.Statement stmt1 = conn.createStatement();
			        java.sql.Statement stmt2 = conn.createStatement();
			        sqlOwner = "SELECT `Name`,Address,Email FROM `Owner` where Owner_Id = '"+Owner_Id+"'";
			        sqlCar = "SELECT Make,Model,MadeDate from Car where Car_Reg = '"+Car_Reg+"'";
			        sqlTestresults = "SELECT Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate from TestResults where Car_Reg = '"+Car_Reg+"'";

			        //sql = "SELECT * FROM testresults WHERE TestDate1 between '2015-04-10%' and '2015-04-13%' ORDER BY TestDate1 ASC";
			    	//sql = " SELECT * FROM testresults WHERE TestDate <= '"+FinishDate+"'  AND TestDate = > '"+StartData+"'";
			        //sql = "SELECT booking.Book_Id,booking.Car_Reg,`owner`.`Name`,car.Make,car.Model,booking.BDate,booking.BTime,car.MadeDate FROM `booking`,car,`owner` where booking.Car_Reg ='"+Car_Reg+"' AND booking.Car_Reg = car.Car_Reg  AND car.Owner_Id = `owner`.Owner_Id ORDER BY booking.BDate DESC";
			        //sql = "SELECT booking.Book_Id,booking.Car_Reg,car.Model,booking.BDate,booking.BTime FROM `booking`,car where booking.Garage_Id ='14' AND booking.Car_Reg = car.Car_Reg  ORDER BY booking.BDate DESC";			        
			        ResultSetMetaData owner =null;
			        ResultSetMetaData car =null;
			        ResultSetMetaData testresults =null;
			        
			        ResultSet resultOwner = stmt.executeQuery(sqlOwner);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			        ResultSet resultCar = stmt1.executeQuery(sqlCar);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			        ResultSet resultTestresults = stmt2.executeQuery(sqlTestresults);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			        
			        owner=(ResultSetMetaData) resultOwner.getMetaData();
			        car=(ResultSetMetaData) resultCar.getMetaData();
			        testresults=(ResultSetMetaData) resultTestresults.getMetaData();


			        Gson gson = new Gson();  
		        	List<ResultDateInfo> resultDateShowInfoList = new ArrayList<ResultDateInfo>(); 
		        	ResultDateInfo resultDateInfo = new ResultDateInfo();
		        	while(resultOwner.next())
			        {			        	 		        	 		        				        	
			        	resultDateInfo.setName(resultOwner.getString(1));
			        	resultDateInfo.setAddress(resultOwner.getString(2));			            
			        	resultDateInfo.setEmail(resultOwner.getString(3));	            
			        }
		        	
		        	while(resultCar.next())
			        {			         		        	 		        				        	
			        	resultDateInfo.setMake(resultCar.getString(1));
			        	resultDateInfo.setModel(resultCar.getString(2));			            
			        	resultDateInfo.setMadeDate(resultCar.getString(3));	            
			        }
		        	
		        	while(resultTestresults.next())
			        {			         		        	 		        				        	
			        	resultDateInfo.setAlignment(resultTestresults.getString(1));
			        	resultDateInfo.setSuspension(resultTestresults.getString(2));			            
			        	resultDateInfo.setBrakes(resultTestresults.getString(3));
			        	resultDateInfo.setExhaust_Emission(resultTestresults.getString(4));
			        	resultDateInfo.setHead_Lights(resultTestresults.getString(5));
			        	resultDateInfo.setTestDate(resultTestresults.getString(6));
			        }
		        	resultDateShowInfoList.add(resultDateInfo);
		        	str= gson.toJson(resultDateShowInfoList);
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
		   System.out.println("Check Result--TestResultDateShow----》"+str);
		   return str;
		   
	   }
	
	public TestResultDateShow() {
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
		String Car_Reg = request.getParameter("Car_Reg");
		String Owner_Id = request.getParameter("Owner_Id");
		String result = returnValue(Car_Reg,Owner_Id);
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter(); 
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
