/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mateusz Pietraszewski
 */
public class AdminMenu extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private BookingsTable pane;
	
	
	/*
	 * Create the frame.
	 * Constructor
	 */
	public AdminMenu() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton viewBookingsBTN = new JButton("");
		viewBookingsBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		
		pane = new BookingsTable();
		pane.setBounds(141, 11, 400, 200);
		getContentPane().add(pane);
		pane.setVisible(false);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(AdminMenu.class.getResource("/graphics/AdminMenu.jpg")));
		backgroundImage.setBounds(0, 0, 558, 407);
		getContentPane().add(backgroundImage);
		
		this.setSize(575,445);
		this.setTitle("Custom JList Example");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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
