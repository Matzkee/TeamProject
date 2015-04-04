package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
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

	/**
	 * Variable declaration.
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Booking> allBookings = new ArrayList<>();
	private Client testClient;
	private JTextField txtCarReg, txtDate, txtTime;
	private JLabel lblR, lblD, lblT, btnDelete, btnEdit, btnSubmit;
	private TableModel tableModel;
	private JTable table;
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 12);
	
	// Image for buttons + scaling
	private ImageIcon editImage = new ImageIcon(BookingsTable.class.getResource("/graphics/edit_propertyy.png"));
	private ImageIcon deleteImage = new ImageIcon(BookingsTable.class.getResource("/graphics/deleteIcon.png"));
	private ImageIcon submitImage = new ImageIcon(BookingsTable.class.getResource("/graphics/submitB.png"));
	private final int buttonW = 40, buttonH = 40;
	private ImageIcon editB = new ImageIcon(editImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon deleteB = new ImageIcon(deleteImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));
	private ImageIcon submitB = new ImageIcon(submitImage.getImage().getScaledInstance(buttonW, buttonH, Image.SCALE_DEFAULT));


	/**
	 * Create the panel.
	 * Constructor
	 */
	public BookingsTable() {
		//setBackground(TRANSPARENTCOLOR);
		setOpaque(false);
		
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
		tableScrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		tableScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		tableScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableScrollPane.getViewport().setOpaque(false);
		tableScrollPane.setOpaque(false);
		tableScrollPane.setBounds(390, 25, 400, 300);
		add(tableScrollPane);
		// CarReg label
		JLabel lblCarRegistration = new JLabel("Car Registration: ");
		lblCarRegistration.setFont(TEXTFONT);
		lblCarRegistration.setForeground(Color.LIGHT_GRAY);
		lblCarRegistration.setBounds(10, 70, 99, 14);
		add(lblCarRegistration);
		// Date label
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setFont(TEXTFONT);
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setBounds(10, 95, 99, 14);
		add(lblDate);
		// Time label
		JLabel lblTime = new JLabel("Time: ");
		lblTime.setFont(TEXTFONT);
		lblTime.setForeground(Color.LIGHT_GRAY);
		lblTime.setBounds(10, 120, 99, 14);
		add(lblTime);
		// CarReg text field
		txtCarReg = new JTextField();
		txtCarReg.setBorder(null);
		txtCarReg.setEditable(false);
		txtCarReg.setForeground(Color.BLACK);
		txtCarReg.setFont(TEXTFONT);
		txtCarReg.setBounds(10, 227, 86, 20);
		add(txtCarReg);
		txtCarReg.setColumns(10);
		// Date text field
		txtDate = new JTextField();
		txtDate.setBorder(null);
		txtDate.setForeground(Color.BLACK);
		txtDate.setFont(TEXTFONT);
		txtDate.setEditable(false);
		txtDate.setBounds(10, 278, 86, 20);
		add(txtDate);
		txtDate.setColumns(10);
		// Time text field
		txtTime = new JTextField();
		txtTime.setBorder(null);
		txtTime.setForeground(Color.BLACK);
		txtTime.setFont(TEXTFONT);
		txtTime.setEditable(false);
		txtTime.setBounds(10, 329, 86, 20);
		add(txtTime);
		txtTime.setColumns(10);
		
		
		btnEdit = new JLabel("");
		btnEdit.setBounds(294, 278, 40, 40);
		btnEdit.setIcon(editB);
		btnEdit.addMouseListener(this);
		add(btnEdit);
		
		btnDelete = new JLabel("");
		btnDelete.setBounds(294, 329, 40, 40);
		btnDelete.setIcon(deleteB);
		btnDelete.addMouseListener(this);
		add(btnDelete);
		
		btnSubmit = new JLabel("");
		btnSubmit.setBounds(294, 227, 40, 40);
		btnSubmit.setIcon(submitB);
		btnSubmit.addMouseListener(this);
		add(btnSubmit);
		
		lblR = new JLabel("");
		lblR.setFont(TEXTFONT);
		lblR.setForeground(Color.LIGHT_GRAY);
		lblR.setBounds(120, 70, 100, 14);
		add(lblR);
		
		lblD = new JLabel("");
		lblD.setFont(TEXTFONT);
		lblD.setForeground(Color.LIGHT_GRAY);
		lblD.setBounds(120, 95, 100, 14);
		add(lblD);
		
		lblT = new JLabel("");
		lblT.setFont(TEXTFONT);
		lblT.setForeground(Color.LIGHT_GRAY);
		lblT.setBounds(120, 120, 100, 14);
		add(lblT);
		
		// Apply Table Mouse Listener
		// Set at the end to ensure components get declared before put into listener
		table.addMouseListener(new TableMouseListener(table,lblR, lblD, lblT));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btnDelete)){
			if (table.getSelectedRow() != -1){
				tableModel.removeRow(table.getSelectedRow());
			}
		}
		else if(o.equals(btnEdit)){
			if(txtCarReg.isEditable()){
				// set editable
				txtCarReg.setEditable(false);
				txtDate.setEditable(false);
				txtTime.setEditable(false);
			}
			else{
				// set editable
				txtCarReg.setEditable(true);
				txtDate.setEditable(true);
				txtTime.setEditable(true);
			}
		}
		else if(o.equals(btnSubmit)){
			if(table.getSelectedRow() != -1){
				tableModel.updateRow(table.getSelectedRow(), txtCarReg.getText(), txtDate.getText(), txtTime.getText());
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btnDelete)){
			if (table.getSelectedRow() != -1){
				tableModel.removeRow(table.getSelectedRow());
			}
		}
		else if(o.equals(btnEdit)){
			if(txtCarReg.isEditable()){
				// set editable
				txtCarReg.setEditable(false);
				txtDate.setEditable(false);
				txtTime.setEditable(false);
			}
			else{
				// set editable
				txtCarReg.setEditable(true);
				txtDate.setEditable(true);
				txtTime.setEditable(true);
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
		private JLabel mCarReg, mDate, mTime;
		private JTable mTable;
		private Booking mBooking;
		
		public TableMouseListener(JTable bTable, JLabel bCarReg, JLabel bDate, JLabel bTime){
			mCarReg = bCarReg;
			mDate = bDate;
			mTime = bTime;
			mTable = bTable;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = mTable.rowAtPoint(e.getPoint());
			mBooking = (Booking) mTable.getValueAt(row, 0);
			mCarReg.setText(mBooking.getCarReg());
			mDate.setText(mBooking.getDate());
			mTime.setText(mBooking.getTime());
		}
		@Override
		public void mousePressed(MouseEvent e) {
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
