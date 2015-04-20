package com.nct.cla;

import java.util.List;

public class Person {  
    public static final int SEX_MAN = 1;  
    public static final int SEX_FEMALE = 2;  
    public static final int SEX_UNKNOWN = 3;  
    
    private String User_Id;
    private String Type_Id;
    private String Name;
    private String LoginName;
    private String Password;
    private String Garage_Id;
	public String getUser_Id() {
		return User_Id;
	}
	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}
	public String getType_Id() {
		return Type_Id;
	}
	public void setType_Id(String type_Id) {
		Type_Id = type_Id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public String getGarage_Id() {
		return Garage_Id;
	}
	public void setGarage_Id(String garage_Id) {
		Garage_Id = garage_Id;
	}

    
   
}  
