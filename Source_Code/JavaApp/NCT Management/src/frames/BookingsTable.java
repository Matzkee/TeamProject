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
	private Client testClient;
	private JTextField txtCarReg, txtDate, txtTime;
	private JLabel btnDelete, btnEditCarReg, btnEditDate, btnEditTime, btnSubmit, btnAdd;
	private JLabel lblSubmit, lblDelete, lblNew;
	private JLabel lblCarRegistration, lblDate, lblTime, lblEdit;
	private JLabel systemInfo;
	private TableModel tableModel;
	private JTable table;
	private JScrollPane tableScrollPane;
	private BookingCreationPane newBookingPane;
	private boolean isCarRegEditToggled, isDateEditToggled, isTimeEditToggled;
	/* Text Font */
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 13);
	private final Font SMALLTEXTFONT = new Font("Segoe UI", Font.PLAIN, 10);
	
	// Image for buttons + scaling
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
	public BookingsTable(int garage) {
		garageId = garage;
		// Main Panel Settings
		setSize(800,400);
		setLayout(null);
		setOpaque(false);
		addMouseListener(this);
		
		isCarRegEditToggled = false;
		isDateEditToggled = false;
		isTimeEditToggled = false;
		
		testClient = new Client();
		testClient.viewBookings();
		
		allBookings = testClient.getBookings();
		
		showTable();
		showLabels();
		showTextFields();
		showButtons();
		
		// Booking Creation Panel
		newBookingPane = new BookingCreationPane(table, garageId);
		newBookingPane.setBounds(10, 220, 250, 120);
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
		// System Label
		// Any relevant & useful info 
		systemInfo = new JLabel("",SwingConstants.RIGHT);
		systemInfo.setFont(SMALLTEXTFONT);
		systemInfo.setForeground(Color.LIGHT_GRAY);
		systemInfo.setBounds(530,388,250,12);
		add(systemInfo);
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
		lblEdit = new JLabel("Edit", SwingConstants.CENTER);
		lblEdit.setBounds(240, 55, 20, 14);
		lblEdit.setForeground(Color.LIGHT_GRAY);
		lblEdit.setFont(SMALLTEXTFONT);
		add(lblEdit);
		// Info: submit
		lblSubmit = new JLabel("Submit", SwingConstants.CENTER);
		lblSubmit.setBounds(300, 70, 40, 14);
		lblSubmit.setForeground(Color.LIGHT_GRAY);
		lblSubmit.setFont(SMALLTEXTFONT);
		add(lblSubmit);
		// Info: delete
		lblDelete = new JLabel("Delete", SwingConstants.CENTER);
		lblDelete.setBounds(300, 205, 40, 14);
		lblDelete.setForeground(Color.LIGHT_GRAY);
		lblDelete.setFont(SMALLTEXTFONT);
		add(lblDelete);
		// Info: new
		lblNew = new JLabel("New",SwingConstants.CENTER);
		lblNew.setBounds(10, 155, 40, 12);
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
		// Button: edit car registration
		btnEditCarReg = new JLabel("");
		btnEditCarReg.setBounds(240, 70, 20, 20);
		btnEditCarReg.setIcon(editB);
		btnEditCarReg.addMouseListener(this);
		add(btnEditCarReg);
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
		btnDelete.setBounds(300, 220, 40, 40);
		btnDelete.setIcon(deleteB);
		btnDelete.addMouseListener(this);
		add(btnDelete);
		// Button: submit changes
		btnSubmit = new JLabel("");
		btnSubmit.setBounds(300, 80, 40, 40);
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
	
	public void toggleFields(boolean carReg, boolean date, boolean time){
		txtCarReg.setEditable(carReg);
		txtDate.setEditable(date);
		txtTime.setEditable(time);
	}
	public void toggleFieldPaints(boolean carReg, boolean date, boolean time){
		isCarRegEditToggled = carReg;
		isDateEditToggled = date;
		isTimeEditToggled = time;
	}
	public void clearFields(){
		txtCarReg.setText("");
		txtDate.setText("");
		txtTime.setText("");
	}
	
	/*
	 * Custom gradient paint for JText boxes
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp, gp2, gp3;
		gp = new GradientPaint(txtCarReg.getX()+(txtCarReg.getWidth()/2),txtCarReg.getY()+txtCarReg.getHeight(),new Color(255,255,255,50),
				txtCarReg.getX()+(txtCarReg.getWidth()/2),txtCarReg.getY()+(txtCarReg.getHeight()/4),new Color(0,0,0,0));
		gp2 = new GradientPaint(txtDate.getX()+(txtDate.getWidth()/2),txtDate.getY()+txtDate.getHeight(),new Color(255,255,255,50),
				txtDate.getX()+(txtDate.getWidth()/2),txtDate.getY()+(txtDate.getHeight()/4),new Color(0,0,0,0));
		gp3 = new GradientPaint(txtTime.getX()+(txtTime.getWidth()/2),txtTime.getY()+txtTime.getHeight(),new Color(255,255,255,50),
				txtTime.getX()+(txtTime.getWidth()/2),txtTime.getY()+(txtTime.getHeight()/4),new Color(0,0,0,0));
		if(isCarRegEditToggled){
			g2d.setPaint(gp);
			g2d.fillRect(txtCarReg.getX(), txtCarReg.getY(), txtCarReg.getWidth(), txtCarReg.getHeight());
		}
		else if(isDateEditToggled){
			g2d.setPaint(gp2);
			g2d.fillRect(txtDate.getX(), txtDate.getY(), txtDate.getWidth(), txtDate.getHeight());			
		}
		else if(isTimeEditToggled){
			g2d.setPaint(gp3);
			g2d.fillRect(txtTime.getX(), txtTime.getY(), txtTime.getWidth(), txtTime.getHeight());			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(this)){
			// Double click event
			// Clear systemInfo text
			if(e.getClickCount() == 2 && !e.isConsumed()){
				e.consume();
				systemInfo.setText("");
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		// Case: JPanel, main booking panel
		if(o.equals(this)){
			systemInfo.setText("Main Panel");
			if (table.getSelectedRow() != -1){
				table.clearSelection();
			}
		}
		// Case: Add booking button
		else if(o.equals(btnAdd)){
			systemInfo.setText("Add new booking");
			btnAdd.setIcon(addBHover);
			newBookingPane.setVisible(true);
		}
		// Case: Delete Button
		else if(o.equals(btnDelete)){
			if (table.getSelectedRow() != -1){
				systemInfo.setText("Successfully deleted booking!");
				btnDelete.setIcon(deleteBHover);
				tableModel.removeRow(table.getSelectedRow());
				toggleFields(false, false, false);
				toggleFieldPaints(false, false, false);
				clearFields();
			}
		}
		// Case: Car Registration edit button
		else if(o.equals(btnEditCarReg)){
			if (table.getSelectedRow() != -1){
				btnEditCarReg.setIcon(editBHover);
				if(txtCarReg.isEditable()){
					// set not editable
					toggleFields(false, false, false);
					toggleFieldPaints(false, false, false);
					repaint();
				}
				else{
					systemInfo.setText("Toggled editable car reg");
					// set editable
					toggleFields(true, false, false);
					toggleFieldPaints(true, false, false);
					repaint();
				}
			}
		}
		// Case: Date edit button
		else if(o.equals(btnEditDate)){
			if (table.getSelectedRow() != -1){
				btnEditDate.setIcon(editBHover);
				if(txtDate.isEditable()){
					// set not editable
					toggleFields(false, false, false);
					toggleFieldPaints(false, false, false);
					repaint();
				}
				else{
					systemInfo.setText("Toggled editable date");
					// set editable
					toggleFields(false, true, false);
					toggleFieldPaints(false, true, false);
					repaint();
				}
			}
		}
		// Case: Time edit button
		else if(o.equals(btnEditTime)){
			if (table.getSelectedRow() != -1){
				btnEditTime.setIcon(editBHover);
				if(txtTime.isEditable()){
					// set not editable
					toggleFields(false, false, false);
					toggleFieldPaints(false, false, false);
					repaint();
				}
				else{
					systemInfo.setText("Toggled editable time");
					// set editable
					toggleFields(false, false, true);
					toggleFieldPaints(false, false, true);
					repaint();
				}
			}
		}
		// Case: Submit button
		else if(o.equals(btnSubmit)){
			if(table.getSelectedRow() != -1){
				btnSubmit.setIcon(submitBHover);
				systemInfo.setText("Successfully submited changes!");
				tableModel.updateRow(table.getSelectedRow(), txtCarReg.getText(), txtDate.getText(), txtTime.getText());
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
		// Case: Car Registration edit button
		else if(o.equals(btnEditCarReg)){
			btnEditCarReg.setIcon(editB);
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
