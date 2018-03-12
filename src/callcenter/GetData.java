package callcenter;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GetData {
    
    static Connection connection;
    
    public void createConnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "root123");
        } catch (ClassNotFoundException | SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void getReport1(JTable t){
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
         dtm.setRowCount(0);
         try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from score");
              while (rs.next()){
                  
                  
                  int agent_id=rs.getInt("agent_id");
                  int call_id= rs.getInt("call_id");
                  int positive_words_used=rs.getInt("positive_words_used");
                  int good_score=rs.getInt("good_score");
                  int neg_words_used=rs.getInt("neg_words_used");
                  int slang_score=rs.getInt("slang_score");
                  int overall_score=rs.getInt("overall_score");
                  String name=null;
                  
                  //===============Get Agent Name=====================
                  ResultSet rs1 = stmt.executeQuery("select first_name, last_name from employee where employee_id="+agent_id);
                  while (rs1.next()) name=rs1.getString("first_name")+" "+rs1.getString("last_name");

                  Object[] row = {call_id,name,agent_id,positive_words_used,good_score,neg_words_used,slang_score,overall_score};
                  dtm.addRow(row);
              }
        }catch(SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getDictionary(JTable t){
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
         dtm.setRowCount(0);
         try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from word_dictionary");
              while (rs.next()){
                  Object[] row = {"", rs.getInt("word_id"),rs.getString("description"),rs.getString("category"),rs.getInt("score"),rs.getDate("date_added"),
                                    rs.getDate("date_modi"),"Manager"};
                  dtm.addRow(row);
              }
        }catch(SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getCustomer(JTable ct){
        DefaultTableModel dtm = (DefaultTableModel) ct.getModel();
         dtm.setRowCount(0);
         try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer");
              while (rs.next()){
                  Object[] row = {"", rs.getInt(1),rs.getString("first_name"),rs.getString("last_name"),rs.getString("emailid"),rs.getLong("mobile"),
                  rs.getString("product_service"),rs.getDate("warranty_date")};
                  dtm.addRow(row);
              }
        }catch(SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getDepartment(JTable t){
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
         dtm.setRowCount(0);
         try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from department");
              while (rs.next()){
                  Object[] row = {"","Department",rs.getString(2),rs.getInt(1)};
                  dtm.addRow(row);
              }
             // t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        }catch(SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getDesk(int i, JTable t){
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        if(i==1)
            dtm.setRowCount(0);
         try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from desk");
              while (rs.next()){
                  Object[] row = {"","Desk",rs.getString(2),rs.getInt(1)};
                  dtm.addRow(row);
              }
               //t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        }catch(SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getCallType(int i,JTable t){
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        if(i==1)
            dtm.setRowCount(0);
         try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from call_type");
              while (rs.next()){
                  Object[] row = {"","Call Type",rs.getString(2),rs.getInt(1)};
                  dtm.addRow(row);
              }
              //t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        }catch(SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getCallCategory(int i,JTable t){
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        if(i==1)
            dtm.setRowCount(0);
         try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from category");
              while (rs.next()){
                  Object[] row = {"","Call Category",rs.getString(2),rs.getInt(1)};
                 // h.settingsT.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
                  dtm.addRow(row);
              }
              //t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        }catch(SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getError(int i,JTable t){
        DefaultTableModel dtm = (DefaultTableModel) t.getModel();
        if(i==1)
            dtm.setRowCount(0);
         try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from error_type");
              while (rs.next()){
                  Object[] row = {"","Error",rs.getString(2),rs.getInt(1)};
                  dtm.addRow(row);
              }
              //t.getColumnModel().getColumn(0).setCellRenderer(new JLabelCellenderer());
        }catch(SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getEmployeeInfo(JTable t){
         DefaultTableModel dtm = (DefaultTableModel) t.getModel();
         dtm.setRowCount(0);
         try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee");
              while (rs.next()){
                  Date d=rs.getDate("date_join");
                  Object[] row = { "",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getLong(5),rs.getString(6),rs.getString(7),
                                    rs.getString(8),rs.getString(9),rs.getInt(10),rs.getString(11),rs.getString(12),rs.getLong(13),rs.getInt(14),
                                    rs.getInt(15),rs.getDate(16)};
                  dtm.addRow(row);

              }
        }catch(SQLException ex){
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[]) {
        GetData gd=new GetData();
        gd.createConnection();
    }
}
