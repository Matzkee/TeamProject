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

public class AdminMenu_v2 extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public AdminMenu_v2() {
		// Frame Settings
		setSize(815,590);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Content Panel
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHome = new JLabel("HOME");
		lblHome.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblHome.setForeground(Color.LIGHT_GRAY);
		lblHome.setBounds(30, 0, 100, 30);
		lblHome.addMouseListener(this);
		contentPane.add(lblHome);
		
		JLabel lblBookings = new JLabel("BOOKINGS");
		lblBookings.setForeground(Color.LIGHT_GRAY);
		lblBookings.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblBookings.setBounds(140, 0, 100, 30);
		lblBookings.addMouseListener(this);
		contentPane.add(lblBookings);
		
		JLabel lblLogOut = new JLabel("LOG OUT");
		lblLogOut.setForeground(Color.LIGHT_GRAY);
		lblLogOut.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLogOut.setBounds(300, 0, 100, 30);
		lblLogOut.addMouseListener(this);
		contentPane.add(lblLogOut);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(AdminMenu_v2.class.getResource("/graphics/AdminMenu_v2.jpg")));
		background.setBounds(0, 0, 800, 550);
		contentPane.add(background);
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
				
			break;
			case "BOOKINGS":
				
			break;
			case "LOG OUT":
				
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
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
