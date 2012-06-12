/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma;

import infopharma.acc.InfoPharmaFrame;
import infopharma.acc.ViewLogin;

/**
 *
 * @author Abdullah
 */
public class LoadingScreen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NewJFrame x = new NewJFrame();
        x.setVisible(true);
        x.animateLogo();
        x.animateIPOS();
        x.animateLoadingBar();
        x.dispose();
        InfoPharmaFrame program = new InfoPharmaFrame();
        //System.exit(0);
        
       // NewJFrame.main(new String[0]);
    }
}
