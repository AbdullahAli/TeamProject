/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.order;

import infopharma.Validator;
import infopharma.acc.InfoPharmaFrame;
import infopharma.acc.InfoPharmaPanel;
import infopharma.acc.ViewMainMenu;
import infopharma.data.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abdullah
 */
public class ViewOrderHistory extends InfoPharmaPanel{
    private static InfoPharmaFrame frame;
    private OrderDBAccess ordDBAccess;
    private DefaultTableModel model;
	
    public ViewOrderHistory(InfoPharmaFrame mainMenuFrame)
    {
        
        ordDBAccess = new OrderDBAccess();
        initComponents();
        model = (DefaultTableModel) tblOrders.getModel();
        populateTable();
        setFrame(mainMenuFrame);
        this.setVisible(true);
    }

    public static InfoPharmaFrame getFrame() 
    {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) 
    {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public String convertToDoubleWithoutPrecisionLose(String e)
    {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");  
        if (!e.isEmpty())
        {
            try
            {
                double d = Double.parseDouble(e);
                return df.format(d);  
            }
            catch(Exception err){}             
        }
        return "0.00";
    }

    public void populateTable()
    {
        ArrayList<Order> orders = ordDBAccess.getMerchantOrders(MerchantAccount.getAccountNumber());
        for(Order order : orders)
        {
            model.addRow(new Object[] {order.getID(), order.getDate(), convertToDoubleWithoutPrecisionLose(""+order.getTotal())});
        }
        System.out.println("populated");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layeredPanel = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        imageLabel = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();
        btnMainMenu1 = new javax.swing.JButton();

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Date", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblOrders);

        jScrollPane1.setBounds(30, 150, 590, 370);
        layeredPanel.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/order/images/orderhistory.png"))); // NOI18N
        imageLabel.setBounds(0, 0, 1100, 570);
        layeredPanel.add(imageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnMainMenu.setText("main menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        btnMainMenu.setBounds(1010, 10, 80, 50);
        layeredPanel.add(btnMainMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnMainMenu1.setText("main menu");
        btnMainMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenu1ActionPerformed(evt);
            }
        });
        btnMainMenu1.setBounds(1020, 80, 70, 490);
        layeredPanel.add(btnMainMenu1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layeredPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layeredPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 570, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        // TODO add your handling code here:
        this.getFrame().setPanel(new ViewMainMenu(this.getFrame()));
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void btnMainMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenu1ActionPerformed
        mainMenu();
    }//GEN-LAST:event_btnMainMenu1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnMainMenu1;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JTable tblOrders;
    // End of variables declaration//GEN-END:variables
}
