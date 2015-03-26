package frames;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookingCreationPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCarReg;
	private JTextField txtDate;
	private JTextField txtTime;

	/**
	 * Create the panel.
	 */
	public BookingCreationPane() {
		setBackground(Color.DARK_GRAY);

		// Panel Size
		setSize(400,380);
		setLayout(null);
		
		JLabel lblCreateBooking = new JLabel("Create Booking");
		lblCreateBooking.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCreateBooking.setForeground(Color.LIGHT_GRAY);
		lblCreateBooking.setBounds(10, 11, 150, 30);
		add(lblCreateBooking);
		
		JLabel lblCarReg = new JLabel("Car Registration:");
		lblCarReg.setForeground(Color.LIGHT_GRAY);
		lblCarReg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCarReg.setBounds(10, 52, 100, 14);
		add(lblCarReg);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDate.setBounds(10, 77, 100, 14);
		add(lblDate);
		
		JLabel lblTime = new JLabel("Time: ");
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTime.setBounds(10, 102, 100, 14);
		add(lblTime);
		
		txtCarReg = new JTextField();
		txtCarReg.setBounds(120, 50, 86, 20);
		add(txtCarReg);
		txtCarReg.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(120, 75, 86, 20);
		add(txtDate);
		
		txtTime = new JTextField();
		txtTime.setColumns(10);
		txtTime.setBounds(120, 100, 86, 20);
		add(txtTime);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDate.setText("");
			}
		});
		btnSubmit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSubmit.setBounds(120, 131, 89, 23);
		add(btnSubmit);
		
		JLabel lblSuccess = new JLabel("");
		lblSuccess.setForeground(Color.LIGHT_GRAY);
		lblSuccess.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSuccess.setBounds(120, 165, 86, 14);
		add(lblSuccess);
		
	}

}
