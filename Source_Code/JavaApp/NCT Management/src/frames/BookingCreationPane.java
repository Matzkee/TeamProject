package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSeparator;

import components.TableModel;

public class BookingCreationPane extends JPanel implements MouseListener{
	
	/*
	 * Gradient Overlay
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(getWidth()/2,getWidth(),new Color(255,255,255,50),getWidth()/2,getHeight()/2,new Color(0,0,0,0));
		g2d.setPaint(gradient);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		GradientPaint gp = new GradientPaint(txtNewCarReg.getX()+(txtNewCarReg.getWidth()/2),txtNewCarReg.getY()+txtNewCarReg.getHeight(),new Color(255,255,255,50),
				txtNewCarReg.getX()+(txtNewCarReg.getWidth()/2),txtNewCarReg.getY()+(txtNewCarReg.getHeight()/4),new Color(0,0,0,0));
		GradientPaint gp2 = new GradientPaint(txtNewDate.getX()+(txtNewDate.getWidth()/2),txtNewDate.getY()+txtNewDate.getHeight(),new Color(255,255,255,50),
				txtNewDate.getX()+(txtNewDate.getWidth()/2),txtNewDate.getY()+(txtNewDate.getHeight()/4),new Color(0,0,0,0));
		GradientPaint gp3 = new GradientPaint(txtNewTime.getX()+(txtNewTime.getWidth()/2),txtNewTime.getY()+txtNewTime.getHeight(),new Color(255,255,255,50),
				txtNewTime.getX()+(txtNewTime.getWidth()/2),txtNewTime.getY()+(txtNewTime.getHeight()/4),new Color(0,0,0,0));
		
		g2d.setPaint(gp);
		g2d.fillRect(txtNewCarReg.getX(), txtNewCarReg.getY(), txtNewCarReg.getWidth(), txtNewCarReg.getHeight());
		g2d.setPaint(gp2);
		g2d.fillRect(txtNewDate.getX(), txtNewDate.getY(), txtNewDate.getWidth(), txtNewDate.getHeight());
		g2d.setPaint(gp3);
		g2d.fillRect(txtNewTime.getX(), txtNewTime.getY(), txtNewTime.getWidth(), txtNewTime.getHeight());
	}

	/*
	 * Variable Declaration
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNewBooking, lblNewCarReg, lblNewDate, lblNewTime;
	private JLabel btnSubmit;
	private JTextField txtNewCarReg, txtNewDate, txtNewTime;
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 13);
	private JTable table;
	private TableModel model;
	private int garageId;
	
	// Image for button & scaling
	private final int buttonW = 40, buttonH = 40;
	private ImageIcon submitImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmit.png"));
	private ImageIcon submitImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmitHover.png"));
	private ImageIcon submitB = new ImageIcon(submitImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon submitBHover = new ImageIcon(submitImageHover.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	
	/**
	 * Create the panel.
	 */
	public BookingCreationPane(JTable mytable, int garage) {
		setOpaque(false);
		setSize(250, 120);
		setLayout(null);
		
		table = mytable;
		model = (TableModel) table.getModel();
		garageId = garage;
		
		lblNewBooking = new JLabel("New Booking");
		lblNewBooking.setRequestFocusEnabled(false);
		lblNewBooking.setBounds(10, 0, 230, 25);
		lblNewBooking.setForeground(Color.LIGHT_GRAY);
		lblNewBooking.setFont(TEXTFONT);
		add(lblNewBooking);
		
		lblNewCarReg = new JLabel("Car Reg: ");
		lblNewCarReg.setBounds(10, 40, 70, 15);
		lblNewCarReg.setForeground(Color.LIGHT_GRAY);
		lblNewCarReg.setFont(TEXTFONT);
		add(lblNewCarReg);
		
		lblNewDate = new JLabel("Date: ");
		lblNewDate.setBounds(10, 65, 70, 15);
		lblNewDate.setForeground(Color.LIGHT_GRAY);
		lblNewDate.setFont(TEXTFONT);
		add(lblNewDate);
		
		lblNewTime = new JLabel("Time: ");
		lblNewTime.setBounds(10, 90, 70, 15);
		lblNewTime.setForeground(Color.LIGHT_GRAY);
		lblNewTime.setFont(TEXTFONT);
		add(lblNewTime);
		
		txtNewCarReg = new JTextField();
		txtNewCarReg.setBorder(null);
		txtNewCarReg.setOpaque(false);
		txtNewCarReg.setForeground(Color.LIGHT_GRAY);
		txtNewCarReg.setBounds(90, 40, 100, 20);
		add(txtNewCarReg);
		txtNewCarReg.setColumns(10);
		
		txtNewDate = new JTextField();
		txtNewDate.setBorder(null);
		txtNewDate.setOpaque(false);
		txtNewDate.setForeground(Color.LIGHT_GRAY);
		txtNewDate.setColumns(10);
		txtNewDate.setBounds(90, 65, 100, 20);
		add(txtNewDate);
		
		txtNewTime = new JTextField();
		txtNewTime.setBorder(null);
		txtNewTime.setOpaque(false);
		txtNewTime.setForeground(Color.LIGHT_GRAY);
		txtNewTime.setColumns(10);
		txtNewTime.setBounds(90, 90, 100, 20);
		add(txtNewTime);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 25, 230, 1);
		separator.setForeground(Color.LIGHT_GRAY);
		add(separator);
		
		btnSubmit = new JLabel("");
		btnSubmit.setBounds(205, 55, 40, 40);
		btnSubmit.setIcon(submitB);
		btnSubmit.addMouseListener(this);
		add(btnSubmit);
	}
	
	public void dispose(){
		txtNewCarReg.setText("");
		txtNewDate.setText("");
		txtNewTime.setText("");
		this.setVisible(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(btnSubmit)){
			btnSubmit.setIcon(submitBHover);
			model.addRow(txtNewCarReg.getText(), txtNewDate.getText(), txtNewTime.getText(), garageId);
			dispose();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(btnSubmit)){
			btnSubmit.setIcon(submitB);
		}
	}
}
