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
import com.nct.cla.Person;

public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}
	
	String name ="";
	   String password ="";
	   String str ="";
	public String returnValue(String username,String password){
		
		   java.sql.Connection conn = null;{

			    try {

			        String sql;
			        String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";
			    	// String url = "jdbc:mysql://localhost:3306/test_jasper?user=root&password=654321&useUnicode=true&characterEncoding=UTF8";
			    	// String url = "jdbc:mysql://localhost:3306/test_jasper?user=root&password=654321&useUnicode=true&characterEncoding=UTF8";
			    	// String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";
			        Class.forName("com.mysql.jdbc.Driver");// 
			        // or:
			        // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			        // or：
			        // new com.mysql.jdbc.Driver();

			        conn = DriverManager.getConnection(url);
			        java.sql.Statement stmt = conn.createStatement();
			        System.out.println("loading MySQL succeed...");
			        //sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
			        sql = " select * from Users where LoginName = '"+username+"' and Password = '"+password+"'";
			        System.out.println("sql ="+sql);
			        //sql = " select * from usertype ";
			       // System.out.println("sql===="+sql);
			        ResultSetMetaData m =null;
			        ResultSet result = stmt.executeQuery(sql);
			        m=(ResultSetMetaData) result.getMetaData();

			        int columns=m.getColumnCount();

			        Gson gson = new Gson();  
		        	List<Person> persons = new ArrayList<Person>(); 
			        while(result.next())
			        {
			        	/* 		        	 		        	
			            for(int i=1;i<=columns;i++)
			            {		            	
			            	System.out.println("进入运行第 "+i+" 次");		               
			            }
			            */
			           // System.out.println(res2);
			            Person p = new Person();
			             p.setUser_Id(result.getString(1));
			             p.setType_Id(result.getString(2));
			             p.setName(result.getString(3));
			             p.setLoginName(result.getString(4));
		        	     p.setPassword(result.getString(5));
		        	     p.setGarage_Id(result.getString(6));
		        	     persons.add(p);
			             str = gson.toJson(persons);
			           // System.out.println("JSON=== "+str);
			        }
			        
			        
			    } catch (SQLException e1) {
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
	    	
		   System.out.println("Check Result--Login----》"+str);
		   //System.out.println("发送前查看str======="+str);
		   return str;
		   		   
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

		String userName = request.getParameter("userName");
    	String password = request.getParameter("password");
    	//System.out.println(userName+"-"+password);
    	//DataOutputStream dos = new DataOutputStream(response.getOutputStream());
    	response.setContentType("application/json");
    	PrintWriter writer = response.getWriter();  
        
    	if (userName != null && password != null) {
    		if (userName.length() > 3 && password.length() > 3) {
    			String result = returnValue(userName,password);
    			//System.out.println("查看接受结果------》"+result);
    			writer.write(result);
    			//dos.writeChars(result);
    		}else{
    			//dos.writeInt(0);
    		}
    	} else {
    		//dos.writeInt(0);
    	}
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
