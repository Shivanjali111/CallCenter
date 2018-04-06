package callcenter;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartUtilities;

public class Home extends javax.swing.JFrame {

    CardLayout c = new CardLayout();
    boolean shown = true;
    boolean loggedIn = false;
    String userID, name,userName;         //Logged in info
    int h, y, sy, dy, cy, ey,edy,ecy;    //Used in panel expanding and collapsing
    Timer reportsT,empReportsT;
    boolean show_reports_p = false,show_empReports_p = false;
    GetData gd = new GetData();
    Connection connection;
    String addEUsername, addEPassword;
    CreateGraph createGraph;

    public Home() {
        initComponents();
        this.setExtendedState(Home.MAXIMIZED_BOTH);
        ImageChange();
        aboutUsB1.setVisible(true);
        loggedL.setVisible(false);
        nameL.setVisible(false);
        msgL.setVisible(false);
        accountP.setVisible(false);
        accountL.setVisible(false);
        reportsP.setVisible(false);
        graphP.setVisible(false);
        baseP.setLayout(c);
        baseP.add(homeP, "h");
        baseP.add(adminP, "a");
        baseP.add(employeeP, "emp");
        baseP.add(aboutUsP, "abtus");
        adminBaseP.setLayout(c);
        adminBaseP.add(employeeInfoP, "e");
        adminBaseP.add(customerP, "c");
        adminBaseP.add(settingsP, "s");
        adminBaseP.add(phrasesP,"phrasesP");
        adminBaseP.add(dictP, "d");
        adminBaseP.add(overallReportP, "report1");
        adminBaseP.add(performanceGraphP, "report2");
        adminBaseP.add(individualReportP, "individualReport");
        employeeBaseP.setLayout(c);
        employeeBaseP.add(custEP, "cemp");
        employeeBaseP.add(dictEP, "demp");
        employeeBaseP.add(homeEP, "homeEP");
        employeeBaseP.add(empGraphP, "empGraph");
        employeeBaseP.add(empReportP, "empReport");
        aboutUsBaseP.setLayout(c);
        aboutUsBaseP.add(aboutUsInfoP, "info");
        aboutUsBaseP.add(contactUs, "contachUs");
        
        empRMenuP.setVisible(false);

        graphMsg1L.setVisible(false);
        graphMsg2L.setVisible(false);
        compareWithP.setVisible(false);
        downloadGraphL.setVisible(false);

        dictP.getRootPane().setDefaultButton(wordSubmitB);
        individualReportP.getRootPane().setDefaultButton(getReportIB);
        TableFormatting();

        reportsT = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        empReportsT = new Timer(100, new ActionListener() {
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
        overallReortT.setDefaultRenderer(Integer.class, centerRenderer);
        custET.setDefaultRenderer(Integer.class, centerRenderer);
        dictET.setDefaultRenderer(Integer.class, centerRenderer);
        individualReportT.setDefaultRenderer(Integer.class, centerRenderer);

        //=====================For Horizontal Scroll Bar===============================
        empInfoT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        settingsT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        dictT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        customerT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        overallReortT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        custET.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        dictET.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

    public void addCust() {
        AddCustomer dialog = new AddCustomer(this, shown);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (dialog.added) {
            String query = "insert into customer values('" + dialog.customerIdT.getText() + "','" + dialog.customerFN.getText() + "','"
                    + dialog.customerLNT.getText() + "','" + dialog.customerEmailT.getText() + "'," + dialog.customerMobT.getText()
                    + ",'" + dialog.productC.getSelectedItem() + "','" + dialog.serviceC.getSelectedItem() + "','"
                    + dialog.dateOfPurchaseD.getDateStringOrEmptyString() + "')";

            try {
                Statement st = connection.createStatement();
                st.execute(query);
                st.execute("commit");
                JOptionPane.showMessageDialog(this, "Customer Added Successfully", "", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            gd.getCustomer(customerT, "manager");
            //searchTable(customerT, customerSearchTB);
        }
    }

    private void ExpandReportsP() {
        if (!reportsT.isRunning()) {

            h = reportsP.getHeight();
            sy = settingsL.getY();
            show_reports_p = true;
            reportsP.setVisible(true);

            reportsT = new Timer(10, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    h = h + 10;
                    sy = sy + 10;
                    reportsP.setBounds(0, reportsP.getY(), reportsP.getWidth(), h);
                    settingsL.setBounds(0, sy, settingsL.getWidth(), settingsL.getHeight());
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
            show_reports_p = false;

            reportsT = new Timer(10, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    h = h - 10;
                    sy = sy - 10;
                    reportsP.setBounds(0, reportsP.getY(), reportsP.getWidth(), h);
                    settingsL.setBounds(0, sy, settingsL.getWidth(), settingsL.getHeight());
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

    private void ExpandEmpReportsP() {
        if (!empReportsT.isRunning()) {

            h = empRMenuP.getHeight();
            edy = dictEL.getY();
            ecy = custEL.getY();
            show_empReports_p = true;
            empRMenuP.setVisible(true);

            empReportsT = new Timer(10, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    h = h + 10;
                    edy = edy + 10;
                    ecy = ecy +10;
                    empRMenuP.setBounds(0, empRMenuP.getY(), empRMenuP.getWidth(), h);
                    dictEL.setBounds(0, edy, dictEL.getWidth(), dictEL.getHeight());
                    custEL.setBounds(0, ecy, custEL.getWidth(), custEL.getHeight());
                    if (h >= 80) {
                        empReportsT.stop();
                    }
                }
            });

            empReportsT.start();
        }
    }

    private void CollapseEmpReportsP() {
        if (!empReportsT.isRunning()) {

            h = empRMenuP.getHeight();
            edy = dictEL.getY();
            ecy = custEL.getY();
            show_empReports_p = false;
            empRMenuP.setVisible(true);

            empReportsT = new Timer(10, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    h = h - 10;
                    edy = edy - 10;
                    ecy = ecy -10;
                    empRMenuP.setBounds(0, empRMenuP.getY(), empRMenuP.getWidth(), h);
                    dictEL.setBounds(0, edy, dictEL.getWidth(), dictEL.getHeight());
                    custEL.setBounds(0, ecy, custEL.getWidth(), custEL.getHeight());
                    if (h <= 1) {
                        empRMenuP.setVisible(false);
                        empReportsT.stop();
                    }
                }
            });

            empReportsT.start();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jScrollPane10 = new javax.swing.JScrollPane();
        individualReportT1 = new javax.swing.JTable();
        searchL10 = new javax.swing.JLabel();
        individualRSearchT1 = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        menuRP4 = new javax.swing.JPanel();
        refreshL4 = new javax.swing.JLabel();
        menuRP5 = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        menuDP2 = new javax.swing.JPanel();
        refreshL5 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        searchL11 = new javax.swing.JLabel();
        individualRT1 = new javax.swing.JTextField();
        getReportIB1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        menuBar = new javax.swing.JPanel();
        homeB = new javax.swing.JButton();
        loginB = new javax.swing.JButton();
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
        showPhraseB = new javax.swing.JButton();
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
        overallReportP = new javax.swing.JPanel();
        headingL5 = new javax.swing.JLabel();
        searchL6 = new javax.swing.JLabel();
        report1SearchTB = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        menuRP = new javax.swing.JPanel();
        refreshL1 = new javax.swing.JLabel();
        menuRP1 = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        menuDP = new javax.swing.JPanel();
        refreshL = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        overallReortT = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        performanceGraphP = new javax.swing.JPanel();
        headingL6 = new javax.swing.JLabel();
        graphP = new javax.swing.JPanel();
        compareWithP = new javax.swing.JPanel();
        compareL = new javax.swing.JLabel();
        compareIDT = new javax.swing.JTextField();
        compareB = new javax.swing.JButton();
        graphMsg2L = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        eidReportT = new javax.swing.JTextField();
        getReportB = new javax.swing.JButton();
        graphMsg1L = new javax.swing.JLabel();
        downloadGraphL = new javax.swing.JLabel();
        individualReportP = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        individualReportT = new javax.swing.JTable();
        headingL7 = new javax.swing.JLabel();
        searchL8 = new javax.swing.JLabel();
        individualRSearchT = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        menuRP2 = new javax.swing.JPanel();
        refreshL2 = new javax.swing.JLabel();
        menuRP3 = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        menuDP1 = new javax.swing.JPanel();
        refreshL3 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        searchL9 = new javax.swing.JLabel();
        individualRT = new javax.swing.JTextField();
        getReportIB = new javax.swing.JButton();
        phrasesP = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        phraseT = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        phraseTB = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        phraseTypeCB = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        phraseScoreT = new javax.swing.JTextField();
        phraseSubmitB = new javax.swing.JButton();
        msgL1 = new javax.swing.JLabel();
        phraseResetB = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        phraseIDT = new javax.swing.JTextField();
        headingL8 = new javax.swing.JLabel();
        updatePhraseB = new javax.swing.JButton();
        searchL12 = new javax.swing.JLabel();
        phraseSearchTB = new javax.swing.JTextField();
        refreshB4 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        showDictB = new javax.swing.JButton();
        menu2P = new javax.swing.JPanel();
        settingsL = new javax.swing.JLabel();
        reportsL = new javax.swing.JLabel();
        reportsP = new javax.swing.JPanel();
        outgoingRL = new javax.swing.JLabel();
        incomingRL = new javax.swing.JLabel();
        performanceGL = new javax.swing.JLabel();
        overallRL = new javax.swing.JLabel();
        editDictL = new javax.swing.JLabel();
        employeeInfoL = new javax.swing.JLabel();
        customerDetailsL = new javax.swing.JLabel();
        employeeP = new javax.swing.JPanel();
        menuEP = new javax.swing.JPanel();
        custEL = new javax.swing.JLabel();
        reportsEL = new javax.swing.JLabel();
        dictEL = new javax.swing.JLabel();
        homeEL = new javax.swing.JLabel();
        empRMenuP = new javax.swing.JPanel();
        performanceL = new javax.swing.JLabel();
        reportL = new javax.swing.JLabel();
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
        homeEP = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        empGraphP = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        employeeGraphP = new javax.swing.JPanel();
        empReportP = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        empReportT = new javax.swing.JTable();
        empRSearchT = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        menuRP6 = new javax.swing.JPanel();
        refreshL6 = new javax.swing.JLabel();
        menuRP7 = new javax.swing.JPanel();
        jSeparator15 = new javax.swing.JSeparator();
        menuDP3 = new javax.swing.JPanel();
        refreshL7 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        searchL13 = new javax.swing.JLabel();
        aboutUsP = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        aboutUsBaseP = new javax.swing.JPanel();
        aboutUsInfoP = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        textAreaImage = new javax.swing.JTextArea();
        imageL2 = new javax.swing.JLabel();
        contactUs = new javax.swing.JPanel();

        popupMenu1.setLabel("popupMenu1");

        individualReportT1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        individualReportT1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Call ID", "Date", "Positive Words Used", "Positive Score", "Negative Words Used", "Negative Score", "Total Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        individualReportT1.setRowHeight(30);
        individualReportT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                individualReportT1MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(individualReportT1);
        if (individualReportT1.getColumnModel().getColumnCount() > 0) {
            individualReportT1.getColumnModel().getColumn(0).setResizable(false);
            individualReportT1.getColumnModel().getColumn(1).setResizable(false);
            individualReportT1.getColumnModel().getColumn(3).setResizable(false);
            individualReportT1.getColumnModel().getColumn(5).setResizable(false);
            individualReportT1.getColumnModel().getColumn(6).setResizable(false);
        }

        searchL10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL10.setText("Employee ID");

        individualRSearchT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                individualRSearchT1ActionPerformed(evt);
            }
        });

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        menuRP4.setBackground(new java.awt.Color(255, 255, 255));
        menuRP4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuRP4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuRP4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuRP4MouseExited(evt);
            }
        });
        menuRP4.setLayout(new javax.swing.BoxLayout(menuRP4, javax.swing.BoxLayout.LINE_AXIS));

        refreshL4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        refreshL4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        refreshL4.setText("Refresh");
        menuRP4.add(refreshL4);

        menuRP5.setBackground(new java.awt.Color(255, 255, 255));
        menuRP5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuRP5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuRP5MouseExited(evt);
            }
        });
        menuRP5.setLayout(new javax.swing.BoxLayout(menuRP5, javax.swing.BoxLayout.LINE_AXIS));
        menuRP4.add(menuRP5);

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        menuDP2.setBackground(new java.awt.Color(255, 255, 255));
        menuDP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDP2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuDP2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuDP2MouseExited(evt);
            }
        });
        menuDP2.setLayout(new javax.swing.BoxLayout(menuDP2, javax.swing.BoxLayout.LINE_AXIS));

        refreshL5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        refreshL5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/download.jpg"))); // NOI18N
        refreshL5.setText("Download");
        menuDP2.add(refreshL5);

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

        searchL11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL11.setText("Search");

        getReportIB1.setText("Get Report");
        getReportIB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getReportIB1ActionPerformed(evt);
            }
        });

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
        loginB.setBounds(220, 0, 130, 40);

        nameL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        nameL.setForeground(new java.awt.Color(255, 255, 255));
        menuBar.add(nameL);
        nameL.setBounds(1190, 0, 110, 40);

        loggedL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        loggedL.setForeground(new java.awt.Color(255, 255, 255));
        loggedL.setText("You are logged in as: ");
        menuBar.add(loggedL);
        loggedL.setBounds(1050, 0, 140, 40);

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
        aboutUsB1.setBounds(110, 0, 110, 40);

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myAccLMouseClicked(evt);
            }
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
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "EmployeeID", "First Name", "Last Name", "DOB", "Address", "City", "State", "Country", "Pin Code", "Email", "CompanyMailID", "Mobile", "DeskID", "DepartmentID", "Date of Join"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, false
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
            empInfoT.getColumnModel().getColumn(0).setResizable(false);
            empInfoT.getColumnModel().getColumn(0).setPreferredWidth(38);
            empInfoT.getColumnModel().getColumn(1).setResizable(false);
            empInfoT.getColumnModel().getColumn(1).setPreferredWidth(80);
            empInfoT.getColumnModel().getColumn(2).setResizable(false);
            empInfoT.getColumnModel().getColumn(2).setPreferredWidth(150);
            empInfoT.getColumnModel().getColumn(3).setResizable(false);
            empInfoT.getColumnModel().getColumn(3).setPreferredWidth(150);
            empInfoT.getColumnModel().getColumn(4).setResizable(false);
            empInfoT.getColumnModel().getColumn(4).setPreferredWidth(120);
            empInfoT.getColumnModel().getColumn(5).setResizable(false);
            empInfoT.getColumnModel().getColumn(5).setPreferredWidth(250);
            empInfoT.getColumnModel().getColumn(6).setResizable(false);
            empInfoT.getColumnModel().getColumn(6).setPreferredWidth(120);
            empInfoT.getColumnModel().getColumn(7).setResizable(false);
            empInfoT.getColumnModel().getColumn(7).setPreferredWidth(120);
            empInfoT.getColumnModel().getColumn(8).setResizable(false);
            empInfoT.getColumnModel().getColumn(8).setPreferredWidth(120);
            empInfoT.getColumnModel().getColumn(9).setResizable(false);
            empInfoT.getColumnModel().getColumn(10).setResizable(false);
            empInfoT.getColumnModel().getColumn(10).setPreferredWidth(250);
            empInfoT.getColumnModel().getColumn(11).setResizable(false);
            empInfoT.getColumnModel().getColumn(11).setPreferredWidth(250);
            empInfoT.getColumnModel().getColumn(12).setResizable(false);
            empInfoT.getColumnModel().getColumn(12).setPreferredWidth(150);
            empInfoT.getColumnModel().getColumn(13).setResizable(false);
            empInfoT.getColumnModel().getColumn(13).setPreferredWidth(100);
            empInfoT.getColumnModel().getColumn(14).setResizable(false);
            empInfoT.getColumnModel().getColumn(14).setPreferredWidth(100);
            empInfoT.getColumnModel().getColumn(15).setResizable(false);
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
            dictT.getColumnModel().getColumn(2).setPreferredWidth(100);
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

        wordIdT.setEnabled(false);
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
        updateDictB.setBounds(150, 70, 80, 30);

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
        refreshB2.setBounds(240, 70, 40, 30);
        dictP.add(jSeparator1);
        jSeparator1.setBounds(0, 360, 1200, 20);

        jLabel7.setBackground(new java.awt.Color(0, 51, 255));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jLabel7.setText("Add Word");
        dictP.add(jLabel7);
        jLabel7.setBounds(70, 370, 110, 30);

        showPhraseB.setText("Phrases");
        showPhraseB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPhraseBActionPerformed(evt);
            }
        });
        dictP.add(showPhraseB);
        showPhraseB.setBounds(50, 70, 80, 30);

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
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Customer ID", "First Name", "Last Name", "E-mail", "Mobile", "Product", "Service", "Date of Purchase"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
            customerT.getColumnModel().getColumn(0).setResizable(false);
            customerT.getColumnModel().getColumn(0).setPreferredWidth(38);
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
            customerT.getColumnModel().getColumn(8).setResizable(false);
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

        overallReportP.setBackground(new java.awt.Color(255, 255, 255));
        overallReportP.setLayout(null);

        headingL5.setBackground(new java.awt.Color(204, 204, 204));
        headingL5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        headingL5.setForeground(new java.awt.Color(0, 51, 255));
        headingL5.setText("                Overall Report");
        headingL5.setOpaque(true);
        overallReportP.add(headingL5);
        headingL5.setBounds(0, 0, 1200, 40);

        searchL6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL6.setText("Search");
        overallReportP.add(searchL6);
        searchL6.setBounds(810, 70, 50, 30);

        report1SearchTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report1SearchTBActionPerformed(evt);
            }
        });
        overallReportP.add(report1SearchTB);
        report1SearchTB.setBounds(870, 70, 200, 30);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        overallReportP.add(jSeparator5);
        jSeparator5.setBounds(310, 70, 10, 40);

        menuRP.setBackground(new java.awt.Color(255, 255, 255));
        menuRP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuRPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuRPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuRPMouseExited(evt);
            }
        });
        menuRP.setLayout(new javax.swing.BoxLayout(menuRP, javax.swing.BoxLayout.LINE_AXIS));

        refreshL1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        refreshL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        refreshL1.setText("Refresh");
        menuRP.add(refreshL1);

        menuRP1.setBackground(new java.awt.Color(255, 255, 255));
        menuRP1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuRP1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuRP1MouseExited(evt);
            }
        });
        menuRP1.setLayout(new javax.swing.BoxLayout(menuRP1, javax.swing.BoxLayout.LINE_AXIS));
        menuRP.add(menuRP1);

        overallReportP.add(menuRP);
        menuRP.setBounds(80, 70, 100, 40);

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        overallReportP.add(jSeparator8);
        jSeparator8.setBounds(70, 70, 10, 40);

        menuDP.setBackground(new java.awt.Color(255, 255, 255));
        menuDP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuDPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuDPMouseExited(evt);
            }
        });
        menuDP.setLayout(new javax.swing.BoxLayout(menuDP, javax.swing.BoxLayout.LINE_AXIS));

        refreshL.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        refreshL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/download.jpg"))); // NOI18N
        refreshL.setText("Download");
        menuDP.add(refreshL);

        overallReportP.add(menuDP);
        menuDP.setBounds(200, 70, 100, 40);

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        overallReportP.add(jSeparator6);
        jSeparator6.setBounds(190, 70, 10, 40);

        overallReortT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        overallReortT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Agent Name", "Employee ID", "Positive Words Used", "Positive Score", "Negative Words Used", "Negative Score", "Total Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        overallReortT.setRowHeight(30);
        jScrollPane7.setViewportView(overallReortT);
        if (overallReortT.getColumnModel().getColumnCount() > 0) {
            overallReortT.getColumnModel().getColumn(1).setResizable(false);
            overallReortT.getColumnModel().getColumn(3).setResizable(false);
            overallReortT.getColumnModel().getColumn(5).setResizable(false);
            overallReortT.getColumnModel().getColumn(6).setResizable(false);
        }

        overallReportP.add(jScrollPane7);
        jScrollPane7.setBounds(60, 130, 1020, 420);

        jButton3.setText("Bar Graph");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        overallReportP.add(jButton3);
        jButton3.setBounds(320, 80, 90, 30);

        adminBaseP.add(overallReportP);
        overallReportP.setBounds(0, 0, 1200, 580);

        performanceGraphP.setBackground(new java.awt.Color(255, 255, 255));
        performanceGraphP.setLayout(null);

        headingL6.setBackground(new java.awt.Color(204, 204, 204));
        headingL6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        headingL6.setForeground(new java.awt.Color(0, 51, 255));
        headingL6.setText("                Performance Graph");
        headingL6.setOpaque(true);
        performanceGraphP.add(headingL6);
        headingL6.setBounds(0, 0, 1200, 40);

        graphP.setBackground(new java.awt.Color(255, 255, 255));
        performanceGraphP.add(graphP);
        graphP.setBounds(390, 80, 700, 440);

        compareWithP.setBackground(new java.awt.Color(255, 255, 255));
        compareWithP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        compareL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        compareL.setText("Compare With");

        compareIDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                compareIDTFocusGained(evt);
            }
        });

        compareB.setText("Compare");
        compareB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareBActionPerformed(evt);
            }
        });

        graphMsg2L.setForeground(new java.awt.Color(255, 0, 0));
        graphMsg2L.setText("Enter Employee ID to Compare");

        javax.swing.GroupLayout compareWithPLayout = new javax.swing.GroupLayout(compareWithP);
        compareWithP.setLayout(compareWithPLayout);
        compareWithPLayout.setHorizontalGroup(
            compareWithPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compareWithPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(compareWithPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(compareWithPLayout.createSequentialGroup()
                        .addComponent(compareL, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compareIDT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(compareB, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, compareWithPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(graphMsg2L, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        compareWithPLayout.setVerticalGroup(
            compareWithPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, compareWithPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(compareWithPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compareIDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compareL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphMsg2L)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compareB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        performanceGraphP.add(compareWithP);
        compareWithP.setBounds(40, 310, 240, 110);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Employee ID");

        eidReportT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                eidReportTFocusGained(evt);
            }
        });

        getReportB.setText("Get Report");
        getReportB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getReportBActionPerformed(evt);
            }
        });

        graphMsg1L.setForeground(new java.awt.Color(255, 0, 0));
        graphMsg1L.setText("Enter Employee ID");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(eidReportT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(getReportB, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(graphMsg1L, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eidReportT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphMsg1L)
                .addGap(11, 11, 11)
                .addComponent(getReportB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        performanceGraphP.add(jPanel3);
        jPanel3.setBounds(40, 130, 240, 110);

        downloadGraphL.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        downloadGraphL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/download.jpg"))); // NOI18N
        downloadGraphL.setText("Download");
        downloadGraphL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                downloadGraphLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                downloadGraphLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                downloadGraphLMouseExited(evt);
            }
        });
        performanceGraphP.add(downloadGraphL);
        downloadGraphL.setBounds(50, 450, 120, 30);

        adminBaseP.add(performanceGraphP);
        performanceGraphP.setBounds(0, 0, 1200, 580);

        individualReportP.setBackground(new java.awt.Color(255, 255, 255));
        individualReportP.setLayout(null);

        individualReportT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        individualReportT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Call ID", "Date", "Positive Words Used", "Positive Score", "Negative Words Used", "Negative Score", "Total Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        individualReportT.setRowHeight(30);
        individualReportT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                individualReportTMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(individualReportT);
        if (individualReportT.getColumnModel().getColumnCount() > 0) {
            individualReportT.getColumnModel().getColumn(0).setResizable(false);
            individualReportT.getColumnModel().getColumn(1).setResizable(false);
            individualReportT.getColumnModel().getColumn(3).setResizable(false);
            individualReportT.getColumnModel().getColumn(5).setResizable(false);
            individualReportT.getColumnModel().getColumn(6).setResizable(false);
        }

        individualReportP.add(jScrollPane9);
        jScrollPane9.setBounds(70, 130, 1010, 400);

        headingL7.setBackground(new java.awt.Color(204, 204, 204));
        headingL7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        headingL7.setForeground(new java.awt.Color(0, 51, 255));
        headingL7.setText("                Individual Report");
        headingL7.setOpaque(true);
        individualReportP.add(headingL7);
        headingL7.setBounds(0, 0, 1200, 40);

        searchL8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL8.setText("Employee ID");
        individualReportP.add(searchL8);
        searchL8.setBounds(70, 80, 80, 30);

        individualRSearchT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                individualRSearchTActionPerformed(evt);
            }
        });
        individualReportP.add(individualRSearchT);
        individualRSearchT.setBounds(880, 80, 200, 30);

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        individualReportP.add(jSeparator7);
        jSeparator7.setBounds(790, 80, 10, 40);

        menuRP2.setBackground(new java.awt.Color(255, 255, 255));
        menuRP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuRP2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuRP2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuRP2MouseExited(evt);
            }
        });
        menuRP2.setLayout(new javax.swing.BoxLayout(menuRP2, javax.swing.BoxLayout.LINE_AXIS));

        refreshL2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        refreshL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        refreshL2.setText("Refresh");
        menuRP2.add(refreshL2);

        menuRP3.setBackground(new java.awt.Color(255, 255, 255));
        menuRP3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuRP3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuRP3MouseExited(evt);
            }
        });
        menuRP3.setLayout(new javax.swing.BoxLayout(menuRP3, javax.swing.BoxLayout.LINE_AXIS));
        menuRP2.add(menuRP3);

        individualReportP.add(menuRP2);
        menuRP2.setBounds(560, 80, 100, 40);

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        individualReportP.add(jSeparator9);
        jSeparator9.setBounds(550, 80, 10, 40);

        menuDP1.setBackground(new java.awt.Color(255, 255, 255));
        menuDP1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDP1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuDP1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuDP1MouseExited(evt);
            }
        });
        menuDP1.setLayout(new javax.swing.BoxLayout(menuDP1, javax.swing.BoxLayout.LINE_AXIS));

        refreshL3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        refreshL3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/download.jpg"))); // NOI18N
        refreshL3.setText("Download");
        menuDP1.add(refreshL3);

        individualReportP.add(menuDP1);
        menuDP1.setBounds(680, 80, 100, 40);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        individualReportP.add(jSeparator10);
        jSeparator10.setBounds(670, 80, 10, 40);

        searchL9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL9.setText("Search");
        individualReportP.add(searchL9);
        searchL9.setBounds(820, 80, 50, 30);
        individualReportP.add(individualRT);
        individualRT.setBounds(170, 80, 80, 30);

        getReportIB.setText("Get Report");
        getReportIB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getReportIBActionPerformed(evt);
            }
        });
        individualReportP.add(getReportIB);
        getReportIB.setBounds(260, 80, 100, 30);

        adminBaseP.add(individualReportP);
        individualReportP.setBounds(0, 0, 1200, 580);

        phrasesP.setBackground(new java.awt.Color(255, 255, 255));
        phrasesP.setLayout(null);

        phraseT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        phraseT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "ID", "Phrase", "Category", "Score", "Date Added", "Date Modified", "Modified By"
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
        phraseT.setRowHeight(25);
        phraseT.getTableHeader().setReorderingAllowed(false);
        phraseT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phraseTMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(phraseT);
        if (phraseT.getColumnModel().getColumnCount() > 0) {
            phraseT.getColumnModel().getColumn(0).setMinWidth(38);
            phraseT.getColumnModel().getColumn(0).setPreferredWidth(38);
            phraseT.getColumnModel().getColumn(0).setMaxWidth(38);
            phraseT.getColumnModel().getColumn(1).setResizable(false);
            phraseT.getColumnModel().getColumn(2).setPreferredWidth(100);
            phraseT.getColumnModel().getColumn(3).setResizable(false);
            phraseT.getColumnModel().getColumn(4).setResizable(false);
            phraseT.getColumnModel().getColumn(5).setResizable(false);
            phraseT.getColumnModel().getColumn(6).setResizable(false);
            phraseT.getColumnModel().getColumn(7).setResizable(false);
        }

        phrasesP.add(jScrollPane12);
        jScrollPane12.setBounds(50, 120, 690, 220);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel18.setText("Phrase ID");
        phrasesP.add(jLabel18);
        jLabel18.setBounds(70, 410, 60, 30);

        phraseTB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                phraseTBFocusGained(evt);
            }
        });
        phraseTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phraseTBActionPerformed(evt);
            }
        });
        phrasesP.add(phraseTB);
        phraseTB.setBounds(140, 460, 450, 30);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel19.setText("Type ");
        phrasesP.add(jLabel19);
        jLabel19.setBounds(230, 410, 40, 30);

        phraseTypeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Positive", "Negative" }));
        phrasesP.add(phraseTypeCB);
        phraseTypeCB.setBounds(270, 410, 140, 30);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel20.setText("Score");
        phrasesP.add(jLabel20);
        jLabel20.setBounds(430, 410, 40, 30);

        phraseScoreT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                phraseScoreTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                phraseScoreTFocusLost(evt);
            }
        });
        phraseScoreT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phraseScoreTActionPerformed(evt);
            }
        });
        phraseScoreT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phraseScoreTKeyPressed(evt);
            }
        });
        phrasesP.add(phraseScoreT);
        phraseScoreT.setBounds(470, 410, 110, 30);

        phraseSubmitB.setText("Submit");
        phraseSubmitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phraseSubmitBActionPerformed(evt);
            }
        });
        phrasesP.add(phraseSubmitB);
        phraseSubmitB.setBounds(210, 510, 100, 30);

        msgL1.setForeground(new java.awt.Color(255, 0, 0));
        msgL1.setText("Please enter all the feilds");
        phrasesP.add(msgL1);
        msgL1.setBounds(250, 490, 150, 14);

        phraseResetB.setText("Reset");
        phraseResetB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phraseResetBActionPerformed(evt);
            }
        });
        phrasesP.add(phraseResetB);
        phraseResetB.setBounds(330, 510, 100, 30);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel21.setText("Phrase");
        phrasesP.add(jLabel21);
        jLabel21.setBounds(70, 460, 80, 30);

        phraseIDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                phraseIDTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                phraseIDTFocusLost(evt);
            }
        });
        phraseIDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phraseIDTActionPerformed(evt);
            }
        });
        phraseIDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phraseIDTKeyPressed(evt);
            }
        });
        phrasesP.add(phraseIDT);
        phraseIDT.setBounds(140, 410, 80, 30);

        headingL8.setBackground(new java.awt.Color(204, 204, 204));
        headingL8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        headingL8.setForeground(new java.awt.Color(0, 51, 255));
        headingL8.setText("                Phrases");
        headingL8.setOpaque(true);
        phrasesP.add(headingL8);
        headingL8.setBounds(0, 0, 1200, 40);

        updatePhraseB.setText("Update");
        updatePhraseB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePhraseBActionPerformed(evt);
            }
        });
        phrasesP.add(updatePhraseB);
        updatePhraseB.setBounds(150, 70, 80, 30);

        searchL12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL12.setText("Search");
        phrasesP.add(searchL12);
        searchL12.setBounds(480, 70, 50, 30);

        phraseSearchTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phraseSearchTBActionPerformed(evt);
            }
        });
        phrasesP.add(phraseSearchTB);
        phraseSearchTB.setBounds(540, 70, 200, 30);

        refreshB4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        refreshB4.setToolTipText("Refresh Table");
        refreshB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshB4ActionPerformed(evt);
            }
        });
        phrasesP.add(refreshB4);
        refreshB4.setBounds(250, 70, 40, 30);
        phrasesP.add(jSeparator2);
        jSeparator2.setBounds(0, 360, 1200, 20);

        jLabel22.setBackground(new java.awt.Color(0, 51, 255));
        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 255));
        jLabel22.setText("Add Phrase");
        phrasesP.add(jLabel22);
        jLabel22.setBounds(70, 370, 110, 30);

        showDictB.setText("Dictionary");
        showDictB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDictBActionPerformed(evt);
            }
        });
        phrasesP.add(showDictB);
        showDictB.setBounds(50, 70, 80, 30);

        adminBaseP.add(phrasesP);
        phrasesP.setBounds(0, 0, 1200, 580);

        adminP.add(adminBaseP);
        adminBaseP.setBounds(170, 0, 1200, 580);

        menu2P.setBackground(new java.awt.Color(102, 102, 102));
        menu2P.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
        settingsL.setBounds(0, 320, 170, 30);

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
        reportsL.setBounds(0, 270, 170, 30);

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
        incomingRL.setText("   Individual Report");
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

        performanceGL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        performanceGL.setForeground(new java.awt.Color(255, 255, 255));
        performanceGL.setText("   Performance Graph");
        performanceGL.setFocusCycleRoot(true);
        performanceGL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        performanceGL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                performanceGLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                performanceGLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                performanceGLMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                performanceGLMousePressed(evt);
            }
        });
        reportsP.add(performanceGL);
        performanceGL.setBounds(0, 30, 170, 30);

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
        reportsP.setBounds(0, 300, 170, 1);

        editDictL.setBackground(new java.awt.Color(0, 102, 255));
        editDictL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        editDictL.setForeground(new java.awt.Color(255, 255, 255));
        editDictL.setText("   Dictionary");
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
        editDictL.setBounds(0, 220, 170, 30);

        employeeInfoL.setBackground(new java.awt.Color(0, 102, 255));
        employeeInfoL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        employeeInfoL.setForeground(new java.awt.Color(255, 255, 255));
        employeeInfoL.setText("   Employee Info");
        employeeInfoL.setFocusCycleRoot(true);
        employeeInfoL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        employeeInfoL.setOpaque(true);
        employeeInfoL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeInfoLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                employeeInfoLMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                employeeInfoLMousePressed(evt);
            }
        });
        menu2P.add(employeeInfoL);
        employeeInfoL.setBounds(0, 120, 170, 30);

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
        customerDetailsL.setBounds(0, 170, 170, 30);

        adminP.add(menu2P);
        menu2P.setBounds(0, 0, 170, 580);

        baseP.add(adminP);
        adminP.setBounds(0, 0, 1370, 580);

        employeeP.setBackground(new java.awt.Color(255, 255, 255));
        employeeP.setLayout(null);

        menuEP.setBackground(new java.awt.Color(102, 102, 102));
        menuEP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
        custEL.setBounds(0, 220, 160, 30);

        reportsEL.setBackground(new java.awt.Color(0, 102, 255));
        reportsEL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reportsEL.setForeground(new java.awt.Color(255, 255, 255));
        reportsEL.setText("   Reports");
        reportsEL.setOpaque(true);
        reportsEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportsELMouseClicked(evt);
            }
        });
        menuEP.add(reportsEL);
        reportsEL.setBounds(0, 120, 160, 30);

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
        dictEL.setBounds(0, 170, 160, 30);

        homeEL.setBackground(new java.awt.Color(0, 102, 255));
        homeEL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        homeEL.setForeground(new java.awt.Color(255, 255, 255));
        homeEL.setText("   Home");
        homeEL.setOpaque(true);
        homeEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeELMouseClicked(evt);
            }
        });
        menuEP.add(homeEL);
        homeEL.setBounds(0, 70, 160, 30);

        empRMenuP.setBackground(new java.awt.Color(102, 102, 102));
        empRMenuP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        empRMenuP.setLayout(null);

        performanceL.setBackground(new java.awt.Color(102, 102, 102));
        performanceL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        performanceL.setForeground(new java.awt.Color(255, 255, 255));
        performanceL.setText(" Performance Graph");
        performanceL.setFocusCycleRoot(true);
        performanceL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        performanceL.setOpaque(true);
        performanceL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                performanceLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                performanceLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                performanceLMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                performanceLMousePressed(evt);
            }
        });
        empRMenuP.add(performanceL);
        performanceL.setBounds(10, 40, 140, 30);

        reportL.setBackground(new java.awt.Color(102, 102, 102));
        reportL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        reportL.setForeground(new java.awt.Color(255, 255, 255));
        reportL.setText("   Report");
        reportL.setFocusCycleRoot(true);
        reportL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reportL.setOpaque(true);
        reportL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportLMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reportLMousePressed(evt);
            }
        });
        empRMenuP.add(reportL);
        reportL.setBounds(3, 10, 150, 30);

        menuEP.add(empRMenuP);
        empRMenuP.setBounds(0, 150, 160, 1);

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
                false, false, false, false, false, false, false
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
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "First Name", "Last Name", "E-mail", "Mobile", "Product", "Service", "Date of Purchase"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        custET.setGridColor(new java.awt.Color(0, 0, 0));
        custET.setRowHeight(25);
        custET.getTableHeader().setReorderingAllowed(false);
        custET.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                custETMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(custET);
        if (custET.getColumnModel().getColumnCount() > 0) {
            custET.getColumnModel().getColumn(0).setResizable(false);
            custET.getColumnModel().getColumn(1).setResizable(false);
            custET.getColumnModel().getColumn(2).setResizable(false);
            custET.getColumnModel().getColumn(3).setResizable(false);
            custET.getColumnModel().getColumn(4).setResizable(false);
            custET.getColumnModel().getColumn(5).setResizable(false);
            custET.getColumnModel().getColumn(6).setResizable(false);
            custET.getColumnModel().getColumn(7).setResizable(false);
        }

        custEP.add(jScrollPane6);
        jScrollPane6.setBounds(50, 150, 1100, 400);

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

        homeEP.setBackground(new java.awt.Color(255, 255, 255));
        homeEP.setLayout(null);

        jLabel13.setBackground(new java.awt.Color(204, 204, 204));
        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 255));
        jLabel13.setText("                Home");
        jLabel13.setOpaque(true);
        homeEP.add(jLabel13);
        jLabel13.setBounds(0, 0, 1200, 40);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Call ID", "Call Type", "Timestamp", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable1);

        homeEP.add(jScrollPane8);
        jScrollPane8.setBounds(50, 90, 620, 190);

        jLabel9.setText("Select Call ID ");
        homeEP.add(jLabel9);
        jLabel9.setBounds(60, 300, 100, 30);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        homeEP.add(jTextField2);
        jTextField2.setBounds(160, 300, 100, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Type 1", "Type 2", "Type 3", "Type 4", " " }));
        homeEP.add(jComboBox1);
        jComboBox1.setBounds(350, 300, 90, 30);

        jLabel10.setText("Call Type");
        homeEP.add(jLabel10);
        jLabel10.setBounds(290, 300, 80, 30);

        jLabel14.setText("Call Description");
        homeEP.add(jLabel14);
        jLabel14.setBounds(60, 360, 110, 30);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        homeEP.add(jTextField1);
        jTextField1.setBounds(160, 360, 170, 30);

        jButton2.setText("Accept");
        homeEP.add(jButton2);
        jButton2.setBounds(150, 430, 100, 30);

        jButton1.setText("Reject");
        homeEP.add(jButton1);
        jButton1.setBounds(270, 430, 100, 30);

        employeeBaseP.add(homeEP);
        homeEP.setBounds(0, 0, 1200, 580);

        empGraphP.setBackground(new java.awt.Color(255, 255, 255));
        empGraphP.setLayout(null);

        jLabel16.setBackground(new java.awt.Color(204, 204, 204));
        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 255));
        jLabel16.setText("                Performance Graph");
        jLabel16.setOpaque(true);
        empGraphP.add(jLabel16);
        jLabel16.setBounds(0, 0, 1200, 40);

        employeeGraphP.setBackground(new java.awt.Color(255, 255, 255));
        empGraphP.add(employeeGraphP);
        employeeGraphP.setBounds(60, 70, 700, 440);

        employeeBaseP.add(empGraphP);
        empGraphP.setBounds(0, 0, 1200, 580);

        empReportP.setBackground(new java.awt.Color(255, 255, 255));
        empReportP.setLayout(null);

        jLabel17.setBackground(new java.awt.Color(204, 204, 204));
        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 255));
        jLabel17.setText("                Performance Graph");
        jLabel17.setOpaque(true);
        empReportP.add(jLabel17);
        jLabel17.setBounds(0, 0, 1200, 40);

        empReportT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        empReportT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Call ID", "Date", "Positive Words Used", "Positive Score", "Negative Words Used", "Negative Score", "Total Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        empReportT.setRowHeight(30);
        empReportT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empReportTMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(empReportT);
        if (empReportT.getColumnModel().getColumnCount() > 0) {
            empReportT.getColumnModel().getColumn(0).setResizable(false);
            empReportT.getColumnModel().getColumn(1).setResizable(false);
            empReportT.getColumnModel().getColumn(3).setResizable(false);
            empReportT.getColumnModel().getColumn(5).setResizable(false);
            empReportT.getColumnModel().getColumn(6).setResizable(false);
        }

        empReportP.add(jScrollPane11);
        jScrollPane11.setBounds(70, 130, 1010, 400);

        empRSearchT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empRSearchTActionPerformed(evt);
            }
        });
        empReportP.add(empRSearchT);
        empRSearchT.setBounds(880, 80, 200, 30);

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        empReportP.add(jSeparator14);
        jSeparator14.setBounds(320, 80, 10, 40);

        menuRP6.setBackground(new java.awt.Color(255, 255, 255));
        menuRP6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuRP6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuRP6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuRP6MouseExited(evt);
            }
        });
        menuRP6.setLayout(new javax.swing.BoxLayout(menuRP6, javax.swing.BoxLayout.LINE_AXIS));

        refreshL6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        refreshL6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        refreshL6.setText("Refresh");
        menuRP6.add(refreshL6);

        menuRP7.setBackground(new java.awt.Color(255, 255, 255));
        menuRP7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuRP7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuRP7MouseExited(evt);
            }
        });
        menuRP7.setLayout(new javax.swing.BoxLayout(menuRP7, javax.swing.BoxLayout.LINE_AXIS));
        menuRP6.add(menuRP7);

        empReportP.add(menuRP6);
        menuRP6.setBounds(90, 80, 100, 40);

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        empReportP.add(jSeparator15);
        jSeparator15.setBounds(80, 80, 10, 40);

        menuDP3.setBackground(new java.awt.Color(255, 255, 255));
        menuDP3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDP3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuDP3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuDP3MouseExited(evt);
            }
        });
        menuDP3.setLayout(new javax.swing.BoxLayout(menuDP3, javax.swing.BoxLayout.LINE_AXIS));

        refreshL7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        refreshL7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/download.jpg"))); // NOI18N
        refreshL7.setText("Download");
        menuDP3.add(refreshL7);

        empReportP.add(menuDP3);
        menuDP3.setBounds(210, 80, 100, 40);

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        empReportP.add(jSeparator16);
        jSeparator16.setBounds(200, 80, 10, 40);

        searchL13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        searchL13.setText("Search");
        empReportP.add(searchL13);
        searchL13.setBounds(820, 80, 50, 30);

        employeeBaseP.add(empReportP);
        empReportP.setBounds(0, 0, 1200, 580);

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

        aboutUsBaseP.setMinimumSize(new java.awt.Dimension(1360, 400));
        aboutUsBaseP.setLayout(null);

        aboutUsInfoP.setMinimumSize(new java.awt.Dimension(1360, 400));
        aboutUsInfoP.setLayout(null);

        scroll.setBackground(new Color(0,0,10));
        scroll.setBorder(null);
        scroll.setEnabled(false);
        scroll.setOpaque(false);

        textAreaImage.setBackground(new Color(0,0,10));
        textAreaImage.setColumns(20);
        textAreaImage.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        textAreaImage.setForeground(new java.awt.Color(51, 51, 51));
        textAreaImage.setRows(5);
        textAreaImage.setText("We are a group of creative Developers here to help you - the\nCall Centers analyse the performance of your Employees.\nThis simple tool is developed for the Performance Analysis\nand Feedback.Rank your Employees according to their \naccomplishments ,get to know which Employee has \nperformed well, number of customers facing the issues, \nimprove the acquirements of Employees by giving them \nFeedback ,satisfy your customers and finally watch your \nBusiness grow!\nSatisfying Customers is every Company's goal - your's and \nour's as well!\nOur Vision is to develop a product which is not only the \nBest but also Legendary!\n \n");
        textAreaImage.setOpaque(false);
        scroll.setViewportView(textAreaImage);

        aboutUsInfoP.add(scroll);
        scroll.setBounds(20, 30, 480, 320);

        imageL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/about-us1.jpg"))); // NOI18N
        aboutUsInfoP.add(imageL2);
        imageL2.setBounds(0, 0, 1360, 400);

        aboutUsBaseP.add(aboutUsInfoP);
        aboutUsInfoP.setBounds(0, 0, 1360, 400);

        contactUs.setMinimumSize(new java.awt.Dimension(1360, 400));
        contactUs.setLayout(null);
        aboutUsBaseP.add(contactUs);
        contactUs.setBounds(0, 0, 1360, 400);

        aboutUsP.add(aboutUsBaseP);
        aboutUsBaseP.setBounds(0, 110, 1360, 400);

        baseP.add(aboutUsP);
        aboutUsP.setBounds(0, 0, 1360, 580);

        jPanel1.add(baseP);
        baseP.setBounds(0, 130, 1370, 580);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1370, 710);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBActionPerformed

        LoginDialog dialog = new LoginDialog(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        if (dialog.flag == 1) {

            loggedIn = true;
            userID = dialog.userID;
            name = dialog.name;
            userName=dialog.username;
            aboutUsB1.setVisible(false);
            homeB.setVisible(false);
            c.show(baseP, "a");
            loggedL.setVisible(true);
            nameL.setVisible(true);
            nameL.setText(name);
            loginB.setVisible(false);
            accountL.setVisible(true);
            c.show(baseP, "a");
            c.show(adminBaseP, "e");
            gd.createConnection();
            connection = gd.getConnection();
            gd.getEmployeeInfo(empInfoT);
            gd.getDictionary(dictT, "manager");
            gd.getCustomer(customerT, "manager");
            gd.getOverallReport(overallReortT);
            gd.getPhrase(phraseT, "manager");
            getSettings();
            GetID getid = new GetID(connection);
            wordIdT.setText(getid.getWordID());

            searchTable(dictT, dictSearchTB);
             searchTable(phraseT, phraseSearchTB);
            searchTable(empInfoT, empInfoSearchTB);
            searchTable(settingsT, settingsSearchTB);
            searchTable(customerT, customerSearchTB);
            searchTable(overallReortT, report1SearchTB);
            searchTable(individualReportT, individualRSearchT);
            //XYLineChartExample xy=new XYLineChartExample(graphP);
            //XYLineChartExample(graphP);
            //new CreateGraph(connection,graphP);
            try {
                Statement statement = connection.createStatement();
                statement.execute("alter session set NLS_DATE_FORMAT= \"YYYY-MM-DD HH24:MI:SS\"");
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (dialog.flag == 2) {
            loggedIn = true;
            userID = dialog.userID;
            name = dialog.name;
            userName=dialog.username;
            aboutUsB1.setVisible(false);
            homeB.setVisible(false);
            c.show(baseP, "emp");
            loggedL.setVisible(true);
            nameL.setVisible(true);
            nameL.setText(name);
            loginB.setVisible(false);
            accountL.setVisible(true);
            c.show(employeeBaseP, "emp");
            gd.createConnection();
            connection = gd.getConnection();
            gd.getDictionary(dictET, "employee");
            gd.getCustomer(custET, "employee");
            searchTable(custET, customerSearchTB1);
            searchTable(dictET, empDictSearchTB);
            searchTable(empReportT, empRSearchT);
            new CreateGraph(connection, employeeGraphP, userID);
            gd.getIndividualReport(empReportT, userID);
            try {
                Statement statement = connection.createStatement();
                statement.execute("alter session set NLS_DATE_FORMAT= \"YYYY-MM-DD\"");
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            homeB.setVisible(true);
            aboutUsB1.setVisible(true);
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
        c.show(adminBaseP, "individualReport");
    }//GEN-LAST:event_incomingRLMouseClicked

    private void incomingRLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomingRLMouseEntered
        incomingRL.setForeground(Color.cyan);
    }//GEN-LAST:event_incomingRLMouseEntered

    private void incomingRLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomingRLMouseExited
        incomingRL.setForeground(Color.white);
    }//GEN-LAST:event_incomingRLMouseExited

    private void performanceGLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performanceGLMouseEntered
        performanceGL.setForeground(Color.cyan);
    }//GEN-LAST:event_performanceGLMouseEntered

    private void performanceGLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performanceGLMouseExited
        performanceGL.setForeground(Color.white);
    }//GEN-LAST:event_performanceGLMouseExited

    private void performanceGLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performanceGLMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_performanceGLMousePressed

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
        AddEmployee dialog = new AddEmployee(this, true, connection);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (dialog.added == true) {

            String eid = dialog.userIDT.getText();
            addEUsername = dialog.lastNameT.getText() + eid;
            addEPassword = dialog.firstNameT.getText() + eid;
            String dateJoin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            System.out.print(dateJoin);
            String post = (String) dialog.postCB.getSelectedItem();
            //Converting Date into SqlDate
            try {
                 String dept_id = getDeptID((String) dialog.deptCB.getSelectedItem());

                String query = "insert into employee values('" + eid + "','" + dialog.firstNameT.getText() + "','" + dialog.lastNameT.getText() + "','"
                        + dialog.dob.getDateStringOrEmptyString() + "', '" + dialog.addressT.getText() + "', '" + dialog.cityCB.getSelectedItem() + "', '"
                        + dialog.stateCB.getSelectedItem() + "', '" + dialog.countryT.getText() + "'," + dialog.pinCodeT.getText() + ", '"
                        + dialog.emailT.getText() + "', '" + dialog.compMailT.getText() + "', " + dialog.mobileT.getText() + ", '"
                        + dialog.deskIDT.getText() + "', '" + dept_id + "', '" + dateJoin + "', null, '" + userID + "','Yes')";
                String query2 = "insert into user_details values('" + eid + "','" + addEUsername + "', '" + addEPassword + "','" + dateJoin + "','" + dateJoin + "','" + post + "')";

                Statement st = connection.createStatement();
                st.execute(query);
                st.execute(query2);
                st.execute("commit");
                JOptionPane.showMessageDialog(this, "Employee " + dialog.firstNameT.getText() + " " + dialog.lastNameT.getText()
                        + " Added Successfully !\nUsername: " + addEUsername + "\nPassword: " + addEPassword, "", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Employee adding failed", "Error", JOptionPane.PLAIN_MESSAGE);
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            gd.getEmployeeInfo(empInfoT);
        }
    }//GEN-LAST:event_addEmpBActionPerformed

    public String getDeptID(String department) throws SQLException {
        Statement stmt = connection.createStatement();
        String dept_id =null;
        String q = "select dept_id from department where deptname='" + department + "'";
        try {
            ResultSet rs = stmt.executeQuery(q);
            while(rs.next()){
             dept_id = rs.getString("dept_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dept_id;
    }

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
            String dateAdded = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            String q = "insert into word_dictionary values('" + wordIdT.getText() + "','" + wordT.getText() + "','" + wordTypeCB.getSelectedItem()
                    + "'," + scoreT.getText() + ",'" + userID + "','" + dateAdded + "','" + dateAdded + "')";

            //Connection connection=gd.getConnection();
            try {
                Statement st = connection.createStatement();
                st.execute(q);
                st.execute("commit");
                wordT.setText("");
                scoreT.setText("");
                wordIdT.setText("");
                wordTypeCB.setSelectedIndex(0);
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            GetID getid = new GetID(connection);
            wordIdT.setText(getid.getWordID());

            gd.getDictionary(dictT, "manager");
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

    private void employeeInfoLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeInfoLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeInfoLMouseClicked

    private void employeeInfoLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeInfoLMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeInfoLMouseEntered

    private void employeeInfoLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeInfoLMousePressed
        c.show(adminBaseP, "e");
    }//GEN-LAST:event_employeeInfoLMousePressed

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
        AddSettings dialog = new AddSettings(this, true, connection);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (dialog.added) {
            String query = " ";
            int k = dialog.settingsCB.getSelectedIndex();

            switch (k) {
                case 1:
                    query = "insert into department values('" + dialog.idT.getText() + "','" + dialog.typeT.getText() + "')";
                    break;
                case 2:
                    query = "insert into call_type values('" + dialog.idT.getText() + "','" + dialog.typeT.getText() + "')";
                    break;
                case 3:
                    query = "insert into category values('" + dialog.idT.getText() + "','" + dialog.typeT.getText() + "')";
                    break;
                case 4:
                    query = "insert into error_type values('" + dialog.idT.getText() + "','" + dialog.typeT.getText() + "')";
                    break;
            }

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
        }
    }//GEN-LAST:event_addTypeBActionPerformed

    private void wordResetBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordResetBActionPerformed
        GetID getid = new GetID(connection);
        wordIdT.setText(getid.getWordID());
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
                    query = "delete from department where dept_id='" + id + "'";
                } else if (category == "Call Type") {
                    query = "delete from call_type where type_id='" + id + "'";
                } else if (category == "Call Category") {
                    query = "delete from category where category_id='" + id + "'";
                } else if (category == "Error") {
                    query = "delete from error_type where error_id='" + id + "'";
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
                    query = "update department set deptname = '" + type + "' where dept_id='" + id + "'";
                } else if (category == "Call Type") {
                    query = "update call_type set description = '" + type + "' where type_id='" + id + "'";
                } else if (category == "Call Category") {
                    query = "update category set description = '" + type + "' where category_id='" + id + "'";
                } else if (category == "Error") {
                    query = "update error_type set error_type = '" + type + "' where error_id='" + id + "'";
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

                //String query = "delete from employee where employee_id='" + id + "'";
                String query = "update employee set active='No' where employee_id='"+id+"'";
                String q2 = "delete from user_details where employee_id='"+id+"'";
                try {
                    Statement st = connection.createStatement();
                    st.execute(query);
                    st.execute(q2);
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
                    /* PreparedStatement stmt = connection.prepareStatement(
                            "update employee set first_name=?, last_name=?, dob=?, address=?, city=?, state=?, country=?, pincode=?, emailid=?, "
                            + "company_mail=?, mobile=?, desk_id=?, dept_id=? where employee_id=?");

                    stmt.setString(1, (String) empInfoT.getValueAt(row, 2));
                    stmt.setString(2, (String) empInfoT.getValueAt(row, 3));
                    stmt.setDate(3, sqlDate);
                    stmt.setString(4, (String) empInfoT.getValueAt(row, 5));
                    stmt.setString(5, (String) empInfoT.getValueAt(row, 6));
                    stmt.setString(6, (String) empInfoT.getValueAt(row, 7));
                    stmt.setString(7, (String) empInfoT.getValueAt(row, 8));
                    stmt.setInt(8, (int) empInfoT.getValueAt(row, 9));
                    stmt.setString(9, (String) empInfoT.getValueAt(row, 10));
                    stmt.setString(10, (String) empInfoT.getValueAt(row, 11));
                    stmt.setLong(11, (long) empInfoT.getValueAt(row, 12));
                    stmt.setString(12, (String) empInfoT.getValueAt(row, 13));
                    stmt.setString(13, (String) empInfoT.getValueAt(row, 14));
                    stmt.setString(14, (String) empInfoT.getValueAt(row, 1));
                     */
                    String dept_id = getDeptID((String) empInfoT.getValueAt(row, 14));
                    String q = "update employee set first_name='" + empInfoT.getValueAt(row, 2) + "',last_name='" + empInfoT.getValueAt(row, 3) + "',dob='" + sqlDate
                            + "',address='" + empInfoT.getValueAt(row, 5) + "',city='" + empInfoT.getValueAt(row, 6) + "',state='" + empInfoT.getValueAt(row, 7)
                            + "',country='" + empInfoT.getValueAt(row, 8) + "',pincode=" + empInfoT.getValueAt(row, 9) + ",emailid='" + empInfoT.getValueAt(row, 10)
                            + "',company_mail='" + empInfoT.getValueAt(row, 11) + "',mobile=" + empInfoT.getValueAt(row, 12) + ",desk_id='" + empInfoT.getValueAt(row, 13)
                            + "',dept_id='" + dept_id + "' where employee_id='" + empInfoT.getValueAt(row, 1) + "'";
                    Statement st = connection.createStatement();
                    st.executeUpdate(q);
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
                    String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date date = sdf1.parse(d);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    PreparedStatement stmt = connection.prepareStatement(
                            "update WORD_DICTIONARY set DESCRIPTION=?, CATEGORY=?, SCORE=?, APPROVED_BY=?, DATE_MODI=? where WORD_ID=?");

                    stmt.setString(1, (String) dictT.getValueAt(row, 2));
                    stmt.setString(2, (String) dictT.getValueAt(row, 3));
                    stmt.setInt(3, (int) dictT.getValueAt(row, 4));
                    stmt.setString(4, userID);
                    stmt.setDate(5, sqlDate);
                    stmt.setString(6, (String) dictT.getValueAt(row, 1));

                    Statement st = connection.createStatement();
                    stmt.executeUpdate();
                    st.execute("commit");
                    JOptionPane.showMessageDialog(this, "Update Successfull", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Update Failed", "", JOptionPane.PLAIN_MESSAGE);
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                gd.getDictionary(dictT, "manager");
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

                String query = "delete from word_dictionary where word_id='" + id + "'";

                try {
                    Statement st = connection.createStatement();
                    st.execute(query);
                    st.execute("commit");
                    JOptionPane.showMessageDialog(this, "Word Deleted Successfully", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                gd.getDictionary(dictT, "manager");
                //searchTable(dictT, dictSearchTB);
            }
        }
    }//GEN-LAST:event_dictTMouseClicked

    private void customerTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTMouseClicked
        int column = customerT.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / customerT.getRowHeight();
        //System.out.println("Col :"+column + " row:"+row);

        if (column == 0) {
            Object id = customerT.getValueAt(row, 1);
            Object fname = customerT.getValueAt(row, 2);
            Object lname = customerT.getValueAt(row, 3);
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete\n" + fname + " " + lname, "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {

                String query = "delete from customer where customer_id='" + id + "'";

                try {
                    Statement st = connection.createStatement();
                    st.execute(query);
                    st.execute("commit");
                    JOptionPane.showMessageDialog(this, "Customer Deleted Successfully", "", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                gd.getCustomer(customerT, "manager");
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

    private void report1SearchTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report1SearchTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_report1SearchTBActionPerformed

    private void refreshB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshB1ActionPerformed
        getSettings();
    }//GEN-LAST:event_refreshB1ActionPerformed

    private void refreshB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshB2ActionPerformed
        gd.getDictionary(dictT, "manager");
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
        c.show(aboutUsBaseP, "info");
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        textAreaImage.setOpaque(false);
        textAreaImage.setBorder(null);
        textAreaImage.setBackground(new Color(0, 0, 0, 10));
        textAreaImage.setText("We are a group of creative Developers here to help you - the \nCall Centers analyse the performance of your Employees.\nThis simple tool is developed for the Performance Analysis\nand Feedback.Rank your Employees according to their\naccomplishments ,get to know which Employee has \nperformed well, number of customers facing the issues, \nimprove the acquirements of Employees by giving them \nFeedback ,satisfy your customers and finally watch your \nBusiness grow!\nSatisfying Customers is every Company's goal - your's and \nour's as well!\nOur Vision is to develop a product which is not only the \nBest but also Legendary!");
        // textAreaImage.setText("");
        textAreaImage.setEditable(false);
//        scroll_contact.setOpaque(false);
//        scroll_contact.getViewport().setOpaque(false);
//        scroll_contact.setBorder(null);
//        scroll_contact.setViewportBorder(null);
//        textAreaContact.setOpaque(false);
//        textAreaContact.setBorder(null);
//        textAreaContact.setBackground(new Color(0,0,0,10));
//        textAreaContact.setText("\t\t\t\t\t\tNeed to talk to us ?\n\t\t\t\t\t\tWe are listening and always ready to assist you!");
//        textAreaContact.setEditable(false);
    }//GEN-LAST:event_aboutUsB1ActionPerformed

    private void customerDetailsLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerDetailsLMouseClicked
    }//GEN-LAST:event_customerDetailsLMouseClicked

    private void customerDetailsLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerDetailsLMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_customerDetailsLMouseEntered

    private void customerDetailsLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerDetailsLMousePressed
        // TODO add your handling code here:
        c.show(adminBaseP, "c");
    }//GEN-LAST:event_customerDetailsLMousePressed

    private void menuRPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRPMouseEntered
        menuRP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.EtchedBorder.RAISED));
    }//GEN-LAST:event_menuRPMouseEntered

    private void menuRPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRPMouseExited
        menuRP.setBorder(null);
    }//GEN-LAST:event_menuRPMouseExited

    private void menuRP1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP1MouseEntered

    private void menuRP1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP1MouseExited

    private void menuRPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRPMouseClicked
        CalculateScore cs = new CalculateScore(connection);
        gd.getOverallReport(overallReortT);
    }//GEN-LAST:event_menuRPMouseClicked

    private void menuDPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDPMouseEntered
        menuDP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.EtchedBorder.RAISED));
    }//GEN-LAST:event_menuDPMouseEntered

    private void menuDPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDPMouseExited
        menuDP.setBorder(null);
    }//GEN-LAST:event_menuDPMouseExited

    public void downloadR(JTable table){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter xlsFilter = new FileNameExtensionFilter("Excel files (*.xls)", "xls");
        fileChooser.addChoosableFileFilter(xlsFilter);
        fileChooser.setFileFilter(xlsFilter);

        //FileNameExtensionFilter a=new FileNameExtensionFilter(description, extensions)
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                SaveReport save = new SaveReport();
                save.exportTable(table, fileToSave.getAbsolutePath() + ".xls");
                JOptionPane.showMessageDialog(this, "Download Complete", "", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Download Failed", "", JOptionPane.PLAIN_MESSAGE);
            }

        }

    }
    
    
    private void menuDPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDPMouseClicked
        downloadR(overallReortT);
    }//GEN-LAST:event_menuDPMouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void homeELMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeELMouseClicked
        c.show(employeeBaseP, "homeEP");
    }//GEN-LAST:event_homeELMouseClicked

    private void performanceGLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performanceGLMouseClicked
        c.show(adminBaseP, "report2");
    }//GEN-LAST:event_performanceGLMouseClicked

    private void getReportBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getReportBActionPerformed
        String eid = eidReportT.getText();
        compareIDT.setText(null);
        if ("".equals(eid)) {
            graphMsg1L.setText("Enter Employee ID");
            graphMsg1L.setVisible(true);
        } else if (!checkIfExists(eid)) {
            graphMsg1L.setText("Employee ID Incorrect");
            graphMsg1L.setVisible(true);
        } else {
            graphP.removeAll();
            graphP.revalidate();
            graphP.repaint();
            createGraph = new CreateGraph(connection, graphP, eid);
            graphP.setVisible(true);
            compareWithP.setVisible(true);
            downloadGraphL.setVisible(true);
        }
    }//GEN-LAST:event_getReportBActionPerformed

    public boolean checkIfExists(String id) {
        try {
            boolean flag = false;
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("select employee_id from employee where employee_id='" + id + "'");
            while (rs.next()) {
                flag = true;
            }
            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private void custETMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custETMouseClicked

    }//GEN-LAST:event_custETMouseClicked

    private void compareBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareBActionPerformed
        String eid = eidReportT.getText();
        String eid1 = compareIDT.getText();
        boolean flag = true;
        if ("".equals(eid)) {
            flag = false;
            graphMsg1L.setText("Enter Employee ID");
            graphMsg1L.setVisible(true);
        }
        if ("".equals(eid1)) {
            flag = false;
            graphMsg2L.setText("Enter Employee ID to Compare");
            graphMsg2L.setVisible(true);
        }
        if (flag) {
            if (!checkIfExists(eid)) {
                flag = false;
                graphMsg1L.setText("Employee ID Incorrect");
                graphMsg1L.setVisible(true);
            }
            if (!checkIfExists(eid1)) {
                flag = false;
                graphMsg2L.setText("Employee ID Incorrect");
                graphMsg2L.setVisible(true);
            }
        }
        if (flag) {
            graphP.removeAll();
            graphP.revalidate();
            graphP.repaint();
            createGraph = new CreateGraph(connection, graphP, eid, eid1);
        }
    }//GEN-LAST:event_compareBActionPerformed

    private void eidReportTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eidReportTFocusGained
        graphMsg1L.setVisible(false);
        graphMsg2L.setVisible(false);
    }//GEN-LAST:event_eidReportTFocusGained

    private void compareIDTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_compareIDTFocusGained
        graphMsg2L.setVisible(false);
        graphMsg1L.setVisible(false);
    }//GEN-LAST:event_compareIDTFocusGained

    private void individualReportTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_individualReportTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_individualReportTMouseClicked

    private void individualRSearchTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_individualRSearchTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_individualRSearchTActionPerformed

    private void menuRP3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP3MouseEntered

    private void menuRP3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP3MouseExited

    private void menuRP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP2MouseClicked

    private void menuRP2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP2MouseEntered

    private void menuRP2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP2MouseExited

    private void menuDP1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDP1MouseClicked
        // TODO add your handling code here:
        downloadR(individualReportT);
    }//GEN-LAST:event_menuDP1MouseClicked

    private void menuDP1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDP1MouseEntered
        // TODO add your handling code here:
                menuDP1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.EtchedBorder.RAISED));

    }//GEN-LAST:event_menuDP1MouseEntered

    private void menuDP1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDP1MouseExited
        // TODO add your handling code here:
        menuDP1.setBorder(null);
    }//GEN-LAST:event_menuDP1MouseExited

    private void downloadGraphLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadGraphLMouseEntered
        downloadGraphL.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.EtchedBorder.RAISED));
    }//GEN-LAST:event_downloadGraphLMouseEntered

    private void downloadGraphLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadGraphLMouseExited
        downloadGraphL.setBorder(null);
    }//GEN-LAST:event_downloadGraphLMouseExited

    private void downloadGraphLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadGraphLMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter xlsFilter = new FileNameExtensionFilter("Image files (*.png)", "png");
        fileChooser.addChoosableFileFilter(xlsFilter);
        fileChooser.setFileFilter(xlsFilter);

        //FileNameExtensionFilter a=new FileNameExtensionFilter(description, extensions)
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                File imageFile = new File(fileToSave.getAbsolutePath() + ".png");
                ChartUtilities.saveChartAsPNG(imageFile, createGraph.plotGraph.chart, 640, 480);
                JOptionPane.showMessageDialog(this, "Download Complete", "", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Download Failed", "", JOptionPane.PLAIN_MESSAGE);
            }

        }

    }//GEN-LAST:event_downloadGraphLMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        OverallReportGraph barGraph = new OverallReportGraph(overallReortT);
        barGraph.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void getReportIBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getReportIBActionPerformed
        String eid = individualRT.getText();
        gd.getIndividualReport(individualReportT, eid);
    }//GEN-LAST:event_getReportIBActionPerformed

    private void myAccLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myAccLMouseClicked
       MyAccount myAccount=new MyAccount(this, shown, name, userName, connection);
       myAccount.setLocationRelativeTo(this);
       myAccount.setVisible(true);
       
    }//GEN-LAST:event_myAccLMouseClicked

    private void performanceLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performanceLMousePressed
        //c.show(employeeBaseP, "empGraphP");
    }//GEN-LAST:event_performanceLMousePressed

    private void performanceLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performanceLMouseEntered
       performanceL.setForeground(Color.cyan);
    }//GEN-LAST:event_performanceLMouseEntered

    private void performanceLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performanceLMouseExited
       performanceL.setForeground(Color.WHITE);
    }//GEN-LAST:event_performanceLMouseExited

    private void performanceLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performanceLMouseClicked
        c.show(employeeBaseP, "empGraph");
    }//GEN-LAST:event_performanceLMouseClicked

    private void individualReportT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_individualReportT1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_individualReportT1MouseClicked

    private void individualRSearchT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_individualRSearchT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_individualRSearchT1ActionPerformed

    private void menuRP5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP5MouseEntered

    private void menuRP5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP5MouseExited

    private void menuRP4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP4MouseClicked

    private void menuRP4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP4MouseEntered

    private void menuRP4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP4MouseExited

    private void menuDP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDP2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menuDP2MouseClicked

    private void menuDP2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDP2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menuDP2MouseEntered

    private void menuDP2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDP2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_menuDP2MouseExited

    private void getReportIB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getReportIB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_getReportIB1ActionPerformed

    private void empReportTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empReportTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_empReportTMouseClicked

    private void empRSearchTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empRSearchTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empRSearchTActionPerformed

    private void menuRP7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP7MouseEntered

    private void menuRP7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_menuRP7MouseExited

    private void menuRP6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP6MouseClicked
        gd.getIndividualReport(empReportT, userID);
    }//GEN-LAST:event_menuRP6MouseClicked

    private void menuRP6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP6MouseEntered
        // TODO add your handling code here:
                menuRP6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    }//GEN-LAST:event_menuRP6MouseEntered

    private void menuRP6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRP6MouseExited
        // TODO add your handling code here:
        menuRP6.setBorder(null);
    }//GEN-LAST:event_menuRP6MouseExited

    private void menuDP3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDP3MouseClicked
        // TODO add your handling code here:
        downloadR(empReportT);
    }//GEN-LAST:event_menuDP3MouseClicked

    private void menuDP3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDP3MouseEntered
        // TODO add your handling code here:
        menuDP3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    }//GEN-LAST:event_menuDP3MouseEntered

    private void menuDP3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDP3MouseExited
        // TODO add your handling code here:
                menuDP3.setBorder(null);
    }//GEN-LAST:event_menuDP3MouseExited

    private void reportLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportLMouseClicked
        c.show(employeeBaseP, "empReport");
    }//GEN-LAST:event_reportLMouseClicked

    private void reportLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportLMouseEntered
       reportL.setForeground(Color.cyan);
    }//GEN-LAST:event_reportLMouseEntered

    private void reportLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportLMouseExited
       reportL.setForeground(Color.white);
    }//GEN-LAST:event_reportLMouseExited

    private void reportLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportLMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_reportLMousePressed

    private void reportsELMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsELMouseClicked
       if(show_empReports_p)
           CollapseEmpReportsP();
       else
           ExpandEmpReportsP();
    }//GEN-LAST:event_reportsELMouseClicked

    private void phraseTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phraseTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseTMouseClicked

    private void phraseTBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phraseTBFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseTBFocusGained

    private void phraseTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phraseTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseTBActionPerformed

    private void phraseScoreTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phraseScoreTFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseScoreTFocusGained

    private void phraseScoreTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phraseScoreTFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseScoreTFocusLost

    private void phraseScoreTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phraseScoreTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseScoreTActionPerformed

    private void phraseScoreTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phraseScoreTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseScoreTKeyPressed

    private void phraseSubmitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phraseSubmitBActionPerformed
        if ("".equals(phraseIDT.getText()) || "".equals(phraseScoreT.getText()) || phraseTypeCB.getSelectedIndex() == 0 || "".equals(phraseTB.getText())) {
            msgL.setVisible(true);
        } else {
            String dateAdded = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            String q = "insert into phrases values('" + phraseIDT.getText() + "','" + phraseTB.getText() + "','" + phraseTypeCB.getSelectedItem()
                    + "'," + phraseScoreT.getText() + ",'" + userID + "','" + dateAdded + "','" + dateAdded + "')";

            //Connection connection=gd.getConnection();
            try {
                Statement st = connection.createStatement();
                st.execute(q);
                st.execute("commit");
                phraseIDT.setText("");
                phraseScoreT.setText("");
                phraseTB.setText("");
                phraseTypeCB.setSelectedIndex(0);
                JOptionPane.showMessageDialog(this, "Phrase Added Successfully", "", JOptionPane.PLAIN_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Phrase Adding Failed", "", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            //GetID getid = new GetID(connection);
            //wordIdT.setText(getid.getWordID());

            gd.getPhrase(phraseT, "manager");
            //searchTable(dictT, dictSearchTB);
        }
    }//GEN-LAST:event_phraseSubmitBActionPerformed

    private void phraseResetBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phraseResetBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseResetBActionPerformed

    private void phraseIDTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phraseIDTFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseIDTFocusGained

    private void phraseIDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phraseIDTFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseIDTFocusLost

    private void phraseIDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phraseIDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseIDTActionPerformed

    private void phraseIDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phraseIDTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseIDTKeyPressed

    private void updatePhraseBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePhraseBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatePhraseBActionPerformed

    private void phraseSearchTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phraseSearchTBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phraseSearchTBActionPerformed

    private void refreshB4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshB4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshB4ActionPerformed

    private void showPhraseBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPhraseBActionPerformed
        c.show(adminBaseP, "phrasesP");
    }//GEN-LAST:event_showPhraseBActionPerformed

    private void showDictBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDictBActionPerformed
         c.show(adminBaseP, "d");
    }//GEN-LAST:event_showDictBActionPerformed

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
    private javax.swing.JPanel aboutUsBaseP;
    private javax.swing.JPanel aboutUsInfoP;
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
    private javax.swing.JButton compareB;
    private javax.swing.JTextField compareIDT;
    private javax.swing.JLabel compareL;
    private javax.swing.JPanel compareWithP;
    private javax.swing.JPanel contactUs;
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
    private javax.swing.JLabel downloadGraphL;
    private javax.swing.JLabel editDictL;
    private javax.swing.JTextField eidReportT;
    private javax.swing.JTextField empDictSearchTB;
    private javax.swing.JPanel empGraphP;
    private javax.swing.JTextField empInfoSearchTB;
    public javax.swing.JTable empInfoT;
    private javax.swing.JPanel empRMenuP;
    private javax.swing.JTextField empRSearchT;
    private javax.swing.JPanel empReportP;
    public javax.swing.JTable empReportT;
    private javax.swing.JPanel employeeBaseP;
    private javax.swing.JPanel employeeGraphP;
    private javax.swing.JLabel employeeInfoL;
    private javax.swing.JPanel employeeInfoP;
    private javax.swing.JPanel employeeP;
    private javax.swing.JButton getReportB;
    private javax.swing.JButton getReportIB;
    private javax.swing.JButton getReportIB1;
    private javax.swing.JLabel graphMsg1L;
    private javax.swing.JLabel graphMsg2L;
    private javax.swing.JPanel graphP;
    private javax.swing.JLabel headingL;
    private javax.swing.JLabel headingL2;
    private javax.swing.JLabel headingL3;
    private javax.swing.JLabel headingL4;
    private javax.swing.JLabel headingL5;
    private javax.swing.JLabel headingL6;
    private javax.swing.JLabel headingL7;
    private javax.swing.JLabel headingL8;
    private javax.swing.JButton homeB;
    private javax.swing.JLabel homeEL;
    private javax.swing.JPanel homeEP;
    private javax.swing.JPanel homeP;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel imageL;
    private javax.swing.JLabel imageL2;
    private javax.swing.JLabel incomingRL;
    private javax.swing.JTextField individualRSearchT;
    private javax.swing.JTextField individualRSearchT1;
    private javax.swing.JTextField individualRT;
    private javax.swing.JTextField individualRT1;
    private javax.swing.JPanel individualReportP;
    public javax.swing.JTable individualReportT;
    public javax.swing.JTable individualReportT1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel loggedL;
    private javax.swing.JButton loginB;
    private javax.swing.JLabel logoutL;
    private javax.swing.JPanel menu2P;
    private javax.swing.JPanel menuBar;
    private javax.swing.JPanel menuDP;
    private javax.swing.JPanel menuDP1;
    private javax.swing.JPanel menuDP2;
    private javax.swing.JPanel menuDP3;
    private javax.swing.JPanel menuEP;
    private javax.swing.JPanel menuRP;
    private javax.swing.JPanel menuRP1;
    private javax.swing.JPanel menuRP2;
    private javax.swing.JPanel menuRP3;
    private javax.swing.JPanel menuRP4;
    private javax.swing.JPanel menuRP5;
    private javax.swing.JPanel menuRP6;
    private javax.swing.JPanel menuRP7;
    private javax.swing.JLabel msgL;
    private javax.swing.JLabel msgL1;
    private javax.swing.JLabel myAccL;
    private javax.swing.JLabel nameL;
    private javax.swing.JLabel outgoingRL;
    private javax.swing.JLabel overallRL;
    public javax.swing.JTable overallReortT;
    private javax.swing.JPanel overallReportP;
    private javax.swing.JLabel performanceGL;
    private javax.swing.JPanel performanceGraphP;
    private javax.swing.JLabel performanceL;
    private javax.swing.JTextField phraseIDT;
    private javax.swing.JButton phraseResetB;
    private javax.swing.JTextField phraseScoreT;
    private javax.swing.JTextField phraseSearchTB;
    private javax.swing.JButton phraseSubmitB;
    public javax.swing.JTable phraseT;
    private javax.swing.JTextField phraseTB;
    private javax.swing.JComboBox<String> phraseTypeCB;
    private javax.swing.JPanel phrasesP;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JButton refreshB1;
    private javax.swing.JButton refreshB2;
    private javax.swing.JButton refreshB3;
    private javax.swing.JButton refreshB4;
    private javax.swing.JLabel refreshL;
    private javax.swing.JLabel refreshL1;
    private javax.swing.JLabel refreshL2;
    private javax.swing.JLabel refreshL3;
    private javax.swing.JLabel refreshL4;
    private javax.swing.JLabel refreshL5;
    private javax.swing.JLabel refreshL6;
    private javax.swing.JLabel refreshL7;
    private javax.swing.JTextField report1SearchTB;
    private javax.swing.JLabel reportL;
    private javax.swing.JLabel reportsEL;
    private javax.swing.JLabel reportsL;
    private javax.swing.JPanel reportsP;
    private javax.swing.JTextField scoreT;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JLabel searchL;
    private javax.swing.JLabel searchL10;
    private javax.swing.JLabel searchL11;
    private javax.swing.JLabel searchL12;
    private javax.swing.JLabel searchL13;
    private javax.swing.JLabel searchL2;
    private javax.swing.JLabel searchL3;
    private javax.swing.JLabel searchL4;
    private javax.swing.JLabel searchL5;
    private javax.swing.JLabel searchL6;
    private javax.swing.JLabel searchL7;
    private javax.swing.JLabel searchL8;
    private javax.swing.JLabel searchL9;
    private javax.swing.JComboBox<String> settingsCB;
    private javax.swing.JLabel settingsL;
    private javax.swing.JPanel settingsP;
    private javax.swing.JTextField settingsSearchTB;
    public javax.swing.JTable settingsT;
    private javax.swing.JButton showDictB;
    private javax.swing.JButton showPhraseB;
    private javax.swing.JTextArea textAreaImage;
    private javax.swing.JButton updateDictB;
    private javax.swing.JButton updateEmpInfoB;
    private javax.swing.JButton updatePhraseB;
    private javax.swing.JButton updateSettB;
    private javax.swing.JTextField wordIdT;
    private javax.swing.JButton wordResetB;
    private javax.swing.JButton wordSubmitB;
    private javax.swing.JTextField wordT;
    private javax.swing.JComboBox<String> wordTypeCB;
    // End of variables declaration//GEN-END:variables
}
