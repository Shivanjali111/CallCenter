package callcenter;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class LoginDialog extends javax.swing.JDialog {

    JFrame p;
    String userID,name;
    int flag;
    Connection connection;
    String username;
    public LoginDialog(JFrame parent, boolean modal) {

        super(parent, modal);
        getContentPane().setBackground(Color.WHITE);
        p = parent;
        initComponents();
        this.getRootPane().setDefaultButton(loginSubmitB);
        this.setTitle("Login");
        loginmsgL.setVisible(false);
        
    }

    public void validateUser() {
        GetData getdata = new GetData();
        String password = passwordT.getText();
         username = userIDT.getText();
        boolean flag1 = false;
        getdata.createConnection();
        connection = getdata.getConnection();
        String query = "select * from user_details ";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String actual_username = rs.getString("username");
                String actual_password = rs.getString("password");
                String post = rs.getString("post");
                if ((password.equals(actual_password)) && (username.equals(actual_username))) {
                    flag1 = true;
                    if ("manager".equalsIgnoreCase(post)) {
                        flag = 1;
                        userID=rs.getString("employee_id");
                        dispose();
                    } else if ("employee".equalsIgnoreCase(post)) {
                        flag = 2;
                        userID=rs.getString("employee_id");
                        dispose();
                    } else {
                        flag = 0;
                    }
                    ResultSet rs1=st.executeQuery("select first_name, last_name from employee where employee_id='"+userID+"'");
                    while(rs1.next()){
                        name=rs1.getString("first_name")+" "+rs1.getString("last_name");
                    }
                    break;
                }
            }
            if (flag1 == false) {
                loginmsgL.setText("Invalid username password!");
                loginmsgL.setVisible(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        userIDT = new javax.swing.JTextField();
        loginSubmitB = new javax.swing.JButton();
        resetB = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        passwordT = new javax.swing.JPasswordField();
        loginmsgL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(300, 230));
        setMinimumSize(new java.awt.Dimension(300, 230));
        setPreferredSize(new java.awt.Dimension(300, 230));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("User ID");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 20, 49, 31);

        userIDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userIDTActionPerformed(evt);
            }
        });
        getContentPane().add(userIDT);
        userIDT.setBounds(100, 20, 150, 31);

        loginSubmitB.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        loginSubmitB.setText("Submit");
        loginSubmitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginSubmitBActionPerformed(evt);
            }
        });
        getContentPane().add(loginSubmitB);
        loginSubmitB.setBounds(50, 130, 71, 23);

        resetB.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        resetB.setText("Reset");
        resetB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBActionPerformed(evt);
            }
        });
        getContentPane().add(resetB);
        resetB.setBounds(140, 130, 77, 23);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 255));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 70, 65, 31);
        getContentPane().add(passwordT);
        passwordT.setBounds(100, 70, 149, 31);

        loginmsgL.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        loginmsgL.setForeground(new java.awt.Color(255, 51, 51));
        loginmsgL.setText("Please enter all the fields");
        getContentPane().add(loginmsgL);
        loginmsgL.setBounds(60, 110, 160, 13);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userIDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userIDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userIDTActionPerformed

    private void loginSubmitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginSubmitBActionPerformed
        
        if ("".equals(userIDT.getText()) || "".equals(passwordT.getText())) {
            loginmsgL.setText("Please enter all the fields");
            loginmsgL.setVisible(true);
        } else {
            validateUser();
        }
    }//GEN-LAST:event_loginSubmitBActionPerformed

    private void resetBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBActionPerformed
        userIDT.setText("");
        passwordT.setText("");
        loginmsgL.setText("");
    }//GEN-LAST:event_resetBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginDialog dialog = new LoginDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginSubmitB;
    private javax.swing.JLabel loginmsgL;
    private javax.swing.JPasswordField passwordT;
    private javax.swing.JButton resetB;
    private javax.swing.JTextField userIDT;
    // End of variables declaration//GEN-END:variables
}
