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
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

/**
 *
 * @author Abdullah
 */
public class ViewNewProduct extends InfoPharmaPanel{
    
    private static InfoPharmaFrame frame;
    private CatDBAccess catDBAccess;
	
    public ViewNewProduct(InfoPharmaFrame mainMenuFrame)
    {
        initComponents();
        catDBAccess = new CatDBAccess();
        setFrame(mainMenuFrame);
        lblError.setVisible(false);
        this.setVisible(true);
        setFieldsOpaque();
    }
    
    public void setFieldsOpaque()  {
        JTextField[] fields = {txtCost, txtInitialStock, txtName, txtStockLimit, txtType, txtUnitsInAPack};
        setFieldsOpaque(fields);
    }

    public static InfoPharmaFrame getFrame() 
    {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) 
    {
        InfoPharmaPanel.setFrame(frame);
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
        txtStockLimit = new javax.swing.JFormattedTextField();
        lblError = new javax.swing.JLabel();
        txtInitialStock = new javax.swing.JFormattedTextField();
        txtUnitsInAPack = new javax.swing.JFormattedTextField();
        txtCost = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtType = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        imageLabel = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();
        btnGo = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        txtStockLimit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtStockLimit.setBounds(380, 220, 250, 28);
        layeredPanel.add(txtStockLimit, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 530, 990, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtInitialStock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtInitialStock.setBounds(380, 140, 250, 28);
        layeredPanel.add(txtInitialStock, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtUnitsInAPack.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtUnitsInAPack.setBounds(40, 510, 250, 28);
        layeredPanel.add(txtUnitsInAPack, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtCost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtCost.setBounds(40, 430, 250, 28);
        layeredPanel.add(txtCost, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane1.setBorder(null);

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setBorder(null);
        jScrollPane1.setViewportView(txtDescription);

        jScrollPane1.setBounds(40, 300, 250, 70);
        layeredPanel.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtType.setBounds(40, 220, 250, 28);
        layeredPanel.add(txtType, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtName.setBounds(40, 140, 250, 28);
        layeredPanel.add(txtName, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/cat/images/newproduct.png"))); // NOI18N
        imageLabel.setBounds(0, 0, 1100, 570);
        layeredPanel.add(imageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnMainMenu.setText("main menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        btnMainMenu.setBounds(1030, 10, 60, 50);
        layeredPanel.add(btnMainMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        btnGo.setBounds(1020, 70, 80, 470);
        layeredPanel.add(btnGo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnCancel.setText("jButton1");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnCancel.setBounds(1010, 540, 97, 29);
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
        validateFields();
    }//GEN-LAST:event_btnGoActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        goToMainMenu();
    }//GEN-LAST:event_btnCancelActionPerformed

    public void validateFields()
    {
        lblError.setVisible(false);
        Object[] fields = {txtCost.getText(), txtInitialStock.getText(), txtName.getText(), txtStockLimit.getText(), txtType.getText(), txtUnitsInAPack.getText()};
        if(Validator.isFilledIn(fields))
        {
            insertNewProduct();
        }
        else
        {
            lblError.setText("Please fill in all the product details");
            lblError.setVisible(true);
        }
    }
    
    public void insertNewProduct()
    {
        if(catDBAccess.insertProduct(txtName.getText(), txtDescription.getText(), txtCost.getText(), txtType.getText(), txtUnitsInAPack.getText(), txtInitialStock.getText(), txtStockLimit.getText()))
        {
            goToMainMenu();
        }
    }
    
    public void goToMainMenu()
    {
        this.getFrame().setPanel(new ViewMainMenu(this.getFrame()));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JFormattedTextField txtCost;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JFormattedTextField txtInitialStock;
    private javax.swing.JTextField txtName;
    private javax.swing.JFormattedTextField txtStockLimit;
    private javax.swing.JTextField txtType;
    private javax.swing.JFormattedTextField txtUnitsInAPack;
    // End of variables declaration//GEN-END:variables
}
