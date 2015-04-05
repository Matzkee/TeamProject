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

import core.Booking;

public class TableRow extends JPanel{

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
	private JLabel lblCarReg;
	private JLabel lblDate;
	private JLabel lblTime;
	
	/**
	 * Create the panel.
	 * Constructor
	 */
	public TableRow() {
		//setForeground(Color.BLACK);
		//setBackground(Color.DARK_GRAY);
		setLayout(null);
		setOpaque(false);
		
		lblCarReg = new JLabel("Car Registration");
		lblCarReg.setForeground(Color.LIGHT_GRAY);
		lblCarReg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCarReg.setBounds(10, 11, 119, 14);
		add(lblCarReg);
		
		lblDate = new JLabel("Date");
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDate.setBounds(150, 11, 78, 14);
		add(lblDate);
		
		lblTime = new JLabel("Time");
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTime.setBounds(250, 11, 78, 14);
		add(lblTime);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBounds(239, 5, 1, 30);
		add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(139, 5, 1, 30);
		add(separator);
	}
	public void setRowLabels(Booking book, boolean isSelected, JTable table){
		lblCarReg.setText(book.getCarReg());
		lblDate.setText(book.getDate());
		lblTime.setText(book.getTime());
		
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
