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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Abdullah
 */
public class ViewMerchant extends InfoPharmaPanel{
    
    private static InfoPharmaFrame frame;
    private AccountDBAccess accountDB;
    private ArrayList<MerchantAccount> merchants;
    private HashMap<Integer, String> accountStatuses;
	
    public ViewMerchant(InfoPharmaFrame mainMenuFrame){
        accountDB = new AccountDBAccess();
        initComponents();
        populateComboMerchants();
        accountStatuses = accountDB.getAccountStatuses();
        setFrame(mainMenuFrame);
        lblError.setVisible(false);
        this.setVisible(true);
        radioBtnGroup.add(radioBtnActive);
        radioBtnGroup.add(radioBtnSuspended);
        radioBtnGroup.add(radioBtnDefault);
    }

    public static InfoPharmaFrame getFrame() {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void populateComboMerchants(){
        merchants = accountDB.getAllMerchants();
        for(MerchantAccount merchant : merchants){
            comboMerchants.addItem(merchant.getCompany());
        }
    }
    
    public void populateMerchantDetails(){
        MerchantAccount merchant = getSelectedMerchant();
        textAccountNumber.setText(Integer.toString(merchant.getAccountNumber()));
        areaAddress.setText(merchant.getAddress());
        textPostcode.setText(merchant.getPostcode());
        textTelNumber.setText(merchant.getTelNumber());
        textCredit.setText(Double.toString(merchant.getCreditLimit()));
        textBalance.setText(Double.toString(merchant.getBalance()));
        textDiscount.setText(Integer.toString(merchant.getDiscountPlan()));
        if(merchant.getAccountStatusID() == 1){
            radioBtnDefault.setSelected(true);
        }else if(merchant.getAccountStatusID() == 2){
            radioBtnActive.setSelected(true);
        }else if(merchant.getAccountStatusID() == 3){
            radioBtnSuspended.setSelected(true);
        }
    }
    
    public MerchantAccount getSelectedMerchant(){
        String company = comboMerchants.getSelectedItem().toString();
        MerchantAccount merchant = null;
        for(int i=0; i<merchants.size(); i++){
            if(merchants.get(i).getCompany().equals(company)){
                merchant = merchants.get(i);
                break;
            }
        }
        return merchant;
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
        comboMerchants = new javax.swing.JComboBox();
        btnMainMenu = new javax.swing.JButton();
        textDiscount = new javax.swing.JTextField();
        textCredit = new javax.swing.JTextField();
        textBalance = new javax.swing.JTextField();
        textPostcode = new javax.swing.JTextField();
        textTelNumber = new javax.swing.JTextField();
        textAccountNumber = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaAddress = new javax.swing.JTextArea();
        lblError = new javax.swing.JLabel();
        radioBtnDefault = new javax.swing.JRadioButton();
        radioBtnActive = new javax.swing.JRadioButton();
        radioBtnSuspended = new javax.swing.JRadioButton();
        imageLabel = new javax.swing.JLabel();

        comboMerchants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMerchantsActionPerformed(evt);
            }
        });
        comboMerchants.setBounds(30, 140, 260, 27);
        layeredPanel.add(comboMerchants, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnMainMenu.setText("main menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        btnMainMenu.setBounds(1010, 10, 80, 50);
        layeredPanel.add(btnMainMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textDiscount.setBounds(380, 140, 250, 30);
        layeredPanel.add(textDiscount, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCreditActionPerformed(evt);
            }
        });
        textCredit.setBounds(380, 220, 250, 30);
        layeredPanel.add(textCredit, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textBalance.setBounds(380, 300, 250, 30);
        layeredPanel.add(textBalance, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textPostcode.setBounds(40, 430, 250, 30);
        layeredPanel.add(textPostcode, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textTelNumber.setBounds(40, 510, 250, 30);
        layeredPanel.add(textTelNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);
        textAccountNumber.setBounds(40, 220, 250, 30);
        layeredPanel.add(textAccountNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);

        areaAddress.setColumns(20);
        areaAddress.setRows(5);
        jScrollPane1.setViewportView(areaAddress);

        jScrollPane1.setBounds(30, 294, 270, 90);
        layeredPanel.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);
        radioBtnDefault.setBounds(370, 480, 40, 23);
        layeredPanel.add(radioBtnDefault, javax.swing.JLayeredPane.DEFAULT_LAYER);
        radioBtnActive.setBounds(370, 420, 40, 23);
        layeredPanel.add(radioBtnActive, javax.swing.JLayeredPane.DEFAULT_LAYER);
        radioBtnSuspended.setBounds(370, 450, 40, 23);
        layeredPanel.add(radioBtnSuspended, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/viewmerchantaccount.png"))); // NOI18N
        imageLabel.setBounds(0, 0, 1100, 570);
        layeredPanel.add(imageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void comboMerchantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMerchantsActionPerformed
        populateMerchantDetails();
    }//GEN-LAST:event_comboMerchantsActionPerformed

    private void textCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCreditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCreditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaAddress;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JComboBox comboMerchants;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JRadioButton radioBtnActive;
    private javax.swing.JRadioButton radioBtnDefault;
    private javax.swing.ButtonGroup radioBtnGroup;
    private javax.swing.JRadioButton radioBtnSuspended;
    private javax.swing.JTextField textAccountNumber;
    private javax.swing.JTextField textBalance;
    private javax.swing.JTextField textCredit;
    private javax.swing.JTextField textDiscount;
    private javax.swing.JTextField textPostcode;
    private javax.swing.JTextField textTelNumber;
    // End of variables declaration//GEN-END:variables
}
