package frames;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import core.Booking;
import core.Client;

public class BookingsTable extends JPanel implements MouseListener{

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


	/**
	 * Variable declaration.
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Booking> allBookings = new ArrayList<>();
	private Client testClient;
	private JTextField txtCarReg, txtDate, txtTime;
	private JLabel btnDelete, btnEditCarReg, btnEditDate, btnEditTime, btnSubmit, btnAdd;
	private JLabel lblSubmit, lblDelete;
	private TableModel tableModel;
	private JTable table;
	private boolean isCarRegEditToggled, isDateEditToggled, isTimeEditToggled;
	/* Text Font */
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 13);
	private final Font SMALLTEXTFONT = new Font("Segoe UI", Font.PLAIN, 10);
	
	// Image for buttons + scaling
	private ImageIcon editImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgEdit.png"));
	private ImageIcon deleteImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgDelete.png"));
	private ImageIcon submitImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgSubmit.png"));
	private ImageIcon addImage = new ImageIcon(BookingsTable.class.getResource("/graphics/imgAdd.png"));
	private final int buttonW = 40, buttonH = 40;
	private ImageIcon addB = new ImageIcon(addImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon editB = new ImageIcon(editImage.getImage().getScaledInstance(buttonW-20, buttonH-20, Image.SCALE_DEFAULT));
	private ImageIcon deleteB = new ImageIcon(deleteImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon submitB = new ImageIcon(submitImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private JLabel lblNew;


	/**
	 * Create the panel.
	 * Constructor
	 */
	public BookingsTable() {
		setOpaque(false);
		addMouseListener(this);
		isCarRegEditToggled = false;
		isDateEditToggled = false;
		isTimeEditToggled = false;
		
		testClient = new Client();
		testClient.viewBookings();
		
		allBookings = testClient.getBookings();
		
		// Panel Size
		setSize(800,400);
		
		// Setting up the table
		tableModel = new TableModel(allBookings);
		table = new JTable(tableModel);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setBorder(null);
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Booking.class, new TableRenderer());
		setLayout(null);
		table.setRowHeight(40);
		table.setTableHeader(null);
		// ScrollPane for the table
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableScrollPane.getViewport().setOpaque(false);
		tableScrollPane.setOpaque(false);
		tableScrollPane.setBounds(390, 25, 400, 300);
		add(tableScrollPane);
		// CarReg label
		JLabel lblCarRegistration = new JLabel("Car Registration: ");
		lblCarRegistration.setFont(TEXTFONT);
		lblCarRegistration.setForeground(Color.LIGHT_GRAY);
		lblCarRegistration.setBounds(10, 70, 110, 20);
		add(lblCarRegistration);
		// Date label
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setFont(TEXTFONT);
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setBounds(10, 100, 110, 20);
		add(lblDate);
		// Time label
		JLabel lblTime = new JLabel("Time: ");
		lblTime.setFont(TEXTFONT);
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setBounds(10, 130, 110, 20);
		add(lblTime);
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
		
		
		btnEditCarReg = new JLabel("");
		btnEditCarReg.setBounds(240, 70, 20, 20);
		btnEditCarReg.setIcon(editB);
		btnEditCarReg.addMouseListener(this);
		add(btnEditCarReg);
		
		btnEditDate = new JLabel("");
		btnEditDate.setBounds(240, 100, 20, 20);
		btnEditDate.setIcon(editB);
		btnEditDate.addMouseListener(this);
		add(btnEditDate);
		
		btnEditTime = new JLabel("");
		btnEditTime.setBounds(240, 130, 20, 20);
		btnEditTime.setIcon(editB);
		btnEditTime.addMouseListener(this);
		add(btnEditTime);
		
		btnDelete = new JLabel("");
		btnDelete.setBounds(110, 170, 40, 40);
		btnDelete.setIcon(deleteB);
		btnDelete.addMouseListener(this);
		add(btnDelete);
		
		btnSubmit = new JLabel("");
		btnSubmit.setBounds(60, 170, 40, 40);
		btnSubmit.setIcon(submitB);
		btnSubmit.addMouseListener(this);
		add(btnSubmit);
		
		JLabel lblEdit = new JLabel("Edit");
		lblEdit.setBounds(240, 55, 20, 14);
		lblEdit.setForeground(Color.LIGHT_GRAY);
		lblEdit.setFont(SMALLTEXTFONT);
		add(lblEdit);
		
		lblSubmit = new JLabel("Submit");
		lblSubmit.setBounds(63, 155, 32, 14);
		lblSubmit.setForeground(Color.LIGHT_GRAY);
		lblSubmit.setFont(SMALLTEXTFONT);
		add(lblSubmit);
		
		lblDelete = new JLabel("Delete");
		lblDelete.setBounds(115, 155, 32, 14);
		lblDelete.setForeground(Color.LIGHT_GRAY);
		lblDelete.setFont(SMALLTEXTFONT);
		add(lblDelete);
			
		btnAdd = new JLabel("");
		btnAdd.setBounds(10, 170, 40, 40);
		btnAdd.setIcon(addB);
		add(btnAdd);
		
		lblNew = new JLabel("New");
		lblNew.setBounds(20, 155, 32, 12);
		lblNew.setForeground(Color.LIGHT_GRAY);
		lblNew.setFont(SMALLTEXTFONT);
		add(lblNew);
		
		// Assign mouse listener to table
		table.addMouseListener(new TableMouseListener(table, txtCarReg, txtDate, txtTime));
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		
		if(o.equals(this)){
			if (table.getSelectedRow() != -1){
				table.clearSelection();
			}
		}
		else if(o.equals(btnDelete)){
			if (table.getSelectedRow() != -1){
				tableModel.removeRow(table.getSelectedRow());
			}
		}
		else if(o.equals(btnEditCarReg)){
			if (table.getSelectedRow() != -1){
				if(txtCarReg.isEditable()){
					// set editable
					toggleFields(false, false, false);
					toggleFieldPaints(false, false, false);
					repaint();
				}
				else{
					// set editable
					toggleFields(true, false, false);
					toggleFieldPaints(true, false, false);
					repaint();
				}
			}
		}
		else if(o.equals(btnEditDate)){
			if (table.getSelectedRow() != -1){
				if(txtDate.isEditable()){
					// set editable
					toggleFields(false, false, false);
					toggleFieldPaints(false, false, false);
					repaint();
				}
				else{
					// set editable
					toggleFields(false, true, false);
					toggleFieldPaints(false, true, false);
					repaint();
				}
			}
		}
		else if(o.equals(btnEditTime)){
			if (table.getSelectedRow() != -1){
				if(txtTime.isEditable()){
					// set editable
					toggleFields(false, false, false);
					toggleFieldPaints(false, false, false);
					repaint();
				}
				else{
					// set editable
					toggleFields(false, false, true);
					toggleFieldPaints(false, false, true);
					repaint();
				}
			}
		}
		else if(o.equals(btnSubmit)){
			if(table.getSelectedRow() != -1){
				tableModel.updateRow(table.getSelectedRow(), txtCarReg.getText(), txtDate.getText(), txtTime.getText());
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	// Table Mouse Listener
	class TableMouseListener implements MouseListener{
		private JTextField mCarReg, mDate, mTime;
		private JTable mTable;
		private Booking mBooking;
		
		public TableMouseListener(JTable bTable, JTextField bCarReg, JTextField bDate, JTextField bTime){
			mCarReg = bCarReg;
			mDate = bDate;
			mTime = bTime;
			mTable = bTable;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
			int row = mTable.rowAtPoint(e.getPoint());
			mBooking = (Booking) mTable.getValueAt(row, 0);
			mCarReg.setText(mBooking.getCarReg());
			mDate.setText(mBooking.getDate());
			mTime.setText(mBooking.getTime());
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	// Table Cell Renderer
	// renders each cell
	class TableRenderer implements TableCellRenderer{
		TableRow cell;
		
		// Constructor
		public TableRenderer(){
			cell = new TableRow();
		}
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Booking book = (Booking) value;
			cell.setRowLabels(book, isSelected, table);
			
			return cell;
		}
	}
	
	
	// Table Model
	// contains all info from Booking classes
	class TableModel extends AbstractTableModel{

		private static final long serialVersionUID = 1L;
		private ArrayList<Booking> bookings = new ArrayList<>();
		
		public TableModel(ArrayList<Booking> newBookings){
			bookings = newBookings;
		}
		
		// Override methods from AbstractTableModel
		@Override
		public String getColumnName(int columnIndex){
			return "Booking";
		}
		@Override
		public Class<Booking> getColumnClass(int columnIndex){
			return Booking.class;
		}
		@Override
		public int getRowCount() {
			return (bookings == null) ? null : bookings.size();
		}
		@Override
		public int getColumnCount() {
			return 1;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return (bookings == null) ? null : bookings.get(rowIndex);
		}
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			bookings.set(rowIndex, (Booking) aValue);
			fireTableCellUpdated(rowIndex, 0);
		}
		@Override
		public boolean isCellEditable(int columnIndex, int rowIndex){
			return false;
		}
		public void removeRow(int rowIndex){
			bookings.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
			System.out.println("Deleted row: " + rowIndex);
		}
		public void updateRow(int rowIndex, String carReg, String date, String time){
			Booking tempBook = bookings.get(rowIndex);
			tempBook.setCarReg(carReg);
			tempBook.setDate(date);
			tempBook.setTime(time);
			setValueAt(tempBook, rowIndex, 0);
		}
	}
}
