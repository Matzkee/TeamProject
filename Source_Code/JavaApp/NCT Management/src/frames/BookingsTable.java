package frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.TableModel;
import components.TableMouseListener;
import components.TableRenderer;

import core.Booking;
import core.Client;

public class BookingsTable extends JPanel implements MouseListener{
	/**
	 * Variable declaration.
	 */
	private static final long serialVersionUID = 1L;
	private int garageId = 1;
	private ArrayList<Booking> allBookings = new ArrayList<>();
	private Client mainClient;
	private JTextField txtCarReg, txtDate, txtTime;
	private JLabel btnDelete, btnEditDate, btnEditTime, btnSubmit, btnAdd;
	private JLabel lblSubmit, lblDelete, lblNew;
	private JLabel lblCarRegistration, lblDate, lblTime, lblEditDate;
	private JLabel systemInfo;
	private TableModel tableModel;
	private JTable table;
	private JScrollPane tableScrollPane;
	private BookingCreationPane newBookingPane;
	private boolean isDateEditToggled, isTimeEditToggled;
	/* Text Font */
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 13);
	private final Font SMALLTEXTFONT = new Font("Segoe UI", Font.PLAIN, 10);
	
	// Images for buttons + scaling
	private ImageIcon editImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgEdit.png"));
	private ImageIcon deleteImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgDelete.png"));
	private ImageIcon submitImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmit.png"));
	private ImageIcon addImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgAdd.png"));
	private ImageIcon editImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgEditHover.png"));
	private ImageIcon deleteImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgDeleteHover.png"));
	private ImageIcon submitImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmitHover.png"));
	private ImageIcon addImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgAddHover.png"));
	private final int buttonW = 40, buttonH = 40;
	private ImageIcon addB = new ImageIcon(addImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon editB = new ImageIcon(editImage.getImage().getScaledInstance(buttonW-20, buttonH-20, Image.SCALE_DEFAULT));
	private ImageIcon deleteB = new ImageIcon(deleteImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon submitB = new ImageIcon(submitImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon addBHover = new ImageIcon(addImageHover.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon editBHover = new ImageIcon(editImageHover.getImage().getScaledInstance(buttonW-20, buttonH-20, Image.SCALE_DEFAULT));
	private ImageIcon deleteBHover = new ImageIcon(deleteImageHover.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon submitBHover = new ImageIcon(submitImageHover.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));

	/**
	 * Create the panel.
	 * Constructor
	 */
	public BookingsTable(int garage, Client programClient, JLabel sysInfo) {
		garageId = garage;
		systemInfo = sysInfo;
		// Main Panel Settings
		setSize(800,400);
		setLayout(null);
		setOpaque(false);
		addMouseListener(this);
		
		isDateEditToggled = false;
		isTimeEditToggled = false;
		
		mainClient = programClient;
		mainClient.viewBookings(garageId);
		
		allBookings = mainClient.getBookings();
		
		showTable();
		showLabels();
		showTextFields();
		showButtons();
		
		// Booking Creation Panel
		newBookingPane = new BookingCreationPane(table, garageId, mainClient, systemInfo);
		newBookingPane.setBounds(10, 220, 250, 150);
		add(newBookingPane);
		newBookingPane.setVisible(false);
		
		// Assign mouse listener to table
		// Set at the end for other components to be initialised first
		table.addMouseListener(new TableMouseListener(table, txtCarReg, txtDate, txtTime, systemInfo));
	}
	
	public void showTable(){
		// Setting up the table
		tableModel = new TableModel(allBookings);
		table = new JTable(tableModel);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setBorder(null);
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Booking.class, new TableRenderer());
		table.setRowHeight(40);
		table.setTableHeader(null);
		// ScrollPane for the table
		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableScrollPane.getViewport().setOpaque(false);
		tableScrollPane.setOpaque(false);
		tableScrollPane.setBounds(390, 25, 400, 300);
		add(tableScrollPane);
	}
	
	public void showLabels(){
		// CarReg label
		lblCarRegistration = new JLabel("Car Registration: ");
		lblCarRegistration.setFont(TEXTFONT);
		lblCarRegistration.setForeground(Color.LIGHT_GRAY);
		lblCarRegistration.setBounds(10, 70, 110, 20);
		add(lblCarRegistration);
		// Date label
		lblDate = new JLabel("Date: ");
		lblDate.setFont(TEXTFONT);
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setBounds(10, 100, 110, 20);
		add(lblDate);
		// Time label
		lblTime = new JLabel("Time: ");
		lblTime.setFont(TEXTFONT);
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setBounds(10, 130, 110, 20);
		add(lblTime);
		// Info: edit
		lblEditDate = new JLabel("Edit", SwingConstants.CENTER);
		lblEditDate.setBounds(265, 104, 20, 14);
		lblEditDate.setForeground(Color.LIGHT_GRAY);
		lblEditDate.setFont(SMALLTEXTFONT);
		add(lblEditDate);
		
		JLabel lblEditTime = new JLabel("Edit", SwingConstants.CENTER);
		lblEditTime.setForeground(Color.LIGHT_GRAY);
		lblEditTime.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		lblEditTime.setBounds(265, 134, 20, 14);
		add(lblEditTime);
		// Info: submit
		lblSubmit = new JLabel("Submit Changes", SwingConstants.CENTER);
		lblSubmit.setBounds(205, 185, 80, 14);
		lblSubmit.setForeground(Color.LIGHT_GRAY);
		lblSubmit.setFont(SMALLTEXTFONT);
		add(lblSubmit);
		// Info: delete
		lblDelete = new JLabel("Delete", SwingConstants.CENTER);
		lblDelete.setBounds(335, 185, 40, 14);
		lblDelete.setForeground(Color.LIGHT_GRAY);
		lblDelete.setFont(SMALLTEXTFONT);
		add(lblDelete);
		// Info: new
		lblNew = new JLabel("Create New Booking",SwingConstants.CENTER);
		lblNew.setBounds(45, 185, 100, 12);
		lblNew.setForeground(Color.LIGHT_GRAY);
		lblNew.setFont(SMALLTEXTFONT);
		add(lblNew);
	}
	
	public void showTextFields(){
		// CarReg text field
		txtCarReg = new JTextField();
		txtCarReg.setBorder(null);
		txtCarReg.setOpaque(false);
		txtCarReg.setEditable(false);
		txtCarReg.setForeground(Color.LIGHT_GRAY);
		txtCarReg.setFont(TEXTFONT);
		txtCarReg.setBounds(130, 70, 100, 20);
		add(txtCarReg);
		txtCarReg.setColumns(10);
		// Date text field
		txtDate = new JTextField();
		txtDate.setBorder(null);
		txtDate.setOpaque(false);
		txtDate.setForeground(Color.LIGHT_GRAY);
		txtDate.setFont(TEXTFONT);
		txtDate.setEditable(false);
		txtDate.setBounds(130, 100, 100, 20);
		add(txtDate);
		txtDate.setColumns(10);
		// Time text field
		txtTime = new JTextField();
		txtTime.setBorder(null);
		txtTime.setOpaque(false);
		txtTime.setForeground(Color.LIGHT_GRAY);
		txtTime.setFont(TEXTFONT);
		txtTime.setEditable(false);
		txtTime.setBounds(130, 130, 100, 20);
		add(txtTime);
		txtTime.setColumns(10);
	}
	
	public void showButtons(){
		// Button: edit date
		btnEditDate = new JLabel("");
		btnEditDate.setBounds(240, 100, 20, 20);
		btnEditDate.setIcon(editB);
		btnEditDate.addMouseListener(this);
		add(btnEditDate);
		// Button: edit time
		btnEditTime = new JLabel("");
		btnEditTime.setBounds(240, 130, 20, 20);
		btnEditTime.setIcon(editB);
		btnEditTime.addMouseListener(this);
		add(btnEditTime);
		// Button: delete booking
		btnDelete = new JLabel("");
		btnDelete.setBounds(300, 170, 40, 40);
		btnDelete.setIcon(deleteB);
		btnDelete.addMouseListener(this);
		add(btnDelete);
		// Button: submit changes
		btnSubmit = new JLabel("");
		btnSubmit.setBounds(170, 170, 40, 40);
		btnSubmit.setIcon(submitB);
		btnSubmit.addMouseListener(this);
		add(btnSubmit);
		// Button: add new booking
		btnAdd = new JLabel("");
		btnAdd.setBounds(10, 170, 40, 40);
		btnAdd.setIcon(addB);
		btnAdd.addMouseListener(this);
		add(btnAdd);
	}
	public void toggleFields(boolean date, boolean time){
		txtDate.setEditable(date);
		txtTime.setEditable(time);
	}
	public void toggleFieldPaints(boolean date, boolean time){
		isDateEditToggled = date;
		isTimeEditToggled = time;
	}
	public void clearFields(){
		txtCarReg.setText("");
		txtDate.setText("");
		txtTime.setText("");
	}
	public void clearAllSelections(){
		toggleFields(false, false);
		toggleFieldPaints(false, false);
		clearFields();
		btnAdd.setIcon(addB);
		btnDelete.setIcon(deleteB);
		btnEditDate.setIcon(editB);
		btnEditTime.setIcon(editB);
		btnSubmit.setIcon(submitB);
	};
	
	/*
	 * Custom gradient paint for JText boxes
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp2, gp3;
		gp2 = new GradientPaint(txtDate.getX()+(txtDate.getWidth()/2),txtDate.getY()+txtDate.getHeight(),new Color(255,255,255,50),
				txtDate.getX()+(txtDate.getWidth()/2),txtDate.getY()+(txtDate.getHeight()/4),new Color(0,0,0,0));
		gp3 = new GradientPaint(txtTime.getX()+(txtTime.getWidth()/2),txtTime.getY()+txtTime.getHeight(),new Color(255,255,255,50),
				txtTime.getX()+(txtTime.getWidth()/2),txtTime.getY()+(txtTime.getHeight()/4),new Color(0,0,0,0));
		if(isDateEditToggled){
			g2d.setPaint(gp2);
			g2d.fillRect(txtDate.getX(), txtDate.getY(), txtDate.getWidth(), txtDate.getHeight());			
		}
		else if(isTimeEditToggled){
			g2d.setPaint(gp3);
			g2d.fillRect(txtTime.getX(), txtTime.getY(), txtTime.getWidth(), txtTime.getHeight());			
		}
	}
	
	// Mouse listener methods
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(this)){
			systemInfo.setText("Main Panel");
			// Double click event
			// Clear systemInfo text
			if(e.getClickCount() == 2 && !e.isConsumed()){
				e.consume();
				if (table.getSelectedRow() != -1){
					table.clearSelection();
				}
				newBookingPane.setVisible(false);
				systemInfo.setText("");
				toggleFields(false, false);
				toggleFieldPaints(false, false);
				clearFields();
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		// Case: Add booking button
		if(o.equals(btnAdd)){
			systemInfo.setText("Add new booking");
			btnAdd.setIcon(addBHover);
			newBookingPane.setVisible(true);
		}
		// Case: Delete Button
		else if(o.equals(btnDelete)){
			if (table.getSelectedRow() != -1){
				Booking tempBook = (Booking) table.getValueAt(table.getSelectedRow(), 0);
				String query = "DELETE FROM Booking WHERE Car_Reg = '"+tempBook.getCarReg()+"' AND BTime = '"+tempBook.getTime()+"'";
				if(mainClient.cancelBooking(query) != false){
					systemInfo.setText("Successfully deleted booking!");
					btnDelete.setIcon(deleteBHover);
					tableModel.removeRow(table.getSelectedRow());
					toggleFields(false, false);
					toggleFieldPaints(false, false);
					clearFields();
					btnDelete.setIcon(deleteB);
				}
				else{
					systemInfo.setText("Connection failure!");
				}
			}
			else{
				systemInfo.setText("Select a booking first to delete it");
			}
		}
		// Case: Date edit button
		else if(o.equals(btnEditDate)){
			if (table.getSelectedRow() != -1){
				btnEditDate.setIcon(editBHover);
				if(txtDate.isEditable()){
					// set not editable
					toggleFields(false, false);
					toggleFieldPaints(false, false);
					repaint();
				}
				else{
					systemInfo.setText("Date is now editable");
					// set editable
					toggleFields(true, false);
					toggleFieldPaints(true, false);
					repaint();
				}
			}
			else{
				systemInfo.setText("Select a booking first to edit it");
			}
		}
		// Case: Time edit button
		else if(o.equals(btnEditTime)){
			if (table.getSelectedRow() != -1){
				btnEditTime.setIcon(editBHover);
				if(txtTime.isEditable()){
					// set not editable
					toggleFields(false, false);
					toggleFieldPaints(false, false);
					repaint();
				}
				else{
					systemInfo.setText("Time is now editable");
					// set editable
					toggleFields(false, true);
					toggleFieldPaints(false, true);
					repaint();
				}
			}
			else{
				systemInfo.setText("Select a booking first to edit it");
			}
		}
		// Case: Edit Submit button
		else if(o.equals(btnSubmit)){
			if(table.getSelectedRow() != -1){
				String query = "UPDATE Booking SET BDate='"+txtDate.getText()+"', BTime='"+txtTime.getText()+"' WHERE Car_Reg='"+txtCarReg.getText()+"'";
				if(mainClient.modifyBooking(query) != false){
					btnSubmit.setIcon(submitBHover);
					systemInfo.setText("Successfully submited changes!");
					tableModel.updateRow(table.getSelectedRow(), txtCarReg.getText(), txtDate.getText(), txtTime.getText());
					toggleFields(false, false);
					toggleFieldPaints(false, false);
					clearFields();
				}
				else{
					systemInfo.setText("Connection failure!");
				}
			}
			else{
				systemInfo.setText("Select a booking first to update it");
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		Object o = e.getSource();
		// Case: Add booking button
		if(o.equals(btnAdd)){
			btnAdd.setIcon(addB);
		}
		// Case: Delete Button
		else if(o.equals(btnDelete)){
			if (table.getSelectedRow() != -1){
				btnDelete.setIcon(deleteB);
			}
		}
		// Case: Date edit button
		else if(o.equals(btnEditDate)){
			btnEditDate.setIcon(editB);
		}
		// Case: Time edit button
		else if(o.equals(btnEditTime)){
			btnEditTime.setIcon(editB);
		}
		// Case: Submit button
		else if(o.equals(btnSubmit)){
			btnSubmit.setIcon(submitB);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
