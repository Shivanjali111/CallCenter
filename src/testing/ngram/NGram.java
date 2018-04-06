package testing.ngram;

import java.util.*;

public class NGram {
    
public float getDistance(String source, String target) {
    final int sl = source.length();
    final int tl = target.length();
    
    if (sl == 0 || tl == 0) {
      if (sl == tl) {
        return 1;
      }
      else {
        return 0;
      }
    }

    int cost = 0;
    if (sl > 1 || tl > 1) {
      for (int i=0,ni=Math.min(sl,tl);i<ni;i++) {
        if (source.charAt(i) == target.charAt(i)) {
          cost++;
        }
      }
      return (float) cost/Math.max(sl, tl);
    }
    else
        return 10;
}

    public static void main(String[] args) {

        NGram ng=new NGram();
        String s = "Hell how are you how may i help you";
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
        
        System.out.println(Arrays.toString(result));
        for(int i=0;i<result.length;i++){
             System.out.println(result[i]+"  "+ng.getDistance(result[i], "Hell how are you"));
        }
       
    }
}
