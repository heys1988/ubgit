/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexecelprocess;

import java.sql.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

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
//        inst.str1 = inst.readExcel("新建 Microsoft Office Excel 工作表.xls");
//        inst.isDBFormat();
        inst.initMySQLConn();
        if(null != inst.mysqlConn){
            inst.str1 = inst.readExcel("邮箱地址.xls");
            boolean bDB = inst.isDBFormat();
            if(true == bDB){
                inst.setFields("mytbl1");
                inst.setFields("myf1");
                inst.setFields("myf2");
                inst.dropTableIfExists();
                inst.createDB();
                inst.insertDataFromExcel();
            }            
        }
        inst.closeMySQLConn();
        System.out.println(inst.str1);
    }
    private String str1 = null;
    
//    private String fileInPath = "~/ubgit/webroot/excel2sql/upload/新建 Microsoft Office Excel 工作表.xls";
//    private String fileInPath = "/home/jupiter/ubgit/webroot/excel2sql/upload/新建 Microsoft Office Excel 工作表.xls";
    private String fileInPath = "/home/jupiter/ubgit/webroot/excel2sql/upload/";
    private HSSFWorkbook wb = null;
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost/heys?characterEncoding=utf8" ;
//    private static final String MYSQL_URL = "jdbc:mysql://localhost/heys" ;
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWD = "root123";
    private Connection mysqlConn = null;
    
    private String tbname = "tblname";
    private List<String> fields = null;
    private long[] fieldsLen = null;
    
    public long[] getFieldsLen(){
        
//        Integer dat = new Integer(7);
        int iCols = getColumns();
        long[] fieldsLen = new long[iCols];
        for(int i = 0; i < iCols; i++){
            fieldsLen[i] = -1;
        }
        HSSFSheet activeSheet = wb.getSheetAt(0);
        int iFirstRow = activeSheet.getFirstRowNum();
        int iLastRow = activeSheet.getLastRowNum();        
        for(int i = iFirstRow + 1; i <= iLastRow; i++){
            HSSFRow row = activeSheet.getRow(i);
            int iFirstCol = row.getFirstCellNum();
            int iLastCol = row.getLastCellNum();
            for(int j = iFirstCol; j < iLastCol; j++){
                HSSFCell cell = row.getCell(j);
                int cellType = cell.getCellType();
                if(HSSFCell.CELL_TYPE_STRING == cellType){
                    long tmpLen = cell.getStringCellValue().length();
                    if(fieldsLen[j-iFirstCol] < tmpLen){
                        fieldsLen[j-iFirstCol] = tmpLen;
                    }
                }else if(HSSFCell.CELL_TYPE_NUMERIC == cellType){
                    fieldsLen[j-iFirstCol] = -1;
                }else{
                    
                }
            }
        }
        
        return fieldsLen;
    }
    public void setFields(String newField){
        if(null == fields){
            fields = new ArrayList<>();
        }
        fields.add(newField);
    }
    private String prepareCreateSql(){
        String sql = "";
        String templateSql ="CREATE TABLE `heys`.`tb2` ( `id` INT NOT NULL AUTO_INCREMENT ," +
" `f1` VARCHAR COLLATE utf8_general_ci NOT NULL ," +
" `f2` DOUBLE NOT NULL ," +
" `f3` VARCHAR COLLATE utf8_general_ci NOT NULL ) ENGINE = InnoDB;";
        if(null != fields){
            
            sql += "CREATE TABLE `heys`.`"+fields.get(0)+"` ( `id` INT NOT NULL AUTO_INCREMENT ,";
            fieldsLen = getFieldsLen();
            for(int i = 0; i < fieldsLen.length; i++){
                if(fieldsLen[i] > 0){
                    //string
                    sql += " `"+fields.get(i+1)+"` VARCHAR("+fieldsLen[i]+") COLLATE utf8_general_ci NOT NULL ,";
                }else if(-1 == fieldsLen[i]){
                    //double
                    sql += " `"+fields.get(i+1)+"` DOUBLE NOT NULL ,";
                }
            }
            
            sql += " PRIMARY KEY (`id`)) ENGINE = InnoDB;";
        }
        return sql;        
    }
    
    public void createDB(){
        String sql = "CREATE TABLE `heys`.`tb1` ( `id` INT(3) NOT NULL AUTO_INCREMENT ," +
" `field1` VARCHAR(30) COLLATE utf8_general_ci NOT NULL ," +
" `field2` VARCHAR(30) COLLATE utf8_general_ci NOT NULL ," +
" PRIMARY KEY (`id`)) ENGINE = InnoDB;";
        
//        sql = "DROP TABLE IF EXISTS tb1;";
//        sql = "DROP TABLE IF EXISTS ?;";
        
        sql = prepareCreateSql();
        if(sql.equals("")){
            return;
        }
        PreparedStatement pst = null;
        try {
            pst = mysqlConn.prepareStatement(sql);
//            pst.setString(1, "tb1");
            pst.execute();
//            pst.executeQuery();
            System.out.println("create table success.") ;
        } catch (SQLException ex) {
            Logger.getLogger(JavaExecelProcess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("create table exception.") ;
        }
        
    }
    public void closeMySQLConn(){
        if(null != mysqlConn){
            try {
                mysqlConn.close();
                System.out.println("mysql db closed.") ;
            } catch (SQLException ex) {
                Logger.getLogger(JavaExecelProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void initMySQLConn(){
        try{
            Class.forName(MYSQL_DRIVER) ;
            mysqlConn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWD) ;
            System.out.println("success...") ;
        }catch(Exception e){
            System.out.println("failure!!!") ;
        }
    }
    public boolean isDBFormat(){
        boolean ret = true;
        HSSFSheet activeSheet = wb.getSheetAt(0);
        int iFirstRow = activeSheet.getFirstRowNum();
        int iLastRow = activeSheet.getLastRowNum();
        List<Integer> fieldsType = getFieldsType();
        if(null == fieldsType){
            ret = false;
            return ret;
        }
        for(int i = iFirstRow + 1; i <= iLastRow; i++){
            HSSFRow row = activeSheet.getRow(i);
            int iFirstCol = row.getFirstCellNum();
            int iLastCol = row.getLastCellNum();
            for(int j = iFirstCol; j < iLastCol; j++){
                HSSFCell cell = row.getCell(j);
//                String cessStr = cell.toString();
                int cellType = cell.getCellType();
//                if(HSSFCell.CELL_TYPE_BLANK == cellType
//                        || HSSFCell.CELL_TYPE_ERROR == cellType){
                Integer colType = fieldsType.get(j);
                if(colType.intValue() != cellType){
                    ret = false;
                    break;
                }
            }
            
            if(false == ret){
                break;
            }
        }
        
        return ret;
    }
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
        HSSFRow row2 = activeSheet.getRow(1);//second row
        ret = row2.getPhysicalNumberOfCells();
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

    private List<Integer> getFieldsType() {
        List<Integer> fieldsType = null;
        
        HSSFSheet activeSheet = wb.getSheetAt(0);
        int iFirstRow = activeSheet.getFirstRowNum();
        int iLastRow = activeSheet.getLastRowNum();
        HSSFRow row = activeSheet.getRow(iFirstRow + 1);
        int iFirstCol = row.getFirstCellNum();
        int iLastCol = row.getLastCellNum();
        int iCols = row.getPhysicalNumberOfCells();
        if(0 != iCols){
            fieldsType = new ArrayList<>();
        }
        for(int j = iFirstCol; j < iLastCol; j++){
            HSSFCell cell = row.getCell(j);
            int cellType = cell.getCellType();
            fieldsType.add(cellType);
        }
        
        return fieldsType;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insertDataFromExcel() {
        String templateInsert = "INSERT INTO `mytbl1` (`id`, `myf1`, `myf2`) VALUES (NULL, 'h111', 'h222');";
//        String templateInsert = "INSERT INTO `mytbl1` (`id`, `myf1`, `myf2`) VALUES ('2', 'h111', 'h222');";
        String sqlHead = "";
        sqlHead += "INSERT INTO `"+fields.get(0)+"` (`id`,";
        int i = 0;
        for(i = 0; i < fields.size()-2; i++){
            sqlHead += " `"+fields.get(i+1)+"`,";
        }
        sqlHead += " `"+fields.get(i+1)+"`) VALUES (NULL,";
        
        PreparedStatement pst = null;
        HSSFSheet activeSheet = wb.getSheetAt(0);
        int iFirstRow = activeSheet.getFirstRowNum();
        int iLastRow = activeSheet.getLastRowNum();
        for(i = iFirstRow + 1; i <= iLastRow; i++){
            String sql = sqlHead;
            HSSFRow row = activeSheet.getRow(i);
            int iFirstCol = row.getFirstCellNum();
            int iLastCol = row.getLastCellNum();
            int j = 0;
            for(j = iFirstCol; j < iLastCol-1; j++){
                HSSFCell cell = row.getCell(j);
                String cessStr = cell.toString();
                sql += " '"+cessStr+"',";                
            }
            HSSFCell cell = row.getCell(j);
            String cessStr = cell.toString();
            sql += " '"+cessStr+"');";
            try {
                pst = mysqlConn.prepareStatement(sql);
                pst.execute();
            } catch (SQLException ex) {
                Logger.getLogger(JavaExecelProcess.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("insert data exception.") ;
            }
        }
//        sql += "INSERT INTO `mytbl1` (`id`, `myf1`, `myf2`) VALUES (NULL, 'f1111', 'f2222');";
        
       
    }

    private void dropTableIfExists() {
        String sql = "";
        if(null != fields){
            sql += "DROP TABLE IF EXISTS "+fields.get(0)+";";
        }
        if(sql.equals("")){
            return;
        }
        PreparedStatement pst = null;
        try {
            pst = mysqlConn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(JavaExecelProcess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("drop table exception.") ;
        }
     }
    
}
