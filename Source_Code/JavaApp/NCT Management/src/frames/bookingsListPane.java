package frames;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ListSelectionModel;

import core.Booking;

public class bookingsListPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Booking> bookings;
	private DefaultListModel<Booking> listModel;
	private JList<Booking> bookingList;

	/**
	 * Create the panel.
	 */
	public bookingsListPane(ArrayList<Booking> bookings) {
		this.bookings = bookings;
		listModel = new DefaultListModel<>();
		Booking tempBook = null;
		
		//Insert Bookings into model
		if(bookings != null){
			for(int i = 0; i < bookings.size(); i++){
				tempBook = new Booking();
				tempBook = bookings.get(i);
				listModel.addElement(tempBook);
			}
		}
		
		setBackground(Color.GRAY);
		setLayout(null);
		
		//Set the list to custom listModel
		bookingList = new JList<>(listModel);
		bookingList.setBackground(Color.LIGHT_GRAY);
		//bookingList.setBounds(0, 0, 400, 175);
		JScrollPane scrollPane = new JScrollPane(bookingList);
		scrollPane.setSize(400, 175);
		add(scrollPane);
		
		//Apply custom row render to the list
		bookingList.setCellRenderer(new bookingRow());
		bookingList.setBackground(Color.LIGHT_GRAY);
		bookingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}
