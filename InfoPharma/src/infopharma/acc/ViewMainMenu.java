/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.acc;

import infopharma.cat.*;
import infopharma.data.UserAccount;
import infopharma.misc.ViewLatePayment;
import infopharma.misc.ViewLowStockNotification;
import infopharma.order.*;
import infopharma.rprt.ViewGenerateReport;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Abdullah
 */
public class ViewMainMenu extends InfoPharmaPanel{
    private String role = UserAccount.getRole();
    private static InfoPharmaFrame frame;
    public ViewMainMenu(InfoPharmaFrame mainMenuFrame)
    {
        frame = mainMenuFrame;
        initComponents();
        this.setFrame(mainMenuFrame);
        this.setVisible(true);
        
        setGeneralVisibility(false);
        setOrderVisibility(false);
        setCatalogueVisibility(false);
        setGeneralVisibility(false);
        setAccountVisiblity(false);
        setReportVisbility(false);
        setUpSideButtons();
    }

    public static InfoPharmaFrame getFrame() 
    {
        return InfoPharmaPanel.getFrame();
    }

    public static void setFrame(InfoPharmaFrame frame) 
    {
        InfoPharmaPanel.setFrame(frame);
    }
    
    public void hideSideButtons()
    {
        btnAccounts.setVisible(false);
        btnAccounts.setEnabled(false);

        btnReports.setVisible(false);
        btnReports.setEnabled(false);

        btnGeneral.setVisible(false);
        btnGeneral.setEnabled(false);
    }
    
    public void setUpSideButtons()
    {
        hideSideButtons();
        
        btnOrder.setVisible(true);
        btnOrder.setEnabled(true);
        
        btnCatalogue.setVisible(true);
        btnCatalogue.setEnabled(true);

        if(role.equals("Administrator"))
        {
            btnAccounts.setVisible(true);
            btnAccounts.setEnabled(true);
            
            btnReports.setVisible(true);
            btnReports.setEnabled(true);
            
            btnGeneral.setVisible(true);
            btnGeneral.setEnabled(true);
        }
        else if(role.equals("Dispatcher"))
        {
            btnCatalogue.setVisible(false);
            btnCatalogue.setEnabled(false);
        }
    }
    
    public void setGeneralVisibility(boolean isVisibleOrEnabled)
    {
        pnlGeneral.setVisible(isVisibleOrEnabled);
        pnlGeneral.setEnabled(isVisibleOrEnabled);
        
        btnViewLatePayments.setVisible(isVisibleOrEnabled);
        btnViewLatePayments.setEnabled(isVisibleOrEnabled);
        
        btnViewLowStock.setVisible(isVisibleOrEnabled);
        btnViewLowStock.setEnabled(isVisibleOrEnabled);
    }
    
    public void setReportVisbility(boolean isVisibleOrEnabled)
    {
        pnlReports.setVisible(isVisibleOrEnabled);
        pnlReports.setEnabled(isVisibleOrEnabled);
        
        btnGenerateReport.setVisible(isVisibleOrEnabled);
        btnGenerateReport.setEnabled(isVisibleOrEnabled);
    }
    
    public void setOrderVisibility(boolean isVisibleOrEnabled)
    {
        pnlOrders.setVisible(isVisibleOrEnabled);
        pnlOrders.setEnabled(isVisibleOrEnabled);
        
        btnPlaceOrder.setVisible(isVisibleOrEnabled);
        btnPlaceOrder.setEnabled(isVisibleOrEnabled);
        
        btnFindOrder.setVisible(isVisibleOrEnabled);
        btnFindOrder.setEnabled(isVisibleOrEnabled);
        
        btnMakePayement.setVisible(isVisibleOrEnabled);
        btnMakePayement.setEnabled(isVisibleOrEnabled);
        
        btnViewOutstandingBalances.setVisible(isVisibleOrEnabled);
        btnViewOutstandingBalances.setEnabled(isVisibleOrEnabled);
        
        btnUpdateOrderStatus.setVisible(isVisibleOrEnabled);
        btnUpdateOrderStatus.setVisible(isVisibleOrEnabled);
        
        btnDispatchOrder.setVisible(isVisibleOrEnabled);
        btnDispatchOrder.setEnabled(isVisibleOrEnabled);
        
        if(role.equals("Merchant"))
        {
            btnUpdateOrderStatus.setVisible(false);
            btnUpdateOrderStatus.setVisible(false);
        
            btnDispatchOrder.setVisible(false);
            btnDispatchOrder.setEnabled(false);
            
            btnPlaceOrder.setVisible(true);
            btnPlaceOrder.setEnabled(true);
        }
        else if(role.equals("Dispatcher"))
        {
            btnPlaceOrder.setVisible(false);
            btnPlaceOrder.setEnabled(false);

            btnFindOrder.setVisible(false);
            btnFindOrder.setEnabled(false);

            btnMakePayement.setVisible(false);
            btnMakePayement.setEnabled(false);

            btnViewOutstandingBalances.setVisible(false);
            btnViewOutstandingBalances.setEnabled(false);

            btnUpdateOrderStatus.setVisible(false);
            btnUpdateOrderStatus.setVisible(false); 
        }
        else if(role.equals("Administrator"))
        {
            btnPlaceOrder.setVisible(false);
            btnPlaceOrder.setEnabled(false);
        }
    }
    
