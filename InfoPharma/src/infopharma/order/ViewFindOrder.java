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
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abdullah
 */
public class ViewFindOrder extends InfoPharmaPanel{
    private static InfoPharmaFrame frame;
    private OrderDBAccess dbOrder;
    private CatDBAccess dbCat;
    private ArrayList<Order> ordersArray;
    private DefaultTableModel model;
    private double vat;
	
    public ViewFindOrder(InfoPharmaFrame mainMenuFrame) {
        vat = 1.175;
        dbOrder = new OrderDBAccess();
        dbCat = new CatDBAccess();
        getOrders();
        initComponents();
        model = (DefaultTableModel) tableProducts.getModel();
        populateComboOrders();
        setFrame(mainMenuFrame);
        lblError.setVisible(false);
        this.setVisible(true);
        setFieldsOpaque();
    }
    
    public void getOrders() {
        if(UserAccount.getRole().equals("Administrator")) {
            ordersArray = dbOrder.getAllOrders();
        } else if(UserAccount.getRole().equals("Merchant")) {
            ordersArray = dbOrder.getAllOrdersByMerchant(MerchantAccount.getAccountNumber());
        }
    }
    
    public void setFieldsOpaque()  {
        JTextField[] fields = {textSubtotal, textTotal, textVAT};
        setFieldsOpaque(fields);
    }

    public static InfoPharmaFrame getFrame() {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void populateComboOrders() {
        for(Order order : ordersArray) {
            comboOrders.addItem(order.getID());
        }
    }
    
    public void displayOrder() {
        clearTable();
        int orderId = Integer.parseInt(comboOrders.getSelectedItem().toString());
        Order order = getOrder(orderId);
        ArrayList<Product> productsInOrderArray = getProductsInOrder(orderId);
        displayTotals(order);
        displayProducts(productsInOrderArray);
    }
    
    public void displayTotals(Order order) {
        double orderSubTotal = order.getTotal();
        textSubtotal.setValue(Double.parseDouble(orderSubTotal+""));
        textVAT.setText("17.5%");
        textTotal.setValue(Double.parseDouble(vat*orderSubTotal+""));
    }
    
    public void displayProducts(ArrayList<Product> productsInOrderArray) {
        for(Product product : productsInOrderArray) {
            String id = Integer.toString(product.getId());
            String description = product.getDescription();
            String unitPrice = Double.toString(product.getUnitPrice());
            String quantity = "1";
            String total = Double.toString(1*product.getUnitPrice());
            model.insertRow(0, new Object[]{id, description, unitPrice, quantity, total});

        }
    }
    
    public void clearTable() {
        for(int i = model.getRowCount() - 1; i >=0; i--) {
            model.removeRow(i);
        }
    }
    public Order getOrder(int orderId) {
        for(Order order : ordersArray) {
            if(order.getID() == orderId) {
                return order;
            }
        }
        return null;
    }
    
    public ArrayList<Product> getProductsInOrder(int orderId) {
        ArrayList<Product> productsInOrderArray = dbOrder.getAllProductsInOrder(orderId);
        return productsInOrderArray;
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
        textTotal = new javax.swing.JFormattedTextField();
        comboOrders = new javax.swing.JComboBox();
        textSubtotal = new javax.swing.JFormattedTextField();
        textVAT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProducts = new javax.swing.JTable();
        imageLabel = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        btnMainMenu = new javax.swing.JButton();

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 530, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        textTotal.setBounds(40, 518, 250, 20);
        layeredPanel.add(textTotal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrdersActionPerformed(evt);
            }
        });
        comboOrders.setBounds(30, 120, 270, 27);
        layeredPanel.add(comboOrders, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textSubtotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        textSubtotal.setBounds(40, 380, 250, 20);
        layeredPanel.add(textSubtotal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textVAT.setEditable(false);
        textVAT.setBounds(40, 450, 260, 20);
        layeredPanel.add(textVAT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product ID", "Description", "Unit Cost", "Quantity", "Total"
            }
        ));
        jScrollPane1.setViewportView(tableProducts);

        jScrollPane1.setBounds(30, 190, 610, 140);
        layeredPanel.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/order/images/findorder.png"))); // NOI18N
        imageLabel.setBounds(0, 0, 1100, 570);
        layeredPanel.add(imageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnOk.setText("main menu");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        btnOk.setBounds(1010, 70, 90, 500);
        layeredPanel.add(btnOk, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        mainMenu();
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void comboOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrdersActionPerformed
        displayOrder();
    }//GEN-LAST:event_comboOrdersActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        mainMenu();
    }//GEN-LAST:event_btnOkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox comboOrders;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JTable tableProducts;
    private javax.swing.JFormattedTextField textSubtotal;
    private javax.swing.JFormattedTextField textTotal;
    private javax.swing.JTextField textVAT;
    // End of variables declaration//GEN-END:variables
}
