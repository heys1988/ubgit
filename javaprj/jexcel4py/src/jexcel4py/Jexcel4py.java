/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jexcel4py;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import py4j.GatewayServer;

/**
 *
 * @author jupiter
 */
public class Jexcel4py {

    private static String cur_path = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Jexcel4py app = new Jexcel4py();
        // app is now the gateway.entry_point
        GatewayServer server = new GatewayServer(app);
        server.start();
        cur_path = System.getProperty("user.dir");
//        System.out.println(System.getProperty("user.dir"));
        
        String strInExcelName = "/home/jupiter/ubgit/pyj/pyj_py4j/upload/osd.xls";
//        app.deliveryExcel(strInExcelName);
//        app.processEnd();
//        app.readExcel(strInExcelName);
//        System.out.println(strExcelPath);
//        System.out.println(strExcelName);
        System.out.println(strOutExcelName);
        //addition(5, 6);
    }
    private static String strOutExcelName = null;
    private static String strExcelName = null;
    private static String strExcelPath = null;
    private Workbook wbRead = null;
    private Workbook wbWrite = null;
    
    public int getRows(){
        Sheet rdSheet = wbRead.getSheetAt(0);
        int rdRows = rdSheet.getLastRowNum();// 获取总行数
        return rdRows;
    }
    
    public int getCols(){
        Sheet rdSheet = wbRead.getSheetAt(0);
        Row row = rdSheet.getRow(0);
        int rdCols = row.getLastCellNum();
        return rdCols;
    }
    
    public void processEnd() throws IOException{
        File fileOut = new File(strOutExcelName);
        if(fileOut.exists()){
            fileOut.delete();
        }
        FileOutputStream fosFileOut = new FileOutputStream(strOutExcelName);
        wbWrite.write(fosFileOut);
        fosFileOut.close();
        wbRead.close();
        wbWrite.close();
    }
    
    public void setExcelCellValue(int r, int c, String val){
        Sheet wrSheet = wbWrite.getSheetAt(0);
        Row wrRow = wrSheet.getRow(r);
        Cell wrCell = wrRow.createCell(c);
//        Cell wrCell = wrRow.getCell(c);
        wrCell.setCellValue(val);
    }
    
    public String getExcelCellValue(int r, int c){
        Sheet rdSheet = wbRead.getSheetAt(0);
        Row row = rdSheet.getRow(r);
        Cell rdCell = row.getCell(c);
        String cellValue = "";
        if(null == rdCell){
            cellValue = " ";
        }
        else{
            cellValue = rdCell.getStringCellValue();
        }
        
        return cellValue;
    }
    
    public void deliveryExcel(String strInExcelName) throws IOException {        
        wbRead = readExcel(strInExcelName);        
        wbWrite = new HSSFWorkbook();
        Sheet wrSheet = wbWrite.createSheet();
        Sheet rdSheet = wbRead.getSheetAt(0);
        int rdRows = rdSheet.getLastRowNum();// 获取总行数
        for (int i = 0; i <= rdRows; i++) {
            Row wrRow = wrSheet.createRow(i);
            Row row = rdSheet.getRow(i);
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                Cell rdCell = row.getCell(j);
//                System.out.print(rdCell + "\t");
                
                Cell wrCell = wrRow.createCell(j);
                copyCell(rdCell, wrCell);
                
            }
//            System.out.println();
        }
        
//        File fileOut = new File(strOutExcelName);
//        if(fileOut.exists()){
//            fileOut.delete();
//        }
//        FileOutputStream fosFileOut = new FileOutputStream(strOutExcelName);
//        wbWrite.write(fosFileOut);
//        fosFileOut.close();
//        wbRead.close();
//        wbWrite.close();
    }

    public Workbook readExcel(String strInExcelName) throws IOException {
        File fileInExcel = new File(strInExcelName);
        strExcelPath = fileInExcel.getParent();
        strExcelName = fileInExcel.getName();
        strOutExcelName = strExcelName.replace(".xls", "-result.xls");
        strOutExcelName = strExcelPath + File.separator +strOutExcelName;
        FileInputStream fisInFile = new FileInputStream(fileInExcel);
        Workbook wb = new HSSFWorkbook(fisInFile);
        fisInFile.close();
        return wb;
    }

    public int addition(int i, int i0) {        
        return i+i0;
    }

    private double getRdCellValue(Cell rdCell) {
        return 0.0;
    }

    private void copyCell(Cell rdCell, Cell wrCell) {
//        wrCell.setCellStyle(rdCell.getCellStyle());
//        wrCell.setCellType(rdCell.getCellType());
        int cellStyle = rdCell.getCellType();
        String rdCellValue = rdCell.getStringCellValue();
        
        switch(cellStyle){
            case Cell.CELL_TYPE_BLANK:
                wrCell.setCellValue("heys");
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                wrCell.setCellValue(rdCell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                wrCell.setCellValue(rdCell.getErrorCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                wrCell.setCellValue(rdCell.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                wrCell.setCellValue(rdCell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                wrCell.setCellValue(rdCell.getStringCellValue());
                break;
                
            default:
                wrCell.setCellValue("heys");
                break;
        }
    }
    
}
