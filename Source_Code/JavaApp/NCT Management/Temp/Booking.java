/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom.list;

/**
 *
 * @author Mati
 */
public class Booking {
    
    public Booking(String date, String time, String carReg){
        this.date = date;
        this.time = time;
        this.carReg = carReg;
    }
    String date;
    String time;
    String carReg;

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCarReg(String carReg) {
        this.carReg = carReg;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCarReg() {
        return carReg;
    }
    
}
