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
import com.nct.dbclass.BookingResult;
import com.nct.dbclass.TestingResultShow;

public class TestResultShow extends HttpServlet {
	
	String str ="";
	public String returnValue(String Car_Reg){
		
		   java.sql.Connection conn = null;{

			    try {
			        // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
			        // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
			    	//System.out.println("测试--》 username="+username+" password="+password);
			        String sql;
			        String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";

			        // String url = "jdbc:mysql://localhost:3306/test_jasper?user=root&password=654321&useUnicode=true&characterEncoding=UTF8";
			    	// String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";
			        Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			        // or:
			        // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			        // or：
			        // new com.mysql.jdbc.Driver();

			
			        // 一个Connection代表一个数据库连接
			        conn = DriverManager.getConnection(url);
			        // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			        java.sql.Statement stmt = conn.createStatement();
			        //sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
			        //sql = " select * from Booking where Garage_Id = '"+Garage_Id+"' order by BDate DESC";

			    	
			        sql = "SELECT Booking.Book_Id,Booking.Car_Reg,`Owner`.`Name`,Car.Make,Car.Model,Booking.BDate,Booking.BTime,Car.MadeDate,`Owner`.Owner_Id FROM `Booking`,Car,`Owner` where Booking.Car_Reg ='"+Car_Reg+"' AND Booking.Car_Reg = Car.Car_Reg  AND Car.Owner_Id = `Owner`.Owner_Id ORDER BY Booking.BDate DESC";
			        //sql = "SELECT booking.Book_Id,booking.Car_Reg,car.Model,booking.BDate,booking.BTime FROM `booking`,car where booking.Garage_Id ='14' AND booking.Car_Reg = car.Car_Reg  ORDER BY booking.BDate DESC";
			        System.out.println("sql===="+sql);
			        ResultSetMetaData m =null;
			        ResultSet result = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			        m=(ResultSetMetaData) result.getMetaData();

			        int columns=m.getColumnCount();

			        Gson gson = new Gson();  
		        	List<TestingResultShow> testingResultShowList = new ArrayList<TestingResultShow>(); 
			        while(result.next())
			        {
			        	/**
			        	 * 
			        	 *  	for(int i=1;i<=columns;i++)
			            {		            	
			            	System.out.println("进入运行第 "+i+" 次");		               
			            }	        	 		        	
			        	 */
			            
			           // System.out.println(res2);
			            TestingResultShow testingResultShow = new TestingResultShow();
			            testingResultShow.setBook_Id(result.getString(1));
			            testingResultShow.setCar_Reg(result.getString(2));
			            testingResultShow.setName(result.getString(3));
			            testingResultShow.setMake(result.getString(4));
			            testingResultShow.setModel(result.getString(5));
			            String Data = result.getString(6)+" "+result.getString(7).substring(0,5);
			            testingResultShow.setBookingTime(Data);
			            testingResultShow.setMakeDate(result.getString(8));
			            testingResultShow.setOwner_Id(result.getString(9));
			            
			            testingResultShowList.add(testingResultShow);
			             str = gson.toJson(testingResultShowList);
			            System.out.println("JSON=== "+str);
			        }
			    } catch (SQLException e1) {
			        System.out.println("MySQL Error");
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
		   System.out.println("Check Result--TestResultShow----》"+str);
		   return str;
		   
	   }
	/**
	 * Constructor of the object.
	 */
	public TestResultShow() {
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
		response.setContentType("application/json");
    	PrintWriter writer = response.getWriter();  
    	String result = returnValue(Car_Reg);
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
