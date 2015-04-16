package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.Client;

public class MechanicMenu extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable declaration.
	 */
	private Login loginPage;
	private JPanel contentPane;
	private JLabel lblHome;
	private JLabel lblBookings;
	private JLabel lblResults;
	private JLabel lblLogOut;
	private int garageId, userId;
	private Client mainClient;
	private final Color HIGHLIGHTCOLOR = new Color(214,137,4);
	private final Color HOVERCOLOR = Color.LIGHT_GRAY;
	private final Color AMBIENTCOLOR = Color.GRAY;
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 20);
	private final Font SMALLTEXTFONT = new Font("Segoe UI", Font.PLAIN, 10);
	private MechanicBookingsTable bookingPane;
	private ResultPane resultPane;
	private JLabel systemInfo;


	/**
	 * Create the frame.
	 */
	public MechanicMenu(int garage, int user, Client programClient, Login loginP) {
		garageId = garage;
		userId = user;
		mainClient = programClient;
		loginPage = loginP;
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
		
		lblHome = new JLabel("HOME", SwingConstants.CENTER);
		lblHome.setFont(TEXTFONT);
		lblHome.setForeground(AMBIENTCOLOR);
		lblHome.setBounds(0, 0, 100, 30);
		lblHome.addMouseListener(this);
		contentPane.add(lblHome);
		
		lblBookings = new JLabel("BOOKINGS", SwingConstants.CENTER);
		lblBookings.setForeground(AMBIENTCOLOR);
		lblBookings.setFont(TEXTFONT);
		lblBookings.setBounds(130, 0, 100, 30);
		lblBookings.addMouseListener(this);
		contentPane.add(lblBookings);
		
		lblResults = new JLabel("RESULTS", SwingConstants.CENTER);
		lblResults.setForeground(Color.GRAY);
		lblResults.setFont(TEXTFONT);
		lblResults.setBounds(260, 0, 100, 30);
		lblResults.addMouseListener(this);
		contentPane.add(lblResults);
		
		lblLogOut = new JLabel("LOG OUT", SwingConstants.CENTER);
		lblLogOut.setForeground(AMBIENTCOLOR);
		lblLogOut.setFont(TEXTFONT);
		lblLogOut.setBounds(390, 0, 100, 30);
		lblLogOut.addMouseListener(this);
		contentPane.add(lblLogOut);
		
		// System Label
		// Any relevant & useful info 
		systemInfo = new JLabel("", SwingConstants.RIGHT);
		systemInfo.setBounds(540, 480, 250, 12);
		systemInfo.setFont(SMALLTEXTFONT);
		systemInfo.setForeground(HOVERCOLOR);
		contentPane.add(systemInfo);
		
		bookingPane = new MechanicBookingsTable(garageId, userId, mainClient, systemInfo);
		bookingPane.setBounds(0, 50, 800, 400);
		contentPane.add(bookingPane);
		bookingPane.setVisible(false);
		
		resultPane = new ResultPane(mainClient, systemInfo);
		resultPane.setBounds(0, 50, 800, 400);
		contentPane.add(resultPane);
		resultPane.setVisible(false);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(AdminMenu.class.getResource("/graphics/AdminMenu.jpg")));
		background.setBounds(0, 0, 800, 550);
		contentPane.add(background);
		
		pack();
	}
	
	
	/**
	 * Launch the application.
	 * Placeholder:
	 * remove once program is finished
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MechanicMenu frame = new MechanicMenu(1, 111111, new Client(), new Login());
					frame.setVisible(true);
					frame.setTitle("NCT Management");
					frame.setLocationRelativeTo(null);
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
	}
	@Override
	public void mousePressed(MouseEvent e) {
		JLabel button = (JLabel) e.getSource();
		switch(button.getText()){
			case "HOME":
				bookingPane.setVisible(false);
				resultPane.setVisible(false);
				button.setForeground(HIGHLIGHTCOLOR);
				systemInfo.setText("HOME");
			break;
			case "BOOKINGS":
				resultPane.setVisible(false);
				bookingPane.setVisible(true);
				button.setForeground(HIGHLIGHTCOLOR);
				systemInfo.setText("BOOKINGS");
			break;
			case "RESULTS":
				bookingPane.setVisible(false);
				resultPane.setVisible(true);
				button.setForeground(HIGHLIGHTCOLOR);
				systemInfo.setText("RESULTS");
			break;
			case "LOG OUT":
				bookingPane.setVisible(false);
				resultPane.setVisible(false);
				button.setForeground(HIGHLIGHTCOLOR);
				systemInfo.setText("LOG OUT");
				mainClient.logOut();
				loginPage.setVisible(true);
				dispose();
			break;
		}
		System.out.println("Clicked: "+button.getText());
	}
	@Override
	public void mouseReleased(MouseEvent e) {
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
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel button = (JLabel) e.getSource();
		switch(button.getText()){
			case "HOME":
				button.setForeground(HOVERCOLOR);
			break;
			case "BOOKINGS":
				button.setForeground(HOVERCOLOR);
			break;
			case "RESULTS":
				button.setForeground(HOVERCOLOR);
			break;
			case "LOG OUT":
				button.setForeground(HOVERCOLOR);
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
			case "RESULTS":
				button.setForeground(AMBIENTCOLOR);
			break;
			case "LOG OUT":
				button.setForeground(AMBIENTCOLOR);
			break;
		}
	}
}
