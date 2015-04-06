package frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

public class AdminMenu_v2 extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable declaration.
	 */
	private JPanel contentPane;
	private JLabel lblHome;
	private JLabel lblBookings;
	private JLabel lblLogOut;
	private final Color HIGHLIGHTCOLOR = new Color(214,137,4);
	private final Color AMBIENTCOLOR = Color.GRAY;
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 20);
	private BookingsTable bookingPane;


	/**
	 * Create the frame.
	 */
	public AdminMenu_v2() {
		// Frame Settings
		setTitle("NCT Management");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Content Panel
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(800, 550));
		contentPane.setLayout(null);
		
		lblHome = new JLabel("HOME");
		lblHome.setFont(TEXTFONT);
		lblHome.setForeground(AMBIENTCOLOR);
		lblHome.setBounds(30, 0, 100, 30);
		lblHome.addMouseListener(this);
		contentPane.add(lblHome);
		
		lblBookings = new JLabel("BOOKINGS");
		lblBookings.setForeground(AMBIENTCOLOR);
		lblBookings.setFont(TEXTFONT);
		lblBookings.setBounds(140, 0, 100, 30);
		lblBookings.addMouseListener(this);
		contentPane.add(lblBookings);
		
		lblLogOut = new JLabel("LOG OUT");
		lblLogOut.setForeground(AMBIENTCOLOR);
		lblLogOut.setFont(TEXTFONT);
		lblLogOut.setBounds(300, 0, 100, 30);
		lblLogOut.addMouseListener(this);
		contentPane.add(lblLogOut);
		
		bookingPane = new BookingsTable();
		bookingPane.setBounds(0, 50, 800, 400);
		contentPane.add(bookingPane);
		bookingPane.setVisible(false);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(AdminMenu_v2.class.getResource("/graphics/AdminMenu.jpg")));
		background.setBounds(0, 0, 800, 550);
		contentPane.add(background);
		
		pack();
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu_v2 frame = new AdminMenu_v2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	// Mouse Listener Methods
	// Interface methods
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel button = (JLabel) e.getSource();
		switch(button.getText()){
			case "HOME":
				bookingPane.setVisible(false);
			break;
			case "BOOKINGS":
				bookingPane.setVisible(true);
			break;
			case "LOG OUT":
				bookingPane.setVisible(false);
			break;
		}
		System.out.println("Clicked: "+button.getText());
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel button = (JLabel) e.getSource();
		switch(button.getText()){
			case "HOME":
				button.setForeground(HIGHLIGHTCOLOR);
			break;
			case "BOOKINGS":
				button.setForeground(HIGHLIGHTCOLOR);
			break;
			case "LOG OUT":
				button.setForeground(HIGHLIGHTCOLOR);
			break;
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		JLabel button = (JLabel) e.getSource();
		switch(button.getText()){
			case "HOME":
				button.setForeground(AMBIENTCOLOR);
			break;
			case "BOOKINGS":
				button.setForeground(AMBIENTCOLOR);
			break;
			case "LOG OUT":
				button.setForeground(AMBIENTCOLOR);
			break;
		}
	}
}
