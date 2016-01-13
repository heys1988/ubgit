/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexecelprocess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 *
 * @author jupiter
 */
public class JavaExecelProcess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello excel process.");
        JavaExecelProcess inst = new JavaExecelProcess();
        inst.str1 = inst.readExcel("新建 Microsoft Office Excel 工作表.xls");
        System.out.println(inst.str1);
    }
    private String str1 = null;
    
//    private String fileInPath = "~/ubgit/webroot/excel2sql/upload/新建 Microsoft Office Excel 工作表.xls";
//    private String fileInPath = "/home/jupiter/ubgit/webroot/excel2sql/upload/新建 Microsoft Office Excel 工作表.xls";
    private String fileInPath = "/home/jupiter/ubgit/webroot/excel2sql/upload/";
    private HSSFWorkbook wb = null;
    public String readExcel(String fileName){
//        System.out.println("Hello excel process.");
//        try{
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//        }catch(UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
        
        str1 = "Read "+fileName+" finished!";
        fileInPath += fileName;
        FileInputStream fisInFile = null;
        
        try {
            fisInFile = new FileInputStream(fileInPath);
            wb = new HSSFWorkbook(fisInFile);
            fisInFile.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        str1 = str1 + " " + getSheets() + " "+ getRows() + " " + getColumns();
        return str1;
    }
    
    public int getSheets(){
        int ret = 0;
        ret = wb.getNumberOfSheets();
        return ret;
    }
    public int getRows(){
        int ret = 0;
        HSSFSheet activeSheet = wb.getSheetAt(0);
        ret = activeSheet.getPhysicalNumberOfRows();
//        ret = activeSheet.getLastRowNum() - activeSheet.getFirstRowNum() + 1;
//        ret = activeSheet.getNumMergedRegions();
//        CellRangeAddress cra = activeSheet.getMergedRegion(0);
//        ret = cra.getLastRow() - cra.getFirstRow() + 1;
        
        return ret;
    }
    public int getColumns(){
        int ret = 0;
        HSSFSheet activeSheet = wb.getSheetAt(0);
        HSSFRow row1 = activeSheet.getRow(0);
        ret = row1.getPhysicalNumberOfCells();
//        CellRangeAddress cra = activeSheet.getMergedRegion(0);
//        ret = cra.getLastColumn() - cra.getFirstColumn() + 1;
        
        return ret;
    }
    public String processExcel(String fileName){
//        System.out.println("Hello excel process.");
        str1 = "Hello excel process second time.";
        String strFileInPath = "webroot/excel2sql/upload/";
        strFileInPath += fileName;
        FileInputStream fisInFile = null;
        HSSFWorkbook wb = null;	
        try {
            fisInFile = new FileInputStream(strFileInPath);
            wb = new HSSFWorkbook(fisInFile);
            fisInFile.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
			
        FileOutputStream fosFileOut = null;
        String strFileOutPath = "webroot/excel2sql/upload/";
        strFileOutPath += "result-"+fileName;
        File fileOut = new File(strFileOutPath);
        if(fileOut.exists()){
            fileOut.delete();
        }
        try {
            fosFileOut = new FileOutputStream(strFileOutPath);
            wb.write(fosFileOut);
            fosFileOut.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
        return str1;
    }
    
}
