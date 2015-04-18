package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import components.TableModel;
import core.Client;

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
		GradientPaint gp2 = new GradientPaint(txtNewTime.getX()+(txtNewTime.getWidth()/2),txtNewTime.getY()+txtNewTime.getHeight(),new Color(255,255,255,50),
				txtNewTime.getX()+(txtNewTime.getWidth()/2),txtNewTime.getY()+(txtNewTime.getHeight()/4),new Color(0,0,0,0));
		
		g2d.setPaint(gp);
		g2d.fillRect(txtNewCarReg.getX(), txtNewCarReg.getY(), txtNewCarReg.getWidth(), txtNewCarReg.getHeight());
		g2d.setPaint(gp2);
		g2d.fillRect(txtNewTime.getX(), txtNewTime.getY(), txtNewTime.getWidth(), txtNewTime.getHeight());
	}

	/*
	 * Variable Declaration
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNewBooking, lblNewCarReg, lblNewDate, lblNewTime;
	private JLabel btnSubmit;
	private JLabel lblDate;
	private JLabel systemInfo;
	private JComboBox<Object> cBoxDay, cBoxMonth, cBoxYear;
	private JTextField txtNewCarReg, txtNewTime;
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 12);
	private JTable table;
	private TableModel model;
	private int garageId;
	private Client mainClient;
	
	// Image for button & scaling
	private final int buttonW = 40, buttonH = 40;
	private ImageIcon submitImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmit.png"));
	private ImageIcon submitImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmitHover.png"));
	private ImageIcon submitB = new ImageIcon(submitImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon submitBHover = new ImageIcon(submitImageHover.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	
	/**
	 * Create the panel.
	 */
	public BookingCreationPane(JTable mytable, int garage, Client programClient, JLabel sysInfo) {
		setOpaque(false);
		setSize(250, 150);
		setLayout(null);
		
		table = mytable;
		model = (TableModel) table.getModel();
		garageId = garage;
		mainClient = programClient;
		systemInfo = sysInfo;
		
		lblNewBooking = new JLabel("New Booking");
		lblNewBooking.setRequestFocusEnabled(false);
		lblNewBooking.setBounds(10, 0, 230, 25);
		lblNewBooking.setForeground(Color.LIGHT_GRAY);
		lblNewBooking.setFont(TEXTFONT);
		add(lblNewBooking);
		
		lblNewCarReg = new JLabel("Car Reg: ");
		lblNewCarReg.setBounds(10, 40, 50, 15);
		lblNewCarReg.setForeground(Color.LIGHT_GRAY);
		lblNewCarReg.setFont(TEXTFONT);
		add(lblNewCarReg);
		
		lblNewDate = new JLabel("Date: ");
		lblNewDate.setBounds(10, 65, 50, 15);
		lblNewDate.setForeground(Color.LIGHT_GRAY);
		lblNewDate.setFont(TEXTFONT);
		add(lblNewDate);
		
		lblNewTime = new JLabel("Time: ");
		lblNewTime.setBounds(10, 115, 50, 15);
		lblNewTime.setForeground(Color.LIGHT_GRAY);
		lblNewTime.setFont(TEXTFONT);
		add(lblNewTime);
		
		txtNewCarReg = new JTextField();
		txtNewCarReg.setBorder(null);
		txtNewCarReg.setOpaque(false);
		txtNewCarReg.setForeground(Color.LIGHT_GRAY);
		txtNewCarReg.setBounds(65, 40, 100, 15);
		add(txtNewCarReg);
		txtNewCarReg.setColumns(10);
		
		txtNewTime = new JTextField();
		txtNewTime.setBorder(null);
		txtNewTime.setOpaque(false);
		txtNewTime.setForeground(Color.LIGHT_GRAY);
		txtNewTime.setColumns(10);
		txtNewTime.setBounds(65, 115, 100, 15);
		add(txtNewTime);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 25, 230, 1);
		separator.setForeground(Color.LIGHT_GRAY);
		add(separator);
		
		btnSubmit = new JLabel("");
		btnSubmit.setBounds(205, 75, 40, 40);
		btnSubmit.setIcon(submitB);
		btnSubmit.addMouseListener(this);
		add(btnSubmit);
			
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		cBoxMonth = new JComboBox<Object>(months);
		cBoxMonth.setBounds(55, 85, 80, 20);
		cBoxMonth.setFont(TEXTFONT);
		cBoxMonth.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				setCorrectDays(cBoxMonth.getSelectedItem().toString());
				setDate();
			}
		});
		add(cBoxMonth);
		
		cBoxDay = new JComboBox<Object>();
		cBoxDay.setBounds(10, 85, 40, 20);
		cBoxDay.setFont(TEXTFONT);
		cBoxDay.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				setDate();
			}
		});
		setCorrectDays(cBoxMonth.getSelectedItem().toString());
		add(cBoxDay);
		
		String[] years = {"2015", "2016", "2017"};
		cBoxYear = new JComboBox<Object>(years);
		cBoxYear.setBounds(140, 85, 50, 20);
		cBoxYear.setFont(TEXTFONT);
		cBoxYear.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				setDate();
			}
		});
		add(cBoxYear);
		
		lblDate = new JLabel("");
		lblDate.setBounds(65, 65, 100, 15);
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setFont(TEXTFONT);
		add(lblDate);
		
		setDate();
	}
	
	public void dispose(){
		txtNewCarReg.setText("");
		txtNewTime.setText("");
		this.setVisible(false);
	}
	public void setDate(){
		String date = "";
		String newMonth = "";
		switch(cBoxMonth.getSelectedItem().toString()){
		case "January": newMonth = "01"; break;
		case "February": newMonth = "02"; break;
		case "March": newMonth = "03"; break;
		case "April": newMonth = "04"; break;
		case "May": newMonth = "05"; break;
		case "June": newMonth = "06"; break;
		case "July": newMonth = "07"; break;
		case "August": newMonth = "08"; break;
		case "September": newMonth = "09"; break;
		case "October": newMonth = "10"; break;
		case "November": newMonth = "11"; break;
		case "December": newMonth = "12"; break;
		}
		date = cBoxYear.getSelectedItem().toString()+"-"+newMonth.toString()+"-"+cBoxDay.getSelectedItem().toString();
		lblDate.setText(date);
	}
	public void setCorrectDays(String month){
		ArrayList<String> days;
		if(month.equals("February")){
			days = createDays(28);
		}
		else if(month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November")){
			days = createDays(30);
		}
		else{
			days = createDays(31);
		}
		cBoxDay.setModel(new DefaultComboBoxModel<Object>(days.toArray()));
	}
	public ArrayList<String> createDays(int num){
		ArrayList<String> days = new ArrayList<String>();
		for(int i = 0; i < num; i++){
			days.add(""+(i+1));
		}
		return days;
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
			String query = "INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('"+
			lblDate.getText()+"','"+txtNewTime.getText()+"','"+txtNewCarReg.getText()+"',"+garageId+")";
			if(mainClient.createBooking(query) != false){
				model.addRow(txtNewCarReg.getText(), lblDate.getText(), txtNewTime.getText(), garageId);
				systemInfo.setText("Successfully added booking!");
				dispose();
			}
			else{
				systemInfo.setText("Connection failure!");
			}
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