    public void setCatalogueVisibility(boolean isVisibleOrEnabled)
    {
        pnlCatalogue.setVisible(isVisibleOrEnabled);
        pnlCatalogue.setEnabled(isVisibleOrEnabled);
        
        btnFindProduct.setVisible(isVisibleOrEnabled);
        btnFindProduct.setEnabled(isVisibleOrEnabled);

        
        btnViewCatalogue.setVisible(isVisibleOrEnabled);
        btnViewCatalogue.setEnabled(isVisibleOrEnabled);
        
        btnCreateANewCatalogue.setVisible(isVisibleOrEnabled);
        btnCreateANewCatalogue.setEnabled(isVisibleOrEnabled);
        
        btnNewProduct.setVisible(isVisibleOrEnabled);
        btnNewProduct.setEnabled(isVisibleOrEnabled);
        
        btnStockMaintenance.setVisible(isVisibleOrEnabled);
        btnStockMaintenance.setEnabled(isVisibleOrEnabled);
        
        if(role.equals("Merchant"))
        {
            btnCreateANewCatalogue.setVisible(false);
            btnCreateANewCatalogue.setEnabled(false);

            btnNewProduct.setVisible(false);
            btnNewProduct.setEnabled(false);

            btnStockMaintenance.setVisible(false);
            btnStockMaintenance.setEnabled(false);
        }
    }
    
