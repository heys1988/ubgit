<?php
session_start();
header("Content-type: text/html; charset=utf-8");
require_once("http://localhost:8080/javabridge/java/Java.inc");
//require_once("../javabridge/META-INF/java/Java.inc");
//require_once("http://localhost:8080/JavaBridge/java/Java.inc");
//java_require("JavaExecelProcess.jar");
echo '<body style="background-color: #cce8cf">';
echo '</body>';
echo '<center>';
$excel_prcess = new java("javaexecelprocess.JavaExecelProcess");
//$ret = $excel_prcess->readExcel(getcwd()."/upload/"."邮箱地址.xls");
//if(isset($_SESSION['excelName'])){
     $filename = $_SESSION['excelName'];
//}
//$ret = $excel_prcess->readExcel($_COOKIE["excelName"]);
//$filename = getcwd()."/upload/".$_FILES["xlsfile"]["name"];
$ret = $excel_prcess->readExcel($filename);
//echo $ret.'<br/>';
//echo $_POST['tblname'].'<br/>';
//for($i = 1; $i <= $_COOKIE["excelCols"]; $i++){
//    echo $_POST["field$i"].'<br/>';
//}
echo 'Creating table...'.'<br/>';
$excel_prcess->initMySQLConn();
//$is_db = $excel_prcess->isDBFormat();
//if(true == $is_db){
    $excel_prcess->dropTableIfExists();
    $excel_prcess->setFields($_POST['tblname']);
    for($i = 1; $i <= $_COOKIE["excelCols"]; $i++){
        $excel_prcess->setFields($_POST["field$i"]);
    }
    $excel_prcess->createDB();
    $excel_prcess->insertDataFromExcel();
//}
$excel_prcess->closeMySQLConn();
echo 'Done.'.'<br/>';

echo '</center>';
?> 