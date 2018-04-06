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
import javax.swing.JOptionPane;

public class CalculateScore {
    
    int positive_score=0;
        int negative_score=0;
        int overall_score=0;
        int positive_words_used=0;
        int neg_words_used=0;

    File folder = new File("D:\\ProjectCallRecords\\Unanalyzed");
    String call_id;
            int temp;
    String employee_id;
    //int positive_words_used, good_score, neg_words_used, slang_score, overall_score;
    String file_name, query;
    Connection connection;
    String call_date;

    public CalculateScore(Connection connection) {
        this.connection = connection;
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles.length != 0) {
            for (File file : listOfFiles) {
                analyze(file);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No files to analyze", "", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void analyze(File file) {

         positive_score=0;
         negative_score=0;
         overall_score=0;
         positive_words_used=0;
         neg_words_used=0;
        ArrayList<String> words = new ArrayList<>();

        file_name = file.getName();
        call_id = file_name.substring(0, 6);
        employee_id = file_name.substring(7, 11);
        call_date = file_name.substring(12,22);

        try {
            

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
            
            String[] fileContents = createNGrams(new String(Files.readAllBytes(Paths.get(file.getPath()))));
            fr.close();

            //================Score Calculation====================================
            Statement st=connection.createStatement();
            ResultSet rs = st.executeQuery("select * from word_dictionary");

            while (rs.next()) {
                for (String w : words) {
                    if (rs.getString(2).trim().equalsIgnoreCase(w)) {
                        temp = rs.getInt(4);
                        if (temp > 0) {
                            positive_words_used++;
                            positive_score = positive_score + temp;
                        } else {
                            neg_words_used++;
                            negative_score = negative_score + temp;
                        }
                    }
                }
                //rs.deleteRow();
            }
            rs.close();
            
            NGramSimilarity ngram=new NGramSimilarity(2);
            ResultSet rs1 = st.executeQuery("select * from phrases");

            while (rs1.next()) {
                for (String p : fileContents) {
                    if (ngram.ifNgramSimilar(p, rs1.getString(2))) {
                        temp = rs1.getInt(4);
                        if (temp > 0) {
                            positive_words_used++;
                            positive_score = positive_score + temp;
                        } else {
                            neg_words_used++;
                            negative_score = negative_score + temp;
                        }
                    }
                }
                //rs.deleteRow();
            }
            rs1.close();


            overall_score = positive_score + negative_score;
            
            
            insertRecords();

            //=================Move File===============================================
            //file.renameTo(new File("D:\\ProjectCallRecords\\Analyzed\\" + file_name));
            String newPath = "D:\\ProjectCallRecords\\Analyzed\\" + file_name;
            Files.move(Paths.get(file.getPath()), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
            
            

        } catch (SQLException ex) {
            Logger.getLogger(CalculateScore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CalculateScore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalculateScore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String[] createNGrams(String s){
        String[] parts = s.split(" ");
        String[] result = new String[parts.length - 4 + 1];
        for (int i = 0; i < parts.length - 4 + 1; i++) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < 4; k++) {
                if (k > 0) {
                    sb.append(' ');
                }
                sb.append(parts[i + k]);
            }
            result[i] = sb.toString();
        }
        return result;
    }
    
    public void insertRecords(){
        try {
            
            Statement st=connection.createStatement();
            //==================Update Call Records===============================
            query = "insert into call_records values('" + call_id + "', '" + employee_id + "', 2, '"+call_date+"')";
            st.execute(query);
            st.execute("commit");
            
            //==================Insert into Score===============================
            query = "insert into score values('"+call_id+"', '"+employee_id+"', "+positive_words_used+", "+positive_score+", "+
                    negative_score+", "+neg_words_used+", 10,"+overall_score+", '"+call_date+"')";
            st.execute(query);
            st.execute("commit");

            
        } catch (SQLException ex) {
            Logger.getLogger(CalculateScore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
