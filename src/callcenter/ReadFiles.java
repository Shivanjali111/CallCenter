package callcenter;

import java.io.File;

public class ReadFiles {
    
    ReadFiles(){
        final File folder = new File("D:\\SQL");
        listFilesForFolder(folder);
    }
    
    
    public void listFilesForFolder(final File folder) {
        //File folder = new File("/home/you/Desktop");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }
    
     public static void main(String args[]) {
         //ReadFiles r=new ReadFiles();
         File folder = new File("D:\\SQL");
File[] listOfFiles = folder.listFiles();

for (File file : listOfFiles) {
    if (file.isFile()) {
        System.out.println(file.getAbsolutePath());
    }
}
     }


}
