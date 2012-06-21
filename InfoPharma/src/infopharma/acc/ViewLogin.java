/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.acc;

import infopharma.Validator;
import infopharma.data.MerchantAccount;
import infopharma.data.UserAccount;
import infopharma.data.MiscDBAccess;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Abdullah
 */
public class ViewLogin extends InfoPharmaPanel{
    
    private static InfoPharmaFrame frame;
	
    public ViewLogin(InfoPharmaFrame mainMenuFrame)
    {
        initComponents();
        setFrame(mainMenuFrame);
        this.setVisible(true);
        lblError.setVisible(false);
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
        txtPassword = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        lblError = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        txtPassword.setBackground(new java.awt.Color(241, 241, 241));
        txtPassword.setBorder(null);
        txtPassword.setOpaque(true);
        txtPassword.setBounds(440, 290, 130, 20);
        layeredPanel.add(txtPassword, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txtUsername.setBackground(new java.awt.Color(241, 241, 241));
        txtUsername.setBorder(null);
        txtUsername.setBounds(250, 290, 130, 20);
        layeredPanel.add(txtUsername, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/acc/images/error.png"))); // NOI18N
        lblError.setBounds(10, 520, 820, 40);
        layeredPanel.add(lblError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/infopharma/misc/images/login.png"))); // NOI18N
        imageLabel.setBounds(0, 0, 1100, 570);
        layeredPanel.add(imageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(620, 170, 97, 230);
        layeredPanel.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loginValidator();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void loginValidator()
    {

        Object[] fields = {txtUsername, txtPassword};
        if(Validator.isFilledIn(fields))
        {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            System.out.println("username:"+username+" password:"+password);
            login(username,password);
        }
        else
        {
            System.out.println("Fill in the username and password");
            lblError.setText("Plese enter your username and password");
            lblError.setVisible(true);
        }
    }
    
    public void login(String username, String password) {
            MiscDBAccess miscDBAccess = new MiscDBAccess();
            UserAccount userAccount = miscDBAccess.login(username, password);
            if(userAccount != null) {
                System.out.println("the username is is: "+username);
                MerchantAccount.setAccountNumber(miscDBAccess.getAccountNumber(username));
                this.getFrame().setPanel(new ViewMainMenu(this.getFrame()));
            }
            else
            {
                lblError.setText("Your username and password do not match anything in the database");
                lblError.setVisible(true);
            }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JLabel lblError;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
