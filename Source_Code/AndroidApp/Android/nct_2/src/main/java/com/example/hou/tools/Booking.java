package com.example.hou.tools;

public class Booking {

    private String Book_Id;
    private String Car_Reg;
    private String BDate;
    private String BTime;
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
    public String getBTime() {
        return BTime;
    }
    public void setBTime(String bTime) {
        BTime = bTime;
    }
    public String getBDate() {
        return BDate;
    }
    public void setBDate(String bDate) {
        BDate = bDate;
    }
    public String getGarage_Id() {
        return Garage_Id;
    }
    public void setGarage_Id(String garage_Id) {
        Garage_Id = garage_Id;
    }

}