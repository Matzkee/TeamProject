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
import javax.swing.border.EmptyBorder;

import components.TestsTableModel;
import components.TestsTableMouseListener;
import components.TestsTableRenderer;
import core.Client;
import core.TestResults;

public class ResultPane extends JPanel implements MouseListener{
	
	/*
	 * Variable declaration
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TestResults> tests;
	private Client mainClient;
	private JTextField txtCarReg;
	private JLabel btnSubmit;
	private JLabel systemLabel;
	private TestsTableModel tableModel;
	private JTable table;
	private JScrollPane tableScrollPane;
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 12);
	
	// Images & scaling
	private final int buttonW = 40, buttonH = 40;
	private ImageIcon submitImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmit.png"));
	private ImageIcon submitImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmitHover.png"));
	private ImageIcon submitB = new ImageIcon(submitImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon submitBHover = new ImageIcon(submitImageHover.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private JLabel lblTest1;
	private JLabel lblTest2;
	private JLabel lblTest3;
	private JLabel lblTest4;
	private JLabel lblTest5;

	/**
	 * Create the panel.
	 */
	public ResultPane(Client c, JLabel sysLabel) {
		mainClient = c;
		systemLabel = sysLabel;
		
		setSize(800,400);
		setOpaque(false);
		setLayout(null);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(Color.LIGHT_GRAY);
		lblSearch.setBounds(10, 25, 46, 14);
		lblSearch.setFont(TEXTFONT);
		add(lblSearch);
		
		JLabel lblInfo = new JLabel("Enter a valid Registration number to view previous test results");
		lblInfo.setForeground(Color.LIGHT_GRAY);
		lblInfo.setFont(TEXTFONT);
		lblInfo.setBounds(10, 60, 350, 14);
		add(lblInfo);
		
		txtCarReg = new JTextField();
		txtCarReg.setForeground(Color.LIGHT_GRAY);
		txtCarReg.setFont(TEXTFONT);
		txtCarReg.setBorder(null);
		txtCarReg.setOpaque(false);
		txtCarReg.setBounds(10, 90, 100, 20);
		add(txtCarReg);
		txtCarReg.setColumns(10);
		
		btnSubmit = new JLabel("");
		btnSubmit.setBounds(140, 80, 40, 40);
		btnSubmit.setIcon(submitB);
		btnSubmit.addMouseListener(this);
		add(btnSubmit);
		
		lblTest1 = new JLabel("");
		lblTest1.setBounds(10, 170, 150, 14);
		lblTest1.setForeground(Color.LIGHT_GRAY);
		lblTest1.setFont(TEXTFONT);
		add(lblTest1);
		
		lblTest2 = new JLabel("");
		lblTest2.setBounds(10, 195, 150, 14);
		lblTest2.setForeground(Color.LIGHT_GRAY);
		lblTest2.setFont(TEXTFONT);
		add(lblTest2);
		
		lblTest3 = new JLabel("");
		lblTest3.setBounds(10, 220, 150, 14);
		lblTest3.setForeground(Color.LIGHT_GRAY);
		lblTest3.setFont(TEXTFONT);
		add(lblTest3);
		
		lblTest4 = new JLabel("");
		lblTest4.setBounds(10, 245, 150, 14);
		lblTest4.setForeground(Color.LIGHT_GRAY);
		lblTest4.setFont(TEXTFONT);
		add(lblTest4);
		
		lblTest5 = new JLabel("");
		lblTest5.setBounds(10, 270, 150, 14);
		lblTest5.setForeground(Color.LIGHT_GRAY);
		lblTest5.setFont(TEXTFONT);
		add(lblTest5);
	}
	
	public void showTable(){
		// Setting up the table
		tableModel = new TestsTableModel(tests);
		table = new JTable(tableModel);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setBorder(null);
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(TestResults.class, new TestsTableRenderer());
		table.setRowHeight(40);
		table.setTableHeader(null);
		table.addMouseListener(new TestsTableMouseListener(table, lblTest1, lblTest2, lblTest3, lblTest4, lblTest5, systemLabel));
		// ScrollPane for the table
		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableScrollPane.getViewport().setOpaque(false);
		tableScrollPane.setOpaque(false);
		tableScrollPane.setBounds(390, 25, 400, 300);
		add(tableScrollPane);
	}
	
	public void clearFields(){
		lblTest1.setText("");
		lblTest2.setText("");
		lblTest3.setText("");
		lblTest4.setText("");
		lblTest5.setText("");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp;
		gp = new GradientPaint(txtCarReg.getX()+(txtCarReg.getWidth()/2),txtCarReg.getY()+txtCarReg.getHeight(),new Color(255,255,255,50),
				txtCarReg.getX()+(txtCarReg.getWidth()/2),txtCarReg.getY()+(txtCarReg.getHeight()/4),new Color(0,0,0,0));
		g2d.setPaint(gp);
		g2d.fillRect(txtCarReg.getX(), txtCarReg.getY(), txtCarReg.getWidth(), txtCarReg.getHeight());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(btnSubmit)){
			btnSubmit.setIcon(submitBHover);
			if(mainClient.connectionTest() != false){
				if(mainClient.viewTestResults(txtCarReg.getText()) != false){
					tests = mainClient.getTestResults();
					if(tableScrollPane != null){
						remove(tableScrollPane);
						clearFields();
					}
					showTable();
					systemLabel.setText("Found data!");
				}
				else{
					systemLabel.setText("Error: no data found or invalid car registration!");
				}				
			}
			else{
				systemLabel.setText("Conenction failure!");
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
