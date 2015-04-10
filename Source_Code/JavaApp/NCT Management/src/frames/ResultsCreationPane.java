package frames;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;

public class ResultsCreationPane extends JPanel {
	/*
	 * Variable declaration
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTestAlignment, lblTestSuspension, lblTestBrakes, lblTestEEmission, lblTestHeadLights;
	private JLabel btnFailAlignment, btnFailSuspension, btnFailBrakes, btnFailEEmission, btnFailHeadLights;
	private JLabel btnPassAlignment, btnPassSuspension, btnPassBrakes, btnPassEEmission, btnPassHeadLights;
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 13);
	private final Color FAILSTATIC = new Color(121,0,0);
	private final Color FAILHOVER = new Color(237,28,36);
	private final Color PASSSTATIC = new Color(0,94,32);
	private final Color PASSHOVER= new Color(57,181,74);

	/**
	 * Create the panel.
	 */
	public ResultsCreationPane() {
		setSize(300,200);
		setOpaque(false);
		setLayout(null);
		
		showLabels();
		showButtons();
	}
	public void showLabels(){
		lblTestAlignment = new JLabel("Alignment: ");
		lblTestAlignment.setForeground(Color.LIGHT_GRAY);
		lblTestAlignment.setBounds(10, 10, 100, 20);
		lblTestAlignment.setFont(TEXTFONT);
		add(lblTestAlignment);
		
		lblTestSuspension = new JLabel("Suspension:");
		lblTestSuspension.setForeground(Color.LIGHT_GRAY);
		lblTestSuspension.setBounds(10, 40, 100, 20);
		lblTestSuspension.setFont(TEXTFONT);
		add(lblTestSuspension);
		
		lblTestBrakes = new JLabel("Brakes:");
		lblTestBrakes.setForeground(Color.LIGHT_GRAY);
		lblTestBrakes.setBounds(10, 70, 100, 20);
		lblTestBrakes.setFont(TEXTFONT);
		add(lblTestBrakes);
		
		lblTestEEmission = new JLabel("Exhaust Emission:");
		lblTestEEmission.setForeground(Color.LIGHT_GRAY);
		lblTestEEmission.setBounds(10, 100, 100, 20);
		lblTestEEmission.setFont(TEXTFONT);
		add(lblTestEEmission);
		
		lblTestHeadLights = new JLabel("Head Lights:");
		lblTestHeadLights.setForeground(Color.LIGHT_GRAY);
		lblTestHeadLights.setBounds(10, 130, 100, 20);
		lblTestHeadLights.setFont(TEXTFONT);
		add(lblTestHeadLights);
	}
	public void showButtons(){
		btnFailAlignment = new JLabel("Failed",SwingConstants.CENTER);
		btnFailAlignment.setBackground(FAILSTATIC);
		btnFailAlignment.setOpaque(true);
		btnFailAlignment.setForeground(Color.LIGHT_GRAY);
		btnFailAlignment.setBounds(120, 10, 60, 20);
		add(btnFailAlignment);
		
		btnFailSuspension = new JLabel("Failed",SwingConstants.CENTER);
		btnFailSuspension.setBackground(FAILSTATIC);
		btnFailSuspension.setOpaque(true);
		btnFailSuspension.setForeground(Color.LIGHT_GRAY);
		btnFailSuspension.setBounds(120, 40, 60, 20);
		add(btnFailSuspension);
		
		btnFailBrakes = new JLabel("Failed",SwingConstants.CENTER);
		btnFailBrakes.setBackground(FAILSTATIC);
		btnFailBrakes.setOpaque(true);
		btnFailBrakes.setForeground(Color.LIGHT_GRAY);
		btnFailBrakes.setBounds(120, 70, 60, 20);
		add(btnFailBrakes);
		
		btnFailEEmission = new JLabel("Failed",SwingConstants.CENTER);
		btnFailEEmission.setBackground(FAILSTATIC);
		btnFailEEmission.setOpaque(true);
		btnFailEEmission.setForeground(Color.LIGHT_GRAY);
		btnFailEEmission.setBounds(120, 100, 60, 20);
		add(btnFailEEmission);
		
		btnFailHeadLights = new JLabel("Failed",SwingConstants.CENTER);
		btnFailHeadLights.setBackground(FAILSTATIC);
		btnFailHeadLights.setOpaque(true);
		btnFailHeadLights.setForeground(Color.LIGHT_GRAY);
		btnFailHeadLights.setBounds(120, 130, 60, 20);
		add(btnFailHeadLights);
		
		btnPassAlignment = new JLabel("Passed",SwingConstants.CENTER);
		btnPassAlignment.setBackground(Color.WHITE);
		btnPassAlignment.setOpaque(true);
		btnPassAlignment.setForeground(Color.LIGHT_GRAY);
		btnPassAlignment.setBounds(180, 10, 60, 20);
		add(btnPassAlignment);
		
		btnPassSuspension = new JLabel("Passed",SwingConstants.CENTER);
		btnPassSuspension.setBackground(Color.WHITE);
		btnPassSuspension.setOpaque(true);
		btnPassSuspension.setForeground(Color.LIGHT_GRAY);
		btnPassSuspension.setBounds(180, 40, 60, 20);
		add(btnPassSuspension);
		
		btnPassBrakes = new JLabel("Passed",SwingConstants.CENTER);
		btnPassBrakes.setBackground(Color.WHITE);
		btnPassBrakes.setOpaque(true);
		btnPassBrakes.setForeground(Color.LIGHT_GRAY);
		btnPassBrakes.setBounds(180, 70, 60, 20);
		add(btnPassBrakes);
		
		btnPassEEmission = new JLabel("Passed",SwingConstants.CENTER);
		btnPassEEmission.setBackground(Color.WHITE);
		btnPassEEmission.setOpaque(true);
		btnPassEEmission.setForeground(Color.LIGHT_GRAY);
		btnPassEEmission.setBounds(180, 100, 60, 20);
		add(btnPassEEmission);
		
		btnPassHeadLights = new JLabel("Passed",SwingConstants.CENTER);
		btnPassHeadLights.setBackground(Color.WHITE);
		btnPassHeadLights.setOpaque(true);
		btnPassHeadLights.setForeground(Color.LIGHT_GRAY);
		btnPassHeadLights.setBounds(180, 130, 60, 20);
		add(btnPassHeadLights);
	}

}
