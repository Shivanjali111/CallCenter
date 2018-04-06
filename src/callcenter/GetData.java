package callcenter;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GetData {

    static Connection connection;

    public void createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "root123");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void getOverallReport(JTable t) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        dtm.setRowCount(0);
        try {
            Statement stmt = connection.createStatement();
            Statement s = connection.createStatement();
            String query="select employee_id,avg(positive_words_used),avg(positive_score),avg(neg_words_used),"
                    + "avg(negative_score),avg(overall_score) from score group by employee_id";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String employee_id = rs.getString("employee_id");
                //String call_id = rs.getString("call_id");
                //Date date= rs.getDate("call_date");
                int positive_words_used = rs.getInt("avg(positive_words_used)");
                int positive_score = rs.getInt("avg(positive_score)");
                int neg_words_used = rs.getInt("avg(neg_words_used)");
                int negative_score = rs.getInt("avg(negative_score)");
                int overall_score = rs.getInt("avg(overall_score)");
                String name = null;

                //===============Get Agent Name=====================
                ResultSet rs1 = s.executeQuery("select first_name, last_name from employee where employee_id='" + employee_id+"'");
                while (rs1.next()) {
                    name = rs1.getString("first_name") + " " + rs1.getString("last_name");
                }

                Object[] row = {name, employee_id, positive_words_used, positive_score, neg_words_used, negative_score, overall_score};
                dtm.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getIndividualReport(JTable t,String id) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        dtm.setRowCount(0);
        try {
            Statement stmt = connection.createStatement();
            Statement s = connection.createStatement();
            String query="select * from score where employee_id='"+id+"'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String call_id = rs.getString("call_id");
                Date date= rs.getDate("call_date");
                int positive_words_used = rs.getInt("positive_words_used");
                int positive_score = rs.getInt("positive_score");
                int neg_words_used = rs.getInt("neg_words_used");
                int negative_score = rs.getInt("negative_score");
                int overall_score = rs.getInt("overall_score");
                String name = null;

                //===============Get Agent Name=====================
                ResultSet rs1 = s.executeQuery("select first_name, last_name from employee where employee_id='" + id+"'");
                while (rs1.next()) {
                    name = rs1.getString("first_name") + " " + rs1.getString("last_name");
                }

                Object[] row = {call_id, date, positive_words_used, positive_score, neg_words_used, negative_score, overall_score};
                dtm.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDictionary(JTable t, String who) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        dtm.setRowCount(0);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from word_dictionary");
            while (rs.next()) {
                if(who.equals("manager")){
                    Object[] row = {"", rs.getString("word_id"), rs.getString("description"), rs.getString("category"), rs.getInt("score"), rs.getDate("date_added"),
                        rs.getDate("date_modi"), rs.getString("APPROVED_BY")};
                    dtm.addRow(row);
                }
                else{
                    Object[] row = {rs.getString("word_id"), rs.getString("description"), rs.getString("category"), rs.getInt("score"), rs.getDate("date_added"),
                        rs.getDate("date_modi"),rs.getString("APPROVED_BY")};
                    dtm.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getPhrase(JTable t, String who) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        dtm.setRowCount(0);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from phrases");
            while (rs.next()) {
                if(who.equals("manager")){
                    Object[] row = {"", rs.getString("phrase_id"), rs.getString("phrase"), rs.getString("category"), rs.getInt("score"), rs.getDate("date_added"),
                        rs.getDate("date_modi"), rs.getString("APPROVED_BY")};
                    dtm.addRow(row);
                }
                else{
                    Object[] row = {rs.getString("phrase_id"), rs.getString("phrase"), rs.getString("category"), rs.getInt("score"), rs.getDate("date_added"),
                        rs.getDate("date_modi"),rs.getString("APPROVED_BY")};
                    dtm.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCustomer(JTable ct, String who) {
        DefaultTableModel dtm = (DefaultTableModel) ct.getModel();
        dtm.setRowCount(0);
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer");
            while (rs.next()) {
                if (who.equals("manager")) {
                    Object[] row = {"", rs.getString(1), rs.getString("first_name"), rs.getString("last_name"), rs.getString("emailid"), rs.getLong("mobile"),
                        rs.getString("product"),rs.getString("service"), rs.getDate("date_of_purchace")};
                    dtm.addRow(row);
                } else {
                    Object[] row = {rs.getString(1), rs.getString("first_name"), rs.getString("last_name"), rs.getString("emailid"), rs.getLong("mobile"),
                        rs.getString("product"),rs.getString("service"), rs.getDate("date_of_purchace")};
                    dtm.addRow(row);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDepartment(JTable t) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        dtm.setRowCount(0);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from department");
            while (rs.next()) {
                Object[] row = {"", "Department", rs.getString(2), rs.getString(1)};
                dtm.addRow(row);
            }
            // t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDesk(int i, JTable t) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        if (i == 1) {
            dtm.setRowCount(0);
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from desk");
            while (rs.next()) {
                Object[] row = {"", "Desk", rs.getString(2), rs.getString(1)};
                dtm.addRow(row);
            }
            //t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCallType(int i, JTable t) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        if (i == 1) {
            dtm.setRowCount(0);
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from call_type");
            while (rs.next()) {
                Object[] row = {"", "Call Type", rs.getString(2), rs.getString(1)};
                dtm.addRow(row);
            }
            //t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCallCategory(int i, JTable t) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        if (i == 1) {
            dtm.setRowCount(0);
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from category");
            while (rs.next()) {
                Object[] row = {"", "Call Category", rs.getString(2), rs.getString(1)};
                // h.settingsT.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
                dtm.addRow(row);
            }
            //t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getError(int i, JTable t) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        if (i == 1) {
            dtm.setRowCount(0);
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from error_type");
            while (rs.next()) {
                Object[] row = {"", "Error", rs.getString(2), rs.getString(1)};
                dtm.addRow(row);
            }
            //t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getEmployeeInfo(JTable t) {
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        dtm.setRowCount(0);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee where active='Yes'");
            while (rs.next()) {
                Date d = rs.getDate("date_join");
                Object[] row = {"", rs.getString("employee_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("dob"), rs.getString("address"), 
                    rs.getString("city"), rs.getString("state"), rs.getString("country"), rs.getInt("pincode"), rs.getString("emailid"), rs.getString("company_mail"), 
                     rs.getLong("mobile"), rs.getString("desk_id"),
                    rs.getString("dept_id"), rs.getDate("date_join")};
                dtm.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        GetData gd = new GetData();
        gd.createConnection();
    }
}
