package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
	private JTextField txtCarReg;
	private JTextField txtDate;
	private JTextField txtTime;
	private TableModel tableModel;
	private JTable table;
	private final Color TRANSPARENTCOLOR = new Color(0,0,0,0);
	private final Font TEXTFONT = new Font("Segoe UI", Font.PLAIN, 12);

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
		setSize(400,380);
		
		// Setting up the table
		tableModel = new TableModel(allBookings);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.DARK_GRAY);
		table.setDefaultRenderer(Booking.class, new TableRenderer());
		setLayout(null);
		table.setRowHeight(40);
		table.setTableHeader(null);
		// ScrollPane for the table
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBackground(Color.DARK_GRAY);
		tableScrollPane.setBounds(0, 30, 400, 170);
		add(tableScrollPane);
		// Header Panel
		JPanel headerPane = new JPanel();
		headerPane.setOpaque(false);
		headerPane.setBounds(0, 0, 400, 30);
		add(headerPane);
		headerPane.setLayout(null);
		// Label for the header panel
		JLabel lblHeader = new JLabel("Bookings");
		lblHeader.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblHeader.setForeground(Color.LIGHT_GRAY);
		lblHeader.setBounds(5, 0, 100, 30);
		headerPane.add(lblHeader);
		// Separator inside header panel
		JSeparator headerSeparator = new JSeparator();
		headerSeparator.setForeground(Color.LIGHT_GRAY);
		headerSeparator.setBounds(100, 25, 275, 1);
		headerPane.add(headerSeparator);
		// CarReg label
		JLabel lblCarRegistration = new JLabel("Car Registration: ");
		lblCarRegistration.setFont(TEXTFONT);
		lblCarRegistration.setForeground(Color.WHITE);
		lblCarRegistration.setBounds(10, 211, 99, 14);
		add(lblCarRegistration);
		// Date label
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setFont(TEXTFONT);
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(10, 236, 99, 14);
		add(lblDate);
		// Time label
		JLabel lblTime = new JLabel("Time: ");
		lblTime.setFont(TEXTFONT);
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(10, 261, 99, 14);
		add(lblTime);
		// CarReg text field
		txtCarReg = new JTextField();
		txtCarReg.setBorder(null);
		txtCarReg.setEditable(false);
		txtCarReg.setForeground(Color.BLACK);
		txtCarReg.setFont(TEXTFONT);
		txtCarReg.setBounds(119, 209, 86, 20);
		add(txtCarReg);
		txtCarReg.setColumns(10);
		// Date text field
		txtDate = new JTextField();
		txtDate.setBorder(null);
		txtDate.setForeground(Color.BLACK);
		txtDate.setFont(TEXTFONT);
		txtDate.setEditable(false);
		txtDate.setBounds(119, 234, 86, 20);
		add(txtDate);
		txtDate.setColumns(10);
		// Time text field
		txtTime = new JTextField();
		txtTime.setBorder(null);
		txtTime.setForeground(Color.BLACK);
		txtTime.setFont(TEXTFONT);
		txtTime.setEditable(false);
		txtTime.setBounds(119, 259, 86, 20);
		add(txtTime);
		txtTime.setColumns(10);
		// Delete Booking button
		JButton btnDeleteBooking = new JButton("Delete Booking");
		btnDeleteBooking.setBounds(280, 258, 110, 23);
		btnDeleteBooking.addMouseListener(this);
		add(btnDeleteBooking);
		// ToggleModify button
		JButton btnEditToggle = new JButton("Toggle Edit");
		btnEditToggle.setBounds(280, 208, 110, 23);
		btnEditToggle.addMouseListener(this);
		add(btnEditToggle);
		// Submit Changes button
		JButton btnSubmitChanges = new JButton("Submit Changes");
		btnSubmitChanges.setBounds(10, 290, 195, 23);
		btnSubmitChanges.addMouseListener(this);
		add(btnSubmitChanges);
		
		// Apply Table Mouse Listener
		// Set at the end to ensure components get declared before put into listener
		table.addMouseListener(new TableMouseListener(table,txtCarReg, txtDate, txtTime));
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		switch(button.getText()){
			case "Delete Booking":
				if (table.getSelectedRow() != -1){
					tableModel.removeRow(table.getSelectedRow());
				}
			break;
			case "Toggle Edit":
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
			break;
			case "Submit Changes":
				if(table.getSelectedRow() != -1){
					tableModel.updateRow(table.getSelectedRow(), txtCarReg.getText(), txtDate.getText(), txtTime.getText());
				}
			break;
		}
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
	
	// Table Mouse Listener
	class TableMouseListener implements MouseListener{
		private JTextField mCarReg;
		private JTextField mDate;
		private JTextField mTime;
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
