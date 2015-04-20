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
import com.nct.dbclass.TestResultDate;
import com.nct.dbclass.TestingResultShow;

public class TestResultDateList extends HttpServlet {
	
	
	String str ="";
	String Car_Reg ="";
			
	public String returnValue(String Car_Reg,String User_Id,String StartData,String FinishDate){
		
		   java.sql.Connection conn = null;{

			    try {
			        String sql;
			        String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";
 
			       // String url = "jdbc:mysql://localhost:3306/test_jasper?user=root&password=654321&useUnicode=true&characterEncoding=UTF8";
			    	// String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";
			        Class.forName("com.mysql.jdbc.Driver");
			        // or:
			        // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			        // or：
			        // new com.mysql.jdbc.Driver();

			        System.out.println("loading MySQL succed...");
			        
			        conn = DriverManager.getConnection(url);
			        java.sql.Statement stmt = conn.createStatement();
			        
			        sql = "SELECT TestResults.Car_Reg,Car.Owner_Id,Car.Model,TestResults.TestDate FROM TestResults,Car WHERE (TestResults.TestDate between '"+StartData+"%' and '"+FinishDate+"%') AND TestResults.Car_Reg = Car.Car_Reg  ORDER BY TestResults.TestDate ASC";

			        //sql = "SELECT * FROM TestResults WHERE TestDate1 between '2015-04-10%' and '2015-04-13%' ORDER BY TestDate1 ASC";
			    	//sql = " SELECT * FROM TestResults WHERE TestDate <= '"+FinishDate+"'  AND TestDate = > '"+StartData+"'";
			        //sql = "SELECT booking.Book_Id,booking.Car_Reg,`owner`.`Name`,car.Make,car.Model,booking.BDate,booking.BTime,car.MadeDate FROM `booking`,car,`owner` where booking.Car_Reg ='"+Car_Reg+"' AND booking.Car_Reg = car.Car_Reg  AND car.Owner_Id = `owner`.Owner_Id ORDER BY booking.BDate DESC";
			        //sql = "SELECT booking.Book_Id,booking.Car_Reg,car.Model,booking.BDate,booking.BTime FROM `booking`,car where booking.Garage_Id ='14' AND booking.Car_Reg = car.Car_Reg  ORDER BY booking.BDate DESC";
			        System.out.println("sql===="+sql);
			        ResultSetMetaData m =null;
			        ResultSet result = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			        m=(ResultSetMetaData) result.getMetaData();

			        int columns=m.getColumnCount();

			        Gson gson = new Gson();  
		        	List<TestResultDate> testingResultShowList = new ArrayList<TestResultDate>(); 
			        while(result.next())
			        {
			        	/**
			        	 * for(int i=1;i<=columns;i++)
			            {		            	
			            	System.out.println("进入运行第 "+i+" 次");		               
			            }
			        	 */
			        	 		        	 		        	
			            
			           
			            TestResultDate testResultDate = new TestResultDate();
			            testResultDate.setCar_Reg(result.getString(1));
			            testResultDate.setOwner_Id(result.getString(2));
			            testResultDate.setModle(result.getString(3));
			            testResultDate.setTestDate(result.getString(4).substring(0, 10));
			            
			            testingResultShowList.add(testResultDate);
			            str= gson.toJson(testingResultShowList);
			               System.out.println("JSON=== "+str);
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
		   System.out.println("Check Result--TestResultDateList----》"+str);
		   return str;
		   
	   }
		

	/**
	 * Constructor of the object.
	 */
	public TestResultDateList() {
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
		//String StartDate = request.getParameter("StartDate") == null?"":request.getParameter("StartDate");
		String StartData = request.getParameter("StartData")== null?"":request.getParameter("StartData");
		String FinishDate = request.getParameter("FinishDate") == null?"":request.getParameter("FinishDate");
		response.setContentType("application/json");
    	PrintWriter writer = response.getWriter(); 
    	//String result = "StartData===="+StartData+"  FinishDate==="+FinishDate;
		String result = returnValue(Garage_Id,User_Id,StartData,FinishDate);
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
