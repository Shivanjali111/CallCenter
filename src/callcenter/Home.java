package callcenter;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Home extends javax.swing.JFrame {

    CardLayout c = new CardLayout();
    boolean shown = true;
    boolean loggedIn = false;
    int userID;
    int h, y, sy, dy, cy;    //Used in panel expanding and collapsing
    Timer reportsT;
    boolean show_reports_p = false;
    GetData gd = new GetData();
    Connection connection;

    public Home() {
        initComponents();
        this.setExtendedState(Home.MAXIMIZED_BOTH);
        ImageChange();
        empInfoT.setAutoResizeMode(empInfoT.AUTO_RESIZE_OFF);
        loggedL.setVisible(false);
        nameL.setVisible(false);
        msgL.setVisible(false);
        employeeInfoB.setVisible(false);
        accountP.setVisible(false);
        accountL.setVisible(false);
        reportsP.setVisible(false);
        baseP.setLayout(c);
        baseP.add(homeP, "h");
        baseP.add(adminP, "a");
        baseP.add(employeeP, "emp");
        baseP.add(aboutUsP,"abtus");
        adminBaseP.setLayout(c);
        adminBaseP.add(employeeInfoP, "e");
        adminBaseP.add(customerP, "c");
        adminBaseP.add(settingsP, "s");
        adminBaseP.add(dictP, "d");
        adminBaseP.add(report1P,"report1");
        employeeBaseP.setLayout(c);
        employeeBaseP.add(custEP, "cemp");
        employeeBaseP.add(dictEP, "demp");

        TableFormatting();

        reportsT = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    public void TableFormatting() {

        //======================================Delete Icon===============================
        settingsT.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        empInfoT.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        dictT.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        customerT.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());

        //=====================Integer Alignment=====================================
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        empInfoT.setDefaultRenderer(Integer.class, centerRenderer);
        settingsT.setDefaultRenderer(Integer.class, centerRenderer);
        dictT.setDefaultRenderer(Integer.class, centerRenderer);
        customerT.setDefaultRenderer(Integer.class, centerRenderer);
        report1T.setDefaultRenderer(Integer.class, centerRenderer);
        custET.setDefaultRenderer(Integer.class, centerRenderer);
        dictET.setDefaultRenderer(Integer.class, centerRenderer);

    }

    public void searchTable(JTable table, JTextField textBox) {
        TableModel model = table.getModel();
        final TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(model);
        table.setRowSorter(sorter1);

        textBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(textBox.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(textBox.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(textBox.getText());
            }

            public void search(String s) {
                if (s.length() == 0) {
                    sorter1.setRowFilter(null);
                } else {
                    sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + s));
                }
            }
        });

    }

    public void ImageChange() {
        Timer timer = new Timer(3000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (shown) {
                    imageL.setIcon(new ImageIcon(getClass().getResource("/icons/1.jpg")));
                    shown = false;
                } else {
                    imageL.setIcon(new ImageIcon(getClass().getResource("/icons/2.jpg")));
                    shown = true;
                }
            }
        });

        timer.start();
    }

    public void getSettings() {
        gd.getDepartment(settingsT);
        gd.getCallType(0, settingsT);
        gd.getCallCategory(0, settingsT);
        gd.getError(0, settingsT);
    }
    
    public void addCust(){
         AddCustomer dialog = new AddCustomer(this, shown);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (dialog.added) {
            String query = "insert into customer values(" + dialog.customerIdT.getText() + ",'" + dialog.customerFN.getText() + "','"
                    + dialog.customerLNT.getText() + "','" + dialog.customerEmailT.getText() + "'," + dialog.customerMobT.getText()
                    + ",'" + dialog.serviceC.getSelectedItem() + "','Yes','" + dialog.dateOfPurchaseD.getDateStringOrEmptyString() + "')";
            // Connection connection=gd.getConnection();

            try {
                Statement st = connection.createStatement();
                st.execute("alter session set NLS_DATE_FORMAT= \"YYYY-MM-DD\"");
                st.execute(query);
                st.execute("commit");
                JOptionPane.showMessageDialog(this, "Customer Added Successfully", "", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            gd.getCustomer(customerT,"manager");
            //searchTable(customerT, customerSearchTB);
        }
    }

    private void ExpandReportsP() {
        if (!reportsT.isRunning()) {

            h = reportsP.getHeight();
            sy = settingsL.getY();
            dy = editDictL.getY();
            cy = customerDetailsL.getY();
            show_reports_p = true;
            reportsP.setVisible(true);

            reportsT = new Timer(10, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    h = h + 10;
                    sy = sy + 10;
                    dy = dy + 10;
                    cy = cy + 10;
                    reportsP.setBounds(0, reportsP.getY(), reportsP.getWidth(), h);
                    settingsL.setBounds(0, sy, settingsL.getWidth(), settingsL.getHeight());
                    editDictL.setBounds(0, dy, editDictL.getWidth(), editDictL.getHeight());
                    customerDetailsL.setBounds(0, cy, customerDetailsL.getWidth(), customerDetailsL.getHeight());

                    if (h >= 120) {
                        reportsT.stop();
                    }
                }
            });

            reportsT.start();
        }
    }

    private void CollapseReportsP() {
        if (!reportsT.isRunning()) {

            h = reportsP.getHeight();
            sy = settingsL.getY();
            dy = editDictL.getY();
            cy = customerDetailsL.getY();
            show_reports_p = false;

            reportsT = new Timer(10, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    h = h - 10;
                    sy = sy - 10;
                    dy = dy - 10;
                    cy = cy - 10;
                    reportsP.setBounds(0, reportsP.getY(), reportsP.getWidth(), h);
                    settingsL.setBounds(0, sy, settingsL.getWidth(), settingsL.getHeight());
                    editDictL.setBounds(0, dy, editDictL.getWidth(), editDictL.getHeight());
                    customerDetailsL.setBounds(0, cy, customerDetailsL.getWidth(), customerDetailsL.getHeight());
                    if (h <= 1) {
                        reportsP.setVisible(false);
                        //settingsL.setBounds(0, temp,settingsL.getWidth(), settingsL.getHeight());
                        reportsT.stop();
                    }
                }
            });

            reportsT.start();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jPanel1 = new javax.swing.JPanel();
        menuBar = new javax.swing.JPanel();
        homeB = new javax.swing.JButton();
        loginB = new javax.swing.JButton();
        employeeInfoB = new javax.swing.JButton();
        nameL = new javax.swing.JLabel();
        loggedL = new javax.swing.JLabel();
        accountL = new javax.swing.JLabel();
        aboutUsB1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        accountP = new javax.swing.JPanel();
        myAccL = new javax.swing.JLabel();
        logoutL = new javax.swing.JLabel();
        baseP = new javax.swing.JPanel();
        homeP = new javax.swing.JPanel();
        imageL = new javax.swing.JLabel();
        adminP = new javax.swing.JPanel();
        adminBaseP = new javax.swing.JPanel();
        employeeInfoP = new javax.swing.JPanel();
        addEmpB = new javax.swing.JButton();
        empInfoSearchTB = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        empInfoT = new javax.swing.JTable();
        headingL4 = new javax.swing.JLabel();
        updateEmpInfoB = new javax.swing.JButton();
        searchL = new javax.swing.JLabel();
        refreshB3 = new javax.swing.JButton();
        dictP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dictT = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        wordT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        wordTypeCB = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        scoreT = new javax.swing.JTextField();
        wordSubmitB = new javax.swing.JButton();
        msgL = new javax.swing.JLabel();
        wordResetB = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        wordIdT = new javax.swing.JTextField();
        headingL3 = new javax.swing.JLabel();
        updateDictB = new javax.swing.JButton();
        searchL2 = new javax.swing.JLabel();
        dictSearchTB = new javax.swing.JTextField();
        refreshB2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        settingsP = new javax.swing.JPanel();
        addTypeB = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        settingsCB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        settingsT = new javax.swing.JTable();
        updateSettB = new javax.swing.JButton();
        headingL2 = new javax.swing.JLabel();
        searchL3 = new javax.swing.JLabel();
        settingsSearchTB = new javax.swing.JTextField();
        refreshB1 = new javax.swing.JButton();
        customerP = new javax.swing.JPanel();
        addCustomerB = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        customerT = new javax.swing.JTable();
        headingL = new javax.swing.JLabel();
        customerSearchTB = new javax.swing.JTextField();
        searchL4 = new javax.swing.JLabel();
        report1P = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        report1T = new javax.swing.JTable();
        headingL5 = new javax.swing.JLabel();
        searchL6 = new javax.swing.JLabel();
        report1SearchTB = new javax.swing.JTextField();
        refreshB = new javax.swing.JButton();
        refreshL = new javax.swing.JLabel();
        menu2P = new javax.swing.JPanel();
        settingsL = new javax.swing.JLabel();
        reportsL = new javax.swing.JLabel();
        reportsP = new javax.swing.JPanel();
        outgoingRL = new javax.swing.JLabel();
        incomingRL = new javax.swing.JLabel();
        individualRL = new javax.swing.JLabel();
        overallRL = new javax.swing.JLabel();
        editDictL = new javax.swing.JLabel();
        customerDetailsL = new javax.swing.JLabel();
        employeeP = new javax.swing.JPanel();
        menuEP = new javax.swing.JPanel();
        custEL = new javax.swing.JLabel();
        reportsEL = new javax.swing.JLabel();
        dictEL = new javax.swing.JLabel();
        employeeBaseP = new javax.swing.JPanel();
        dictEP = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        dictET = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        empDictSearchTB = new javax.swing.JTextField();
        searchL5 = new javax.swing.JLabel();
        custEP = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        custET = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        addCustomerB1 = new javax.swing.JButton();
        searchL7 = new javax.swing.JLabel();
        customerSearchTB1 = new javax.swing.JTextField();
        aboutUsP = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setMaximumSize(new java.awt.Dimension(3000, 3000));
        jPanel1.setMinimumSize(new java.awt.Dimension(1280, 800));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });
        jPanel1.setLayout(null);

        menuBar.setBackground(new java.awt.Color(102, 102, 102));
        menuBar.setLayout(null);

        homeB.setBackground(new java.awt.Color(102, 102, 255));
        homeB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        homeB.setForeground(new java.awt.Color(255, 255, 255));
        homeB.setText("Home");
        homeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBActionPerformed(evt);
            }
        });
        menuBar.add(homeB);
        homeB.setBounds(0, 0, 110, 40);

        loginB.setBackground(new java.awt.Color(102, 102, 255));
        loginB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        loginB.setForeground(new java.awt.Color(255, 255, 255));
        loginB.setText("Login");
        loginB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBActionPerformed(evt);
            }
        });
        menuBar.add(loginB);
        loginB.setBounds(240, 0, 130, 40);

        employeeInfoB.setBackground(new java.awt.Color(102, 102, 255));
        employeeInfoB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        employeeInfoB.setForeground(new java.awt.Color(255, 255, 255));
        employeeInfoB.setText("Employee Info");
        employeeInfoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeInfoBActionPerformed(evt);
            }
        });
        menuBar.add(employeeInfoB);
        employeeInfoB.setBounds(240, 0, 130, 40);

        nameL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        nameL.setForeground(new java.awt.Color(255, 255, 255));
        menuBar.add(nameL);
        nameL.setBounds(1160, 0, 110, 40);

        loggedL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        loggedL.setForeground(new java.awt.Color(255, 255, 255));
        loggedL.setText("Logged In As:");
        menuBar.add(loggedL);
        loggedL.setBounds(1060, 0, 90, 40);

        accountL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/account.jpg"))); // NOI18N
        accountL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accountLMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                accountLMousePressed(evt);
            }
        });
        menuBar.add(accountL);
        accountL.setBounds(1320, 0, 40, 40);

        aboutUsB1.setBackground(new java.awt.Color(102, 102, 255));
        aboutUsB1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        aboutUsB1.setForeground(new java.awt.Color(255, 255, 255));
        aboutUsB1.setText("About Us");
        aboutUsB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutUsB1ActionPerformed(evt);
            }
        });
        menuBar.add(aboutUsB1);
        aboutUsB1.setBounds(120, 0, 110, 40);

        jPanel1.add(menuBar);
        menuBar.setBounds(0, 90, 1370, 40);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Call Center Performance Analysis");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(410, 20, 560, 50);

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo2.jpg"))); // NOI18N
        jPanel1.add(icon);
        icon.setBounds(0, 0, 100, 90);

        accountP.setBackground(new java.awt.Color(204, 204, 204));
        accountP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        accountP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accountPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accountPMouseExited(evt);
            }
        });
        accountP.setLayout(null);

        myAccL.setBackground(new java.awt.Color(204, 204, 204));
        myAccL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        myAccL.setText("    My Account");
        myAccL.setOpaque(true);
        myAccL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                myAccLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                myAccLMouseExited(evt);
            }
        });
        accountP.add(myAccL);
        myAccL.setBounds(0, 0, 130, 30);

        logoutL.setBackground(new java.awt.Color(204, 204, 204));
        logoutL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        logoutL.setText("    Logout");
        logoutL.setOpaque(true);
        logoutL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutLMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutLMousePressed(evt);
            }
        });
        accountP.add(logoutL);
        logoutL.setBounds(0, 30, 130, 30);

        jPanel1.add(accountP);
        accountP.setBounds(1250, 130, 120, 60);

        baseP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                basePMouseEntered(evt);
            }
        });
        baseP.setLayout(null);

        homeP.setBackground(new java.awt.Color(255, 255, 255));
        homeP.setLayout(null);

        imageL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/1.jpg"))); // NOI18N
        homeP.add(imageL);
        imageL.setBounds(0, 0, 1370, 320);

        baseP.add(homeP);
        homeP.setBounds(0, 0, 1360, 580);

        adminP.setBackground(new java.awt.Color(255, 255, 255));
        adminP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        adminP.setMaximumSize(new java.awt.Dimension(3000, 3000));
        adminP.setMinimumSize(new java.awt.Dimension(1280, 800));
        adminP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminPMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                adminPMousePressed(evt);
            }
        });
        adminP.setLayout(null);

        adminBaseP.setBackground(new java.awt.Color(204, 204, 204));
        adminBaseP.setLayout(null);

        employeeInfoP.setBackground(new java.awt.Color(255, 255, 255));
        employeeInfoP.setLayout(null);

        addEmpB.setText("Add Employee");
        addEmpB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmpBActionPerformed(evt);
            }
        });
        employeeInfoP.add(addEmpB);
        addEmpB.setBounds(60, 80, 120, 30);

        empInfoSearchTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empInfoSearchTBActionPerformed(evt);
            }
        });
        employeeInfoP.add(empInfoSearchTB);
        empInfoSearchTB.setBounds(860, 80, 200, 30);

        empInfoT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "UserID", "First Name", "Last Name", "DOB", "CompanyID", "Address", "City", "State", "Country", "Pin Code", "Email", "CompanyMailID", "Mobile", "DeskID", "DepartmentID", "Date of Join"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, false, true, true, true, true, true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        empInfoT.setGridColor(new java.awt.Color(0, 0, 0));
        empInfoT.setRowHeight(25);
        empInfoT.getTableHeader().setReorderingAllowed(false);
        empInfoT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empInfoTMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(empInfoT);
        if (empInfoT.getColumnModel().getColumnCount() > 0) {
            empInfoT.getColumnModel().getColumn(0).setMinWidth(38);
            empInfoT.getColumnModel().getColumn(0).setPreferredWidth(38);
            empInfoT.getColumnModel().getColumn(0).setMaxWidth(38);
            empInfoT.getColumnModel().getColumn(1).setResizable(false);
            empInfoT.getColumnModel().getColumn(1).setPreferredWidth(80);
            empInfoT.getColumnModel().getColumn(2).setResizable(false);
            empInfoT.getColumnModel().getColumn(2).setPreferredWidth(150);
            empInfoT.getColumnModel().getColumn(3).setResizable(false);
            empInfoT.getColumnModel().getColumn(3).setPreferredWidth(150);
            empInfoT.getColumnModel().getColumn(4).setPreferredWidth(120);
            empInfoT.getColumnModel().getColumn(5).setPreferredWidth(100);
            empInfoT.getColumnModel().getColumn(6).setResizable(false);
            empInfoT.getColumnModel().getColumn(6).setPreferredWidth(250);
            empInfoT.getColumnModel().getColumn(7).setPreferredWidth(120);
            empInfoT.getColumnModel().getColumn(8).setPreferredWidth(120);
            empInfoT.getColumnModel().getColumn(9).setPreferredWidth(120);
            empInfoT.getColumnModel().getColumn(11).setPreferredWidth(250);
            empInfoT.getColumnModel().getColumn(12).setPreferredWidth(250);
            empInfoT.getColumnModel().getColumn(13).setResizable(false);
            empInfoT.getColumnModel().getColumn(13).setPreferredWidth(150);
            empInfoT.getColumnModel().getColumn(14).setPreferredWidth(100);
            empInfoT.getColumnModel().getColumn(15).setPreferredWidth(100);
        }

        employeeInfoP.add(jScrollPane3);
        jScrollPane3.setBounds(60, 130, 1020, 390);

        headingL4.setBackground(new java.awt.Color(204, 204, 204));
        headingL4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        headingL4.setForeground(new java.awt.Color(0, 51, 255));
        headingL4.setText("                Employee Details");
        headingL4.setOpaque(true);
        employeeInfoP.add(headingL4);
        headingL4.setBounds(0, 0, 1200, 40);

        updateEmpInfoB.setText("Update");
        updateEmpInfoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateEmpInfoBActionPerformed(evt);
            }
        });
        employeeInfoP.add(updateEmpInfoB);
        updateEmpInfoB.setBounds(200, 80, 80, 30);

        searchL.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL.setText("Search");
        employeeInfoP.add(searchL);
        searchL.setBounds(800, 80, 50, 30);

        refreshB3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        refreshB3.setToolTipText("Refresh Table");
        refreshB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshB3ActionPerformed(evt);
            }
        });
        employeeInfoP.add(refreshB3);
        refreshB3.setBounds(300, 80, 40, 30);

        adminBaseP.add(employeeInfoP);
        employeeInfoP.setBounds(0, 0, 1200, 580);

        dictP.setBackground(new java.awt.Color(255, 255, 255));
        dictP.setLayout(null);

        dictT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        dictT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "ID", "Word", "Category", "Score", "Date Added", "Date Modified", "Modified By"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dictT.setRowHeight(25);
        dictT.getTableHeader().setReorderingAllowed(false);
        dictT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dictTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dictT);
        if (dictT.getColumnModel().getColumnCount() > 0) {
            dictT.getColumnModel().getColumn(0).setMinWidth(38);
            dictT.getColumnModel().getColumn(0).setPreferredWidth(38);
            dictT.getColumnModel().getColumn(0).setMaxWidth(38);
            dictT.getColumnModel().getColumn(1).setResizable(false);
            dictT.getColumnModel().getColumn(2).setResizable(false);
            dictT.getColumnModel().getColumn(3).setResizable(false);
            dictT.getColumnModel().getColumn(4).setResizable(false);
            dictT.getColumnModel().getColumn(5).setResizable(false);
            dictT.getColumnModel().getColumn(6).setResizable(false);
            dictT.getColumnModel().getColumn(7).setResizable(false);
        }

        dictP.add(jScrollPane1);
        jScrollPane1.setBounds(50, 120, 690, 220);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Word ID");
        dictP.add(jLabel5);
        jLabel5.setBounds(70, 410, 60, 30);

        wordT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                wordTFocusGained(evt);
            }
        });
        wordT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordTActionPerformed(evt);
            }
        });
        dictP.add(wordT);
        wordT.setBounds(140, 460, 140, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Select Type ");
        dictP.add(jLabel2);
        jLabel2.setBounds(310, 410, 80, 30);

        wordTypeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Positive", "Negative" }));
        dictP.add(wordTypeCB);
        wordTypeCB.setBounds(390, 410, 140, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Score");
        dictP.add(jLabel3);
        jLabel3.setBounds(310, 460, 50, 30);

        scoreT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                scoreTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                scoreTFocusLost(evt);
            }
        });
        scoreT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreTActionPerformed(evt);
            }
        });
        scoreT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                scoreTKeyPressed(evt);
            }
        });
        dictP.add(scoreT);
        scoreT.setBounds(390, 460, 140, 30);

        wordSubmitB.setText("Submit");
        wordSubmitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordSubmitBActionPerformed(evt);
            }
        });
        dictP.add(wordSubmitB);
        wordSubmitB.setBounds(210, 510, 100, 30);

        msgL.setForeground(new java.awt.Color(255, 0, 0));
        msgL.setText("Please enter all the feilds");
        dictP.add(msgL);
        msgL.setBounds(250, 490, 150, 14);

        wordResetB.setText("Reset");
        wordResetB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordResetBActionPerformed(evt);
            }
        });
        dictP.add(wordResetB);
        wordResetB.setBounds(330, 510, 100, 30);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("Word");
        dictP.add(jLabel6);
        jLabel6.setBounds(70, 460, 80, 30);

        wordIdT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                wordIdTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                wordIdTFocusLost(evt);
            }
        });
        wordIdT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordIdTActionPerformed(evt);
            }
        });
        wordIdT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wordIdTKeyPressed(evt);
            }
        });
        dictP.add(wordIdT);
        wordIdT.setBounds(140, 410, 140, 30);

        headingL3.setBackground(new java.awt.Color(204, 204, 204));
        headingL3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        headingL3.setForeground(new java.awt.Color(0, 51, 255));
        headingL3.setText("                Word Dictionary");
        headingL3.setOpaque(true);
        dictP.add(headingL3);
        headingL3.setBounds(0, 0, 1200, 40);

        updateDictB.setText("Update");
        updateDictB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDictBActionPerformed(evt);
            }
        });
        dictP.add(updateDictB);
        updateDictB.setBounds(50, 70, 80, 30);

        searchL2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL2.setText("Search");
        dictP.add(searchL2);
        searchL2.setBounds(480, 70, 50, 30);

        dictSearchTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dictSearchTBActionPerformed(evt);
            }
        });
        dictP.add(dictSearchTB);
        dictSearchTB.setBounds(540, 70, 200, 30);

        refreshB2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        refreshB2.setToolTipText("Refresh Table");
        refreshB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshB2ActionPerformed(evt);
            }
        });
        dictP.add(refreshB2);
        refreshB2.setBounds(150, 70, 40, 30);
        dictP.add(jSeparator1);
        jSeparator1.setBounds(0, 360, 1200, 20);

        jLabel7.setBackground(new java.awt.Color(0, 51, 255));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jLabel7.setText("Add Word");
        dictP.add(jLabel7);
        jLabel7.setBounds(70, 370, 110, 30);

        adminBaseP.add(dictP);
        dictP.setBounds(0, 0, 1200, 580);

        settingsP.setBackground(new java.awt.Color(255, 255, 255));
        settingsP.setLayout(null);

        addTypeB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addTypeB.setText("Add Type");
        addTypeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTypeBActionPerformed(evt);
            }
        });
        settingsP.add(addTypeB);
        addTypeB.setBounds(60, 340, 100, 30);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Filter");
        settingsP.add(jLabel4);
        jLabel4.setBounds(70, 80, 50, 30);

        settingsCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Department", "Call Type", "Call Category", "Error" }));
        settingsCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsCBActionPerformed(evt);
            }
        });
        settingsP.add(settingsCB);
        settingsCB.setBounds(130, 80, 120, 30);

        settingsT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        settingsT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "Ctegory", "Type", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        settingsT.setRowHeight(25);
        settingsT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(settingsT);
        if (settingsT.getColumnModel().getColumnCount() > 0) {
            settingsT.getColumnModel().getColumn(0).setMinWidth(38);
            settingsT.getColumnModel().getColumn(0).setPreferredWidth(38);
            settingsT.getColumnModel().getColumn(0).setMaxWidth(38);
            settingsT.getColumnModel().getColumn(1).setResizable(false);
            settingsT.getColumnModel().getColumn(2).setResizable(false);
            settingsT.getColumnModel().getColumn(3).setResizable(false);
        }

        settingsP.add(jScrollPane2);
        jScrollPane2.setBounds(60, 140, 480, 180);

        updateSettB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        updateSettB.setText("Update");
        updateSettB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSettBActionPerformed(evt);
            }
        });
        settingsP.add(updateSettB);
        updateSettB.setBounds(180, 340, 100, 30);

        headingL2.setBackground(new java.awt.Color(204, 204, 204));
        headingL2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        headingL2.setForeground(new java.awt.Color(0, 51, 255));
        headingL2.setText("                Settings");
        headingL2.setOpaque(true);
        settingsP.add(headingL2);
        headingL2.setBounds(0, 0, 1200, 40);

        searchL3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL3.setText("Search");
        settingsP.add(searchL3);
        searchL3.setBounds(280, 80, 50, 30);

        settingsSearchTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsSearchTBActionPerformed(evt);
            }
        });
        settingsP.add(settingsSearchTB);
        settingsSearchTB.setBounds(340, 80, 130, 30);

        refreshB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        refreshB1.setToolTipText("Refresh Table");
        refreshB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshB1ActionPerformed(evt);
            }
        });
        settingsP.add(refreshB1);
        refreshB1.setBounds(300, 340, 40, 30);

        adminBaseP.add(settingsP);
        settingsP.setBounds(0, 0, 1200, 580);

        customerP.setBackground(new java.awt.Color(255, 255, 255));
        customerP.setLayout(null);

        addCustomerB.setText("Add Customer");
        addCustomerB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerBActionPerformed(evt);
            }
        });
        customerP.add(addCustomerB);
        addCustomerB.setBounds(60, 80, 130, 30);

        customerT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Customer ID", "First Name", "Last Name", "E-mail", "Mobile", "Product/Service", "Warranty Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerT.setGridColor(new java.awt.Color(0, 0, 0));
        customerT.setRowHeight(25);
        customerT.getTableHeader().setReorderingAllowed(false);
        customerT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(customerT);
        if (customerT.getColumnModel().getColumnCount() > 0) {
            customerT.getColumnModel().getColumn(0).setMinWidth(38);
            customerT.getColumnModel().getColumn(0).setPreferredWidth(38);
            customerT.getColumnModel().getColumn(0).setMaxWidth(38);
            customerT.getColumnModel().getColumn(1).setResizable(false);
            customerT.getColumnModel().getColumn(1).setPreferredWidth(100);
            customerT.getColumnModel().getColumn(2).setResizable(false);
            customerT.getColumnModel().getColumn(2).setPreferredWidth(150);
            customerT.getColumnModel().getColumn(3).setResizable(false);
            customerT.getColumnModel().getColumn(3).setPreferredWidth(150);
            customerT.getColumnModel().getColumn(4).setResizable(false);
            customerT.getColumnModel().getColumn(5).setResizable(false);
            customerT.getColumnModel().getColumn(6).setResizable(false);
            customerT.getColumnModel().getColumn(7).setResizable(false);
        }

        customerP.add(jScrollPane5);
        jScrollPane5.setBounds(60, 130, 1020, 380);

        headingL.setBackground(new java.awt.Color(204, 204, 204));
        headingL.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        headingL.setForeground(new java.awt.Color(0, 51, 255));
        headingL.setText("                Customer Details");
        headingL.setOpaque(true);
        customerP.add(headingL);
        headingL.setBounds(0, 0, 1200, 40);

        customerSearchTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerSearchTBActionPerformed(evt);
            }
        });
        customerP.add(customerSearchTB);
        customerSearchTB.setBounds(880, 80, 200, 30);

        searchL4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL4.setText("Search");
        customerP.add(searchL4);
        searchL4.setBounds(830, 80, 50, 30);

        adminBaseP.add(customerP);
        customerP.setBounds(0, 0, 1200, 580);

        report1P.setBackground(new java.awt.Color(255, 255, 255));
        report1P.setLayout(null);

        report1T.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        report1T.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Call ID", "Agent Name", "Agent ID", "Positive Words Used", "Positive Score", "Negative Words Used", "Negative Score", "Total Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        report1T.setRowHeight(30);
        report1T.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                report1TMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(report1T);
        if (report1T.getColumnModel().getColumnCount() > 0) {
            report1T.getColumnModel().getColumn(0).setResizable(false);
            report1T.getColumnModel().getColumn(1).setResizable(false);
            report1T.getColumnModel().getColumn(2).setResizable(false);
            report1T.getColumnModel().getColumn(4).setResizable(false);
            report1T.getColumnModel().getColumn(6).setResizable(false);
            report1T.getColumnModel().getColumn(7).setResizable(false);
        }

        report1P.add(jScrollPane7);
        jScrollPane7.setBounds(60, 120, 1010, 400);

        headingL5.setBackground(new java.awt.Color(204, 204, 204));
        headingL5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        headingL5.setForeground(new java.awt.Color(0, 51, 255));
        headingL5.setText("                Overall Report");
        headingL5.setOpaque(true);
        report1P.add(headingL5);
        headingL5.setBounds(0, 0, 1200, 40);

        searchL6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL6.setText("Search");
        report1P.add(searchL6);
        searchL6.setBounds(810, 70, 50, 30);

        report1SearchTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report1SearchTBActionPerformed(evt);
            }
        });
        report1P.add(report1SearchTB);
        report1SearchTB.setBounds(870, 70, 200, 30);

        refreshB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        refreshB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBActionPerformed(evt);
            }
        });
        report1P.add(refreshB);
        refreshB.setBounds(60, 70, 40, 30);

        refreshL.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        refreshL.setText("Refresh");
        report1P.add(refreshL);
        refreshL.setBounds(110, 70, 50, 30);

        adminBaseP.add(report1P);
        report1P.setBounds(0, 0, 1200, 580);

        adminP.add(adminBaseP);
        adminBaseP.setBounds(170, 0, 1200, 580);

        menu2P.setBackground(new java.awt.Color(102, 102, 102));
        menu2P.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu2PMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu2PMousePressed(evt);
            }
        });
        menu2P.setLayout(null);

        settingsL.setBackground(new java.awt.Color(0, 102, 255));
        settingsL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        settingsL.setForeground(new java.awt.Color(255, 255, 255));
        settingsL.setText("   Settings");
        settingsL.setFocusCycleRoot(true);
        settingsL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        settingsL.setOpaque(true);
        settingsL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsLMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                settingsLMousePressed(evt);
            }
        });
        menu2P.add(settingsL);
        settingsL.setBounds(0, 160, 170, 30);

        reportsL.setBackground(new java.awt.Color(0, 102, 255));
        reportsL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        reportsL.setForeground(new java.awt.Color(255, 255, 255));
        reportsL.setText("   Reports");
        reportsL.setFocusCycleRoot(true);
        reportsL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reportsL.setOpaque(true);
        reportsL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportsLMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reportsLMousePressed(evt);
            }
        });
        menu2P.add(reportsL);
        reportsL.setBounds(0, 110, 170, 30);

        reportsP.setBackground(new java.awt.Color(102, 102, 102));
        reportsP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        reportsP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportsPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportsPMouseExited(evt);
            }
        });
        reportsP.setLayout(null);

        outgoingRL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        outgoingRL.setForeground(new java.awt.Color(255, 255, 255));
        outgoingRL.setText("   Outgoing Calls");
        outgoingRL.setFocusCycleRoot(true);
        outgoingRL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        outgoingRL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outgoingRLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                outgoingRLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                outgoingRLMouseExited(evt);
            }
        });
        reportsP.add(outgoingRL);
        outgoingRL.setBounds(0, 90, 170, 30);

        incomingRL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        incomingRL.setForeground(new java.awt.Color(255, 255, 255));
        incomingRL.setText("   Incoming Calls");
        incomingRL.setFocusCycleRoot(true);
        incomingRL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        incomingRL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                incomingRLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                incomingRLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                incomingRLMouseExited(evt);
            }
        });
        reportsP.add(incomingRL);
        incomingRL.setBounds(0, 60, 170, 30);

        individualRL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        individualRL.setForeground(new java.awt.Color(255, 255, 255));
        individualRL.setText("   Individual");
        individualRL.setFocusCycleRoot(true);
        individualRL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        individualRL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                individualRLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                individualRLMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                individualRLMousePressed(evt);
            }
        });
        reportsP.add(individualRL);
        individualRL.setBounds(0, 30, 170, 30);

        overallRL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        overallRL.setForeground(new java.awt.Color(255, 255, 255));
        overallRL.setText("   Overall Report");
        overallRL.setFocusCycleRoot(true);
        overallRL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        overallRL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                overallRLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                overallRLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                overallRLMouseExited(evt);
            }
        });
        reportsP.add(overallRL);
        overallRL.setBounds(0, 0, 170, 30);

        menu2P.add(reportsP);
        reportsP.setBounds(0, 140, 170, 1);

        editDictL.setBackground(new java.awt.Color(0, 102, 255));
        editDictL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        editDictL.setForeground(new java.awt.Color(255, 255, 255));
        editDictL.setText("   Edit Dictionary");
        editDictL.setFocusCycleRoot(true);
        editDictL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editDictL.setOpaque(true);
        editDictL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editDictLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editDictLMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editDictLMousePressed(evt);
            }
        });
        menu2P.add(editDictL);
        editDictL.setBounds(0, 210, 170, 30);

        customerDetailsL.setBackground(new java.awt.Color(0, 102, 255));
        customerDetailsL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        customerDetailsL.setForeground(new java.awt.Color(255, 255, 255));
        customerDetailsL.setText("   Customer Details");
        customerDetailsL.setFocusCycleRoot(true);
        customerDetailsL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        customerDetailsL.setOpaque(true);
        customerDetailsL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerDetailsLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customerDetailsLMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                customerDetailsLMousePressed(evt);
            }
        });
        menu2P.add(customerDetailsL);
        customerDetailsL.setBounds(0, 260, 170, 30);

        adminP.add(menu2P);
        menu2P.setBounds(0, 0, 170, 580);

        baseP.add(adminP);
        adminP.setBounds(0, 0, 1370, 580);

        employeeP.setBackground(new java.awt.Color(255, 255, 255));
        employeeP.setLayout(null);

        menuEP.setBackground(new java.awt.Color(102, 102, 102));
        menuEP.setLayout(null);

        custEL.setBackground(new java.awt.Color(0, 102, 255));
        custEL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        custEL.setForeground(new java.awt.Color(255, 255, 255));
        custEL.setText("   Customer Details");
        custEL.setOpaque(true);
        custEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                custELMouseClicked(evt);
            }
        });
        menuEP.add(custEL);
        custEL.setBounds(0, 190, 160, 30);

        reportsEL.setBackground(new java.awt.Color(0, 102, 255));
        reportsEL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reportsEL.setForeground(new java.awt.Color(255, 255, 255));
        reportsEL.setText("   Reports");
        reportsEL.setOpaque(true);
        menuEP.add(reportsEL);
        reportsEL.setBounds(0, 90, 160, 30);

        dictEL.setBackground(new java.awt.Color(0, 102, 255));
        dictEL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        dictEL.setForeground(new java.awt.Color(255, 255, 255));
        dictEL.setText("   Dictionary");
        dictEL.setOpaque(true);
        dictEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dictELMouseClicked(evt);
            }
        });
        menuEP.add(dictEL);
        dictEL.setBounds(0, 140, 160, 30);

        employeeP.add(menuEP);
        menuEP.setBounds(0, 0, 160, 580);

        employeeBaseP.setLayout(null);

        dictEP.setBackground(new java.awt.Color(255, 255, 255));
        dictEP.setLayout(null);

        dictET.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        dictET.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Word", "Category", "Score", "Date Added", "Date Modified", "Modified By"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dictET.setRowHeight(25);
        jScrollPane4.setViewportView(dictET);

        dictEP.add(jScrollPane4);
        jScrollPane4.setBounds(50, 150, 1060, 390);

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 255));
        jLabel11.setText("                Word Dictionary");
        jLabel11.setOpaque(true);
        dictEP.add(jLabel11);
        jLabel11.setBounds(0, 0, 1200, 40);

        empDictSearchTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empDictSearchTBActionPerformed(evt);
            }
        });
        dictEP.add(empDictSearchTB);
        empDictSearchTB.setBounds(100, 100, 200, 30);

        searchL5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL5.setText("Search");
        dictEP.add(searchL5);
        searchL5.setBounds(50, 100, 50, 30);

        employeeBaseP.add(dictEP);
        dictEP.setBounds(0, 0, 1200, 580);

        custEP.setBackground(new java.awt.Color(255, 255, 255));
        custEP.setLayout(null);

        custET.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "First Name", "Last Name", "E-mail", "Mobile", "Product/Service", "Warranty Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        custET.setGridColor(new java.awt.Color(0, 0, 0));
        custET.setRowHeight(25);
        custET.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(custET);

        custEP.add(jScrollPane6);
        jScrollPane6.setBounds(50, 150, 1100, 440);

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 255));
        jLabel12.setText("                Customer Details");
        jLabel12.setOpaque(true);
        custEP.add(jLabel12);
        jLabel12.setBounds(0, 0, 1200, 40);

        addCustomerB1.setText("Add Customer");
        addCustomerB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerB1ActionPerformed(evt);
            }
        });
        custEP.add(addCustomerB1);
        addCustomerB1.setBounds(60, 80, 130, 30);

        searchL7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL7.setText("Search");
        custEP.add(searchL7);
        searchL7.setBounds(830, 80, 50, 30);

        customerSearchTB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerSearchTB1ActionPerformed(evt);
            }
        });
        custEP.add(customerSearchTB1);
        customerSearchTB1.setBounds(880, 80, 200, 30);

        employeeBaseP.add(custEP);
        custEP.setBounds(0, 0, 1200, 580);

        employeeP.add(employeeBaseP);
        employeeBaseP.setBounds(160, 0, 1200, 580);

        baseP.add(employeeP);
        employeeP.setBounds(0, 0, 1360, 580);

        aboutUsP.setBackground(new java.awt.Color(255, 255, 255));
        aboutUsP.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText(" About Us");
        aboutUsP.add(jLabel8);
        jLabel8.setBounds(0, 0, 1360, 50);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setLayout(null);

        jTextArea1.setBackground(new java.awt.Color(0, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("\t         Description\n");
        jScrollPane8.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane8);
        jScrollPane8.setBounds(610, 40, 460, 440);

        jTextArea2.setBackground(new java.awt.Color(0, 204, 204));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("\t             Project By\n\n - Shivanjali Gaikwad\n - Simran Gulati\n - Pooja Choudhary\n - Trupti Dere\n\n\t          Guide \n - Prof.Muzaffar Shabad");
        jScrollPane9.setViewportView(jTextArea2);

        jPanel2.add(jScrollPane9);
        jScrollPane9.setBounds(16, 40, 460, 440);

        aboutUsP.add(jPanel2);
        jPanel2.setBounds(0, 70, 1410, 620);

        baseP.add(aboutUsP);
        aboutUsP.setBounds(0, 0, 1360, 580);

        jPanel1.add(baseP);
        baseP.setBounds(0, 130, 1370, 580);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1370, 710);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employeeInfoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeInfoBActionPerformed
        c.show(baseP, "a");
        c.show(adminBaseP, "e");
    }//GEN-LAST:event_employeeInfoBActionPerformed

    private void loginBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBActionPerformed

        LoginDialog dialog = new LoginDialog(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        if (dialog.flag == 1) {

            loggedIn = true;
            homeB.setVisible(false);
            c.show(baseP, "a");
            loggedL.setVisible(true);
            nameL.setVisible(true);
            nameL.setText("Manager");
            loginB.setVisible(false);
            accountL.setVisible(true);
            employeeInfoB.setVisible(true);
            c.show(baseP, "a");
            c.show(adminBaseP, "e");
            gd.createConnection();
            connection = gd.getConnection();
            gd.getEmployeeInfo(empInfoT);
            gd.getDictionary(dictT,"manager");
            gd.getCustomer(customerT,"manager");
            gd.getReport1(report1T);
            getSettings();

            searchTable(dictT, dictSearchTB);
            searchTable(empInfoT, empInfoSearchTB);
            searchTable(settingsT, settingsSearchTB);
            searchTable(customerT, customerSearchTB);
            searchTable(report1T, report1SearchTB);

        } else if (dialog.flag == 2) {
            loggedIn = true;
            aboutUsB1.setVisible(false);
            homeB.setVisible(false);
            c.show(baseP, "emp");
            loggedL.setVisible(true);
            nameL.setVisible(true);
            nameL.setText("Employee");
            loginB.setVisible(false);
            accountL.setVisible(true);
            c.show(employeeBaseP, "emp");
            gd.createConnection();
            connection = gd.getConnection();
            gd.getDictionary(dictET,"employee");
            gd.getCustomer(custET,"employee");
            searchTable(custET, customerSearchTB1);
            searchTable(dictET, empDictSearchTB);
        }

    }//GEN-LAST:event_loginBActionPerformed


    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        accountP.setVisible(false);
    }//GEN-LAST:event_jPanel1MouseEntered

    private void homeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBActionPerformed
        // TODO add your handling code here:
        c.show(baseP, "h");
    }//GEN-LAST:event_homeBActionPerformed

    private void logoutLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLMouseEntered
        logoutL.setBackground(new Color(0, 102, 204));
        logoutL.setForeground(Color.white);
    }//GEN-LAST:event_logoutLMouseEntered

    private void logoutLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLMouseExited
        logoutL.setBackground(new Color(204, 204, 204));
        logoutL.setForeground(Color.BLACK);
    }//GEN-LAST:event_logoutLMouseExited

    private void accountPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountPMouseEntered
        accountP.setVisible(true);
    }//GEN-LAST:event_accountPMouseEntered

    private void accountPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountPMouseExited
        accountP.setVisible(false);
    }//GEN-LAST:event_accountPMouseExited

    private void accountLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountLMouseEntered
        accountP.setVisible(true);
    }//GEN-LAST:event_accountLMouseEntered

    private void accountLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountLMousePressed
        accountP.setVisible(true);
    }//GEN-LAST:event_accountLMousePressed

    private void adminPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminPMouseEntered
        accountP.setVisible(false);
        //settingsP.setVisible(false);
    }//GEN-LAST:event_adminPMouseEntered

    private void basePMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_basePMouseEntered

    }//GEN-LAST:event_basePMouseEntered

    private void myAccLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myAccLMouseExited
        myAccL.setBackground(new Color(204, 204, 204));
        myAccL.setForeground(Color.BLACK);
    }//GEN-LAST:event_myAccLMouseExited

    private void myAccLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myAccLMouseEntered
        myAccL.setBackground(new Color(0, 102, 204));
        myAccL.setForeground(Color.white);
    }//GEN-LAST:event_myAccLMouseEntered

    private void logoutLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLMousePressed
        accountP.setVisible(false);
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            loggedIn = false;
            c.show(baseP, "h");
            loggedL.setVisible(false);
            nameL.setVisible(false);
            nameL.setText("");
            employeeInfoB.setVisible(false);
            loginB.setVisible(true);
            accountL.setVisible(false);
        }
    }//GEN-LAST:event_logoutLMousePressed

    private void settingsLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLMouseEntered
        //settingsP.setVisible(true);
        //setttingsL.setBackground(new Color(0,51,255));
    }//GEN-LAST:event_settingsLMouseEntered

    private void settingsLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLMousePressed
