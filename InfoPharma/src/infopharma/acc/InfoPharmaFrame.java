/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.acc;

import infopharma.cat.*;
import infopharma.misc.ViewLatePayment;
import infopharma.misc.ViewLowStockNotification;
import infopharma.order.JButtonTableExample;
import infopharma.order.ViewFindOrder;
import infopharma.order.ViewPlaceOrder;
import infopharma.rprt.ReportActivity;
import infopharma.rprt.ReportLowStock;
import infopharma.rprt.ReportMerchantOrders;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Abdullah
 */
public class InfoPharmaFrame extends javax.swing.JFrame {
    private JPanel contentPane;
    /**
     * Creates new form InfoPharmaFrame
     */
	public InfoPharmaFrame() {
            initComponents();
            this.setPanel(new ViewFindOrder(this));
//            JButtonTableExample x = new JButtonTableExample();
            
            //will generate a pdf on the desktop - im just using this for testing.
            //ReportMerchantOrders x = new ReportMerchantOrders("4", "2011-08-07", "2011-08-07");
            //ReportActivity x = new ReportActivity("4", "2011-08-07", "2011-08-07");
            //ReportLowStock x = new ReportLowStock();

            this.setVisible(true);
            setTitle("InfoPharma");
	}
	
	public void setPanel(JPanel panel) {
            this.setContentPane(panel);
            this.setVisible(true);
	}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 570, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1100)/2, (screenSize.height-592)/2, 1100, 592);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
