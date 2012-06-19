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
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

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
        getDispatcher();
        dateDispatch.setMinSelectableDate(new Date());
        populateComboOrders();
        setFrame(mainMenuFrame);
        lblError.setVisible(false);
        this.setVisible(true);
        setFieldsOpaque();
    }
    
    public void getDispatcher() {
        textDispatcher.setText(UserAccount.getUsername() + "");
    }
    
    public void setFieldsOpaque()  {
        JTextField[] fields = {textCompany, textCourier, textCourierRef, textDispatcher, textStatus};
        setFieldsOpaque(fields);
    }

    public static InfoPharmaFrame getFrame() {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void populateComboOrders() {
        comboOrders.removeAllItems();
        for(Order order : ordersArray) {
            comboOrders.addItem(order.getID());
        }
    }
    
    public void displayDetails() {
        clearFields();
        if(!ordersArray.isEmpty()) {
            int orderId = Integer.parseInt(comboOrders.getSelectedItem().toString());
            Order order = getOrder(orderId);
            String status = orderStatuses.get(order.getStatusID());
            MerchantAccount merchant = dbAccount.getMerchantByOrder(orderId);
            textStatus.setText(status);
            textCompany.setText(merchant.getCompany());
        } 
    }
    
    public void clearFields() {
        //Need to add code to reset the fields
    }
    
    public Order getOrder(int orderId) {
        for(Order order : ordersArray) {
            if(order.getID() == orderId) {
                return order;
            }
        }
        return null;
    }
    
    public int getOrderStatusId(String status) {
        Iterator it = orderStatuses.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            if(pairs.getValue().toString().toLowerCase().contains(status)) {
                int orderStatusId = (Integer) pairs.getKey();
                return orderStatusId;
            }
        }
        return 0;
    }
    
    public void dispatchOrder(Dispatch dispatch) {
        //Possibly add code if no items in combo
        try
        {
            int orderId = Integer.parseInt(comboOrders.getSelectedItem().toString());
            int orderStatus = getOrderStatusId("dispatch");
            dbOrder.dispatchOrder(orderId, orderStatus, dispatch);
            if(ordersArray.remove(getOrder(orderId))) {
                populateComboOrders();
                displayDetails();
                mainMenu();
            } else {
                displayError("Ooops. Something went wrong.");
            }
        }
        catch(Exception e)
        {
            displayError("You did not select any order to dispatch.");
        }
        
    }
    
    public void validateDispatch() {
        String dispatchDate = convertToShortDateString(dateDispatch.getDate());
        String deliveryDate = convertToShortDateString(dateDelivery.getDate());
        String courier = textCourier.getText();
        String courierRef = textCourierRef.getText();
        Object[] fields = {dispatchDate, deliveryDate, courier, courierRef};
        if(Validator.isFilledIn(fields)) {
            Dispatch dispatch = new Dispatch(courierRef, courier, deliveryDate, dispatchDate);
            dispatchOrder(dispatch);
        } else {
            displayError("Please fill in all the details");
        }
    }
    
    public void displayError(String error) {
        lblError.setText(error);
        lblError.setVisible(true);
    }
    
    public String convertToShortDateString(Date date) {
        if(date != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
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
        btnGo = new javax.swing.JButton();
        btnMainMenu1 = new javax.swing.JButton();

        comboOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrdersActionPerformed(evt);
            }
        });
        comboOrders.setBounds(30, 120, 270, 40);
        layeredPanel.add(comboOrders, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textStatus.setEditable(false);
        textStatus.setBounds(390, 200, 250, 30);
        layeredPanel.add(textStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textDispatcher.setEditable(false);
        textDispatcher.setBounds(40, 200, 260, 30);
        layeredPanel.add(textDispatcher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textCourier.setBounds(40, 360, 250, 30);
        layeredPanel.add(textCourier, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textCourierRef.setBounds(40, 440, 250, 30);
        layeredPanel.add(textCourierRef, javax.swing.JLayeredPane.DEFAULT_LAYER);

        dateDelivery.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDeliveryPropertyChange(evt);
            }
        });
        dateDelivery.setBounds(40, 530, 250, 20);
        layeredPanel.add(dateDelivery, javax.swing.JLayeredPane.DEFAULT_LAYER);

        dateDispatch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDispatchPropertyChange(evt);
            }
        });
        dateDispatch.setBounds(40, 290, 250, 20);
        layeredPanel.add(dateDispatch, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textCompany.setEditable(false);
        textCompany.setBounds(390, 120, 250, 30);
        layeredPanel.add(textCompany, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 540, 820, 40);
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

        btnGo.setText("OK");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        btnGo.setBounds(1020, 70, 80, 470);
        layeredPanel.add(btnGo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnMainMenu1.setText("main menu");
        btnMainMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenu1ActionPerformed(evt);
            }
        });
        btnMainMenu1.setBounds(1020, 540, 80, 30);
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
        mainMenu();
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void comboOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrdersActionPerformed
        displayDetails();
    }//GEN-LAST:event_comboOrdersActionPerformed

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        validateDispatch();
    }//GEN-LAST:event_btnGoActionPerformed

    private void dateDispatchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDispatchPropertyChange
        dateDelivery.setMinSelectableDate(dateDispatch.getDate());
    }//GEN-LAST:event_dateDispatchPropertyChange

    private void dateDeliveryPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDeliveryPropertyChange
        dateDispatch.setMaxSelectableDate(dateDelivery.getDate());
    }//GEN-LAST:event_dateDeliveryPropertyChange

    private void btnMainMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenu1ActionPerformed
        mainMenu();
    }//GEN-LAST:event_btnMainMenu1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnMainMenu1;
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
