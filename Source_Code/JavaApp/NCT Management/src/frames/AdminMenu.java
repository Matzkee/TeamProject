package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AdminMenu extends JFrame implements ActionListener{

	public AdminMenu() {
		initComponents();
	}
	                        
	private void initComponents() {
		
		menuLabel = new javax.swing.JLabel();
		addButton = new javax.swing.JButton();
		viewButton = new javax.swing.JButton();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		menuLabel.setText("Admin Menu");
		
		addButton.setText("Add Booking");
		
		viewButton.setText("View Bookings");
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(menuLabel)
								.addComponent(addButton)
								.addComponent(viewButton))
								.addGap(0, 128, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(menuLabel)
						.addGap(18, 18, 18)
						.addComponent(addButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(viewButton)
						.addGap(0, 56, Short.MAX_VALUE))
				);
		
		pack();
	}
	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AdminMenu().setVisible(true);
			}
		});
	}
	
	// Variables declaration - do not modify                     
	private javax.swing.JButton addButton;
	private javax.swing.JLabel menuLabel;
	private javax.swing.JButton viewButton;
	// End of variables declaration                   
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton){
			
		}
		else if(e.getSource() == viewButton){
			
		}
		
	}

}
