/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.rprt;

import infopharma.acc.InfoPharmaFrame;
import infopharma.acc.InfoPharmaPanel;
import infopharma.acc.ViewMainMenu;
import infopharma.data.RprtDBAccess;
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Abdullah
 */
public class ViewGenerateReport extends InfoPharmaPanel{
    private static InfoPharmaFrame frame;
    private RprtDBAccess rprtDBAccess;
    String documentPath = "";
	
    public ViewGenerateReport(InfoPharmaFrame mainMenuFrame)
    {
        initComponents();
        rprtDBAccess = new RprtDBAccess();
        setFrame(mainMenuFrame);
        lblError.setVisible(false);
        this.setVisible(true);
        btnOpen.setVisible(false);
        pnlID.setVisible(false);
        pnlID.setEnabled(false);
        populateComboProducts();
        
    }
    
    public void populateComboProducts()
    {
        ddlMerchantIDs.removeAllItems();
        ArrayList<String> merchantIDs = rprtDBAccess.getAllMerchantIDs();
        for(int i =0; i< merchantIDs.size();i++)
        {
            ddlMerchantIDs.addItem(merchantIDs.get(i));
        }
        ddlMerchantIDs.updateUI();
    }
    
    public String convertToShortDateString(Date date)
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
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
        pnlID = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ddlMerchantIDs = new javax.swing.JComboBox();
        btnOpen = new javax.swing.JButton();
        calFrom = new com.toedter.calendar.JDateChooser();
        calTo = new com.toedter.calendar.JDateChooser();
        ddlReportType = new javax.swing.JComboBox();
        lblError = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();
        btnGenerate = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        pnlID.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Merchant ID");

        org.jdesktop.layout.GroupLayout pnlIDLayout = new org.jdesktop.layout.GroupLayout(pnlID);
        pnlID.setLayout(pnlIDLayout);
        pnlIDLayout.setHorizontalGroup(
            pnlIDLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlIDLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addContainerGap())
            .add(pnlIDLayout.createSequentialGroup()
                .add(ddlMerchantIDs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 270, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 60, Short.MAX_VALUE))
        );
        pnlIDLayout.setVerticalGroup(
            pnlIDLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlIDLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ddlMerchantIDs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlID.setBounds(360, 100, 330, 80);
        layeredPanel.add(pnlID, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/buttons/report/open.png"))); // NOI18N
        btnOpen.setBorderPainted(false);
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        btnOpen.setBounds(10, 300, 130, 42);
        layeredPanel.add(btnOpen, javax.swing.JLayeredPane.DEFAULT_LAYER);

        calFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calFromPropertyChange(evt);
            }
        });
        calFrom.setBounds(40, 230, 120, 20);
        layeredPanel.add(calFrom, javax.swing.JLayeredPane.DEFAULT_LAYER);

        calTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calToPropertyChange(evt);
            }
        });
        calTo.setBounds(170, 230, 123, 20);
        layeredPanel.add(calTo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ddlReportType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Activity Report", "Low Stock Report", "Merchant Orders Report", "Stock Turnaround Report" }));
        ddlReportType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddlReportTypeActionPerformed(evt);
            }
        });
        ddlReportType.setBounds(30, 140, 270, 27);
        layeredPanel.add(ddlReportType, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/rprt/images/reports.png"))); // NOI18N
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

        btnGenerate.setText("GENERATE");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });
        btnGenerate.setBounds(1019, 70, 80, 470);
        layeredPanel.add(btnGenerate, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnClose.setText("jButton1");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        btnClose.setBounds(1010, 540, 97, 29);
        layeredPanel.add(btnClose, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        lblError.setVisible(false);
        if(calFrom.getDate() == null || calTo.getDate() == null)
        {
            lblError.setText("Please select a date range");
            lblError.setVisible(true);
        }
        else
        {
            if(ddlReportType.getSelectedIndex() != 0)
            {
                generateNewReport();
                btnOpen.setVisible(true);
            }
            else
            {
                lblError.setText("Please select a report type");
                lblError.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        openReport(documentPath);
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.getFrame().setPanel(new ViewMainMenu(this.getFrame()));
    }//GEN-LAST:event_btnCloseActionPerformed

    private void calFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calFromPropertyChange
        calTo.setMinSelectableDate(calFrom.getDate());
    }//GEN-LAST:event_calFromPropertyChange

    private void calToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calToPropertyChange
        calFrom.setMaxSelectableDate(calTo.getDate());
    }//GEN-LAST:event_calToPropertyChange

    private void ddlReportTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddlReportTypeActionPerformed
        pnlID.setVisible(false);
        pnlID.setEnabled(false);
        if(ddlReportType.getSelectedItem().equals("Activity Report") || ddlReportType.getSelectedItem().equals("Merchant Orders Report"))
        {
            pnlID.setVisible(true);
            pnlID.setEnabled(true);
        }
        btnOpen.setVisible(false);
    }//GEN-LAST:event_ddlReportTypeActionPerformed

    public void generateNewReport()
    {
        String reportType = ddlReportType.getSelectedItem().toString();
        System.out.println("Selected: "+reportType);
        
        String from = convertToShortDateString(calFrom.getDate());
        String to = convertToShortDateString(calTo.getDate());
        
        pnlID.setVisible(false);
        pnlID.setEnabled(false);
        ddlReportType.setSelectedIndex(0);
        
        String accountNumber = ddlMerchantIDs.getSelectedItem().toString();
        System.out.println(accountNumber);
        
        
        if(reportType.equals("Activity Report"))
        {
            ReportActivity report = new ReportActivity(accountNumber, from, to);
            documentPath = report.documentName;
        }
        else if(reportType.equals("Low Stock Report"))
        {
            ReportLowStock report = new ReportLowStock();
            documentPath = report.documentName;
        }
        else if(reportType.equals("Merchant Orders Report"))
        {   
            ReportMerchantOrders report = new ReportMerchantOrders(accountNumber, from, to);
            documentPath = report.documentName;
        }
        else if(reportType.equals("Stock Turnaround Report"))
        {
            ReportStockTurnaround report = new ReportStockTurnaround();
            documentPath = report.documentName;
        }
        else if(reportType.equals("Product Turnaround Report"))
        {
            ReportTurnAround report = new ReportTurnAround();
            documentPath = report.documentName;
        }
        
        
    }
    
    public void openReport(String documentPath)
    {
        lblError.setVisible(false);
        try 
        {
            String filePath = documentPath;
            File pdfFile = new File(filePath);
            if (pdfFile.exists()) 
            {

                if (Desktop.isDesktopSupported()) 
                {
                    Desktop.getDesktop().open(pdfFile);
                } 
                else 
                {

                    System.out.println("Awt Desktop is not supported!");
                }

            } 
            else 
            {
                System.out.println("File is not exists!");
                lblError.setText("Sorry, can not find the file.  Please regenerate it.");
                lblError.setVisible(true);
            }

            System.out.println("Done");
 
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
	}
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnOpen;
    private com.toedter.calendar.JDateChooser calFrom;
    private com.toedter.calendar.JDateChooser calTo;
    private javax.swing.JComboBox ddlMerchantIDs;
    private javax.swing.JComboBox ddlReportType;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JPanel pnlID;
    // End of variables declaration//GEN-END:variables
}
