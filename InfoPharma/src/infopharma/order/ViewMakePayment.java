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
        JTextField[] fields = {textDateOrdered, 
                               textCard, 
                               textSecurity, 
                               textAmount, 
                               textCard,
                               textHolder,
                               textChequeNumber,
                               textChequeName,
                               textAccountNumber,
                               textSortCode};
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
        Object chequeNumber = textChequeNumber.getValue();
        Object chequeAccNumber = textAccountNumber.getValue();
        Object sortcode = textSortCode.getValue();
        String payerName = textChequeName.getText();
        Object[] fields = {chequeNumber, chequeAccNumber, sortcode, payerName};
        if(Validator.isFilledIn(fields)) {
            int paymentTypeId = getPaymentTypeId("cheque");
            String dateNow = convertToShortDateString(new Date());
            double amount = Double.parseDouble(textAmount.getText());
            ChequePayment payment = new ChequePayment(0, 
                                                      paymentTypeId, 
                                                      dateNow,
                                                      amount,
                                                      payerName,
                                                      Integer.parseInt(sortcode.toString()),
                                                      chequeAccNumber.toString(),
                                                      chequeNumber.toString());
            makeChequePayment(payment);
        } else {
            System.err.println("Fill the shit in");
        }
    }
    
    public void makeCardPayment(CardPayment payment) {
        Order order = getOrder(Integer.parseInt(comboOrders.getSelectedItem().toString()));
        int orderId = order.getID();
        dbOrder.makeCardPayment(dummyAccount, orderId, payment);
        unpaidOrders.remove(order);
        populateComboOrders();
    }
    
    public String convertToShortDateString(Date date) {
        if(date != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
        return null;
    }
    
    public void makeChequePayment(ChequePayment payment) {
        Order order = getOrder(Integer.parseInt(comboOrders.getSelectedItem().toString()));
        int orderId = order.getID();
        dbOrder.makeChequePayment(dummyAccount, orderId, payment);
        unpaidOrders.remove(order);
        populateComboOrders();
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
        paneCard = new javax.swing.JLayeredPane();
        textHolder = new javax.swing.JTextField();
        dateExpiry = new com.toedter.calendar.JDateChooser();
        dateStart = new com.toedter.calendar.JDateChooser();
        comboCards = new javax.swing.JComboBox();
        textCard = new javax.swing.JFormattedTextField();
        textSecurity = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        paneCheque = new javax.swing.JLayeredPane();
        textChequeName = new javax.swing.JTextField();
        textChequeNumber = new javax.swing.JFormattedTextField();
        textAccountNumber = new javax.swing.JFormattedTextField();
        textSortCode = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        radioBtnCheque = new javax.swing.JRadioButton();
        radioBtnCard = new javax.swing.JRadioButton();
        comboOrders = new javax.swing.JComboBox();
        textAmount = new javax.swing.JTextField();
        textDateOrdered = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        imgBackground = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textHolder.setBounds(380, 40, 250, 30);
        paneCard.add(textHolder, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dateExpiry.setBounds(160, 200, 140, 40);
        paneCard.add(dateExpiry, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dateStart.setBounds(30, 198, 130, 40);
        paneCard.add(dateStart, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboCards.setBounds(30, 30, 260, 50);
        paneCard.add(comboCards, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textCard.setBounds(40, 120, 250, 30);
        paneCard.add(textCard, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textSecurity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textSecurity.setBounds(40, 280, 240, 30);
        paneCard.add(textSecurity, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/order/images/makepayment_card.png"))); // NOI18N
        jLabel2.setBounds(10, -10, 750, 340);
        paneCard.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneCard.setBounds(0, 240, 750, 320);
        layeredPanel.add(paneCard, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textChequeName.setBounds(40, 40, 240, 28);
        paneCheque.add(textChequeName, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textChequeNumber.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textChequeNumber.setBounds(40, 270, 250, 40);
        paneCheque.add(textChequeNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textAccountNumber.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textAccountNumber.setBounds(40, 210, 240, 28);
        paneCheque.add(textAccountNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textSortCode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textSortCode.setBounds(40, 120, 240, 28);
        paneCheque.add(textSortCode, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/order/images/makepayment_cheque.png"))); // NOI18N
        jLabel3.setBounds(10, -10, 750, 340);
        paneCheque.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneCheque.setBounds(0, 240, 750, 320);
        layeredPanel.add(paneCheque, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        imgBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/order/images/makepayment_background.png"))); // NOI18N
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
        paneCheque.setVisible(false);
        paneCard.setVisible(true);
    }//GEN-LAST:event_radioBtnCardActionPerformed

    private void radioBtnChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnChequeActionPerformed
        paneCard.setVisible(false);
        paneCheque.setVisible(true);
    }//GEN-LAST:event_radioBtnChequeActionPerformed

    private void comboOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrdersActionPerformed
        displayDetails();
    }//GEN-LAST:event_comboOrdersActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        validatePayment();
    }//GEN-LAST:event_btnOkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox comboCards;
    private javax.swing.JComboBox comboOrders;
    private com.toedter.calendar.JDateChooser dateExpiry;
    private com.toedter.calendar.JDateChooser dateStart;
    private javax.swing.JLabel imgBackground;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JLayeredPane paneCard;
    private javax.swing.JLayeredPane paneCheque;
    private javax.swing.JRadioButton radioBtnCard;
    private javax.swing.JRadioButton radioBtnCheque;
    private javax.swing.ButtonGroup radioBtnGroup;
    private javax.swing.JFormattedTextField textAccountNumber;
    private javax.swing.JTextField textAmount;
    private javax.swing.JFormattedTextField textCard;
    private javax.swing.JTextField textChequeName;
    private javax.swing.JFormattedTextField textChequeNumber;
    private javax.swing.JTextField textDateOrdered;
    private javax.swing.JTextField textHolder;
    private javax.swing.JFormattedTextField textSecurity;
    private javax.swing.JFormattedTextField textSortCode;
    // End of variables declaration//GEN-END:variables
}
