package callcenter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class CreateGraph {

    String name1, name2, eid1, eid2;
    Connection connection;
    JPanel p;
    int count1;
    int[] score1;
    Date[] call_date1;
    int count2;
    int[] score2;
    Date[] call_date2;
    PlotGraph plotGraph;

    public CreateGraph(Connection c, JPanel p, String id) {
        connection = c;
        this.p = p;
        count1 = 0;
        eid1 = id;
        getScore1(eid1);
         plotGraph = new PlotGraph(p, score1, call_date1, name1);
    }
    
    public CreateGraph(Connection c, JPanel p, String id1,String id2) {
        connection = c;
        this.p = p;
        count1 = 0;
        count2 = 0;
        eid1 = id1;
        eid2=id2;
        getScore1(eid1);
        getScore2(eid2);
         plotGraph = new PlotGraph(p, score1, call_date1, name1,score2,call_date2,name2);
    }

    public void getScore1(String id) {
        String eid=id;

        try {

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from score where employee_id='" + eid + "'");
            while (rs.next()) {
                count1++;
                //rs.beforeFirst();
            }
            rs = stm.executeQuery("select * from score where employee_id='" + eid + "'");
            score1 = new int[count1];
            call_date1 = new Date[count1];

            int i = 0;
            while (rs.next()) {
                score1[i] = rs.getInt("OVERALL_SCORE");
                call_date1[i] = rs.getDate("CALL_DATE");
                i++;
            }

            //===============Get Agent Name=====================
            ResultSet rs1 = stm.executeQuery("select first_name, last_name from employee where employee_id='" + eid1 + "'");
            while (rs1.next()) {
                name1 = rs1.getString("first_name") + " " + rs1.getString("last_name");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void getScore2(String id) {
        String eid=id;

        try {

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from score where employee_id='" + eid + "'");
            while (rs.next()) {
                count2++;
                //rs.beforeFirst();
            }
            rs = stm.executeQuery("select * from score where employee_id='" + eid + "'");
            score2 = new int[count1];
            call_date2 = new Date[count1];

            int i = 0;
            while (rs.next()) {
                score2[i] = rs.getInt("OVERALL_SCORE");
                call_date2[i] = rs.getDate("CALL_DATE");
                i++;
            }

            //===============Get Agent Name=====================
            ResultSet rs1 = stm.executeQuery("select first_name, last_name from employee where employee_id='" + eid + "'");
            while (rs1.next()) {
                name2 = rs1.getString("first_name") + " " + rs1.getString("last_name");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
