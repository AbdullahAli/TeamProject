/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.order;

import infopharma.Validator;
import infopharma.acc.InfoPharmaFrame;
import infopharma.acc.InfoPharmaPanel;
import infopharma.acc.ViewMainMenu;
import infopharma.data.UserAccount;
import infopharma.data.MiscDBAccess;
import infopharma.data.Order;
import infopharma.data.OrderDBAccess;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Abdullah
 */
public class ViewMakePayment extends InfoPharmaPanel {
    
    private static InfoPharmaFrame frame;
    private OrderDBAccess dbOrder;
    private ArrayList<Order> unpaidOrders;
    //Dummy accountNumber until we store the logged in account somewhere
    private int dummyAccount = 1;
	
    public ViewMakePayment(InfoPharmaFrame mainMenuFrame) {
        dbOrder = new OrderDBAccess();
        getUnpaidOrders();
        initComponents();
        populateComboOrders();
        setFrame(mainMenuFrame);
        lblError.setVisible(false);
        this.setVisible(true);
    }

    public static InfoPharmaFrame getFrame()  {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void getUnpaidOrders() {
        unpaidOrders = dbOrder.getUnpaidMerchantOrders(dummyAccount);
    }
    
    public void populateComboOrders() {
        comboOrders.removeAllItems();
        for(Order order : unpaidOrders) {
            comboOrders.addItem(order.getID());
        }
    }
    
    public void displayDetails() {
        int orderId = Integer.parseInt(comboOrders.getSelectedItem().toString());
        Order order = getOrder(orderId);
        textDateOrdered.setText(order.getDate());
        textAmount.setText(order.getTotal() + "");
    }
    
    public Order getOrder(int orderId) {
        for(Order order : unpaidOrders) {
            if(order.getID() == orderId) {
                return order;
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioBtnGroup = new javax.swing.ButtonGroup();
        layeredPanel = new javax.swing.JLayeredPane();
        lblError = new javax.swing.JLabel();
        radioBtnCheque = new javax.swing.JRadioButton();
        radioBtnCard = new javax.swing.JRadioButton();
        comboOrders = new javax.swing.JComboBox();
        textAmount = new javax.swing.JTextField();
        textDateOrdered = new javax.swing.JTextField();
        txtAmount = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioBtnGroup.add(radioBtnCheque);
        radioBtnCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnChequeActionPerformed(evt);
            }
        });
        radioBtnCheque.setBounds(120, 210, 40, 23);
        layeredPanel.add(radioBtnCheque, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioBtnGroup.add(radioBtnCard);
        radioBtnCard.setSelected(true);
        radioBtnCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnCardActionPerformed(evt);
            }
        });
        radioBtnCard.setBounds(30, 210, 40, 23);
        layeredPanel.add(radioBtnCard, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrdersActionPerformed(evt);
            }
        });
        comboOrders.setBounds(30, 120, 270, 40);
        layeredPanel.add(comboOrders, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textAmount.setEditable(false);
        textAmount.setBounds(370, 200, 270, 40);
        layeredPanel.add(textAmount, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textDateOrdered.setEditable(false);
        textDateOrdered.setBounds(370, 120, 270, 40);
        layeredPanel.add(textDateOrdered, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAmount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/order/images/makepayment.png"))); // NOI18N
        txtAmount.setBounds(0, 0, 1100, 570);
        layeredPanel.add(txtAmount, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnMainMenu.setText("main menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        btnMainMenu.setBounds(1010, 10, 80, 50);
        layeredPanel.add(btnMainMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void radioBtnCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBtnCardActionPerformed

    private void radioBtnChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnChequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBtnChequeActionPerformed

    private void comboOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrdersActionPerformed
        displayDetails();
    }//GEN-LAST:event_comboOrdersActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JComboBox comboOrders;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JRadioButton radioBtnCard;
    private javax.swing.JRadioButton radioBtnCheque;
    private javax.swing.ButtonGroup radioBtnGroup;
    private javax.swing.JTextField textAmount;
    private javax.swing.JTextField textDateOrdered;
    private javax.swing.JLabel txtAmount;
    // End of variables declaration//GEN-END:variables
}
