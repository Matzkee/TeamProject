/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import core.Booking;
import core.Client;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Mateusz Pietraszewski
 */
public class AdminMenu extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private Client testClient;
	
	private ArrayList<Booking> bookings = null;
	private DefaultListModel<Booking> listModel;
	private JList<Booking> bookingList;
	private bookingsListPane pane;
	
	public AdminMenu() {
		getContentPane().setLayout(null);
		testClient = new Client();
		
		JButton viewBookingsBTN = new JButton("");
		viewBookingsBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				testClient.setUsername("admin");
				testClient.setPassword("admin");
				testClient.logIn();
				testClient.viewBookings();
				pane = new bookingsListPane(testClient.getBookings());
				pane.setVisible(true);
			}
		});
		viewBookingsBTN.setIcon(new ImageIcon(AdminMenu.class.getResource("/graphics/ViewBookingsButton.jpg")));
		viewBookingsBTN.setBounds(0, 145, 120, 40);
		getContentPane().add(viewBookingsBTN);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(AdminMenu.class.getResource("/graphics/DeleteBookingButton.jpg")));
		button.setBounds(0, 186, 120, 40);
		getContentPane().add(button);
		
		testClient.viewBookings();
		pane = new bookingsListPane(testClient.getBookings());
		//pane.setBackground(Color.GRAY);
		pane.setBounds(141, 11, 400, 175);
		getContentPane().add(pane);
		pane.setVisible(true);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(AdminMenu.class.getResource("/graphics/AdminMenu.jpg")));
		backgroundImage.setBounds(0, 0, 558, 407);
		getContentPane().add(backgroundImage);
		
		this.setSize(575,445);
		this.setTitle("Custom JList Example");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
	
	public void addBookingsPanel(ArrayList<Booking> bookings) {
		this.bookings = bookings;
		listModel = new DefaultListModel<>();
		Booking tempBook = null;
		
		//Insert Bookings into model
		for(int i = 0; i > bookings.size(); i++){
			tempBook = bookings.get(i);
			listModel.addElement(tempBook);
		}
		
		setBackground(Color.GRAY);
		getContentPane().setLayout(null);
		
		//Set the list to custom listModel
		bookingList = new JList<>(listModel);
		//Apply custom row render to the list
		bookingList.setCellRenderer(new bookingRow());
		bookingList.setBackground(Color.GRAY);
		bookingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookingList.setBounds(141, 11, 400, 175);
		getContentPane().add(bookingList);
		bookingList.setVisible(true);
		
	}
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {

	            @Override
	            public void run() {
	                new AdminMenu();
	            }
	        });
	    }
}
