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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author jupiter
 */
public class JavaExecelProcess {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        System.out.println("Hello excel process.");
//    }
    private String str1 = null;
    public String processExcel(){
//        System.out.println("Hello excel process.");
        str1 = "Hello excel process second time.";
        String strFileInPath = "/home/jupiter/ubgit/webroot/excel2sql/upload/杰科电子通讯录.xls";
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
        String strFileOutPath = "/home/jupiter/ubgit/webroot/excel2sql/upload/result-杰科电子通讯录.xls";
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
