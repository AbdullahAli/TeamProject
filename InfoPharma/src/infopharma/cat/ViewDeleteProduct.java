/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.cat;

import infopharma.Validator;
import infopharma.acc.InfoPharmaFrame;
import infopharma.acc.InfoPharmaPanel;
import infopharma.acc.ViewMainMenu;
import infopharma.data.CatDBAccess;
import infopharma.data.UserAccount;
import infopharma.data.MiscDBAccess;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Abdullah
 */
public class ViewDeleteProduct extends InfoPharmaPanel{
    private static InfoPharmaFrame frame;
    private CatDBAccess catDBAccess;
    HashMap<Integer, String> products;
	
    public ViewDeleteProduct(InfoPharmaFrame mainMenuFrame)
    {
        catDBAccess = new CatDBAccess();
        initComponents();
        setFrame(mainMenuFrame);
        populateComboProducts();
        lblError.setVisible(false);
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
    
    public void populateComboProducts()
    {
        ddlProducts.removeAllItems();
        products = catDBAccess.getProducts();
        ddlProducts.addItem("");
        for(String product : products.values())
        {
            ddlProducts.addItem(product);
        }
        ddlProducts.updateUI();
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
        boxConfirm = new javax.swing.JRadioButton();
        ddlProducts = new javax.swing.JComboBox();
        lblError = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();
        btnGo = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        boxConfirm.setText("I confirm that I would like this product to be deleted permanetly.");
        boxConfirm.setBounds(30, 220, 450, 23);
        layeredPanel.add(boxConfirm, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ddlProducts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ddlProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddlProductsActionPerformed(evt);
            }
        });
        ddlProducts.setBounds(30, 130, 270, 27);
        layeredPanel.add(ddlProducts, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/cat/images/deleteproduct.png"))); // NOI18N
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

        btnGo.setText("btnGo");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        btnGo.setBounds(1017, 70, 80, 470);
        layeredPanel.add(btnGo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnCancel.setText("jButton1");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnCancel.setBounds(1020, 540, 80, 29);
        layeredPanel.add(btnCancel, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        if(isValidDeletion())
        {
            deleteProduct();
            populateComboProducts();
            boxConfirm.setSelected(false);
        }
        boxConfirm.setSelected(false);
    }//GEN-LAST:event_btnGoActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        goToMainMenu();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void ddlProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddlProductsActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ddlProductsActionPerformed

    public void goToMainMenu()
    {
        this.getFrame().setPanel(new ViewMainMenu(this.getFrame()));
    }
    
    public void deleteProduct()
    {
        String selectedName = ddlProducts.getSelectedItem().toString();
        System.out.println("selected product name: "+selectedName);
        catDBAccess.deleteProduct(selectedName);
    }
    
    public boolean isValidDeletion()
    {
        lblError.setVisible(false);
        if(ddlProducts.getSelectedIndex() == 0)//nothing selected...
        {
            lblError.setText("You have not selected a product to delete");
            lblError.setVisible(true);
            return false;
        }
        else if(ddlProducts.getSelectedIndex() != 0 && !boxConfirm.isSelected())
        {
            lblError.setText("You need to confirm product deletion");
            lblError.setVisible(true);
            return false;
        }
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton boxConfirm;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JComboBox ddlProducts;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    // End of variables declaration//GEN-END:variables
}
