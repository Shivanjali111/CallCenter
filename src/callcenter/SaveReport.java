package callcenter;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class SaveReport {
    

    public void exportTable(JTable table, String path) throws IOException {
//        new WorkbookFactory();
//    Workbook wb = new XSSFWorkbook(); //Excell workbook
//        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet(); //WorkSheet
//    Row row = sheet.createRow(2); //Row created at line 3
//    TableModel model = table.getModel(); //Table model
//
//
//    Row headerRow = sheet.createRow(0); //Create row at line 0
//    for(int headings = 0; headings < model.getColumnCount(); headings++){ //For each column
//        headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
//    }
//
//    for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
//        for(int cols = 0; cols < table.getColumnCount(); cols++){ //For each table column
//            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
//        }
//
//        //Set the row to the next one in the sequence 
//        row = sheet.createRow((rows + 3)); 
//    }
//    wb.write(new FileOutputStream(path.toString()));//Save the file

        try{
        Workbook wb = new HSSFWorkbook();
        CreationHelper createhelper = wb.getCreationHelper();
        //Sheet sheet = (Sheet) wb.createSheet("new sheet");
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet();
        Row row;
        Cell cell;
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();

        row = sheet.createRow(0);
        for (int i = 0; i < dtm.getColumnCount(); i++) {
            
            cell = row.createCell(i);
            cell.setCellValue(dtm.getColumnName(i));
        }

        for (int i = 0; i < dtm.getRowCount(); i++) {
            row = sheet.createRow(i+1);
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                cell = row.createCell(j);
                //cell.setCellValue((String) dtm.getValueAt(i, j));
                cell.setCellValue(dtm.getValueAt(i, j).toString());
            }
        }

        FileOutputStream out = new FileOutputStream(path);
        wb.write(out);
        out.close();
        }catch(Exception e){
            
        }
    }
}