//        settingsP.setVisible(true);
//        int w=settingsP.getWidth();
//        h=settingsP.getHeight();
//        
//        int i=0;
//        while(h<220){
//            h=h+10;
//            settingsP.setBounds(0, 150, w, h);
//            try {
//                sleep(500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

        c.show(adminBaseP, "s");

    }//GEN-LAST:event_settingsLMousePressed

    private void menu2PMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2PMouseEntered
        //settingsP.setVisible(false);
    }//GEN-LAST:event_menu2PMouseEntered

    private void menu2PMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2PMousePressed
        if (show_reports_p) {
            CollapseReportsP();
        }
    }//GEN-LAST:event_menu2PMousePressed

    private void adminPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminPMousePressed
        if (show_reports_p) {
            CollapseReportsP();
        }
    }//GEN-LAST:event_adminPMousePressed

    private void reportsLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsLMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_reportsLMouseEntered

    private void reportsLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsLMousePressed
        if (show_reports_p) {
            CollapseReportsP();
        } else {
            ExpandReportsP();
        }
    }//GEN-LAST:event_reportsLMousePressed

    private void outgoingRLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outgoingRLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_outgoingRLMouseClicked

    private void outgoingRLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outgoingRLMouseEntered
        outgoingRL.setForeground(Color.cyan);
    }//GEN-LAST:event_outgoingRLMouseEntered

    private void outgoingRLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outgoingRLMouseExited
        outgoingRL.setForeground(Color.white);
    }//GEN-LAST:event_outgoingRLMouseExited

    private void incomingRLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomingRLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_incomingRLMouseClicked

    private void incomingRLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomingRLMouseEntered
        incomingRL.setForeground(Color.cyan);
    }//GEN-LAST:event_incomingRLMouseEntered

    private void incomingRLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomingRLMouseExited
        incomingRL.setForeground(Color.white);
    }//GEN-LAST:event_incomingRLMouseExited

    private void individualRLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_individualRLMouseEntered
        individualRL.setForeground(Color.cyan);
    }//GEN-LAST:event_individualRLMouseEntered

    private void individualRLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_individualRLMouseExited
        individualRL.setForeground(Color.white);
    }//GEN-LAST:event_individualRLMouseExited

    private void individualRLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_individualRLMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_individualRLMousePressed

    private void overallRLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_overallRLMouseClicked
        c.show(adminBaseP, "report1");
    }//GEN-LAST:event_overallRLMouseClicked

    private void overallRLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_overallRLMouseEntered
        overallRL.setForeground(Color.cyan);
    }//GEN-LAST:event_overallRLMouseEntered

    private void overallRLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_overallRLMouseExited
        overallRL.setForeground(Color.white);
    }//GEN-LAST:event_overallRLMouseExited

    private void reportsPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_reportsPMouseEntered

    private void reportsPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsPMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_reportsPMouseExited

    private void addEmpBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmpBActionPerformed
        AddEmployee dialog = new AddEmployee(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (dialog.added == true) {
            int eid = Integer.parseInt(dialog.userIDT.getText());
            int deptId = Integer.parseInt(dialog.deptIDT.getText());
            int deskId = Integer.parseInt(dialog.deskIDT.getText());
            long mobile = Long.parseLong(dialog.mobileT.getText());
            String city = (String) dialog.cityCB.getSelectedItem();
            String state = (String) dialog.stateCB.getSelectedItem();
            int pin = Integer.parseInt(dialog.pinCodeT.getText());
            String dateJoin = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String fn = "<html><b>" + dialog.firstNameT.getText() + "</b></html>";
            String ln = "<html><b>" + dialog.lastNameT.getText() + "</b></html>";
            //String d=dialog.dob.getDate();
//            java.util.Date myDate = new java.util.Date(dateJoin);
//            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
//            java.util.Date myDate2 = new java.util.Date(dialog.dob.getDateStringOrEmptyString());
//            java.sql.Date sqlDate2 = new java.sql.Date(myDate2.getTime());

            String query = "insert into employee values(" + eid + ",'" + dialog.firstNameT.getText() + "','" + dialog.lastNameT.getText() + "','"
                    + dialog.dob.getDateStringOrEmptyString() + "'," + dialog.cid + ",'" + dialog.addressT.getText() + "','" + city + "','"
                    + state + "','India'," + pin + ",'" + dialog.emailT.getText() + "','" + dialog.compMailT.getText() + "'," + mobile + "," + deskId + ","
                    + deptId + ",'" + dateJoin + "',''," + 10 + ")";
            //Connection connection=gd.getConnection();

            try {

                Statement st = connection.createStatement();
                st.execute("alter session set NLS_DATE_FORMAT= \"YYYY-MM-DD\"");
                st.execute(query);
                st.execute("commit");
                JOptionPane.showMessageDialog(this, "Employee " + dialog.firstNameT.getText() + " " + dialog.lastNameT.getText()
                        + " Added Successfully !\nYour Company ID: " + dialog.cid, "", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            } 

            gd.getEmployeeInfo(empInfoT);
        }
    }//GEN-LAST:event_addEmpBActionPerformed

    private void empInfoSearchTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empInfoSearchTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empInfoSearchTBActionPerformed

    private void editDictLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editDictLMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_editDictLMouseEntered

    private void editDictLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editDictLMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_editDictLMousePressed

    private void wordTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wordTActionPerformed

    private void scoreTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scoreTActionPerformed

    private void wordSubmitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordSubmitBActionPerformed
        if ("".equals(wordT.getText()) || "".equals(scoreT.getText()) || wordTypeCB.getSelectedIndex() == 0 || "".equals(wordIdT.getText())) {
            msgL.setVisible(true);
        } else {
            String dateJoin = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            String q = "insert into word_dictionary values(" + wordIdT.getText() + ",'" + wordT.getText() + "','" + wordTypeCB.getSelectedItem()
                    + "'," + scoreT.getText() + "," + 101 + ",'" + dateJoin + "','" + dateJoin + "','" + dateJoin + "')";

            //Connection connection=gd.getConnection();
            try {
                Statement st = connection.createStatement();
                st.execute("alter session set NLS_DATE_FORMAT= \"YYYY-MM-DD\"");
                st.execute(q);
                st.execute("commit");
                wordT.setText("");
                scoreT.setText("");
                wordIdT.setText("");
                wordTypeCB.setSelectedIndex(0);
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            gd.getDictionary(dictT,"manager");
            //searchTable(dictT, dictSearchTB);
        }
    }//GEN-LAST:event_wordSubmitBActionPerformed

    private void editDictLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editDictLMouseClicked
        c.show(adminBaseP, "d");
    }//GEN-LAST:event_editDictLMouseClicked

    private void scoreTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_scoreTKeyPressed
        char a = evt.getKeyChar();
        System.out.println(a + "  " + evt.getKeyCode());
        if ((a >= '0' && a <= '9') || evt.getKeyCode() == 8 || a == '+' || a == '-') {
            scoreT.setEditable(true);
        } else {
            scoreT.setEditable(false);
        }
    }//GEN-LAST:event_scoreTKeyPressed

    private void scoreTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_scoreTFocusLost
        scoreT.setEditable(true);
    }//GEN-LAST:event_scoreTFocusLost

    private void customerDetailsLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerDetailsLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_customerDetailsLMouseClicked

    private void customerDetailsLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerDetailsLMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_customerDetailsLMouseEntered

    private void customerDetailsLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerDetailsLMousePressed
        c.show(adminBaseP, "c");
    }//GEN-LAST:event_customerDetailsLMousePressed

    private void settingsCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsCBActionPerformed
        int k = settingsCB.getSelectedIndex();
        switch (k) {
            case 0:
                getSettings();
                break;
            case 1:
                gd.getDepartment(settingsT);
                break;
            case 2:
                gd.getCallType(1, settingsT);
                break;
            case 3:
                gd.getCallCategory(1, settingsT);
                break;
            case 4:
                gd.getError(1, settingsT);
                break;
        }
    }//GEN-LAST:event_settingsCBActionPerformed

    private void addTypeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTypeBActionPerformed
        AddSettings dialog = new AddSettings(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (dialog.added) {
            String query = " ";
            int k = dialog.settingsCB.getSelectedIndex();

            switch (k) {
                case 1:
                    query = "insert into department values(" + dialog.idT.getText() + ",'" + dialog.typeT.getText() + "')";
                    break;
                case 2:
                    query = "insert into call_type values(" + dialog.idT.getText() + ",'" + dialog.typeT.getText() + "')";
                    break;
                case 3:
                    query = "insert into category values(" + dialog.idT.getText() + ",'" + dialog.typeT.getText() + "')";
                    break;
                case 4:
                    query = "insert into error_type values(" + dialog.idT.getText() + ",'" + dialog.typeT.getText() + "')";
                    break;
            }

            // Connection connection=gd.getConnection();
            try {
                Statement st = connection.createStatement();
                st.execute(query);
                st.execute("commit");
                JOptionPane.showMessageDialog(this, "Successfully Added!", "", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            getSettings();
            settingsCB.setSelectedItem("All");
            //searchTable(settingsT, settingsSearchTB);
        }
    }//GEN-LAST:event_addTypeBActionPerformed

    private void wordResetBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordResetBActionPerformed
        wordT.setText("");
        scoreT.setText("");
        wordIdT.setText("");
        wordTypeCB.setSelectedIndex(0);
        msgL.setVisible(false);
    }//GEN-LAST:event_wordResetBActionPerformed

    private void wordIdTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wordIdTFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_wordIdTFocusLost

    private void wordIdTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordIdTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wordIdTActionPerformed

    private void wordIdTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wordIdTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_wordIdTKeyPressed

    private void wordIdTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wordIdTFocusGained
        msgL.setVisible(false);
    }//GEN-LAST:event_wordIdTFocusGained

    private void wordTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wordTFocusGained
        msgL.setVisible(false);
    }//GEN-LAST:event_wordTFocusGained

    private void scoreTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_scoreTFocusGained
        msgL.setVisible(false);
    }//GEN-LAST:event_scoreTFocusGained

    private void addCustomerBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerBActionPerformed
       addCust();
    }//GEN-LAST:event_addCustomerBActionPerformed

    private void settingsTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsTMouseClicked
        int column = settingsT.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / settingsT.getRowHeight();
        //System.out.println("Col :"+column + " row:"+row);

        if (column == 0) {
            Object type = settingsT.getValueAt(row, 2);
            Object category = settingsT.getValueAt(row, 1);
            Object id = settingsT.getValueAt(row, 3);
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete\n" + category + "  " + type, "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            String query = "";

            if (response == JOptionPane.YES_OPTION) {

                if (category == "Department") {
                    query = "delete from department where dept_id=" + id;
                } else if (category == "Call Type") {
                    query = "delete from call_type where type_id=" + id;
                } else if (category == "Call Category") {
                    query = "delete from category where category_id=" + id;
                } else if (category == "Error") {
                    query = "delete from error_type where error_id=" + id;
                }

                try {
                    Statement st = connection.createStatement();
                    st.execute(query);
                    st.execute("commit");
                    getSettings();
                    JOptionPane.showMessageDialog(this, "Entry Deleted Successfully", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                getSettings();
                //searchTable(settingsT, settingsSearchTB);
            }
        }

//        if (row < settingsT.getRowCount() && row >= 0 && column < settingsT.getColumnCount() && column >= 0) {
//          Object value = settingsT.getValueAt(row, 1);
//          System.out.println("Value :"+value.getClass().getName());
//          System.out.println("Value :"+value);
//          
//          if (value instanceof JButton) {
//            ((JButton)value).doClick();
//          }
//        }
    }//GEN-LAST:event_settingsTMouseClicked

    private void custELMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custELMouseClicked
        c.show(employeeBaseP, "cemp");
    }//GEN-LAST:event_custELMouseClicked

    private void dictELMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dictELMouseClicked
        c.show(employeeBaseP, "demp");
    }//GEN-LAST:event_dictELMouseClicked

    private void updateSettBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSettBActionPerformed
        int row = settingsT.getSelectedRow();
        if (row != -1) {
            //update <table name> set (<col1 = val1, col2 = val2,col3 = val3,?) where <expression identifying rows to change>;
            Object type = settingsT.getValueAt(row, 2);
            Object category = settingsT.getValueAt(row, 1);
            Object id = settingsT.getValueAt(row, 3);
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to update ?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            String query = "";

            if (response == JOptionPane.YES_OPTION) {

                if (category == "Department") {
                    query = "update department set deptname = '" + type + "' where dept_id=" + id;
                } else if (category == "Call Type") {
                    query = "update call_type set description = '" + type + "' where type_id=" + id;
                } else if (category == "Call Category") {
                    query = "update category set description = '" + type + "' where category_id=" + id;
                } else if (category == "Error") {
                    query = "update error_type set description = '" + type + "' where error_id=" + id;
                }

                try {
                    Statement st = connection.createStatement();
                    st.execute(query);
                    st.execute("commit");
                    //getSettings();
                    JOptionPane.showMessageDialog(this, "Update Successfull", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                getSettings();
                //searchTable(settingsT, settingsSearchTB);
            }

        }
    }//GEN-LAST:event_updateSettBActionPerformed

    private void empInfoTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empInfoTMouseClicked
        int column = empInfoT.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / empInfoT.getRowHeight();
        //System.out.println("Col :"+column + " row:"+row);

        if (column == 0) {
            Object id = empInfoT.getValueAt(row, 1);
            Object fname = empInfoT.getValueAt(row, 2);
            Object lname = empInfoT.getValueAt(row, 3);
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete\n" + fname + " " + lname, "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {

                String query = "delete from employee where employee_id=" + id;

                try {
                    Statement st = connection.createStatement();
                    st.execute(query);
                    st.execute("commit");
                    JOptionPane.showMessageDialog(this, "Employee Deleted Successfully", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                gd.getEmployeeInfo(empInfoT);
                //searchTable(empInfoT, empInfoSearchTB);
            }
        }
    }//GEN-LAST:event_empInfoTMouseClicked

    private void updateEmpInfoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateEmpInfoBActionPerformed
        int row = empInfoT.getSelectedRow();
        if (row != -1) {

            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to update ?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {

                Date date = (Date) empInfoT.getValueAt(row, 4);
                java.sql.Date sqlDate;
                sqlDate = new java.sql.Date(date.getTime());

                try {
                    PreparedStatement stmt = connection.prepareStatement(
                            "update employee set first_name=?, last_name=?, dob=?, address=?, city=?, state=?, country=?, pincode=?, emailid=?, "
                            + "company_mail=?, mobile=?, desk_id=?, dept_id=? where employee_id=?");

                    stmt.setString(1, (String) empInfoT.getValueAt(row, 2));
                    stmt.setString(2, (String) empInfoT.getValueAt(row, 3));
                    stmt.setDate(3, sqlDate);
                    stmt.setString(4, (String) empInfoT.getValueAt(row, 6));
                    stmt.setString(5, (String) empInfoT.getValueAt(row, 7));
                    stmt.setString(6, (String) empInfoT.getValueAt(row, 8));
                    stmt.setString(7, (String) empInfoT.getValueAt(row, 9));
                    stmt.setInt(8, (int) empInfoT.getValueAt(row, 10));
                    stmt.setString(9, (String) empInfoT.getValueAt(row, 11));
                    stmt.setString(10, (String) empInfoT.getValueAt(row, 12));
                    stmt.setLong(11, (long) empInfoT.getValueAt(row, 13));
                    stmt.setInt(12, (int) empInfoT.getValueAt(row, 14));
                    stmt.setInt(13, (int) empInfoT.getValueAt(row, 15));
                    stmt.setInt(14, (int) empInfoT.getValueAt(row, 1));

                    Statement st = connection.createStatement();
                    st.execute("alter session set NLS_DATE_FORMAT= \"YYYY-MM-DD\"");
                    stmt.executeUpdate();
                    st.execute("commit");
                    JOptionPane.showMessageDialog(this, "Update Successfull", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Update Failed", "", JOptionPane.PLAIN_MESSAGE);
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                gd.getEmployeeInfo(empInfoT);
                //searchTable(empInfoT, empInfoSearchTB);
            }

        }
    }//GEN-LAST:event_updateEmpInfoBActionPerformed

    private void updateDictBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDictBActionPerformed
        int row = dictT.getSelectedRow();
        if (row != -1) {

            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to update ?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {

                try {

                    //Date Formating
                    String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
                    java.util.Date date = sdf1.parse(d);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    PreparedStatement stmt = connection.prepareStatement(
                            "update WORD_DICTIONARY set DESCRIPTION=?, CATEGORY=?, SCORE=?, APPROVED_BY=? where WORD_ID=?");

                    stmt.setString(1, (String) dictT.getValueAt(row, 2));
                    stmt.setString(2, (String) dictT.getValueAt(row, 3));
                    stmt.setInt(3, (int) dictT.getValueAt(row, 4));
                    stmt.setInt(4, 202);
                    stmt.setInt(5, (int) dictT.getValueAt(row, 1));

                    Statement st = connection.createStatement();
                    st.execute("alter session set NLS_DATE_FORMAT= \"YYYY-MM-DD\"");
                    // st.executeUpdate(q);
                    stmt.executeUpdate();
                    st.execute("commit");
                    JOptionPane.showMessageDialog(this, "Update Successfull", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Update Failed", "", JOptionPane.PLAIN_MESSAGE);
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                gd.getDictionary(dictT,"manager");
                //searchTable(dictT, dictSearchTB);
            }

        }
    }//GEN-LAST:event_updateDictBActionPerformed

    private void dictTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dictTMouseClicked
        int column = dictT.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / dictT.getRowHeight();
        //System.out.println("Col :"+column + " row:"+row);

        if (column == 0) {
            Object id = dictT.getValueAt(row, 1);
            Object word = dictT.getValueAt(row, 2);
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + word + " ?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {

                String query = "delete from word_dictionary where word_id=" + id;

                try {
                    Statement st = connection.createStatement();
                    st.execute(query);
                    st.execute("commit");
                    JOptionPane.showMessageDialog(this, "Word Deleted Successfully", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                gd.getDictionary(dictT,"manager");
                //searchTable(dictT, dictSearchTB);
            }
        }
    }//GEN-LAST:event_dictTMouseClicked

    private void customerTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTMouseClicked
        int column = customerT.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / customerT.getRowHeight();
        //System.out.println("Col :"+column + " row:"+row);

        if (column == 0) {
            Object id = customerT.getValueAt(row, 0);
            Object fname = customerT.getValueAt(row, 1);
            Object lname = customerT.getValueAt(row, 2);
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete\n" + fname + " " + lname, "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {

                String query = "delete from customer where customer_id=" + id;

                try {
                    Statement st = connection.createStatement();
                    st.execute(query);
                    st.execute("commit");
                    JOptionPane.showMessageDialog(this, "Customer Deleted Successfully", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                gd.getCustomer(customerT,"manager");
                //searchTable(customerT, customerSearchTB);
            }
        }
    }//GEN-LAST:event_customerTMouseClicked

    private void dictSearchTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dictSearchTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dictSearchTBActionPerformed

    private void settingsSearchTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsSearchTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_settingsSearchTBActionPerformed

    private void customerSearchTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerSearchTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerSearchTBActionPerformed

    private void empDictSearchTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empDictSearchTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empDictSearchTBActionPerformed

    private void report1TMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report1TMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_report1TMouseClicked

    private void report1SearchTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report1SearchTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_report1SearchTBActionPerformed

    private void refreshBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBActionPerformed
       CalculateScore cs= new CalculateScore(connection);
        gd.getReport1(report1T);
    }//GEN-LAST:event_refreshBActionPerformed

    private void refreshB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshB1ActionPerformed
        getSettings();
    }//GEN-LAST:event_refreshB1ActionPerformed

    private void refreshB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshB2ActionPerformed
        gd.getDictionary(dictT,"manager");
    }//GEN-LAST:event_refreshB2ActionPerformed

    private void refreshB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshB3ActionPerformed
        gd.getEmployeeInfo(empInfoT);
    }//GEN-LAST:event_refreshB3ActionPerformed

    private void addCustomerB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerB1ActionPerformed
        // TODO add your handling code here:
        addCust();
    }//GEN-LAST:event_addCustomerB1ActionPerformed

    private void customerSearchTB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerSearchTB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerSearchTB1ActionPerformed

    private void aboutUsB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutUsB1ActionPerformed
        // TODO add your handling code here:
        c.show(baseP, "abtus");
    }//GEN-LAST:event_aboutUsB1ActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

