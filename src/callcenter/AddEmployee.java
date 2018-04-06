package callcenter;

import java.awt.CardLayout;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class AddEmployee extends javax.swing.JDialog {

    boolean added = false;
    CardLayout c = new CardLayout();
    String empID;
    Connection connection;

    public AddEmployee(java.awt.Frame parent, boolean modal, Connection connection) {
        super(parent, modal);
        initComponents();
        this.connection = connection;
        mobileMsgL.setVisible(false);
        emailMsgL.setVisible(false);
        pinMsgL.setVisible(false);
        cemailMsgL.setVisible(false);
        addEmployeeBP.setLayout(c);
        addEmployeeBP.add(addEmployeeFP, "first");
        addEmployeeBP.add(addEmployeeSP, "second");
        generateID();

    }

    public void generateID() {
        GetID getID = new GetID(connection);
        empID = getID.getEmployeeID();
        int id = Integer.parseInt(empID.substring(1));
        id = id + 1;
        String newID = null;
        if(postCB.getSelectedItem().equals("Employee")){
        newID = "A" + id;
        
        }
        else if(postCB.getSelectedItem().equals("Manager")){
            newID = "M" + id;
        }
        userIDT.setText(newID); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel16 = new javax.swing.JLabel();
        addEmployeeBP = new javax.swing.JPanel();
        addEmployeeFP = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        firstNameT = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        genderCB = new javax.swing.JComboBox<>();
        lastNameT = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        addressT = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        countryT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pinCodeT = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        userIDT = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        compMailT = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        deskIDT = new javax.swing.JTextField();
        dob = new com.github.lgooddatepicker.components.DatePicker();
        stateCB = new javax.swing.JComboBox<>();
        cityCB = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        mobileT = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        emailT = new javax.swing.JTextField();
        mobileMsgL = new javax.swing.JLabel();
        emailMsgL = new javax.swing.JLabel();
        pinMsgL = new javax.swing.JLabel();
        cemailMsgL = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nextB = new javax.swing.JButton();
        resetB = new javax.swing.JButton();
        deptCB = new javax.swing.JComboBox<>();
        postCB = new javax.swing.JComboBox<>();
        addEmployeeSP = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lastnameL = new javax.swing.JLabel();
        firstnameL = new javax.swing.JLabel();
        dobL = new javax.swing.JLabel();
        mobL = new javax.swing.JLabel();
        emailL = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        addressL = new javax.swing.JLabel();
        countryL = new javax.swing.JLabel();
        stateL = new javax.swing.JLabel();
        cityL = new javax.swing.JLabel();
        pincodeL = new javax.swing.JLabel();
        deptIDL = new javax.swing.JLabel();
        userIDL = new javax.swing.JLabel();
        deskIDL = new javax.swing.JLabel();
        comMailL = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        submitB = new javax.swing.JButton();
        prevB = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        postL = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();

        jToggleButton1.setText("jToggleButton1");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 255));
        jLabel16.setText("Personal Details");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(681, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        addEmployeeBP.setMaximumSize(new java.awt.Dimension(681, 700));
        addEmployeeBP.setMinimumSize(new java.awt.Dimension(681, 700));
        addEmployeeBP.setLayout(null);

        addEmployeeFP.setMaximumSize(new java.awt.Dimension(681, 700));
        addEmployeeFP.setMinimumSize(new java.awt.Dimension(681, 700));
        addEmployeeFP.setLayout(null);

        jLabel4.setText("First Name");
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel4);
        jLabel4.setBounds(10, 60, 90, 30);

        jLabel10.setText("DOB");
        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel10);
        jLabel10.setBounds(10, 110, 90, 30);
        addEmployeeFP.add(firstNameT);
        firstNameT.setBounds(110, 60, 172, 30);

        jLabel21.setText("Last Name");
        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel21);
        jLabel21.setBounds(310, 60, 76, 30);

        jLabel12.setText("Gender");
        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel12);
        jLabel12.setBounds(310, 110, 76, 30);

        genderCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Male", "Female" }));
        addEmployeeFP.add(genderCB);
        genderCB.setBounds(410, 110, 90, 30);
        addEmployeeFP.add(lastNameT);
        lastNameT.setBounds(410, 60, 246, 30);

        jLabel17.setText("Address");
        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel17);
        jLabel17.setBounds(10, 250, 90, 30);

        addressT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTActionPerformed(evt);
            }
        });
        addEmployeeFP.add(addressT);
        addressT.setBounds(80, 250, 480, 30);

        jLabel13.setText("Country");
        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel13);
        jLabel13.setBounds(10, 300, 54, 30);

        countryT.setEditable(false);
        countryT.setText("India");
        addEmployeeFP.add(countryT);
        countryT.setBounds(80, 300, 200, 30);

        jLabel7.setText("State");
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel7);
        jLabel7.setBounds(320, 300, 66, 30);

        jLabel9.setText("Pin Code");
        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel9);
        jLabel9.setBounds(320, 350, 55, 30);

        jLabel8.setText("City");
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel8);
        jLabel8.setBounds(10, 350, 39, 30);

        pinCodeT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pinCodeTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pinCodeTFocusLost(evt);
            }
        });
        pinCodeT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinCodeTActionPerformed(evt);
            }
        });
        pinCodeT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pinCodeTKeyPressed(evt);
            }
        });
        addEmployeeFP.add(pinCodeT);
        pinCodeT.setBounds(400, 350, 200, 30);

        jLabel18.setText("User ID");
        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel18);
        jLabel18.setBounds(10, 550, 60, 30);

        userIDT.setEnabled(false);
        userIDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userIDTKeyPressed(evt);
            }
        });
        addEmployeeFP.add(userIDT);
        userIDT.setBounds(120, 550, 180, 30);

        jLabel11.setText("Company Mail");
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel11);
        jLabel11.setBounds(10, 450, 90, 30);

        compMailT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                compMailTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                compMailTFocusLost(evt);
            }
        });
        compMailT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                compMailTKeyPressed(evt);
            }
        });
        addEmployeeFP.add(compMailT);
        compMailT.setBounds(120, 450, 210, 30);

        jLabel15.setText("Department ID");
        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel15);
        jLabel15.setBounds(10, 500, 94, 30);

        jLabel14.setText("Desk ID");
        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel14);
        jLabel14.setBounds(370, 500, 68, 30);

        deskIDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deskIDTKeyPressed(evt);
            }
        });
        addEmployeeFP.add(deskIDT);
        deskIDT.setBounds(460, 500, 180, 30);
        addEmployeeFP.add(dob);
        dob.setBounds(110, 110, 170, 30);

        stateCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chandigarh", "Chhattisgarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Pondicherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengall" }));
        stateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateCBActionPerformed(evt);
            }
        });
        addEmployeeFP.add(stateCB);
        stateCB.setBounds(400, 300, 200, 30);

        cityCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--" }));
        cityCB.setEnabled(false);
        addEmployeeFP.add(cityCB);
        cityCB.setBounds(80, 350, 200, 30);

        jLabel20.setText("Company Details");
        jLabel20.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 255));
        addEmployeeFP.add(jLabel20);
        jLabel20.setBounds(20, 400, 150, 30);

        jLabel3.setText("Address Details");
        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        addEmployeeFP.add(jLabel3);
        jLabel3.setBounds(20, 200, 150, 30);

        jLabel1.setText("Personal Details");
        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        addEmployeeFP.add(jLabel1);
        jLabel1.setBounds(20, 10, 150, 30);
        addEmployeeFP.add(jSeparator1);
        jSeparator1.setBounds(-10, 430, 700, 10);
        addEmployeeFP.add(jSeparator2);
        jSeparator2.setBounds(0, 40, 680, 10);
        addEmployeeFP.add(jSeparator3);
        jSeparator3.setBounds(0, 230, 680, 10);

        jLabel5.setText("Mobile No");
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel5);
        jLabel5.setBounds(10, 160, 90, 30);

        mobileT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mobileTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mobileTFocusLost(evt);
            }
        });
        mobileT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mobileTKeyPressed(evt);
            }
        });
        addEmployeeFP.add(mobileT);
        mobileT.setBounds(110, 160, 172, 30);

        jLabel22.setText("E-Mail");
        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addEmployeeFP.add(jLabel22);
        jLabel22.setBounds(310, 160, 76, 30);

        emailT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailTFocusLost(evt);
            }
        });
        emailT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailTKeyPressed(evt);
            }
        });
        addEmployeeFP.add(emailT);
        emailT.setBounds(410, 160, 246, 30);

        mobileMsgL.setText("Enter a valid number");
        mobileMsgL.setForeground(new java.awt.Color(255, 0, 0));
        addEmployeeFP.add(mobileMsgL);
        mobileMsgL.setBounds(130, 190, 140, 14);

        emailMsgL.setText("Please enter a valid email ID");
        emailMsgL.setForeground(new java.awt.Color(255, 0, 0));
        addEmployeeFP.add(emailMsgL);
        emailMsgL.setBounds(430, 190, 210, 14);

        pinMsgL.setText("Enter a valid pincode");
        pinMsgL.setForeground(new java.awt.Color(255, 0, 0));
        addEmployeeFP.add(pinMsgL);
        pinMsgL.setBounds(420, 380, 140, 14);

        cemailMsgL.setText("Please enter a valid email ID");
        cemailMsgL.setForeground(new java.awt.Color(255, 0, 0));
        addEmployeeFP.add(cemailMsgL);
        cemailMsgL.setBounds(130, 480, 210, 14);

        jLabel2.setText("Post");
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        addEmployeeFP.add(jLabel2);
        jLabel2.setBounds(380, 450, 50, 30);

        nextB.setText("Next");
        nextB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        nextB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBActionPerformed(evt);
            }
        });
        addEmployeeFP.add(nextB);
        nextB.setBounds(230, 600, 80, 30);

        resetB.setText("Reset");
        resetB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        resetB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBActionPerformed(evt);
            }
        });
        addEmployeeFP.add(resetB);
        resetB.setBounds(350, 600, 80, 30);

        deptCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Management", "Technical", "Service Provider", "Other" }));
        addEmployeeFP.add(deptCB);
        deptCB.setBounds(120, 500, 180, 30);

        postCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employee", "Manager" }));
        postCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postCBActionPerformed(evt);
            }
        });
        addEmployeeFP.add(postCB);
        postCB.setBounds(460, 450, 180, 30);

        addEmployeeBP.add(addEmployeeFP);
        addEmployeeFP.setBounds(0, 0, 681, 700);

        addEmployeeSP.setMaximumSize(new java.awt.Dimension(681, 700));
        addEmployeeSP.setMinimumSize(new java.awt.Dimension(681, 700));
        addEmployeeSP.setPreferredSize(new java.awt.Dimension(681, 700));
        addEmployeeSP.setLayout(null);

        jLabel23.setText("First Name");
        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 255));
        jLabel23.setOpaque(true);
        addEmployeeSP.add(jLabel23);
        jLabel23.setBounds(20, 60, 90, 30);

        jLabel25.setText("DOB");
        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 255));
        jLabel25.setOpaque(true);
        addEmployeeSP.add(jLabel25);
        jLabel25.setBounds(20, 100, 90, 30);

        jLabel26.setText("Mobile No");
        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 255));
        jLabel26.setOpaque(true);
        addEmployeeSP.add(jLabel26);
        jLabel26.setBounds(10, 160, 90, 30);

        jLabel27.setText("Last Name");
        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 255));
        jLabel27.setOpaque(true);
        addEmployeeSP.add(jLabel27);
        jLabel27.setBounds(300, 60, 76, 30);

        jLabel28.setText("E-Mail");
        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 255));
        jLabel28.setOpaque(true);
        addEmployeeSP.add(jLabel28);
        jLabel28.setBounds(310, 160, 76, 30);

        lastnameL.setOpaque(true);
        addEmployeeSP.add(lastnameL);
        lastnameL.setBounds(410, 60, 170, 30);

        firstnameL.setOpaque(true);
        addEmployeeSP.add(firstnameL);
        firstnameL.setBounds(110, 60, 160, 30);

        dobL.setOpaque(true);
        addEmployeeSP.add(dobL);
        dobL.setBounds(110, 100, 180, 30);

        mobL.setOpaque(true);
        addEmployeeSP.add(mobL);
        mobL.setBounds(90, 160, 200, 30);

        emailL.setOpaque(true);
        addEmployeeSP.add(emailL);
        emailL.setBounds(400, 160, 360, 30);

        jLabel29.setText("Address");
        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 102, 255));
        jLabel29.setOpaque(true);
        addEmployeeSP.add(jLabel29);
        jLabel29.setBounds(10, 210, 90, 30);

        jLabel30.setText("Country");
        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 102, 255));
        jLabel30.setOpaque(true);
        addEmployeeSP.add(jLabel30);
        jLabel30.setBounds(10, 260, 54, 30);

        jLabel31.setText("City");
        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 102, 255));
        jLabel31.setOpaque(true);
        addEmployeeSP.add(jLabel31);
        jLabel31.setBounds(10, 310, 39, 30);

        jLabel32.setText("State");
        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 102, 255));
        jLabel32.setOpaque(true);
        addEmployeeSP.add(jLabel32);
        jLabel32.setBounds(320, 260, 66, 30);

        jLabel33.setText("Pin Code");
        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 102, 255));
        jLabel33.setOpaque(true);
        addEmployeeSP.add(jLabel33);
        jLabel33.setBounds(320, 310, 55, 30);

        jLabel34.setText("Company Mail");
        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 102, 255));
        jLabel34.setOpaque(true);
        addEmployeeSP.add(jLabel34);
        jLabel34.setBounds(10, 350, 90, 30);

        jLabel35.setText("Department ID");
        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 255));
        jLabel35.setOpaque(true);
        addEmployeeSP.add(jLabel35);
        jLabel35.setBounds(10, 400, 94, 30);

        jLabel36.setText("User ID");
        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 102, 255));
        jLabel36.setOpaque(true);
        addEmployeeSP.add(jLabel36);
        jLabel36.setBounds(10, 440, 60, 30);

        addressL.setOpaque(true);
        addEmployeeSP.add(addressL);
        addressL.setBounds(100, 210, 640, 30);

        countryL.setOpaque(true);
        addEmployeeSP.add(countryL);
        countryL.setBounds(80, 260, 180, 30);

        stateL.setOpaque(true);
        addEmployeeSP.add(stateL);
        stateL.setBounds(420, 260, 240, 30);

        cityL.setOpaque(true);
        addEmployeeSP.add(cityL);
        cityL.setBounds(70, 310, 210, 30);

        pincodeL.setOpaque(true);
        addEmployeeSP.add(pincodeL);
        pincodeL.setBounds(430, 310, 220, 30);

        deptIDL.setOpaque(true);
        addEmployeeSP.add(deptIDL);
        deptIDL.setBounds(130, 400, 330, 30);

        userIDL.setOpaque(true);
        addEmployeeSP.add(userIDL);
        userIDL.setBounds(120, 440, 330, 30);

        deskIDL.setOpaque(true);
        addEmployeeSP.add(deskIDL);
        deskIDL.setBounds(100, 490, 330, 30);

        comMailL.setOpaque(true);
        addEmployeeSP.add(comMailL);
        comMailL.setBounds(120, 350, 330, 30);

        jLabel38.setText("Desk ID");
        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 102, 255));
        jLabel38.setOpaque(true);
        addEmployeeSP.add(jLabel38);
        jLabel38.setBounds(10, 490, 68, 30);

        submitB.setText("Submit");
        submitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBActionPerformed(evt);
            }
        });
        addEmployeeSP.add(submitB);
        submitB.setBounds(300, 590, 110, 23);

        prevB.setText("Previous");
        prevB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBActionPerformed(evt);
            }
        });
        addEmployeeSP.add(prevB);
        prevB.setBounds(170, 590, 110, 23);

        jLabel6.setText("Post");
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 255));
        jLabel6.setOpaque(true);
        addEmployeeSP.add(jLabel6);
        jLabel6.setBounds(10, 540, 29, 19);

        postL.setOpaque(true);
        addEmployeeSP.add(postL);
        postL.setBounds(100, 530, 210, 30);
        addEmployeeSP.add(jSeparator4);
        jSeparator4.setBounds(0, 40, 680, 10);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 255));
        jLabel19.setText("Employee Details");
        jLabel19.setOpaque(true);
        addEmployeeSP.add(jLabel19);
        jLabel19.setBounds(20, 10, 150, 30);

        addEmployeeBP.add(addEmployeeSP);
        addEmployeeSP.setBounds(0, 0, 681, 700);

        getContentPane().add(addEmployeeBP);
        addEmployeeBP.setBounds(0, 0, 681, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addressTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTActionPerformed

    private void pinCodeTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pinCodeTFocusGained
        pinMsgL.setVisible(false);
    }//GEN-LAST:event_pinCodeTFocusGained

    private void pinCodeTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pinCodeTFocusLost
        Pattern reg = Pattern.compile("^[0-9]{6}$");
        Matcher matcher = reg.matcher(pinCodeT.getText());
        if (!matcher.matches()) {
            pinMsgL.setVisible(true);
        }
    }//GEN-LAST:event_pinCodeTFocusLost

    private void pinCodeTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinCodeTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pinCodeTActionPerformed

    private void pinCodeTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pinCodeTKeyPressed
        char a = evt.getKeyChar();
        if ((a >= '0' && a <= '9') || evt.getKeyCode() == 8) {
            mobileT.setEditable(true);
        } else {
            mobileT.setEditable(false);
        }
    }//GEN-LAST:event_pinCodeTKeyPressed

    private void userIDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userIDTKeyPressed
        char a = evt.getKeyChar();
        if ((a >= '0' && a <= '9') || evt.getKeyCode() == 8) {
            mobileT.setEditable(true);
        } else {
            mobileT.setEditable(false);
        }
    }//GEN-LAST:event_userIDTKeyPressed

    private void compMailTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_compMailTFocusGained
        cemailMsgL.setVisible(false);
    }//GEN-LAST:event_compMailTFocusGained

    private void compMailTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_compMailTFocusLost
        Pattern reg = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = reg.matcher(compMailT.getText());
        if (!matcher.matches()) {
            cemailMsgL.setVisible(true);
        }
    }//GEN-LAST:event_compMailTFocusLost

    private void compMailTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_compMailTKeyPressed
        compMailT.setText(compMailT.getText().toLowerCase());
    }//GEN-LAST:event_compMailTKeyPressed

    private void nextBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBActionPerformed
        if ("".equals(emailT.getText()) || "".equals(mobileT.getText())
                || 0 == cityCB.getSelectedIndex() || "".equals(userIDT.getText()) || "".equals(pinCodeT.getText())
                || "".equals(addressT.getText()) || stateCB.getSelectedIndex() == 0
                || "".equals(firstNameT.getText()) || "".equals(lastNameT.getText()) || dob.getDate() == null || genderCB.getSelectedIndex() == 0 || "".equals(compMailT.getText()) || "".equals(deskIDT.getText())) {
            JOptionPane.showMessageDialog(this, "Please enter all the feilds", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            firstnameL.setText(firstNameT.getText());
            lastnameL.setText(lastNameT.getText());
            dobL.setText(dob.getText());
            mobL.setText(mobileT.getText());
            emailL.setText(emailT.getText());
            addressL.setText(addressT.getText());
            countryL.setText(countryT.getText());
            stateL.setText((String) stateCB.getSelectedItem());
            cityL.setText((String) cityCB.getSelectedItem());
            pincodeL.setText(pinCodeT.getText());
            comMailL.setText(compMailT.getText());
            userIDL.setText(userIDT.getText());
            deptIDL.setText((String) deptCB.getSelectedItem());
            deskIDL.setText(deskIDT.getText());
            postL.setText((String) postCB.getSelectedItem());

            c.show(addEmployeeBP, "second");
        }
//        else{
//            added=true;
//            cid=(int)(Math.random()*9000)+1000;
//            dispose();
//        }
    }//GEN-LAST:event_nextBActionPerformed

    private void resetBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBActionPerformed
        firstNameT.setText("");
        lastNameT.setText("");
        dob.setDate(null);
        genderCB.setSelectedIndex(0);
        mobileT.setText("");
        emailT.setText("");
        addressT.setText("");
        stateCB.setSelectedIndex(0);
        cityCB.setSelectedIndex(0);
        pinCodeT.setText("");
        compMailT.setText("");
        userIDT.setText("");
        deskIDT.setText("");
    }//GEN-LAST:event_resetBActionPerformed

    private void stateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateCBActionPerformed
        cityCB.removeAllItems();
        cityCB.addItem("--Select--");

        int state = stateCB.getSelectedIndex();

        switch (state) {
            case 0:
                cityCB.setEnabled(false);
                break;
            case 1:
                for (String w : Cities.andhraPradesh) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 2:
                for (String w : Cities.arunachalPradesh) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 3:
                for (String w : Cities.assam) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 4:
                for (String w : Cities.bihar) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 5:
                for (String w : Cities.chandigarh) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 6:
                for (String w : Cities.chhattisgarh) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 7:
                for (String w : Cities.dadarNagar) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 8:
                for (String w : Cities.damanDiu) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 9:
                for (String w : Cities.delhi) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 10:
                for (String w : Cities.goa) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 11:
                for (String w : Cities.gujrat) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 12:
                for (String w : Cities.haryana) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 13:
                for (String w : Cities.himachalPradesh) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 14:
                for (String w : Cities.jammuKashmir) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 15:
                for (String w : Cities.jharkhand) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 16:
                for (String w : Cities.karnataka) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 17:
                for (String w : Cities.kerla) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 18:
                for (String w : Cities.madhyaPradesh) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 19:
                for (String w : Cities.maharashtra) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 20:
                for (String w : Cities.manipur) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 21:
                for (String w : Cities.meghalaya) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 22:
                for (String w : Cities.mizoram) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 23:
                for (String w : Cities.nagaland) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 24:
                for (String w : Cities.orissa) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 25:
                for (String w : Cities.pondicherry) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 26:
                for (String w : Cities.punjab) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 27:
                for (String w : Cities.rajasthan) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 28:
                for (String w : Cities.sikkim) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 29:
                for (String w : Cities.tamilNadu) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 30:
                for (String w : Cities.tripura) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 31:
                for (String w : Cities.uttarPradesh) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 32:
                for (String w : Cities.uttarakhand) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            case 33:
                for (String w : Cities.westBengal) {
                    cityCB.addItem(w);
                }
                cityCB.setEnabled(true);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_stateCBActionPerformed

    private void mobileTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mobileTFocusGained
        mobileMsgL.setVisible(false);
    }//GEN-LAST:event_mobileTFocusGained

    private void mobileTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mobileTFocusLost
        Pattern reg = Pattern.compile("^[0-9]{10}$");
        Matcher matcher = reg.matcher(mobileT.getText());
        if (!matcher.matches()) {
            mobileMsgL.setVisible(true);
        }
    }//GEN-LAST:event_mobileTFocusLost

    private void mobileTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mobileTKeyPressed
        char a = evt.getKeyChar();
        if ((a >= '0' && a <= '9') || evt.getKeyCode() == 8) {
            mobileT.setEditable(true);
        } else {
            mobileT.setEditable(false);
        }
        //            int key = evt.getKeyCode();
        //
        //            if((key>=96 && key<=105)||key==8)
        //                 mobileT.setEditable(true);
        //            else
        //                mobileT.setEditable(false);
    }//GEN-LAST:event_mobileTKeyPressed

    private void emailTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTFocusGained
        emailMsgL.setVisible(false);
    }//GEN-LAST:event_emailTFocusGained

    private void emailTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTFocusLost
        Pattern reg = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = reg.matcher(emailT.getText());
        if (!matcher.matches()) {
            emailMsgL.setVisible(true);
        }
    }//GEN-LAST:event_emailTFocusLost

    private void emailTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailTKeyPressed
        emailT.setText(emailT.getText().toLowerCase());
    }//GEN-LAST:event_emailTKeyPressed

    private void deskIDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deskIDTKeyPressed
        char a = evt.getKeyChar();
        if ((a >= '0' && a <= '9') || evt.getKeyCode() == 8) {
            mobileT.setEditable(true);
        } else {
            mobileT.setEditable(false);
        }
    }//GEN-LAST:event_deskIDTKeyPressed

    private void prevBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBActionPerformed
        // TODO add your handling code here:
        c.show(addEmployeeBP, "first");
    }//GEN-LAST:event_prevBActionPerformed

    private void submitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBActionPerformed
        // TODO add your handling code here:
        added = true;
        dispose();
    }//GEN-LAST:event_submitBActionPerformed

    private void postCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postCBActionPerformed
        // TODO add your handling code here:
        generateID();
    }//GEN-LAST:event_postCBActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            AddEmployee dialog = new AddEmployee(new javax.swing.JFrame(), true, null);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addEmployeeBP;
    private javax.swing.JPanel addEmployeeFP;
    private javax.swing.JPanel addEmployeeSP;
    private javax.swing.JLabel addressL;
    public javax.swing.JTextField addressT;
    private javax.swing.JLabel cemailMsgL;
    public javax.swing.JComboBox<String> cityCB;
    private javax.swing.JLabel cityL;
    private javax.swing.JLabel comMailL;
    public javax.swing.JTextField compMailT;
    private javax.swing.JLabel countryL;
    public javax.swing.JTextField countryT;
    public javax.swing.JComboBox<String> deptCB;
    private javax.swing.JLabel deptIDL;
    private javax.swing.JLabel deskIDL;
    public javax.swing.JTextField deskIDT;
    public com.github.lgooddatepicker.components.DatePicker dob;
    private javax.swing.JLabel dobL;
    private javax.swing.JLabel emailL;
    private javax.swing.JLabel emailMsgL;
    public javax.swing.JTextField emailT;
    public javax.swing.JTextField firstNameT;
    private javax.swing.JLabel firstnameL;
    public javax.swing.JComboBox<String> genderCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToggleButton jToggleButton1;
    public javax.swing.JTextField lastNameT;
    private javax.swing.JLabel lastnameL;
    private javax.swing.JLabel mobL;
    private javax.swing.JLabel mobileMsgL;
    public javax.swing.JTextField mobileT;
    private javax.swing.JButton nextB;
    public javax.swing.JTextField pinCodeT;
    private javax.swing.JLabel pinMsgL;
    private javax.swing.JLabel pincodeL;
    public javax.swing.JComboBox<String> postCB;
    private javax.swing.JLabel postL;
    private javax.swing.JButton prevB;
    private javax.swing.JButton resetB;
    public javax.swing.JComboBox<String> stateCB;
    private javax.swing.JLabel stateL;
    private javax.swing.JButton submitB;
    private javax.swing.JLabel userIDL;
    public javax.swing.JTextField userIDT;
    // End of variables declaration//GEN-END:variables
}
