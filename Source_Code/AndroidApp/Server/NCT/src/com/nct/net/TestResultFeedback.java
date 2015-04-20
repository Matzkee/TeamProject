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
import com.nct.cla.TestFeedback;
import com.nct.dbclass.BookingHistory;
import com.nct.dbclass.BookingResult;
import com.nct.dbclass.TestingResultShow;

public class TestResultFeedback extends HttpServlet {
	
	String str ="";
	String isOk ="";
	
	String Owner_Id_his = "";
	String Car_Reg_his = "";
	String BDate_his = "";
	String BTime_his = "";
	
	int YYYY,MM,DD,hh,mm,ss;
	
	String NewMM = "";
	String NewDD = "";
	String Newhh = "";
	String Newmm = "";
	String Newss = "";  
	
	public String returnValue(String User_Id,String Car_Reg,int Alignment,int Suspension,int Brakes,int Exhaust_Emission,int Head_Lights,String Owner_Id){
		
		   java.sql.Connection conn = null;{

			    try {

			        String sql;
			        String url = "jdbc:mysql://83.212.127.2:3306/NCT?user=user&password=TeamGravity123&useUnicode=true&characterEncoding=UTF8";
 
			        //String url = "jdbc:mysql://localhost:3306/test_jasper?user=root&password=654321&useUnicode=true&characterEncoding=UTF8";
			        Class.forName("com.mysql.jdbc.Driver");
	

			        // 一个Connection代表一个数据库连接
			        conn = DriverManager.getConnection(url);
			        // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			        java.sql.Statement stmt = conn.createStatement();
			          
			        /**
			         * Calendar cal=Calendar.getInstance();    
			        YYYY=cal.get(Calendar.YEAR);    
			        MM=cal.get(Calendar.MONTH);    
			        DD=cal.get(Calendar.DATE);    
			        hh=cal.get(Calendar.HOUR_OF_DAY);    
			        mm=cal.get(Calendar.MINUTE);    
			        ss=cal.get(Calendar.SECOND);
			        
			        if(MM!=10 && sizeOfInt(MM)==1){
			        	NewMM = "0"+Integer.toString(MM);
			        }
			        
			        if(DD!=10 &&sizeOfInt(DD)==1){
			        	NewDD = "0"+Integer.toString(DD);
			        }
			        
			        if(hh!=10 &&sizeOfInt(hh)==1){
			        	Newhh = "0"+Integer.toString(hh);
			        }
			        
			        if(mm!=10 &&sizeOfInt(mm)==1){
			        	Newmm = "0"+Integer.toString(mm);
			        }
			        
			        if(ss!=10 &&sizeOfInt(ss)==1){
			        	Newss = "0"+Integer.toString(ss);
			        }
			        
			        NewMM = ( "" == NewMM?Integer.toString(MM):NewMM );
			        NewDD = ( "" == NewDD?Integer.toString(DD):NewDD );
			        Newhh = ( "" == Newhh?Integer.toString(hh):Newhh );
			        Newmm = ( "" == Newmm?Integer.toString(mm):Newmm );
			        Newss = ( "" == Newss?Integer.toString(ss):Newss );
			        
			        String Date = YYYY+"-"+NewMM+"-"+NewDD;
			        String Time = Newhh+":"+Newmm+":"+Newss;
			         */
			        
			        sql = "insert into TestResults set User_Id="+User_Id+",Car_Reg='"+Car_Reg+"',Alignment="+Alignment+",Suspension="+Suspension+",Brakes="+Brakes+
			        		",Exhaust_Emission="+Exhaust_Emission+",Head_Lights="+Head_Lights;
			    	//sql = "insert into Table_hou set name='tomcat1',boolen=0";
			        // sql = "SELECT booking.Book_Id,booking.Car_Reg,`owner`.`Name`,car.Make,car.Model,booking.BDate,booking.BTime,car.MadeDate FROM `booking`,car,`owner` where booking.Car_Reg ='cccc' AND booking.Car_Reg = car.Car_Reg  AND car.Owner_Id = `owner`.Owner_Id ORDER BY booking.BDate DESC";
			         //sql = "SELECT booking.Book_Id,booking.Car_Reg,car.Model,booking.BDate,booking.BTime FROM `booking`,car where booking.Garage_Id ='14' AND booking.Car_Reg = car.Car_Reg  ORDER BY booking.BDate DESC";
			        System.out.println("sql===="+sql);
			        
			        int result = stmt.executeUpdate(sql);			
					//处理结果
					if(result>0){
						System.out.println("insert TestResults succeed");
						/**
						 * String sql1 = "SELECT Booking.Car_Reg,`Owner`.Owner_Id,Booking.BDate,Booking.BTime FROM `Booking`,Car,`Owner` where Booking.Car_Reg ='"+Car_Reg+"' AND Booking.Car_Reg = Car.Car_Reg  AND Car.Owner_Id = `Owner`.Owner_Id ORDER BY Booking.BDate DESC";
						ResultSet result1 = stmt.executeQuery(sql1);
						while(result1.next())
				        {
							System.out.println("222222222222222222");
							BookingHistory bookingHistory = new BookingHistory();
							Car_Reg_his  = (result1.getString(1));
							Owner_Id_his = (result1.getString(2));
							BDate_his = result1.getString(3);
				            BTime_his = result1.getString(4);
				            
				        }
						
						 */
						
						//String sql2 = "insert into BookingHistory set Owner_Id='"+Owner_Id_his+"',Car_Reg='"+Car_Reg_his+"',BTime='"+BTime_his+"'";
						//String sql2 = "insert into BookingHistory (Owner_Id,Car_Reg,BTime) values ('54','cccc','2009-07-08 23:53:17')";
						//String sql2 = "insert into BookingHistory (Owner_Id,Car_Reg,BDate,BTime) values ('"+Owner_Id_his+"','"+Car_Reg_his+"','"+BDate_his+"','"+BTime_his+"')";

					//	System.out.println("sql2===="+sql2);
					//	int resultBookingHistory = stmt.executeUpdate(sql2);
							//if(resultBookingHistory>0){
							//	System.out.println("insert BookingHistory succeed");
								String sql3 = "DELETE FROM Booking where Car_Reg = '"+Car_Reg+"'";
								System.out.println("sql3==="+sql3);
								int resultBookingDelete = stmt.executeUpdate(sql3);
									if(resultBookingDelete>0){
										System.out.println("DELETE booking succeed");
										isOk = "succeed";
									}
						//	}
						
					}else{
						System.out.println("date handle error");
						isOk = "failed";
					}
					Gson gson = new Gson(); 
					List<TestFeedback> testFeedbackList = new ArrayList<TestFeedback>();
					TestFeedback testFeedBack = new TestFeedback();
					testFeedBack.setIsOk(isOk);
					testFeedbackList.add(testFeedBack);
					str = gson.toJson(testFeedbackList);
					
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
		   System.out.println("Check Result--TestResultFeedback----》"+str);
		   return str;
		   
	   }
	
	
	
	final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,    
        99999999, 999999999, Integer.MAX_VALUE }; 

	static int sizeOfInt(int x) {    
    for (int i = 0;; i++)    
        if (x <= sizeTable[i])    
            return i + 1;    
	}
	/**
	 * Constructor of the object.
	 */
	public TestResultFeedback() {
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
		  * String Car_Reg = request.getParameter("Car_Reg");
    	String Alignment = request.getParameter("alignment");
    	String Suspension = request.getParameter("suspension");
    	String Brakes = request.getParameter("brakes");
    	String Exhaust_Emission = request.getParameter("exhaust_Emission");
    	String Head_Lights = request.getParameter("head_Lights");
    	
		  */
		String Car_Reg = request.getParameter("Car_Reg");
		String User_Id = request.getParameter("User_Id");
		String Owner_Id = request.getParameter("Owner_Id");
    	int Alignment = request.getParameter("alignment") == "PASS"?1:0;
    	int Suspension = request.getParameter("suspension") == "PASS"?1:0;
    	int Brakes = request.getParameter("brakes") == "PASS"?1:0;
    	int Exhaust_Emission = request.getParameter("exhaust_Emission") == "PASS"?1:0;
    	int Head_Lights = request.getParameter("head_Lights") == "PASS"?1:0; 
    	//String result ="输出TestResultFeedback====Car_Reg="+Car_Reg;
    	//System.out.println(userName+"-"+password);
    	//DataOutputStream dos = new DataOutputStream(response.getOudddtputStream());
    	response.setContentType("application/json");
    	PrintWriter writer = response.getWriter();  
    	String result = returnValue(User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,Owner_Id);
    	//String result = "输出TestResultFeedback====开始---》Car_Reg=="+Car_Reg+" Alignment=="+Alignment+" Suspension=="+Suspension+" Brakes=="+Brakes
    	//		+" Exhaust_Emission=="+Exhaust_Emission+" Head_Lights=="+Head_Lights+" Owner_Id=="+Owner_Id;
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
