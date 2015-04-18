package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import components.TableModel;

import core.Booking;
import core.Client;

public class ResultsCreationPane extends JPanel implements MouseListener{
	/*
	 * Variable declaration
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private Client mainClient;
	private JTable bookingTable;
	private TableModel tableModel;
	private JLabel systemInfo;
	private JLabel lblTestAlignment, lblTestSuspension, lblTestBrakes, lblTestEEmission, lblTestHeadLights;
	private JLabel btnFailAlignment, btnFailSuspension, btnFailBrakes, btnFailEEmission, btnFailHeadLights;
	private JLabel btnPassAlignment, btnPassSuspension, btnPassBrakes, btnPassEEmission, btnPassHeadLights;
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 13);
	private int alignment, suspension, brakes, eemission, headlights;
	private boolean alignmentChange, suspensionChange, brakesChange, eemissionChange, headlightsChange;
	private final Color AMBIENTCOLOR = Color.LIGHT_GRAY;
	private final Color HIGHLIGHTCOLOR = Color.WHITE;
	private final Color FAILSTATIC = new Color(121,0,0,100);
	private final Color FAILHOVER = new Color(237,28,36,200);
	private final Color PASSSTATIC = new Color(0,94,32,100);
	private final Color PASSHOVER= new Color(57,181,74,200);
	// Images & scaling
	private final int buttonW = 40, buttonH = 40;
	private ImageIcon submitImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmit.png"));
	private ImageIcon submitImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmitHover.png"));
	private ImageIcon submitB = new ImageIcon(submitImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon submitBHover = new ImageIcon(submitImageHover.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private JLabel btnSubmit;
	private JLabel lblSubmitResults;

	/**
	 * Create the panel.
	 */
	public ResultsCreationPane(JTable table, JLabel sysLabel, int user, Client client) {
		bookingTable = table;
		systemInfo = sysLabel;
		userId = user;
		mainClient = client;
		tableModel = (TableModel) bookingTable.getModel();
		
		setSize(300,200);
		setOpaque(false);
		setLayout(null);
		
		showLabels();
		showButtons();
		clearEverything();
	}
	public void clearEverything(){
		alignment = 0;
		suspension = 0;
		brakes = 0;
		eemission = 0;
		headlights = 0;
		alignmentChange = false;
		suspensionChange = false;
		brakesChange = false;
		eemissionChange = false;
		headlightsChange = false;
		btnFailAlignment.setBackground(FAILSTATIC);
		btnFailAlignment.setForeground(AMBIENTCOLOR);
		btnFailSuspension.setBackground(FAILSTATIC);
		btnFailSuspension.setForeground(AMBIENTCOLOR);
		btnFailBrakes.setBackground(FAILSTATIC);
		btnFailBrakes.setForeground(AMBIENTCOLOR);
		btnFailEEmission.setBackground(FAILSTATIC);
		btnFailEEmission.setForeground(AMBIENTCOLOR);
		btnFailHeadLights.setBackground(FAILSTATIC);
		btnFailHeadLights.setForeground(AMBIENTCOLOR);
		btnPassAlignment.setBackground(PASSSTATIC);
		btnPassAlignment.setForeground(AMBIENTCOLOR);
		btnPassSuspension.setBackground(PASSSTATIC);
		btnPassSuspension.setForeground(AMBIENTCOLOR);
		btnPassBrakes.setBackground(PASSSTATIC);
		btnPassBrakes.setForeground(AMBIENTCOLOR);
		btnPassEEmission.setBackground(PASSSTATIC);
		btnPassEEmission.setForeground(AMBIENTCOLOR);
		btnPassHeadLights.setBackground(PASSSTATIC);
		btnPassHeadLights.setForeground(AMBIENTCOLOR);
	}
	public void showLabels(){
		lblTestAlignment = new JLabel("Alignment: ");
		lblTestAlignment.setForeground(AMBIENTCOLOR);
		lblTestAlignment.setBounds(10, 10, 100, 20);
		lblTestAlignment.setFont(TEXTFONT);
		add(lblTestAlignment);
		
		lblTestSuspension = new JLabel("Suspension:");
		lblTestSuspension.setForeground(AMBIENTCOLOR);
		lblTestSuspension.setBounds(10, 40, 100, 20);
		lblTestSuspension.setFont(TEXTFONT);
		add(lblTestSuspension);
		
		lblTestBrakes = new JLabel("Brakes:");
		lblTestBrakes.setForeground(AMBIENTCOLOR);
		lblTestBrakes.setBounds(10, 70, 100, 20);
		lblTestBrakes.setFont(TEXTFONT);
		add(lblTestBrakes);
		
		lblTestEEmission = new JLabel("Exhaust Emission:");
		lblTestEEmission.setForeground(AMBIENTCOLOR);
		lblTestEEmission.setBounds(10, 100, 100, 20);
		lblTestEEmission.setFont(TEXTFONT);
		add(lblTestEEmission);
		
		lblTestHeadLights = new JLabel("Head Lights:");
		lblTestHeadLights.setForeground(AMBIENTCOLOR);
		lblTestHeadLights.setBounds(10, 130, 100, 20);
		lblTestHeadLights.setFont(TEXTFONT);
		add(lblTestHeadLights);
	}
	public void showButtons(){
		btnFailAlignment = new JLabel("Failed",SwingConstants.CENTER);
		btnFailAlignment.setBackground(FAILSTATIC);
		btnFailAlignment.setOpaque(true);
		btnFailAlignment.setForeground(AMBIENTCOLOR);
		btnFailAlignment.setBounds(110, 10, 70, 20);
		btnFailAlignment.addMouseListener(this);
		add(btnFailAlignment);
		
		btnFailSuspension = new JLabel("Failed",SwingConstants.CENTER);
		btnFailSuspension.setBackground(FAILSTATIC);
		btnFailSuspension.setOpaque(true);
		btnFailSuspension.setForeground(AMBIENTCOLOR);
		btnFailSuspension.setBounds(110, 40, 70, 20);
		btnFailSuspension.addMouseListener(this);
		add(btnFailSuspension);
		
		btnFailBrakes = new JLabel("Failed",SwingConstants.CENTER);
		btnFailBrakes.setBackground(FAILSTATIC);
		btnFailBrakes.setOpaque(true);
		btnFailBrakes.setForeground(AMBIENTCOLOR);
		btnFailBrakes.setBounds(110, 70, 70, 20);
		btnFailBrakes.addMouseListener(this);
		add(btnFailBrakes);
		
		btnFailEEmission = new JLabel("Failed",SwingConstants.CENTER);
		btnFailEEmission.setBackground(FAILSTATIC);
		btnFailEEmission.setOpaque(true);
		btnFailEEmission.setForeground(AMBIENTCOLOR);
		btnFailEEmission.setBounds(110, 100, 70, 20);
		btnFailEEmission.addMouseListener(this);
		add(btnFailEEmission);
		
		btnFailHeadLights = new JLabel("Failed",SwingConstants.CENTER);
		btnFailHeadLights.setBackground(FAILSTATIC);
		btnFailHeadLights.setOpaque(true);
		btnFailHeadLights.setForeground(AMBIENTCOLOR);
		btnFailHeadLights.setBounds(110, 130, 70, 20);
		btnFailHeadLights.addMouseListener(this);
		add(btnFailHeadLights);
		
		btnPassAlignment = new JLabel("Passed",SwingConstants.CENTER);
		btnPassAlignment.setBackground(PASSSTATIC);
		btnPassAlignment.setOpaque(true);
		btnPassAlignment.setForeground(AMBIENTCOLOR);
		btnPassAlignment.setBounds(180, 10, 70, 20);
		btnPassAlignment.addMouseListener(this);
		add(btnPassAlignment);
		
		btnPassSuspension = new JLabel("Passed",SwingConstants.CENTER);
		btnPassSuspension.setBackground(PASSSTATIC);
		btnPassSuspension.setOpaque(true);
		btnPassSuspension.setForeground(AMBIENTCOLOR);
		btnPassSuspension.setBounds(180, 40, 70, 20);
		btnPassSuspension.addMouseListener(this);
		add(btnPassSuspension);
		
		btnPassBrakes = new JLabel("Passed",SwingConstants.CENTER);
		btnPassBrakes.setBackground(PASSSTATIC);
		btnPassBrakes.setOpaque(true);
		btnPassBrakes.setForeground(AMBIENTCOLOR);
		btnPassBrakes.setBounds(180, 70, 70, 20);
		btnPassBrakes.addMouseListener(this);
		add(btnPassBrakes);
		
		btnPassEEmission = new JLabel("Passed",SwingConstants.CENTER);
		btnPassEEmission.setBackground(PASSSTATIC);
		btnPassEEmission.setOpaque(true);
		btnPassEEmission.setForeground(AMBIENTCOLOR);
		btnPassEEmission.setBounds(180, 100, 70, 20);
		btnPassEEmission.addMouseListener(this);
		add(btnPassEEmission);
		
		btnPassHeadLights = new JLabel("Passed",SwingConstants.CENTER);
		btnPassHeadLights.setBackground(PASSSTATIC);
		btnPassHeadLights.setOpaque(true);
		btnPassHeadLights.setForeground(AMBIENTCOLOR);
		btnPassHeadLights.setBounds(180, 130, 70, 20);
		btnPassHeadLights.addMouseListener(this);
		add(btnPassHeadLights);
		
		btnSubmit = new JLabel("");
		btnSubmit.setBounds(10, 160, 40, 40);
		btnSubmit.setIcon(submitB);
		btnSubmit.addMouseListener(this);
		add(btnSubmit);
		
		lblSubmitResults = new JLabel("Submit results");
		lblSubmitResults.setForeground(Color.LIGHT_GRAY);
		lblSubmitResults.setFont(TEXTFONT);
		lblSubmitResults.setBounds(60, 170, 100, 14);
		add(lblSubmitResults);
	}
	public void highlightButton(JLabel toHighlight, JLabel toDeHighlight, boolean pass){
		if(!pass){
			toHighlight.setBackground(FAILHOVER);
			toDeHighlight.setBackground(PASSSTATIC);
		}
		else{
			toHighlight.setBackground(PASSHOVER);
			toDeHighlight.setBackground(FAILSTATIC);
		}
		toHighlight.setForeground(HIGHLIGHTCOLOR);
		toDeHighlight.setForeground(AMBIENTCOLOR);
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(btnSubmit)){
			btnSubmit.setIcon(submitBHover);
			if (bookingTable.getSelectedRow() != -1){
				Booking currentBook = (Booking) bookingTable.getValueAt(bookingTable.getSelectedRow(), 0);
				if(alignmentChange && suspensionChange && brakesChange && eemissionChange && headlightsChange){
					String query = "INSERT INTO TestResults(User_Id, Car_Reg, Alignment, Suspension,"
							+ " Brakes, Exhaust_Emission, Head_Lights) VALUES ("
							+ userId + ",'"+currentBook.getCarReg()+"',"+alignment+","+suspension+","
							+ brakes+","+eemission+","+headlights+")";//,'"+getCurrentDate()+"')";
					if(mainClient.addTestResults(query) != false){
						// Delete the booking
						query = "DELETE FROM Booking WHERE Car_Reg='"+currentBook.getCarReg()+"'";
						mainClient.cancelBooking(query);
						// Update the table & label
						tableModel.removeRow(bookingTable.getSelectedRow());
						systemInfo.setText("Test results successfully added!");
						clearEverything();
						this.setVisible(false);
					}
					else{
						systemInfo.setText("Error: database unreachable!");
					}
				}
				else{
					systemInfo.setText("You must change all test values first!");
				}
			}
			else{
				systemInfo.setText("You must select a booking first!");
			}
		}
		else if(o.equals(btnFailAlignment)){
			if(alignment != 0|| !alignmentChange){
				highlightButton(btnFailAlignment, btnPassAlignment, false);
				alignmentChange = true;
				alignment = 0;
			}
		}
		else if(o.equals(btnFailSuspension)){
			if(suspension != 0|| !suspensionChange){
				highlightButton(btnFailSuspension, btnPassSuspension, false);
				suspensionChange = true;
				suspension = 0;
			}
		}
		else if(o.equals(btnFailBrakes)){
			if(brakes != 0|| !brakesChange){
				highlightButton(btnFailBrakes, btnPassBrakes, false);
				brakesChange = true;
				brakes = 0;
			}	
		}
		else if(o.equals(btnFailEEmission)){
			if(eemission != 0|| !eemissionChange){
				highlightButton(btnFailEEmission, btnPassEEmission, false);
				eemissionChange = true;
				eemission = 0;
			}
		}
		else if(o.equals(btnFailHeadLights)){
			if(headlights != 0|| !headlightsChange){
				highlightButton(btnFailHeadLights, btnPassHeadLights, false);
				headlightsChange = true;
				headlights = 0;
			}
		}
		else if(o.equals(btnPassAlignment)){
			if(alignment != 1|| !alignmentChange){
				highlightButton(btnPassAlignment, btnFailAlignment, true);
				alignmentChange = true;
				alignment = 1;
			}
		}
		else if(o.equals(btnPassSuspension)){
			if(suspension != 1|| !suspensionChange){
				highlightButton(btnPassSuspension, btnFailSuspension, true);
				suspensionChange = true;
				suspension = 1;
			}
		}
		else if(o.equals(btnPassBrakes)){
			if(brakes != 1|| !brakesChange){
				highlightButton(btnPassBrakes, btnFailBrakes, true);
				brakesChange = true;
				brakes = 1;
			}
		}
		else if(o.equals(btnPassEEmission)){
			if(eemission != 1|| !eemissionChange){
				highlightButton(btnPassEEmission, btnFailEEmission, true);
				eemissionChange = true;
				eemission = 1;
			}	
		}
		else if(o.equals(btnPassHeadLights)){
			if(headlights != 1|| !headlightsChange){
				highlightButton(btnPassHeadLights, btnFailHeadLights, true);
				headlightsChange = true;
				headlights = 1;
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
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
