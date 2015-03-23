package frames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import core.Booking;

public class TableRow extends JPanel{

	private static final long serialVersionUID = 1L;
	
	// Components declaration
	private JLabel lblCarReg;
	private JLabel lblDate;
	private JLabel lblTime;
	
	/**
	 * Create the panel.
	 * Constructor
	 */
	public TableRow() {
		setForeground(Color.BLACK);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		lblCarReg = new JLabel("Car Registration");
		lblCarReg.setForeground(Color.LIGHT_GRAY);
		lblCarReg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCarReg.setBounds(10, 11, 119, 14);
		add(lblCarReg);
		
		lblDate = new JLabel("Date");
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDate.setBounds(150, 11, 78, 14);
		add(lblDate);
		
		lblTime = new JLabel("Time");
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTime.setBounds(250, 11, 78, 14);
		add(lblTime);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(239, 5, 1, 30);
		add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.GRAY);
		separator.setBounds(139, 5, 1, 30);
		add(separator);
	}
	public void setRowLabels(Booking book, boolean isSelected, JTable table){
		lblCarReg.setText(book.getCarReg());
		lblDate.setText(book.getDate());
		lblTime.setText(book.getTime());
		
		if(isSelected){
			setBackground(table.getSelectionForeground());
		}
		else{
			setBackground(table.getBackground());
		}
	}

}
