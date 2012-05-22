/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.cat;

import infopharma.Validator;
import infopharma.data.UserAccount;
import infopharma.data.MiscDBAccess;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import infopharma.acc.*;
import infopharma.data.CatDBAccess;

/**
 *
 * @author Abdullah
 */
public class ViewNewCatalogue extends InfoPharmaPanel{
    private static InfoPharmaFrame frame;
	
    public ViewNewCatalogue(InfoPharmaFrame mainMenuFrame)
    {
        initComponents();
        setFrame(mainMenuFrame);
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
        btnMainMenu = new javax.swing.JButton();
        txtCatalogueName = new javax.swing.JTextField();
        imageLabel = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnGo = new javax.swing.JButton();

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 990, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnMainMenu.setText("main menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });
        btnMainMenu.setBounds(1010, 10, 80, 50);
        layeredPanel.add(btnMainMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtCatalogueName.setBounds(30, 138, 270, 40);
        layeredPanel.add(txtCatalogueName, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/cat/images/createnewcatalogue.png"))); // NOI18N
        imageLabel.setBounds(0, 0, 1100, 570);
        layeredPanel.add(imageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnCancel.setText("jButton2");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnCancel.setBounds(1020, 529, 80, 40);
        layeredPanel.add(btnCancel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnGo.setText("jButton1");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        btnGo.setBounds(1020, 70, 80, 470);
        layeredPanel.add(btnGo, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        System.out.println("Clicked go");
        lblError.setVisible(false);
        validateCatalogueName();
    }//GEN-LAST:event_btnGoActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        goToMainMenu();
    }//GEN-LAST:event_btnCancelActionPerformed

    public void goToMainMenu()
    {
        this.getFrame().setPanel(new ViewMainMenu(this.getFrame()));
    }
    
    public void validateCatalogueName()
    {
        if(Validator.isFilledIn(txtCatalogueName.getText()))
        {
            System.out.println("Is filled i");
            insertCatalogue();
        }
        else
        {
            System.out.println("catalogue name is empty");
            lblError.setText("Catalogue name is empty");
            lblError.setVisible(true);
        }
    }
    
    public void insertCatalogue()
    {
        CatDBAccess catDBAccess = new CatDBAccess();
        if(catDBAccess.canCreateCatalogue())
        {
            //there is no catalogue, so can insert into database
            catDBAccess.insertNewCatalogue(txtCatalogueName.getText());
            goToMainMenu();
        }
        else
        {
            //there is a catalogue already!
            System.out.println("There is a catalogue already!!!");
            lblError.setText("The database already contains a catalogue");
            lblError.setVisible(true);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JTextField txtCatalogueName;
    // End of variables declaration//GEN-END:variables
}
