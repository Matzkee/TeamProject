package com.example.hou.tools;

/**
 * Created by yuan on 2015/4/7.
 */
public class BookingResult {
    private String Book_Id;
    private String Car_Reg;
    private String Model;
    private String BDate;
    private String User_Id;
    private String Garage_Id;


    public String getBook_Id() {
        return Book_Id;
    }
    public void setBook_Id(String book_Id) {
        Book_Id = book_Id;
    }
    public String getCar_Reg() {
        return Car_Reg;
    }
    public void setCar_Reg(String car_Reg) {
        Car_Reg = car_Reg;
    }
    public String getBDate() {
        return BDate;
    }
    public void setBDate(String bDate) {
        BDate = bDate;
    }

    public String getModel() {
        return Model;
    }
    public void setModel(String model) {
        Model = model;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getGarage_Id() {
        return Garage_Id;
    }

    public void setGarage_Id(String garage_Id) {
        Garage_Id = garage_Id;
    }
}