    public void setAccountVisiblity(boolean isVisibleOrEnalbed)
    {
        pnlAccounts.setVisible(isVisibleOrEnalbed);
        pnlAccounts.setEnabled(isVisibleOrEnalbed);
        
        btnRegister.setVisible(isVisibleOrEnalbed);
        btnRegister.setEnabled(isVisibleOrEnalbed);
        
        btnRestoreAccount.setVisible(isVisibleOrEnalbed);
        btnRestoreAccount.setEnabled(isVisibleOrEnalbed);
        
        btnViewMerchantAccount.setVisible(isVisibleOrEnalbed);
        btnViewMerchantAccount.setEnabled(isVisibleOrEnalbed);
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
        btnCatalogue = new javax.swing.JButton();
        btnAccounts = new javax.swing.JButton();
        btnReports = new javax.swing.JButton();
        btnGeneral = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        pnlOrders = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnDispatchOrder = new javax.swing.JButton();
        btnPlaceOrder = new javax.swing.JButton();
        btnFindOrder = new javax.swing.JButton();
        btnMakePayement = new javax.swing.JButton();
        btnViewOrderHistory = new javax.swing.JButton();
        btnUpdateOrderStatus = new javax.swing.JButton();
        btnViewOutstandingBalances = new javax.swing.JButton();
        pnlCatalogue = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnCreateANewCatalogue = new javax.swing.JButton();
        btnStockMaintenance = new javax.swing.JButton();
        btnFindProduct = new javax.swing.JButton();
        btnNewProduct = new javax.swing.JButton();
        btnViewCatalogue = new javax.swing.JButton();
        pnlGeneral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnViewLatePayments = new javax.swing.JButton();
        btnViewLowStock = new javax.swing.JButton();
        pnlReports = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnGenerateReport = new javax.swing.JButton();
        pnlAccounts = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnViewMerchantAccount = new javax.swing.JButton();
        btnRestoreAccount = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();

        btnCatalogue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/sidebuttons/catalogue.png"))); // NOI18N
        btnCatalogue.setBorderPainted(false);
        btnCatalogue.setContentAreaFilled(false);
        btnCatalogue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogueActionPerformed(evt);
            }
        });
        btnCatalogue.setBounds(30, 190, 80, 78);
        layeredPanel.add(btnCatalogue, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnAccounts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/sidebuttons/accounts.png"))); // NOI18N
        btnAccounts.setBorderPainted(false);
        btnAccounts.setContentAreaFilled(false);
        btnAccounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccountsActionPerformed(evt);
            }
        });
        btnAccounts.setBounds(20, 280, 100, 80);
        layeredPanel.add(btnAccounts, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/sidebuttons/reports.png"))); // NOI18N
        btnReports.setBorderPainted(false);
        btnReports.setContentAreaFilled(false);
        btnReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportsActionPerformed(evt);
            }
        });
        btnReports.setBounds(20, 370, 90, 90);
        layeredPanel.add(btnReports, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/sidebuttons/general.png"))); // NOI18N
        btnGeneral.setBorderPainted(false);
        btnGeneral.setContentAreaFilled(false);
        btnGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralActionPerformed(evt);
            }
        });
        btnGeneral.setBounds(20, 480, 90, 80);
        layeredPanel.add(btnGeneral, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/sidebuttons/order.png"))); // NOI18N
        btnOrder.setBorderPainted(false);
        btnOrder.setContentAreaFilled(false);
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });
        btnOrder.setBounds(20, 80, 100, 98);
        layeredPanel.add(btnOrder, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("ORDERS");
        jLabel3.setOpaque(true);

        btnDispatchOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/order/dispatchorder.png"))); // NOI18N
        btnDispatchOrder.setBorderPainted(false);
        btnDispatchOrder.setContentAreaFilled(false);
        btnDispatchOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDispatchOrderActionPerformed(evt);
            }
        });

        btnPlaceOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/order/placeorder.png"))); // NOI18N
        btnPlaceOrder.setBorderPainted(false);
        btnPlaceOrder.setContentAreaFilled(false);
        btnPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaceOrderActionPerformed(evt);
            }
        });

        btnFindOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/order/findorder.png"))); // NOI18N
        btnFindOrder.setBorderPainted(false);
        btnFindOrder.setContentAreaFilled(false);
        btnFindOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindOrderActionPerformed(evt);
            }
        });

        btnMakePayement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/order/makepayment.png"))); // NOI18N
        btnMakePayement.setBorderPainted(false);
        btnMakePayement.setContentAreaFilled(false);
        btnMakePayement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakePayementActionPerformed(evt);
            }
        });

        btnViewOrderHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/order/vieworderhistory.png"))); // NOI18N
        btnViewOrderHistory.setBorderPainted(false);
        btnViewOrderHistory.setContentAreaFilled(false);
        btnViewOrderHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewOrderHistoryActionPerformed(evt);
            }
        });

        btnUpdateOrderStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/order/updateorderstatus.png"))); // NOI18N
        btnUpdateOrderStatus.setBorderPainted(false);
        btnUpdateOrderStatus.setContentAreaFilled(false);
        btnUpdateOrderStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateOrderStatusActionPerformed(evt);
            }
        });

        btnViewOutstandingBalances.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/order/viewoutstandingbalances.png"))); // NOI18N
        btnViewOutstandingBalances.setBorderPainted(false);
        btnViewOutstandingBalances.setContentAreaFilled(false);
        btnViewOutstandingBalances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewOutstandingBalancesActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlOrdersLayout = new org.jdesktop.layout.GroupLayout(pnlOrders);
        pnlOrders.setLayout(pnlOrdersLayout);
        pnlOrdersLayout.setHorizontalGroup(
            pnlOrdersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlOrdersLayout.createSequentialGroup()
                .add(pnlOrdersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlOrdersLayout.createSequentialGroup()
                        .add(41, 41, 41)
                        .add(jLabel3))
                    .add(pnlOrdersLayout.createSequentialGroup()
                        .add(24, 24, 24)
                        .add(pnlOrdersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(btnDispatchOrder)
                            .add(btnFindOrder)
                            .add(btnPlaceOrder)
                            .add(btnMakePayement)
                            .add(btnViewOutstandingBalances)
                            .add(btnUpdateOrderStatus))
                        .add(276, 276, 276)
                        .add(btnViewOrderHistory)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOrdersLayout.setVerticalGroup(
            pnlOrdersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlOrdersLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnlOrdersLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(btnViewOrderHistory)
                    .add(pnlOrdersLayout.createSequentialGroup()
                        .add(btnDispatchOrder)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnPlaceOrder)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnFindOrder)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnMakePayement)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnViewOutstandingBalances)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnUpdateOrderStatus)))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pnlOrders.setBounds(140, 80, 540, 440);
        layeredPanel.add(pnlOrders, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("CATALOGUE");
        jLabel4.setOpaque(true);

        btnCreateANewCatalogue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/cat/createanewcatalogue.png"))); // NOI18N
        btnCreateANewCatalogue.setBorderPainted(false);
        btnCreateANewCatalogue.setContentAreaFilled(false);
        btnCreateANewCatalogue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateANewCatalogueActionPerformed(evt);
            }
        });

        btnStockMaintenance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/cat/stockmaintenance.png"))); // NOI18N
        btnStockMaintenance.setBorderPainted(false);
        btnStockMaintenance.setContentAreaFilled(false);
        btnStockMaintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockMaintenanceActionPerformed(evt);
            }
        });

        btnFindProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/cat/findproduct.png"))); // NOI18N
        btnFindProduct.setBorderPainted(false);
        btnFindProduct.setContentAreaFilled(false);
        btnFindProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindProductActionPerformed(evt);
            }
        });

        btnNewProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/cat/newproduct.png"))); // NOI18N
        btnNewProduct.setBorderPainted(false);
        btnNewProduct.setContentAreaFilled(false);
        btnNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewProductActionPerformed(evt);
            }
        });

        btnViewCatalogue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/cat/viewcatalogue.png"))); // NOI18N
        btnViewCatalogue.setBorderPainted(false);
        btnViewCatalogue.setContentAreaFilled(false);
        btnViewCatalogue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewCatalogueActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlCatalogueLayout = new org.jdesktop.layout.GroupLayout(pnlCatalogue);
        pnlCatalogue.setLayout(pnlCatalogueLayout);
        pnlCatalogueLayout.setHorizontalGroup(
            pnlCatalogueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlCatalogueLayout.createSequentialGroup()
                .add(41, 41, 41)
                .add(jLabel4)
                .addContainerGap())
            .add(pnlCatalogueLayout.createSequentialGroup()
                .add(23, 23, 23)
                .add(pnlCatalogueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlCatalogueLayout.createSequentialGroup()
                        .add(pnlCatalogueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(btnViewCatalogue)
                            .add(btnCreateANewCatalogue)
                            .add(btnNewProduct)
                            .add(btnStockMaintenance))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(pnlCatalogueLayout.createSequentialGroup()
                        .add(btnFindProduct)
                        .addContainerGap(242, Short.MAX_VALUE))))
        );
        pnlCatalogueLayout.setVerticalGroup(
            pnlCatalogueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlCatalogueLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnFindProduct)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnViewCatalogue)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnCreateANewCatalogue)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnNewProduct)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnStockMaintenance)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        pnlCatalogue.setBounds(140, 80, 530, 430);
        layeredPanel.add(pnlCatalogue, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("GENERAL");
        jLabel1.setOpaque(true);

        btnViewLatePayments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/general/viewlatepatments.png"))); // NOI18N
        btnViewLatePayments.setBorderPainted(false);
        btnViewLatePayments.setContentAreaFilled(false);
        btnViewLatePayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewLatePaymentsActionPerformed(evt);
            }
        });

        btnViewLowStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/general/viewlowstock.png"))); // NOI18N
        btnViewLowStock.setBorderPainted(false);
        btnViewLowStock.setContentAreaFilled(false);
        btnViewLowStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewLowStockActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlGeneralLayout = new org.jdesktop.layout.GroupLayout(pnlGeneral);
        pnlGeneral.setLayout(pnlGeneralLayout);
        pnlGeneralLayout.setHorizontalGroup(
            pnlGeneralLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlGeneralLayout.createSequentialGroup()
                .add(pnlGeneralLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlGeneralLayout.createSequentialGroup()
                        .add(41, 41, 41)
                        .add(jLabel1))
                    .add(pnlGeneralLayout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(pnlGeneralLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btnViewLowStock)
                            .add(btnViewLatePayments))))
                .addContainerGap(243, Short.MAX_VALUE))
        );
        pnlGeneralLayout.setVerticalGroup(
            pnlGeneralLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlGeneralLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnViewLatePayments)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnViewLowStock)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        pnlGeneral.setBounds(140, 80, 530, 440);
        layeredPanel.add(pnlGeneral, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("REPORTS");
        jLabel2.setOpaque(true);

        btnGenerateReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/report/generatereport.png"))); // NOI18N
        btnGenerateReport.setBorderPainted(false);
        btnGenerateReport.setContentAreaFilled(false);
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlReportsLayout = new org.jdesktop.layout.GroupLayout(pnlReports);
        pnlReports.setLayout(pnlReportsLayout);
        pnlReportsLayout.setHorizontalGroup(
            pnlReportsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlReportsLayout.createSequentialGroup()
                .add(pnlReportsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlReportsLayout.createSequentialGroup()
                        .add(41, 41, 41)
                        .add(jLabel2))
                    .add(pnlReportsLayout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(btnGenerateReport)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlReportsLayout.setVerticalGroup(
            pnlReportsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlReportsLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnGenerateReport)
                .addContainerGap(179, Short.MAX_VALUE))
        );

        pnlReports.setBounds(140, 80, 310, 290);
        layeredPanel.add(pnlReports, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("ACCOUNTS");
        jLabel5.setOpaque(true);

        btnViewMerchantAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/account/viewmerchantaccount.png"))); // NOI18N
        btnViewMerchantAccount.setBorderPainted(false);
        btnViewMerchantAccount.setContentAreaFilled(false);
        btnViewMerchantAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewMerchantAccountActionPerformed(evt);
            }
        });

        btnRestoreAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/account/restoreaccount.png"))); // NOI18N
        btnRestoreAccount.setBorderPainted(false);
        btnRestoreAccount.setContentAreaFilled(false);
        btnRestoreAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreAccountActionPerformed(evt);
            }
        });

        btnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/account/register.png"))); // NOI18N
        btnRegister.setBorderPainted(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlAccountsLayout = new org.jdesktop.layout.GroupLayout(pnlAccounts);
        pnlAccounts.setLayout(pnlAccountsLayout);
        pnlAccountsLayout.setHorizontalGroup(
            pnlAccountsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlAccountsLayout.createSequentialGroup()
                .add(pnlAccountsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pnlAccountsLayout.createSequentialGroup()
                        .add(41, 41, 41)
                        .add(jLabel5))
                    .add(pnlAccountsLayout.createSequentialGroup()
                        .add(23, 23, 23)
                        .add(pnlAccountsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btnRestoreAccount)
                            .add(btnViewMerchantAccount)
                            .add(btnRegister))))
                .addContainerGap(382, Short.MAX_VALUE))
        );
        pnlAccountsLayout.setVerticalGroup(
            pnlAccountsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlAccountsLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnViewMerchantAccount)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnRestoreAccount)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnRegister)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        pnlAccounts.setBounds(140, 80, 670, 440);
        layeredPanel.add(pnlAccounts, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/misc/images/mainmenu.png"))); // NOI18N
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

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        setGeneralVisibility(false);
        setOrderVisibility(true);
        setCatalogueVisibility(false);
        setGeneralVisibility(false);
        setAccountVisiblity(false);
        setReportVisbility(false);
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnCatalogueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogueActionPerformed
        setGeneralVisibility(false);
        setOrderVisibility(false);
        setCatalogueVisibility(true);
        setGeneralVisibility(false);
        setAccountVisiblity(false);
        setReportVisbility(false);
    }//GEN-LAST:event_btnCatalogueActionPerformed

    private void btnAccountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountsActionPerformed
        setGeneralVisibility(false);
        setOrderVisibility(false);
        setCatalogueVisibility(false);
        setGeneralVisibility(false);
        setAccountVisiblity(true);
        setReportVisbility(false);
    }//GEN-LAST:event_btnAccountsActionPerformed

    private void btnReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportsActionPerformed
        setGeneralVisibility(false);
        setOrderVisibility(false);
        setCatalogueVisibility(false);
        setGeneralVisibility(false);
        setAccountVisiblity(false);
        setReportVisbility(true);
    }//GEN-LAST:event_btnReportsActionPerformed

    private void btnGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralActionPerformed
        setGeneralVisibility(false);
        setOrderVisibility(false);
        setCatalogueVisibility(false);
        setGeneralVisibility(true);
        setAccountVisiblity(false);
        setReportVisbility(false);
    }//GEN-LAST:event_btnGeneralActionPerformed

    private void btnDispatchOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDispatchOrderActionPerformed
        frame.setPanel(new ViewDispatchOrder(this.getFrame()));
    }//GEN-LAST:event_btnDispatchOrderActionPerformed

    private void btnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaceOrderActionPerformed
        frame.setPanel(new ViewPlaceOrder(this.getFrame()));
    }//GEN-LAST:event_btnPlaceOrderActionPerformed

    private void btnFindOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindOrderActionPerformed
        frame.setPanel(new ViewFindOrder(this.getFrame()));
    }//GEN-LAST:event_btnFindOrderActionPerformed

    private void btnMakePayementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakePayementActionPerformed
        frame.setPanel(new ViewMakePayment(this.getFrame()));
    }//GEN-LAST:event_btnMakePayementActionPerformed

    private void btnViewOrderHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewOrderHistoryActionPerformed
        frame.setPanel(new ViewOrderHistory(this.getFrame()));
    }//GEN-LAST:event_btnViewOrderHistoryActionPerformed

    private void btnUpdateOrderStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateOrderStatusActionPerformed
        frame.setPanel(new ViewUpdateOrderStatus(this.getFrame()));
    }//GEN-LAST:event_btnUpdateOrderStatusActionPerformed

    private void btnViewOutstandingBalancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewOutstandingBalancesActionPerformed
        frame.setPanel(new ViewOutstandingBalances(this.getFrame()));
    }//GEN-LAST:event_btnViewOutstandingBalancesActionPerformed

    private void btnCreateANewCatalogueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateANewCatalogueActionPerformed
        frame.setPanel(new ViewNewCatalogue(this.getFrame()));
    }//GEN-LAST:event_btnCreateANewCatalogueActionPerformed

    private void btnStockMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockMaintenanceActionPerformed
        frame.setPanel(new ViewStockMaintenance(this.getFrame()));
    }//GEN-LAST:event_btnStockMaintenanceActionPerformed

    private void btnFindProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindProductActionPerformed
        frame.setPanel(new ViewFindProduct(this.getFrame()));
    }//GEN-LAST:event_btnFindProductActionPerformed

    private void btnNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewProductActionPerformed
        frame.setPanel(new ViewNewProduct(this.getFrame()));
    }//GEN-LAST:event_btnNewProductActionPerformed

    private void btnViewCatalogueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCatalogueActionPerformed
        frame.setPanel(new ViewViewCatalogue(this.getFrame()));
    }//GEN-LAST:event_btnViewCatalogueActionPerformed

    private void btnViewLatePaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewLatePaymentsActionPerformed
        frame.setPanel(new ViewLatePayment(this.getFrame()));
    }//GEN-LAST:event_btnViewLatePaymentsActionPerformed

    private void btnViewLowStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewLowStockActionPerformed
        frame.setPanel(new ViewLowStockNotification(this.getFrame()));
    }//GEN-LAST:event_btnViewLowStockActionPerformed

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed
        frame.setPanel(new ViewGenerateReport(this.getFrame()));
    }//GEN-LAST:event_btnGenerateReportActionPerformed

    private void btnViewMerchantAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewMerchantAccountActionPerformed
        frame.setPanel(new ViewMerchant(this.getFrame()));
    }//GEN-LAST:event_btnViewMerchantAccountActionPerformed

    private void btnRestoreAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreAccountActionPerformed
        frame.setPanel(new ViewRestoreAccount(this.getFrame()));
    }//GEN-LAST:event_btnRestoreAccountActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        frame.setPanel(new ViewRegister(this.getFrame()));
    }//GEN-LAST:event_btnRegisterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccounts;
    private javax.swing.JButton btnCatalogue;
    private javax.swing.JButton btnCreateANewCatalogue;
    private javax.swing.JButton btnDispatchOrder;
    private javax.swing.JButton btnFindOrder;
    private javax.swing.JButton btnFindProduct;
    private javax.swing.JButton btnGeneral;
    private javax.swing.JButton btnGenerateReport;
    private javax.swing.JButton btnMakePayement;
    private javax.swing.JButton btnNewProduct;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnPlaceOrder;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnReports;
    private javax.swing.JButton btnRestoreAccount;
    private javax.swing.JButton btnStockMaintenance;
    private javax.swing.JButton btnUpdateOrderStatus;
    private javax.swing.JButton btnViewCatalogue;
    private javax.swing.JButton btnViewLatePayments;
    private javax.swing.JButton btnViewLowStock;
    private javax.swing.JButton btnViewMerchantAccount;
    private javax.swing.JButton btnViewOrderHistory;
    private javax.swing.JButton btnViewOutstandingBalances;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JPanel pnlAccounts;
    private javax.swing.JPanel pnlCatalogue;
    private javax.swing.JPanel pnlGeneral;
    private javax.swing.JPanel pnlOrders;
    private javax.swing.JPanel pnlReports;
    // End of variables declaration//GEN-END:variables
}
