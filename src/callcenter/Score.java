package callcenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Score {

    public void calculate(String name) throws FileNotFoundException, IOException {
        int positive = 0, temp = 0;
        int negative = 0;
        int score = 0;
        int c = 0;

        ArrayList<String> words = new ArrayList<>();
        String[] found = new String[100];
        int[] count = new int[100];
        int[] points = new int[100];

        FileReader fr = new FileReader(name);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            String[] parts = line.split(" ");
            for (String w : parts) {
                //w = w.replace(System.getProperty("line.separator"), "");
                w = w.trim();
                temp++;
                words.add(w);
                //System.out.println(w);

            }
            line = br.readLine();
        }
        System.out.println("Total Words: " + temp);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "root123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from word_dictionary");

            int foundWords = 0;

            while (rs.next()) {
                for (String w : words) {
                    if (rs.getString(2).trim().equalsIgnoreCase(w)) {
                        foundWords++;
                        temp = rs.getInt(4);
                        if (temp > 0) {
                            positive++;
                        } else {
                            negative++;
                        }
                        c = 0;
                        int flag = 0;
                        for (String f : found) {
                            if (w.equals(f)) {
                                count[c]++;
                                points[c] = temp;
                                foundWords--;
                                flag = 1;
                                break;
                            }
                            c++;
                        }
                        if (flag == 0) {
                            found[foundWords] = w;
                            count[foundWords]++;
                            points[foundWords] = temp;
                        }
                        score = score + temp;
                    }
                }
            }
            System.out.println("Positive words used: " + positive);
            System.out.println("Negative words used: " + negative);
            System.out.println("Total Score: " + score);
            //System.out.println("===========================================================\n");
            System.out.format("%1s%20s%10s", "Word", "Occurence", "Score");
            System.out.println("\n");
            for (int k = 1; k <= foundWords; k++) {
                System.out.format("%1s%10s%10s", found[k], count[k], points[k]);
                System.out.println();
            }

            con.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
        Score score = new Score();
        File folder = new File("D:\\ProjectCallRecords\\Unanalyzed");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println("\n");
                score.calculate(file.getPath());
                System.out.println("\n\n===================================================================");
            }
        }

    }
}
