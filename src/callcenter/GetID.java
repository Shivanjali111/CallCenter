package callcenter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetID {
    
    Connection connection;
    
    public GetID(Connection connection){
        this.connection=connection;
    }
    
    public String getEmployeeID(){
        String employee_id = null;
        try {
            
            String query="select employee_id from employee where date_join=(select max(date_join) from employee)";
            Statement statement=connection.createStatement();
            ResultSet rs= statement.executeQuery(query);
            while(rs.next()){
                employee_id=rs.getString("employee_id");
            }
            return employee_id;
            
        } catch (SQLException ex) {
            Logger.getLogger(GetID.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String getSettingsID(String table,String column,String prefix){
        String id=null;
        int max=0;
        int flag = 0;
        try {
            String query="select "+column+" from "+table+" ";
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                flag=1;
                String temp=rs.getString(1);
                int i=Integer.parseInt(temp.substring(2));
                if(i>max){
                    max=i;
                }
            }
            if(flag==1){
            max=max+1;
            id=prefix+max;
            return id;
            }else{
                return prefix+01;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    public String getWordID(){
      String word_id = null;
        try {
            
            String query="select word_id from word_dictionary where date_added=(select max(date_added) from word_dictionary)";
            Statement statement=connection.createStatement();
            ResultSet rs= statement.executeQuery(query);
            while(rs.next()){
                word_id=rs.getString("word_id");
            }
            int i =Integer.parseInt(word_id.substring(1));
            i = i+1;
            return "W"+i;
            
        } catch (SQLException ex) {
            Logger.getLogger(GetID.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
