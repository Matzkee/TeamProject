package frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
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
import javax.swing.border.EmptyBorder;

import components.TableModel;
import components.TableMouseListener;
import components.TableRenderer;

import core.Booking;
import core.Client;

public class MechanicBookingsTable extends JPanel implements MouseListener{
	/**
	 * Variable declaration.
	 */
	private static final long serialVersionUID = 1L;
	private int garageId, userId;
	private ArrayList<Booking> allBookings = new ArrayList<>();
	private Client mainClient;
	private JTextField txtCarReg, txtDate, txtTime;
	private JLabel lblCarRegistration, lblDate, lblTime;
	private JLabel systemInfo;
	private JLabel btnCreateResults;
	private JLabel lblCreateResults;
	private TableModel tableModel;
	private JTable table;
	private JScrollPane tableScrollPane;
	private ResultsCreationPane resultsPane;
	/* Text Font */
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 13);

	// Images for button & scaling
	private ImageIcon addImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgAdd.png"));
	private ImageIcon addImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgAddHover.png"));
	private final int buttonW = 40, buttonH = 40;
	private ImageIcon addB = new ImageIcon(addImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon addBHover = new ImageIcon(addImageHover.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	
	/**
	 * Create the panel.
	 * Constructor
	 */
	public MechanicBookingsTable(int garage, int user, Client programClient, JLabel sysInfo) {
		garageId = garage;
		userId = user;
		systemInfo = sysInfo;
		// Main Panel Settings
		setSize(800,400);
		setLayout(null);
		setOpaque(false);
		addMouseListener(this);
		
		mainClient = programClient;
		mainClient.viewBookings(garageId);
		
		allBookings = mainClient.getBookings();
		
		showTable();
		showLabels();
		showTextFields();
		showButtons();
		
		resultsPane = new ResultsCreationPane(table, systemInfo, userId, mainClient);
		resultsPane.setBounds(10, 160, 300, 200);
		add(resultsPane);
		resultsPane.setVisible(false);
		
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
		lblCarRegistration.setBounds(10, 20, 110, 20);
		add(lblCarRegistration);
		// Date label
		lblDate = new JLabel("Date: ");
		lblDate.setFont(TEXTFONT);
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setBounds(10, 50, 110, 20);
		add(lblDate);
		// Time label
		lblTime = new JLabel("Time: ");
		lblTime.setFont(TEXTFONT);
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setBounds(10, 80, 110, 20);
		add(lblTime);
		// Info label: create results
		lblCreateResults = new JLabel("Create New Results");
		lblCreateResults.setForeground(Color.LIGHT_GRAY);
		lblCreateResults.setBounds(60, 125, 150, 14);
		lblCreateResults.setFont(TEXTFONT);
		add(lblCreateResults);
	}
	
	public void showTextFields(){
		// CarReg text field
		txtCarReg = new JTextField();
		txtCarReg.setBorder(null);
		txtCarReg.setOpaque(false);
		txtCarReg.setEditable(false);
		txtCarReg.setForeground(Color.LIGHT_GRAY);
		txtCarReg.setFont(TEXTFONT);
		txtCarReg.setBounds(130, 20, 100, 20);
		add(txtCarReg);
		txtCarReg.setColumns(10);
		// Date text field
		txtDate = new JTextField();
		txtDate.setBorder(null);
		txtDate.setOpaque(false);
		txtDate.setForeground(Color.LIGHT_GRAY);
		txtDate.setFont(TEXTFONT);
		txtDate.setEditable(false);
		txtDate.setBounds(130, 50, 100, 20);
		add(txtDate);
		txtDate.setColumns(10);
		// Time text field
		txtTime = new JTextField();
		txtTime.setBorder(null);
		txtTime.setOpaque(false);
		txtTime.setForeground(Color.LIGHT_GRAY);
		txtTime.setFont(TEXTFONT);
		txtTime.setEditable(false);
		txtTime.setBounds(130, 80, 100, 20);
		add(txtTime);
		txtTime.setColumns(10);
	}
	
	public void showButtons(){
		// Button: create new set of results
		btnCreateResults = new JLabel("");
		btnCreateResults.setBounds(10, 110, 40, 40);
		btnCreateResults.setIcon(addB);
		btnCreateResults.addMouseListener(this);
		add(btnCreateResults);
	}
	public void clearFields(){
		txtCarReg.setText("");
		txtDate.setText("");
		txtTime.setText("");
	}
	
	// Mouse listener methods
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(this)){
			// Double click event
			// Clear systemInfo text
			if(e.getClickCount() == 2 && !e.isConsumed()){
				e.consume();
				if (table.getSelectedRow() != -1){
					table.clearSelection();
				}
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
		}
		else if(o.equals(btnCreateResults)){
			if (table.getSelectedRow() != -1){
				btnCreateResults.setIcon(addBHover);
				resultsPane.setVisible(true);
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(btnCreateResults)){
			btnCreateResults.setIcon(addB);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}