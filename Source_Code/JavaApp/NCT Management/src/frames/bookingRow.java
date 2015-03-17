package frames;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import core.Booking;
import java.awt.Dimension;
import java.awt.Rectangle;

public class bookingRow extends JLabel implements ListCellRenderer<Booking>{
	
	// Components declaration
	JLabel lblCarReg;
	JLabel lblDate;
	JLabel lblTime;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public bookingRow() {
		setBackground(Color.DARK_GRAY);
		setOpaque(true);
		setBounds(new Rectangle(0, 0, 400, 40));
		
		lblCarReg = new JLabel("Car Reg");
		lblCarReg.setForeground(Color.LIGHT_GRAY);
		lblCarReg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCarReg.setBounds(10, 11, 46, 14);
		add(lblCarReg);
		
		lblDate = new JLabel("Date");
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDate.setBounds(100, 11, 46, 14);
		add(lblDate);
		
		lblTime = new JLabel("Time");
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTime.setBounds(200, 11, 46, 14);
		add(lblTime);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(189, 5, 1, 30);
		add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.GRAY);
		separator.setBounds(88, 5, 1, 30);
		add(separator);

	}

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Booking> list, Booking booking, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		
		
		lblCarReg.setText(booking.getCarReg());
		lblDate.setText(booking.getDate());
		lblTime.setText(booking.getTime());
		
		if (isSelected){
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else{
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
		
		return this;
	}
}
