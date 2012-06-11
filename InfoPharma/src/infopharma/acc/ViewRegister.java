/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.acc;

import infopharma.Validator;
import infopharma.data.AccountDBAccess;
import infopharma.data.MerchantAccount;
import infopharma.data.UserAccount;
import infopharma.data.MiscDBAccess;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

/**
 *
 * @author Abdullah
 */
public class ViewRegister extends InfoPharmaPanel{
    private static InfoPharmaFrame frame;
    private AccountDBAccess accountDB;
    private boolean isMerchant = false;
    
	
    public ViewRegister(InfoPharmaFrame mainMenuFrame){
        accountDB = new AccountDBAccess();
        initComponents();
        setFrame(mainMenuFrame);
        this.setVisible(true);
        lblError.setVisible(false);
        populateCombos();
        setFieldsOpaque();
    }
    
    public void setFieldsOpaque()  {
        JTextField[] fields = {textCompany, textCredit, textNumber, textPostcode};
        setFieldsOpaque(fields);
    }

    public static InfoPharmaFrame getFrame() {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void populateCombos(){
        populateComboUserTypes();
        populateComboDiscountPlan();
    }
    
    public void populateComboUserTypes(){
        HashMap<Integer, String> userTypes = accountDB.getUserTypes();
        for(String userType : userTypes.values()){
            comboUserType.addItem(userType);
        }
    }
    
    public void populateComboDiscountPlan(){
        ArrayList<Integer> discountPlans = accountDB.getDiscountPlans();
        for(int discountPlanID : discountPlans){
            comboDiscountPlan.addItem(discountPlanID);
        }
    }
    
    public void updatePane(){
        if(comboUserType.getSelectedItem().toString().toLowerCase().equals("merchant")){
            paneMerchant.setVisible(true);
            isMerchant = true;
        }else{ 
            paneMerchant.setVisible(false);
            isMerchant = false;
        }
    }
    
    public void registerValidation(){
        if(isMerchant){
            validateMerchant();
        }else{
            String role = comboUserType.getSelectedItem().toString();
            registerStaffUser(role);
        }
    }
    
    public void validateMerchant(){
        lblError.setVisible(false);
        String company = textCompany.getText();
        String address = textAreaAddress.getText();
        String contact = textNumber.getText();
        String postcode = textPostcode.getText();
        String telNumber = textNumber.getText();
        int discountPlanID = Integer.parseInt(comboDiscountPlan.getSelectedItem().toString());
        Object creditLimitObj = textCredit.getValue();
        Object[] fields = {company, address, contact, postcode, telNumber, creditLimitObj};
        if(Validator.isFilledIn(fields)){
            double creditLimit = Double.parseDouble(creditLimitObj.toString());
            MerchantAccount merchantAccount = new MerchantAccount(company, address, postcode, telNumber, creditLimit, discountPlanID, "default");
            registerMerchantUser(merchantAccount);
        }else{
            lblError.setText("Please fill in all the details");
            lblError.setVisible(true);
            
        }
    }
    
    public void registerMerchantUser(MerchantAccount merchantAccount){
        String username = generateUsername();
        String password = generatePassword();
        try{
            accountDB.registerMerchantUser(username, password, merchantAccount);
            System.out.println("Username: " + username + "\nPassword: " + password + "");
            mainMenu();
        }catch(Exception e){
            System.out.println("Could not create user: " + e.getMessage());
        }
    }
    
    public void registerStaffUser(String role){
        String username = generateUsername();
        String password = generatePassword();
        try{
            accountDB.registerStaffUser(username, password, role);
            System.out.println("Username: " + username + "\nPassword: " + password + "");
            mainMenu();
        }catch(Exception e){
            System.out.println("Could not create user.");
        }
    }
    
    public String generateUsername(){
        SecureRandom random = new SecureRandom();
        String username = new BigInteger(60, random).toString(32);
        return username;
    }
    
    public String generatePassword(){
        SecureRandom random = new SecureRandom();
        String password = new BigInteger(60, random).toString(32);
        return password;
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
        lblError = new javax.swing.JLabel();
        paneMerchant = new javax.swing.JLayeredPane();
        textCredit = new javax.swing.JFormattedTextField();
        paneAddress = new javax.swing.JScrollPane();
        textAreaAddress = new javax.swing.JTextArea();
        textCompany = new javax.swing.JTextField();
        comboDiscountPlan = new javax.swing.JComboBox();
        textPostcode = new javax.swing.JTextField();
        textNumber = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboUserType = new javax.swing.JComboBox();
        image = new javax.swing.JLabel();
        btnMainMenu1 = new javax.swing.JButton();
        btnGo = new javax.swing.JButton();
        btnMainMenu = new javax.swing.JButton();

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textCredit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCreditActionPerformed(evt);
            }
        });
        textCredit.setBounds(380, 120, 250, 25);
        paneMerchant.add(textCredit, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneAddress.setBorder(null);

        textAreaAddress.setColumns(20);
        textAreaAddress.setRows(5);
        textAreaAddress.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        textAreaAddress.setMinimumSize(new java.awt.Dimension(0, 0));
        paneAddress.setViewportView(textAreaAddress);

        paneAddress.setBounds(40, 130, 250, 70);
        paneMerchant.add(paneAddress, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textCompany.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        textCompany.setOpaque(true);
        textCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCompanyActionPerformed(evt);
            }
        });
        textCompany.setBounds(40, 40, 250, 25);
        paneMerchant.add(textCompany, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboDiscountPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDiscountPlanActionPerformed(evt);
            }
        });
        comboDiscountPlan.setBounds(370, 30, 280, 27);
        paneMerchant.add(comboDiscountPlan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textPostcode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        textPostcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPostcodeActionPerformed(evt);
            }
        });
        textPostcode.setBounds(40, 250, 250, 25);
        paneMerchant.add(textPostcode, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textNumber.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        textNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNumberActionPerformed(evt);
            }
        });
        textNumber.setBounds(40, 330, 250, 25);
        paneMerchant.add(textNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/register_merchantpanel.png"))); // NOI18N
        jLabel1.setBounds(20, 10, 990, 360);
        paneMerchant.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneMerchant.setBounds(0, 180, 1020, 380);
        layeredPanel.add(paneMerchant, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboUserType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUserTypeActionPerformed(evt);
            }
        });
        comboUserType.setBounds(30, 130, 270, 30);
        layeredPanel.add(comboUserType, javax.swing.JLayeredPane.DEFAULT_LAYER);

        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/register_background.png"))); // NOI18N
        image.setBounds(0, 0, 1100, 570);
        layeredPanel.add(image, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnMainMenu1.setText("cancel");
        btnMainMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenu1ActionPerformed(evt);
            }
        });
        btnMainMenu1.setBounds(1010, 540, 100, 30);
        layeredPanel.add(btnMainMenu1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnGo.setText("jButton1");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        btnGo.setBounds(1022, 70, 70, 470);
        layeredPanel.add(btnGo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnMainMenu.setText("main menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        btnMainMenu.setBounds(1030, 10, 60, 50);
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

    private void comboUserTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUserTypeActionPerformed
        updatePane();
    }//GEN-LAST:event_comboUserTypeActionPerformed

    private void comboDiscountPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDiscountPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDiscountPlanActionPerformed

    private void textCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCompanyActionPerformed

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        registerValidation();
    }//GEN-LAST:event_btnGoActionPerformed

    private void textCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCreditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCreditActionPerformed

    private void textPostcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPostcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPostcodeActionPerformed

    private void textNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNumberActionPerformed

    private void btnMainMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenu1ActionPerformed
        mainMenu();
    }//GEN-LAST:event_btnMainMenu1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnMainMenu1;
    private javax.swing.JComboBox comboDiscountPlan;
    private javax.swing.JComboBox comboUserType;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JScrollPane paneAddress;
    private javax.swing.JLayeredPane paneMerchant;
    private javax.swing.JTextArea textAreaAddress;
    private javax.swing.JTextField textCompany;
    private javax.swing.JFormattedTextField textCredit;
    private javax.swing.JTextField textNumber;
    private javax.swing.JTextField textPostcode;
    // End of variables declaration//GEN-END:variables
}
