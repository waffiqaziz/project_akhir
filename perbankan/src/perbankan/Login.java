/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perbankan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class Login extends JFrame {

  String user;
  String pass;

  MyConnection con = new MyConnection();

  //DEKLARASI KOMPONEN
  JFrame window = new JFrame("Login");
  JLabel lUser = new JLabel("Username  ");
  JTextField tfUser = new JTextField();
  JLabel lPass = new JLabel("Passowrd  ");
  JPasswordField pfPass = new JPasswordField();
  JLabel lguide = new JLabel("Dont Have Account?");

  JButton btnLogin = new JButton("Login");
  JButton btnReset = new JButton("Reset");

  public Login() {
    window.setLayout(null);
    window.setSize(380, 250);
    //  window.setDefaultCloseOperation(3);
    window.setVisible(true);
    window.setLocationRelativeTo(null); // center
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan

//ADD COMPONENT
    window.add(lUser);
    window.add(tfUser);
    window.add(pfPass);
    window.add(lPass);
    window.add(lguide);
    window.add(btnLogin);
    window.add(btnReset);

// SETT BOUNDS
// sett bounds(m,n,o,p) >>> (sumbu-x,sumbu-y,panjang komponen, tinggi komponen)
    lUser.setBounds(80, 35, 120, 30);
    lPass.setBounds(80, 75, 120, 30);
    lguide.setBounds(130, 115, 120, 30);

    tfUser.setBounds(170, 35, 120, 30);
    pfPass.setBounds(170, 75, 120, 30);

    btnLogin.setBounds(200, 155, 90, 30);
    btnReset.setBounds(80, 155, 90, 30);

    btnLogin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        PreparedStatement ps = null;
        ResultSet rs;
        rs = null;

        // Tangkap input user        
        user = tfUser.getText();
        pass = String.valueOf(pfPass.getPassword());

        String query = "SELECT * FROM `user` WHERE `user` =? AND `pass` =?";

        try {
          MyConnection myConnection = new MyConnection();

          ps = myConnection.con.prepareStatement(query);
          ps.setString(1, user);
          ps.setString(2, pass);
          rs = ps.executeQuery();

          if (rs.next()) {
            LoginSuccess ls = new LoginSuccess();
            ls.setVisible(true);
            ls.pack();
            ls.setLocationRelativeTo(null); // center
//            ls.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
//            ls.jLabel.setText("Welcome " + user + " ");
            ls.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tobol close di tekan
            JOptionPane.showMessageDialog(null, "Login Success");
          } else {
            JOptionPane.showMessageDialog(null, "Incorrect Username or Password" + " Login Failed");
          }

        } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
      }

    });

    btnReset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        pfPass.setText("");
        tfUser.setText("");
      }
    });
  }
}