//         try {   
//            javax.swing.UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
//        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutUsB1;
    private javax.swing.JPanel aboutUsP;
    private javax.swing.JLabel accountL;
    private javax.swing.JPanel accountP;
    private javax.swing.JButton addCustomerB;
    private javax.swing.JButton addCustomerB1;
    private javax.swing.JButton addEmpB;
    private javax.swing.JButton addTypeB;
    private javax.swing.JPanel adminBaseP;
    private javax.swing.JPanel adminP;
    private javax.swing.JPanel baseP;
    private javax.swing.JLabel custEL;
    private javax.swing.JPanel custEP;
    public javax.swing.JTable custET;
    private javax.swing.JLabel customerDetailsL;
    private javax.swing.JPanel customerP;
    private javax.swing.JTextField customerSearchTB;
    private javax.swing.JTextField customerSearchTB1;
    public javax.swing.JTable customerT;
    private javax.swing.JLabel dictEL;
    private javax.swing.JPanel dictEP;
    public javax.swing.JTable dictET;
    private javax.swing.JPanel dictP;
    private javax.swing.JTextField dictSearchTB;
    public javax.swing.JTable dictT;
    private javax.swing.JLabel editDictL;
    private javax.swing.JTextField empDictSearchTB;
    private javax.swing.JTextField empInfoSearchTB;
    public javax.swing.JTable empInfoT;
    private javax.swing.JPanel employeeBaseP;
    private javax.swing.JButton employeeInfoB;
    private javax.swing.JPanel employeeInfoP;
    private javax.swing.JPanel employeeP;
    private javax.swing.JLabel headingL;
    private javax.swing.JLabel headingL2;
    private javax.swing.JLabel headingL3;
    private javax.swing.JLabel headingL4;
    private javax.swing.JLabel headingL5;
    private javax.swing.JButton homeB;
    private javax.swing.JPanel homeP;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel imageL;
    private javax.swing.JLabel incomingRL;
    private javax.swing.JLabel individualRL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel loggedL;
    private javax.swing.JButton loginB;
    private javax.swing.JLabel logoutL;
    private javax.swing.JPanel menu2P;
    private javax.swing.JPanel menuBar;
    private javax.swing.JPanel menuEP;
    private javax.swing.JLabel msgL;
    private javax.swing.JLabel myAccL;
    private javax.swing.JLabel nameL;
    private javax.swing.JLabel outgoingRL;
    private javax.swing.JLabel overallRL;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JButton refreshB;
    private javax.swing.JButton refreshB1;
    private javax.swing.JButton refreshB2;
    private javax.swing.JButton refreshB3;
    private javax.swing.JLabel refreshL;
    private javax.swing.JPanel report1P;
    private javax.swing.JTextField report1SearchTB;
    public javax.swing.JTable report1T;
    private javax.swing.JLabel reportsEL;
    private javax.swing.JLabel reportsL;
    private javax.swing.JPanel reportsP;
    private javax.swing.JTextField scoreT;
    private javax.swing.JLabel searchL;
    private javax.swing.JLabel searchL2;
    private javax.swing.JLabel searchL3;
    private javax.swing.JLabel searchL4;
    private javax.swing.JLabel searchL5;
    private javax.swing.JLabel searchL6;
    private javax.swing.JLabel searchL7;
    private javax.swing.JComboBox<String> settingsCB;
    private javax.swing.JLabel settingsL;
    private javax.swing.JPanel settingsP;
    private javax.swing.JTextField settingsSearchTB;
    public javax.swing.JTable settingsT;
    private javax.swing.JButton updateDictB;
    private javax.swing.JButton updateEmpInfoB;
    private javax.swing.JButton updateSettB;
    private javax.swing.JTextField wordIdT;
    private javax.swing.JButton wordResetB;
    private javax.swing.JButton wordSubmitB;
    private javax.swing.JTextField wordT;
    private javax.swing.JComboBox<String> wordTypeCB;
    // End of variables declaration//GEN-END:variables
}
