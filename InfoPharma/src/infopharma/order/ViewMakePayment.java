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
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

/**
 *
 * @author Abdullah
 */
public class ViewMakePayment extends InfoPharmaPanel {
    
    private static InfoPharmaFrame frame;
    private OrderDBAccess dbOrder;
    private ArrayList<Order> unpaidOrders;
    private HashMap<Integer, String> cardTypes;
    private HashMap<Integer, String> paymentTypes;
    //Dummy accountNumber until we store the logged in account somewhere
    private int dummyAccount = 1;
	
    public ViewMakePayment(InfoPharmaFrame mainMenuFrame) {
        dbOrder = new OrderDBAccess();
        getUnpaidOrders();
        getCardTypes();
        getPaymentTypes();
        initComponents();
        populateComboOrders();
        populateComboCards();
        setFrame(mainMenuFrame);
        lblError.setVisible(false);
        setFieldsOpaque();
        this.setVisible(true);
    }

    public static InfoPharmaFrame getFrame()  {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void setFieldsOpaque() {
        JTextField[] fields = {textDateOrdered, textCard, textSecurity, textAmount, textCard};
        setFieldsOpaque(fields);
    }
    
    public void getUnpaidOrders() {
        unpaidOrders = dbOrder.getUnpaidMerchantOrders(dummyAccount);
    }
    
    public void getCardTypes() {
        cardTypes = dbOrder.getCardTypes();
    }
    
    public void getPaymentTypes() {
        paymentTypes = dbOrder.getPaymentTypes();
    }
    
    public void populateComboOrders() {
        comboOrders.removeAllItems();
        for(Order order : unpaidOrders) {
            comboOrders.addItem(order.getID());
        }
    }
    
    public void populateComboCards() {
        comboCards.removeAllItems();
        for(String type : cardTypes.values()) {
            comboCards.addItem(type);
        }
    }
    
    public void displayDetails() {
        int orderId = getSelectedOrderId();
        if(orderId != -1) {
            Order order = getOrder(orderId);
            textDateOrdered.setText(order.getDate());
            textAmount.setText(order.getTotal() + "");
        }
    }
    
    public int getSelectedOrderId() {
        if(comboOrders.getSelectedItem() != null) {
            return Integer.parseInt(comboOrders.getSelectedItem().toString());
        }
        return -1;
    }
    
    public Order getOrder(int orderId) {
        for(Order order : unpaidOrders) {
            if(order.getID() == orderId) {
                return order;
            }
        }
        return null;
    }
    
    public void validatePayment() {
        if(radioBtnCard.isSelected()) {
            validateCardPayment();
        } else if(radioBtnCheque.isSelected()) {
            validateChequePayment();
        }
    }
    
    public void validateCardPayment() {
        Object cardNo = textCard.getValue();
        String startDate = convertToShortDateString(dateStart.getDate());
        String expDate = convertToShortDateString(dateExpiry.getDate());
        Object secCode = textSecurity.getValue();
        String cardHolder = textHolder.getText();
        Object[] fields = {cardNo, startDate, expDate, secCode, cardHolder};
        if(Validator.isFilledIn(fields)) {
            int paymentTypeId = getPaymentTypeId("card");
            String dateNow = convertToShortDateString(new Date());
            double amount = Double.parseDouble(textAmount.getText());
            CardPayment payment = new CardPayment(0, 
                                                  paymentTypeId, 
                                                  dateNow, 
                                                  amount, 
                                                  cardNo.toString(), 
                                                  cardHolder, 
                                                  expDate, 
                                                  startDate, 
                                                  Integer.parseInt(secCode.toString()));
            makeCardPayment(payment);
        } else {
            System.err.println("Fill the shit in");
        }
        
    }
    
    public int getPaymentTypeId(String type) {
        Iterator it = paymentTypes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            if(pairs.getValue().toString().toLowerCase().contains(type)) {
                return (Integer) pairs.getKey();
            }
        }
        return 0;
    }
    
    public void validateChequePayment() {
        
    }
    
    public void makeCardPayment(CardPayment payment) {
        Order order = getOrder(Integer.parseInt(comboOrders.getSelectedItem().toString()));
        int orderId = order.getID();
//        dbOrder.makeCardPayment(dummyAccount, orderId, payment);
        unpaidOrders.remove(order);
        populateComboOrders();
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

        radioBtnGroup = new javax.swing.ButtonGroup();
        layeredPanel = new javax.swing.JLayeredPane();
        textSecurity = new javax.swing.JFormattedTextField();
        lblError = new javax.swing.JLabel();
        radioBtnCheque = new javax.swing.JRadioButton();
        radioBtnCard = new javax.swing.JRadioButton();
        comboOrders = new javax.swing.JComboBox();
        textAmount = new javax.swing.JTextField();
        textDateOrdered = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        textCard = new javax.swing.JFormattedTextField();
        textHolder = new javax.swing.JTextField();
        comboCards = new javax.swing.JComboBox();
        dateExpiry = new com.toedter.calendar.JDateChooser();
        dateStart = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        imgBackground = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();

        textSecurity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textSecurity.setBounds(40, 520, 250, 30);
        layeredPanel.add(textSecurity, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        textAmount.setBounds(380, 200, 250, 30);
        layeredPanel.add(textAmount, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textDateOrdered.setEditable(false);
        textDateOrdered.setBounds(380, 120, 250, 30);
        layeredPanel.add(textDateOrdered, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        btnOk.setBounds(1020, 70, 80, 470);
        layeredPanel.add(btnOk, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textCard.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textCard.setBounds(40, 360, 250, 30);
        layeredPanel.add(textCard, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textHolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textHolderActionPerformed(evt);
            }
        });
        textHolder.setBounds(370, 360, 270, 40);
        layeredPanel.add(textHolder, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboCards.setBounds(30, 280, 270, 40);
        layeredPanel.add(comboCards, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dateExpiry.setBounds(170, 440, 130, 40);
        layeredPanel.add(dateExpiry, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dateStart.setBounds(33, 440, 130, 40);
        layeredPanel.add(dateStart, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Card Holder's Name");
        jLabel1.setBounds(370, 340, 160, 16);
        layeredPanel.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imgBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/order/images/makepayment.png"))); // NOI18N
        imgBackground.setBounds(0, 0, 1100, 570);
        layeredPanel.add(imgBackground, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        
    }//GEN-LAST:event_radioBtnCardActionPerformed

    private void radioBtnChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnChequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBtnChequeActionPerformed

    private void comboOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrdersActionPerformed
        displayDetails();
    }//GEN-LAST:event_comboOrdersActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        validatePayment();
    }//GEN-LAST:event_btnOkActionPerformed

    private void textHolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textHolderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textHolderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox comboCards;
    private javax.swing.JComboBox comboOrders;
    private com.toedter.calendar.JDateChooser dateExpiry;
    private com.toedter.calendar.JDateChooser dateStart;
    private javax.swing.JLabel imgBackground;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JRadioButton radioBtnCard;
    private javax.swing.JRadioButton radioBtnCheque;
    private javax.swing.ButtonGroup radioBtnGroup;
    private javax.swing.JTextField textAmount;
    private javax.swing.JFormattedTextField textCard;
    private javax.swing.JTextField textDateOrdered;
    private javax.swing.JTextField textHolder;
    private javax.swing.JFormattedTextField textSecurity;
    // End of variables declaration//GEN-END:variables
}
