package callcenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculateScore {

    File folder = new File("D:\\ProjectCallRecords\\Unanalyzed");
    int call_id, agent_id, temp;
    //int positive_words_used, good_score, neg_words_used, slang_score, overall_score;
    String file_name, query;
    Connection connection;
    

    public CalculateScore(Connection connection) {
        this.connection = connection;
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles.length != 0) {
            for (File file : listOfFiles) {
                analyze(file);

            }
        }
    }

    public void analyze(File file) {

        int good_score=0;
        int slang_score=0;
        int overall_score=0;
        int positive_words_used=0;
        int neg_words_used=0;
        ArrayList<String> words = new ArrayList<>();

        file_name = file.getName();
        call_id = Integer.parseInt(file_name.substring(0, 4));
        agent_id = Integer.parseInt(file_name.substring(5, 8));

        try {
            //==================Update Call Records===============================
            query = "insert into call_records values(" + call_id + ", 22, " + agent_id + ", 2)";
            Statement st = connection.createStatement();
            st.execute(query);
            st.execute("commit");

            //================Read All Words From File============================
            FileReader fr = new FileReader(file.getPath());
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(" ");
                for (String w : parts) {
                    w = w.trim();
                    words.add(w);
                }
                line = br.readLine();
            }

            fr.close();

            //================Score Calculation====================================
            ResultSet rs = st.executeQuery("select * from word_dictionary");

            while (rs.next()) {
                for (String w : words) {
                    if (rs.getString(2).trim().equalsIgnoreCase(w)) {
                        temp = rs.getInt(4);
                        if (temp > 0) {
                            positive_words_used++;
                            good_score = good_score + temp;
                        } else {
                            neg_words_used++;
                            slang_score = slang_score + temp;
                        }
                    }
                }
                //rs.deleteRow();
            }
            
            rs.close();

            overall_score = good_score + slang_score;
            
            
            

            //=================Insert Into Score Table===============================
            query = "insert into score values(" + call_id + ", 22, " + agent_id + ", " + good_score + ", " + slang_score + ", "
                    + "10, " + overall_score + ", " + positive_words_used + ", " + neg_words_used + ")";
            st.execute(query);
            st.execute("commit");

            //=================Move File===============================================
            //file.renameTo(new File("D:\\ProjectCallRecords\\Analyzed\\" + file_name));
            String newPath = "D:\\ProjectCallRecords\\Analyzed\\" + file_name;
            Files.move(Paths.get(file.getPath()), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
            //child.renameTo(new File(destinationFolder + "\\" + child.getName()));

        } catch (SQLException ex) {
            Logger.getLogger(CalculateScore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CalculateScore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalculateScore.class.getName()).log(Level.SEVERE, null, ex);
            /* br.reafline() */
        }

    }

}
