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
        JLabel background = new JLabel();
        
        for(int i = 0; i < 4; i++){
            book = new Booking("date: "+i,"time: "+i,"carReg"+i);
            myBookingList.add(book);
            myListModel.addElement(book);
        }
        
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        //create the list
        JList<Booking> bookingList = new JList<>(myListModel);
        getContentPane().add(new JScrollPane(bookingList),new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 400, 300));
        bookingList.setCellRenderer(new Renderer());
        
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/custom/list/BookingsBG.jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        
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
