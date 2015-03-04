/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom.list;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mati
 */
public class CustomList extends JFrame{

    public CustomList(){
        ArrayList<Booking> myBookingList = new ArrayList<>();
        Booking book = null;
        DefaultListModel<Booking> myListModel = new DefaultListModel<>();
        
        JLabel image = new JLabel();
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/custom/list/BookingsBG.jpg")));
        
        setContentPane(image);
        
        for(int i = 0; i < 4; i++){
            book = new Booking("date: "+i,"time: "+i,"carReg"+i);
            myBookingList.add(book);
            myListModel.addElement(book);
        }
        
        //create the list
        JList<Booking> bookingList = new JList<>(myListModel);
        add(new JScrollPane(bookingList));
        bookingList.setCellRenderer(new Renderer());
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Custom JList Example");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        
        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new CustomList();
            }
        });
    }
    
}
