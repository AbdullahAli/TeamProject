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
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Abdullah
 */
public class ViewDispatchOrder extends InfoPharmaPanel {
    private static InfoPharmaFrame frame;
    private OrderDBAccess dbOrder;
    private AccountDBAccess dbAccount;
    private ArrayList<Order> ordersArray;
    private HashMap<Integer, String> orderStatuses;
	
    public ViewDispatchOrder(InfoPharmaFrame mainMenuFrame) {
        dbOrder = new OrderDBAccess();
        dbAccount = new AccountDBAccess();
        ordersArray = dbOrder.getAllUndispatchedOrders();
        orderStatuses = dbOrder.getOrderStatuses();
        initComponents();
        populateComboOrders();
        setFrame(mainMenuFrame);
        lblError.setVisible(false);
        this.setVisible(true);
    }

    public static InfoPharmaFrame getFrame() {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void populateComboOrders() {
        for(Order order : ordersArray) {
            comboOrders.addItem(order.getID());
        }
    }
    
    public void displayDetails() {
        int orderId = Integer.parseInt(comboOrders.getSelectedItem().toString());
        Order order = getOrder(orderId);
        String status = orderStatuses.get(order.getStatusID());
        MerchantAccount merchant = dbAccount.getMerchantByOrder(orderId);
        textStatus.setText(status);
        textCompany.setText(merchant.getCompany());
    }
    
    public Order getOrder(int orderId) {
        for(Order order : ordersArray) {
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

        layeredPanel = new javax.swing.JLayeredPane();
        comboOrders = new javax.swing.JComboBox();
        textStatus = new javax.swing.JTextField();
        textDispatcher = new javax.swing.JTextField();
        textCourier = new javax.swing.JTextField();
        textCourierRef = new javax.swing.JTextField();
        dateDelivery = new com.toedter.calendar.JDateChooser();
        dateDispatch = new com.toedter.calendar.JDateChooser();
        textCompany = new javax.swing.JTextField();
        lblError = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();

        comboOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrdersActionPerformed(evt);
            }
        });
        comboOrders.setBounds(30, 120, 270, 40);
        layeredPanel.add(comboOrders, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textStatus.setEditable(false);
        textStatus.setBounds(370, 200, 270, 40);
        layeredPanel.add(textStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textDispatcher.setBounds(30, 200, 270, 40);
        layeredPanel.add(textDispatcher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textCourier.setBounds(30, 360, 270, 40);
        layeredPanel.add(textCourier, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textCourierRef.setBounds(30, 440, 270, 40);
        layeredPanel.add(textCourierRef, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dateDelivery.setBounds(30, 520, 270, 40);
        layeredPanel.add(dateDelivery, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dateDispatch.setBounds(30, 280, 270, 40);
        layeredPanel.add(dateDispatch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textCompany.setBounds(370, 120, 270, 40);
        layeredPanel.add(textCompany, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/order/images/dispatchorders.png"))); // NOI18N
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

    private void comboOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrdersActionPerformed
        displayDetails();
    }//GEN-LAST:event_comboOrdersActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JComboBox comboOrders;
    private com.toedter.calendar.JDateChooser dateDelivery;
    private com.toedter.calendar.JDateChooser dateDispatch;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JTextField textCompany;
    private javax.swing.JTextField textCourier;
    private javax.swing.JTextField textCourierRef;
    private javax.swing.JTextField textDispatcher;
    private javax.swing.JTextField textStatus;
    // End of variables declaration//GEN-END:variables
}
