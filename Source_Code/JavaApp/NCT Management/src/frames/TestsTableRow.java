package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import core.TestResults;

public class TestsTableRow extends JPanel{

	private boolean isHighlighted = false;
	
	
	// Paint Component -- mostly used for gradient highlight
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp;
		if(isHighlighted){
			gp = new GradientPaint(getWidth()/2,getHeight(),new Color(250,150,25,100),
					getWidth()/2,getHeight()/3,new Color(0,0,0,0));			
		}
		else{
			gp = new GradientPaint(getWidth()/2,getHeight(),new Color(255,255,255,50),
					getWidth()/2,getHeight()-10,new Color(0,0,0,0));
		}
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	private static final long serialVersionUID = 1L;
	
	// Components declaration
	private JLabel lblTest;
	
	/**
	 * Create the panel.
	 * Constructor
	 */
	public TestsTableRow() {
		//setForeground(Color.BLACK);
		//setBackground(Color.DARK_GRAY);
		setLayout(null);
		setOpaque(false);
		
		lblTest = new JLabel("Test");
		lblTest.setForeground(Color.LIGHT_GRAY);
		lblTest.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTest.setBounds(5, 11, 80, 14);
		add(lblTest);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(86, 5, 1, 30);
		add(separator);
	}
	public void setRowLabels(TestResults test, boolean isSelected, JTable table){
		lblTest.setText("Test");
		
		if(isSelected){
			isHighlighted = true;
			repaint();
		}
		else{
			isHighlighted = false;
			repaint();
		}
	}
}
