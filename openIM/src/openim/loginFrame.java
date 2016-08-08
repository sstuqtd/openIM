/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openim;

import javax.swing.*;

/**
 *
 * @author abelk
 */
public class loginFrame extends JFrame{
    public loginFrame(){
        super("openIM login");
        
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Hello Java Swing");

        panel.add(label);

        super.add(panel);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(400, 280);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }
    
    
}
