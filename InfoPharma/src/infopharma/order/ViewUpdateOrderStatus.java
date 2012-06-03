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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Abdullah
 */
public class ViewUpdateOrderStatus extends InfoPharmaPanel {
    
    private static InfoPharmaFrame frame;
    private OrderDBAccess dbOrder;
    private HashMap<Integer, String> orderStatusesHash;
    private ArrayList<Order> ordersArray;
	
    public ViewUpdateOrderStatus(InfoPharmaFrame mainMenuFrame) {
        dbOrder = new OrderDBAccess();
        orderStatusesHash = dbOrder.getOrderStatuses();
        ordersArray = dbOrder.getAllOrders();
        initComponents();
        radioBtnProcessed.setActionCommand("process");
        radioBtnAccepted.setActionCommand("accept");
        radioBtnDispatched.setActionCommand("dispatch");
        setFrame(mainMenuFrame);
        lblError.setVisible(false);
        popualateComboOrderID();
        this.setVisible(true);
    }

    public static InfoPharmaFrame getFrame() {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void popualateComboOrderID() {
        for(Order order : ordersArray) {
            int orderID = order.getID();
            comboOrderID.addItem(orderID);
        }
    }
    
    public void setFields() {
        String orderStatus = getOrderStatus();
        textOrderStatus.setText(orderStatus);
        setRadioBtnOrderStatus(orderStatus);
    }
    
    public String getOrderStatus() {
        Order order = getSelectedOrder();
        int statusID = order.getStatusID();
        String orderStatus = orderStatusesHash.get(statusID);
        return orderStatus;
    }
    
    public void setRadioBtnOrderStatus(String orderStatus) {
        if(orderStatus.toLowerCase().contains("process")) {
            radioBtnProcessed.setSelected(true);
        }else if(orderStatus.toLowerCase().contains("accept")) {
            radioBtnAccepted.setSelected(true);
        }else if(orderStatus.toLowerCase().contains("dispatch")) {
            radioBtnDispatched.setSelected(true);
        }
    }
    
    public void setOrderStatus() {
        int statusID = getSelectedRadioBtn();
        Order order = getSelectedOrder();
        order.setStatusId(statusID);
        int orderID = order.getID();
        try {
            dbOrder.updateOrderStatus(orderID, statusID);
        }catch(Exception e) {
            System.err.println("Could not update the Order's status: " + e.getMessage());
        }
        setFields();
    }
    
    public Order getSelectedOrder() {
        int orderID = Integer.parseInt(comboOrderID.getSelectedItem().toString());
        for(Order order : ordersArray) {
            if(order.getID() == orderID) {
                return order;
            }
        }
        return null;
    }
    
    public int getSelectedRadioBtn() {
        int statusID = 0;
        String status = radioBtnGroup.getSelection().getActionCommand();
        System.out.println(status);
        Iterator it = orderStatusesHash.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            if(pairs.getValue().toString().toLowerCase().contains(status)) {
                statusID = (Integer) pairs.getKey();
                break;
            }
        }
        return statusID;
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
        comboOrderID = new javax.swing.JComboBox();
        textOrderStatus = new javax.swing.JTextField();
        radioBtnProcessed = new javax.swing.JRadioButton();
        radioBtnAccepted = new javax.swing.JRadioButton();
        radioBtnDispatched = new javax.swing.JRadioButton();
        btnOk = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboOrderID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrderIDActionPerformed(evt);
            }
        });
        comboOrderID.setBounds(30, 140, 270, 27);
        layeredPanel.add(comboOrderID, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textOrderStatus.setEditable(false);
        textOrderStatus.setBounds(40, 220, 250, 28);
        layeredPanel.add(textOrderStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioBtnGroup.add(radioBtnProcessed);
        radioBtnProcessed.setBounds(30, 320, 30, 23);
        layeredPanel.add(radioBtnProcessed, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioBtnGroup.add(radioBtnAccepted);
        radioBtnAccepted.setBounds(30, 290, 30, 23);
        layeredPanel.add(radioBtnAccepted, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioBtnGroup.add(radioBtnDispatched);
        radioBtnDispatched.setBounds(30, 350, 30, 23);
        layeredPanel.add(radioBtnDispatched, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        btnOk.setBounds(1020, 70, 80, 470);
        layeredPanel.add(btnOk, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/order/images/updateorderstatus.png"))); // NOI18N
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

    private void comboOrderIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrderIDActionPerformed
        setFields();
    }//GEN-LAST:event_comboOrderIDActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        setOrderStatus();
    }//GEN-LAST:event_btnOkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox comboOrderID;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JRadioButton radioBtnAccepted;
    private javax.swing.JRadioButton radioBtnDispatched;
    private javax.swing.ButtonGroup radioBtnGroup;
    private javax.swing.JRadioButton radioBtnProcessed;
    private javax.swing.JTextField textOrderStatus;
    // End of variables declaration//GEN-END:variables
};