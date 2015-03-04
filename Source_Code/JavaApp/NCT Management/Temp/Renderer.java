/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom.list;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Mati
 */
public class Renderer extends JPanel implements ListCellRenderer<Booking>{

    private JLabel dLabel, tLabel, cLabel;
    private Image image;
    
    public Renderer(){
        dLabel = new JLabel();
        tLabel = new JLabel();
        cLabel = new JLabel();
        
        try {
            image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("/custom/list/Cell.jpg"), "Cell.jpg"));
        } catch (Exception ex) {
            Logger.getLogger(Renderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dLabel)
                    .addComponent(tLabel)
                    .addComponent(cLabel))
                .addGap(0, 166, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cLabel))
        );
        
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Booking> list, Booking booking, int index, 
            boolean isSelected, boolean cellHasFocus) {
        
        dLabel.setText(booking.getDate());
        tLabel.setText(booking.getTime());
        cLabel.setText(booking.getCarReg());
        
        return this;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
    
}
