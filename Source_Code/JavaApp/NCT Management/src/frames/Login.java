package frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPasswordField;

import core.Client;

public class Login extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HintTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel btnSubmit;
	private ImageIcon btnStatic = new ImageIcon(Login.class.getResource("/graphics/LoginBtnStatic.png"));
	private ImageIcon btnHover = new ImageIcon(Login.class.getResource("/graphics/LoginBtnHover.png"));
	private ImageIcon btnClick = new ImageIcon(Login.class.getResource("/graphics/LoginBtnClick.png"));
	private JLabel lblSuccess;
	private Client client;
	private AdminMenu_v2 adminMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.requestFocusInWindow();
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
		
		txtUsername = new HintTextField("username");
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
		lblSuccess.setBounds(128, 185, 101, 16);
		contentPane.add(lblSuccess);
		
		JLabel loginBackground = new JLabel("");
		loginBackground.setIcon(new ImageIcon(Login.class.getResource("/graphics/Login.jpg")));
		loginBackground.setBounds(0, 0, 450, 250);
		contentPane.add(loginBackground);
		
		contentPane.addMouseListener(this);
		pack();
	}
	
	// Custom text field showing hints
	class HintTextField extends JTextField implements FocusListener{
		private static final long serialVersionUID = 1L;
		private final String hint;
		private boolean showingHint;

		public HintTextField(final String hint) {
		    super(hint);
		    this.hint = hint;
		    this.showingHint = true;
		    super.addFocusListener(this);
		}
		@Override
		public void focusGained(FocusEvent e) {
			if(this.getText().isEmpty()) {
				super.setForeground(Color.BLACK);
				super.setText("");
				showingHint = false;
		    }
		}
		@Override
		public void focusLost(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		    	super.setForeground(Color.GRAY);
		    	super.setText(hint);
		    	showingHint = true;
		    }
		}
		@Override
		public String getText() {
		    return showingHint ? "" : super.getText();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(contentPane)){
			contentPane.requestFocusInWindow();
		}
		else if(e.getSource().equals(btnSubmit)){
			char[] pass = null;
			client.setUsername(txtUsername.getText());
			pass = txtPassword.getPassword();
			String newPass = new String(pass);
			client.setPassword(newPass);
			if(client.logIn() == 1)
			{
				lblSuccess.setText("Login Successful");
				
				adminMenu = new AdminMenu_v2();
				adminMenu.setVisible(true);
				dispose();
			}
			else if(client.logIn() == 2){
				lblSuccess.setText("Login Successful");
			}
			else
			{
				lblSuccess.setText("Login Failed");
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource().equals(btnSubmit)){
			btnSubmit.setIcon(btnClick);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(btnSubmit)){
			btnSubmit.setIcon(btnHover);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(btnSubmit)){
			btnSubmit.setIcon(btnStatic);
		}
	}
}
