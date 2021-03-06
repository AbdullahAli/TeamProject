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
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Abdullah
 */
public class ViewFindProduct extends InfoPharmaPanel{
    private CatDBAccess catDBAccess;
    HashMap<Integer, String> products;
	
    public ViewFindProduct(InfoPharmaFrame mainMenuFrame)
    {
        catDBAccess = new CatDBAccess();
        initComponents();
        setFrame(mainMenuFrame);
        populateComboProducts();
        lblError.setVisible(false);
        this.setVisible(true);
        setFieldsOpaque();
        xx.setVisible(false);
        xxx.setVisible(false);
    }
    
    public void setFieldsOpaque()  {
        JTextField[] fields = {txtID, txtName};
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
    
    public void populateComboProducts()
    {
        ddlProducts.removeAllItems();
        products = catDBAccess.getProducts();
        ddlProducts.addItem("Please select an item");
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
        xxx = new javax.swing.JComboBox();
        txtName = new javax.swing.JTextField();
        txtID = new javax.swing.JFormattedTextField();
        ddlProducts = new javax.swing.JComboBox();
        lblError = new javax.swing.JLabel();
        xx = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();
        btnGo = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        xxx.setBounds(30, 410, 270, 27);
        layeredPanel.add(xxx, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtName.setBounds(40, 340, 240, 28);
        layeredPanel.add(txtName, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtID.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtID.setBounds(40, 260, 250, 28);
        layeredPanel.add(txtID, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ddlProducts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ddlProducts.setBounds(30, 170, 270, 27);
        layeredPanel.add(ddlProducts, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        xx.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        xx.setForeground(new java.awt.Color(102, 102, 102));
        xx.setText("Found");
        xx.setBounds(30, 390, 44, 17);
        layeredPanel.add(xx, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/cat/images/findproduct.png"))); // NOI18N
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

        btnGo.setText("jButton1");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        btnGo.setBounds(1017, 90, 80, 450);
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
        xx.setVisible(false);
        xxx.setVisible(false);
        
        System.out.println("clicked GO");
        setErrorParameters(false, "");
        
        
        
        if(xxx.getItemCount() == 0 && validateSearchInformation())
        {
            System.out.println("Valid infomraotion");
            populateFoundProducts();
            xx.setVisible(true);
            xxx.setVisible(true);
            
        }
        else if (!validateSearchInformation())
        {
            System.out.println("Not valid information");
        }
        
        
        if(xxx.getItemCount() > 1 && xxx.getSelectedIndex() == 0)
        {
            System.out.println(": "+xxx.getSelectedIndex());
            setErrorParameters(true, "Please select a product to view from the found products");
        }
        else if (xxx.getSelectedIndex() != 0)
        {
            System.out.println("Product is: "+xxx.getSelectedItem().toString());
            this.
            getFrame().setPanel(new ViewProductInformation(getFrame(), xxx.getSelectedItem().toString()));
        }
        
    }//GEN-LAST:event_btnGoActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        goToMainMenu();
    }//GEN-LAST:event_btnCancelActionPerformed

    public void populateFoundProducts()
    {
        CatDBAccess catDBAccess = new CatDBAccess();
        String name = "";
        if(ddlProducts.getSelectedItem() != null)
        {
            name = ddlProducts.getSelectedItem().toString();
        }
        
        
        HashMap<Integer, String> foundProducts = catDBAccess.getMatchingProducts(txtID.getText(), txtName.getText(), name);
        if(foundProducts != null)
        {
            Iterator it = foundProducts.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry pairs = (Map.Entry)it.next();
                System.out.println(pairs.getKey() + " = " + pairs.getValue());
                
                
                
                xxx.removeAllItems();
                xxx.addItem("Please select an item");
                for(String product : foundProducts.values())
                {
                    xxx.addItem(product);
                }
                    xxx.updateUI();
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        else
        {
            setErrorParameters(true, "Did not find any matching products");
        }
    }
    public void goToMainMenu()
    {
        this.getFrame().setPanel(new ViewMainMenu(this.getFrame()));
    }
    
    public boolean validateSearchInformation()
    {
        setErrorParameters(false, "");
        if(!isFilledIn())
        {
            setErrorParameters(true, "You need to fill in at least one criteria");
            return false;
        }
        return true;
    }
    
    public void setErrorParameters(boolean isVisible, String message)
    {
        lblError.setVisible(isVisible);
        lblError.setText(message);
    }
    
    public boolean isFilledIn()
    {
        if(txtID.getValue() == null && txtName.getText().equals("") && ddlProducts.getSelectedIndex() == 0)
        {
            return false;
        }
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JComboBox ddlProducts;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JFormattedTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JLabel xx;
    private javax.swing.JComboBox xxx;
    // End of variables declaration//GEN-END:variables
}
