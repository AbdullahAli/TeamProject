/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.acc;

import infopharma.Validator;
import infopharma.data.AccountDBAccess;
import infopharma.data.UserAccount;
import infopharma.data.MiscDBAccess;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Abdullah
 */
public class ViewRegister extends InfoPharmaPanel{
    private static InfoPharmaFrame frame;
    private AccountDBAccess accountDB;
    
	
    public ViewRegister(InfoPharmaFrame mainMenuFrame){
        accountDB = new AccountDBAccess();
        initComponents();
        setFrame(mainMenuFrame);
        this.setVisible(true);
        populateComboUserTypes();
    }

    public static InfoPharmaFrame getFrame() {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void populateComboUserTypes(){
        HashMap<Integer, String> userTypes = accountDB.getUserTypes();
        for(String userType : userTypes.values()){
            comboUserType.addItem(userType);
        }
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
        btnMainMenu = new javax.swing.JButton();
        comboUserType = new javax.swing.JComboBox();
        comboDiscountPlan = new javax.swing.JComboBox();
        textNumber = new javax.swing.JTextField();
        textCompany = new javax.swing.JTextField();
        textPostcode = new javax.swing.JTextField();
        paneAddress = new javax.swing.JScrollPane();
        textAreaAddress = new javax.swing.JTextArea();
        textCredit = new javax.swing.JTextField();
        imageLabel = new javax.swing.JLabel();

        btnMainMenu.setText("main menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        btnMainMenu.setBounds(990, 10, 100, 50);
        layeredPanel.add(btnMainMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboUserType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUserTypeActionPerformed(evt);
            }
        });
        comboUserType.setBounds(30, 140, 270, 27);
        layeredPanel.add(comboUserType, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboDiscountPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDiscountPlanActionPerformed(evt);
            }
        });
        comboDiscountPlan.setBounds(370, 140, 270, 27);
        layeredPanel.add(comboDiscountPlan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textNumber.setSize(new java.awt.Dimension(250, 25));
        textNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNumberActionPerformed(evt);
            }
        });
        textNumber.setBounds(40, 510, 250, 25);
        layeredPanel.add(textNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCompanyActionPerformed(evt);
            }
        });
        textCompany.setBounds(40, 220, 250, 25);
        layeredPanel.add(textCompany, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textPostcode.setSize(new java.awt.Dimension(250, 25));
        textPostcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPostcodeActionPerformed(evt);
            }
        });
        textPostcode.setBounds(40, 430, 250, 25);
        layeredPanel.add(textPostcode, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneAddress.setBorder(null);

        textAreaAddress.setColumns(20);
        textAreaAddress.setRows(5);
        textAreaAddress.setMinimumSize(new java.awt.Dimension(0, 0));
        paneAddress.setViewportView(textAreaAddress);

        paneAddress.setBounds(40, 300, 250, 70);
        layeredPanel.add(paneAddress, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCreditActionPerformed(evt);
            }
        });
        textCredit.setBounds(380, 220, 250, 25);
        layeredPanel.add(textCredit, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/register.png"))); // NOI18N
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

    private void comboUserTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUserTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboUserTypeActionPerformed

    private void comboDiscountPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDiscountPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDiscountPlanActionPerformed

    private void textNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNumberActionPerformed

    private void textCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCompanyActionPerformed

    private void textPostcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPostcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPostcodeActionPerformed

    private void textCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCreditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCreditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JComboBox comboDiscountPlan;
    private javax.swing.JComboBox comboUserType;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JScrollPane paneAddress;
    private javax.swing.JTextArea textAreaAddress;
    private javax.swing.JTextField textCompany;
    private javax.swing.JTextField textCredit;
    private javax.swing.JTextField textNumber;
    private javax.swing.JTextField textPostcode;
    // End of variables declaration//GEN-END:variables
}
