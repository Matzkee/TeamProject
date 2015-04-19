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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.Client;

public class Login extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel btnSubmit;
	private ImageIcon btnStatic = new ImageIcon(Login.class.getResource("/graphics/LoginBtnStatic.png"));
	private ImageIcon btnHover = new ImageIcon(Login.class.getResource("/graphics/LoginBtnHover.png"));
	private ImageIcon btnClick = new ImageIcon(Login.class.getResource("/graphics/LoginBtnClick.png"));
	private JLabel lblSuccess;
	private Client client;
	private AdminMenu adminMenu;
	private MechanicMenu mechanicMenu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setTitle("NCT Management");
					frame.requestFocusInWindow();
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		client = new Client();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(450, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtUsername.setForeground(Color.GRAY);
		txtUsername.setOpaque(false);
		txtUsername.setBorder(null);
		txtUsername.setBounds(37, 100, 192, 26);
		contentPane.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setOpaque(false);
		txtPassword.setBorder(null);
		txtPassword.setToolTipText("");
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPassword.setBounds(37, 148, 192, 26);
		contentPane.add(txtPassword);
		
		btnSubmit = new JLabel("");
		btnSubmit.setIcon(btnStatic);
		btnSubmit.setBounds(30, 185, 50, 25);
		btnSubmit.addMouseListener(this);
		contentPane.add(btnSubmit);
		
		lblSuccess = new JLabel("");
		lblSuccess.setForeground(Color.WHITE);
		lblSuccess.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSuccess.setBounds(128, 185, 300, 16);
		contentPane.add(lblSuccess);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblUsername.setForeground(Color.LIGHT_GRAY);
		lblUsername.setBounds(183, 81, 50, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setBounds(183, 128, 50, 14);
		contentPane.add(lblPassword);
		
		JLabel loginBackground = new JLabel("");
		loginBackground.setIcon(new ImageIcon(Login.class.getResource("/graphics/Login.jpg")));
		loginBackground.setBounds(0, 0, 450, 250);
		contentPane.add(loginBackground);
		
		contentPane.addMouseListener(this);
		pack();
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(contentPane)){
			contentPane.requestFocusInWindow();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource().equals(btnSubmit)) {
			lblSuccess.setText("");
			btnSubmit.setIcon(btnHover);
			
			if(client.connectionTest() != false){
				int[] user;
				char[] pass = null;
				client.setUsername(txtUsername.getText());
				pass = txtPassword.getPassword();
				String newPass = new String(pass);
				client.setPassword(newPass);
				user = client.logIn();
				// Case: Administrator
				if(user[1] == 1)
				{
					lblSuccess.setText("Login Successful!");
					txtUsername.setText("");
					txtPassword.setText("");
					lblSuccess.setText("");
					adminMenu = new AdminMenu(user[2], client, this);
					adminMenu.setVisible(true);
					adminMenu.setLocationRelativeTo(null);
					dispose();
				}
				// Case: Mechanic
				else if(user[1] == 2){
					lblSuccess.setText("Login Successful!");
					txtUsername.setText("");
					txtPassword.setText("");
					lblSuccess.setText("");
					mechanicMenu = new MechanicMenu(user[2], user[0], client, this);
					mechanicMenu.setVisible(true);
					mechanicMenu.setLocationRelativeTo(null);
					dispose();
				}
				else{
					lblSuccess.setText("Login Failed! Invalid Username or Password");
				}	
			}
			else{
				lblSuccess.setText("Connection Failed!");
			}
			
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource().equals(btnSubmit)){
			btnSubmit.setIcon(btnStatic);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(btnSubmit)){
			btnSubmit.setIcon(btnClick);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(btnSubmit)){
			btnSubmit.setIcon(btnStatic);
		}
	}
}
