package frames;

import javax.swing.JPanel;

import core.Client;
import core.TestResults;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

public class ResultPane extends JPanel implements MouseListener{
	
	/*
	 * Variable declaration
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TestResults> tests;
	private Client mainClient;
	private JTextField txtCarReg;
	JLabel btnSubmit;
	
	// Images & scaling
	private final int buttonW = 40, buttonH = 40;
	private ImageIcon submitImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmit.png"));
	private ImageIcon submitImageHover = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmitHover.png"));
	private ImageIcon submitB = new ImageIcon(submitImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon submitBHover = new ImageIcon(submitImageHover.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));

	/**
	 * Create the panel.
	 */
	public ResultPane(Client c) {
		mainClient = c;
		mainClient.viewTestResults(txtCarReg.getText());
		
		setSize(800,400);
		setOpaque(false);
		setLayout(null);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(Color.LIGHT_GRAY);
		lblSearch.setBounds(10, 25, 46, 14);
		add(lblSearch);
		
		JLabel lblInfo = new JLabel("Enter a valid Registration number to view previous test results");
		lblInfo.setForeground(Color.LIGHT_GRAY);
		lblInfo.setBounds(10, 60, 300, 14);
		add(lblInfo);
		
		txtCarReg = new JTextField();
		txtCarReg.setForeground(Color.LIGHT_GRAY);
		txtCarReg.setBorder(null);
		txtCarReg.setOpaque(false);
		txtCarReg.setBounds(10, 90, 100, 20);
		add(txtCarReg);
		txtCarReg.setColumns(10);
		
		JLabel btnSubmit = new JLabel("");
		btnSubmit.setBounds(140, 80, 40, 40);
		btnSubmit.setIcon(submitB);
		btnSubmit.addMouseListener(this);
		add(btnSubmit);
		
		JScrollPane tableScrollPane = new JScrollPane((Component) null);
		tableScrollPane.setOpaque(false);
		tableScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableScrollPane.setBounds(390, 25, 400, 300);
		add(tableScrollPane);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(btnSubmit)){
			btnSubmit.setIcon(submitBHover);
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
